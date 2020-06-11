package asw.instagnam.ricetteseguite.domain.consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		List<RicettaSeguita> ricetteSeguite = new ArrayList<>();
		ricette.stream().forEach(ricetta -> ricetteSeguite.add(new RicettaSeguita(connessione.getFollower(),
				ricetta.getId(), ricetta.getAutore(), ricetta.getTitolo())));

        // Save new connessione in all repositories
		connessioniRepository.save(connessione);
		ricetteSeguite.stream().forEach(ricettaSeguita -> ricetteSeguiteRepository.save(ricettaSeguita));


	}

}