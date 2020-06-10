package asw.instagnam.ricette.domain;

import org.springframework.stereotype.Service;

import asw.instagnam.common.api.event.DomainEvent;
import ricette.service.api.event.RicettaCreatedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*; 

@Service
public class RicetteService {

	@Autowired
	private RicetteRepository ricetteRepository;
	@Autowired
	private RicetteDomainEventPublisher domainEventPublisher;

 	public RicettaCompleta createRicetta(String autore, String titolo, String preparazione) {
		RicettaCompleta ricetta = new RicettaCompleta(autore, titolo, preparazione); 
		ricetta = ricetteRepository.save(ricetta);
		DomainEvent event = new RicettaCreatedEvent(ricetta.getId(),ricetta.getAutore(),ricetta.getTitolo()); 
		domainEventPublisher.publish(event);
		return ricetta;
	}

 	public RicettaCompleta getRicetta(Long id) {
		RicettaCompleta ricetta = ricetteRepository.findById(id).orElse(null);
		return ricetta;
	}

	public Collection<RicettaCompleta> getRicette() {
		Collection<RicettaCompleta> ricette = ricetteRepository.findAll();
		return ricette;
	}

	public Collection<RicettaCompleta> getRicetteByAutore(String autore) {
		Collection<RicettaCompleta> ricette = ricetteRepository.findAllByAutore(autore);
		return ricette;
	}

}
