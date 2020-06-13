package asw.instagnam.ricetteseguite.domain.consumer;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.instagnam.common.api.event.DomainEvent;
import asw.instagnam.ricetteseguite.domain.model.Ricetta;
import asw.instagnam.ricetteseguite.domain.model.Connessione;
import asw.instagnam.ricetteseguite.domain.model.RicettaSeguita;
import asw.instagnam.ricetteseguite.domain.repository.ConnessioniRepository;
import asw.instagnam.ricetteseguite.domain.repository.RicetteRepository;
import asw.instagnam.ricetteseguite.domain.repository.RicetteSeguiteRepository;
import connessioni.service.api.event.ConnessioneCreatedEvent;

@Service
public class ConnessioniDomainEventConsumer implements DomainEventConsumer {

	@Autowired
	private ConnessioniRepository connessioniRepository;
	@Autowired
	private RicetteSeguiteRepository ricetteSeguiteRepository;
	@Autowired
	private RicetteRepository ricetteRepository;

	@Override
	public void onEvent(DomainEvent event) {
		if (event instanceof ConnessioneCreatedEvent) {
			this.handleConnessioneCreatedEvent(event);
		} else {
			System.out.println("Evento non riconosciuto\n");
		}
	}

	private void handleConnessioneCreatedEvent(DomainEvent event) {
		ConnessioneCreatedEvent c = (ConnessioneCreatedEvent) event;
		// Create new connection from the event
		Connessione connessione = new Connessione(c.getId(), c.getFollower(), c.getFollowed());
		// Finding all followed user's ricette
		String autore = connessione.getFollowed();
		Collection<Ricetta> ricette = ricetteRepository.findAllByAutore(autore);
		// Save new ricette seguite
		for(Ricetta r: ricette) {
			this.ricetteSeguiteRepository.save(new RicettaSeguita(connessione.getFollower(),
					r.getId(), r.getAutore(), r.getTitolo()));
		}
		// Save new connessione in repository
		connessioniRepository.save(connessione);
	}
}