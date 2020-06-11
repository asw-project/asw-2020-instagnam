package asw.instagnam.ricetteseguite.domain.consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import asw.instagnam.common.api.event.DomainEvent;
import asw.instagnam.ricetteseguite.domain.model.Ricetta;
import asw.instagnam.ricetteseguite.domain.repository.RicetteRepository;
import ricette.service.api.event.RicettaCreatedEvent;

@Service
public class RicetteDomainEventConsumer implements DomainEventConsumer {

	@Autowired
	private RicetteRepository ricetteRepository; 

	@Override
	public void onEvent(DomainEvent event) {
		if(event instanceof RicettaCreatedEvent) {
			this.handleRicettaCreatedEvent(event);
		}
		else {
			System.out.println("UNKNOWN EVENT\n"); 
		}
		
	}
	
	
	private void handleRicettaCreatedEvent(DomainEvent event) {
		RicettaCreatedEvent r = (RicettaCreatedEvent) event;
		Ricetta ricetta = new Ricetta(r.getId(), r.getAutore(), r.getTitolo());
		final Ricetta ricettaNuova = ricetteRepository.save(ricetta);
	}

	
}
