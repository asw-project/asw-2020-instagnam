package asw.instagnam.ricetteseguite.domain.consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import asw.instagnam.ricetteseguite.domain.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import asw.instagnam.common.api.event.DomainEvent;
import asw.instagnam.ricetteseguite.domain.model.Connessione;
import asw.instagnam.ricetteseguite.domain.model.Ricetta;
import asw.instagnam.ricetteseguite.domain.repository.RicetteRepository;
import asw.instagnam.ricetteseguite.domain.repository.RicetteSeguiteRepository;
import ricette.service.api.event.RicettaCreatedEvent;
import asw.instagnam.ricetteseguite.domain.model.RicettaSeguita;

@Service
public class RicetteDomainEventConsumer implements DomainEventConsumer {

	@Autowired
	private RicetteRepository ricetteRepository; 
	@Autowired
	private RicetteSeguiteRepository ricetteSeguiteRepository;
	@Autowired
	private ConnessioniRepository connessioniRepository; 
	

	@Override
	public void onEvent(DomainEvent event) {
		if(event instanceof RicettaCreatedEvent) {
			this.handleRicettaCreatedEvent(event);
		}
		else {
			System.out.println("Evento non riconosciuto\n"); 
		}
		
	}
	
	
	private void handleRicettaCreatedEvent(DomainEvent event) {
		RicettaCreatedEvent r = (RicettaCreatedEvent) event;
		Ricetta ricetta = new Ricetta(r.getId(), r.getAutore(), r.getTitolo());
		final Ricetta ricettaNuova = ricetteRepository.save(ricetta);
		
		//  Find all follower for author(autore)
		Collection<Connessione> connessioni = connessioniRepository.findAllByFollowed(ricetta.getAutore());
		List<RicettaSeguita> ricetteSeguite = new ArrayList<>();
		
		
		//  Create ricetteSeguiteList with follower for author about a new ricetta
		connessioni.stream().forEach
		(connessione -> ricetteSeguite.add(new RicettaSeguita(connessione.getFollower(),
		ricettaNuova.getId(), ricettaNuova.getAutore(), ricettaNuova.getTitolo())));
		
		// Save RicetteSeguite on repository 
		ricetteSeguite.stream().forEach(ricettaSeguita -> ricetteSeguiteRepository.save(ricettaSeguita));
	}

	
}
