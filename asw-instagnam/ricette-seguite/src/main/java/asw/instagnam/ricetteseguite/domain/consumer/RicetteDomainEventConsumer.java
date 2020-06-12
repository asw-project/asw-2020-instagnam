package asw.instagnam.ricetteseguite.domain.consumer;

import java.util.Collection;


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
		ricetteRepository.save(ricetta);
		
		Collection<Connessione> connessioni = connessioniRepository.findAllByFollowed(ricetta.getAutore());
		for (Connessione c: connessioni) {
			     this.ricetteSeguiteRepository.save(new RicettaSeguita(c.getFollower(),ricetta.getId(),ricetta.getAutore(),ricetta.getTitolo()));
	
		}
		
	}

	
}
