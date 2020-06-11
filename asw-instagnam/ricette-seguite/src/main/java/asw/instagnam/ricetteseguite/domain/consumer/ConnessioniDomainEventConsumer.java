package asw.instagnam.ricetteseguite.domain.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.instagnam.common.api.event.DomainEvent;
import asw.instagnam.ricetteseguite.domain.model.Connessione;
import asw.instagnam.ricetteseguite.domain.repository.ConnessioniRepository;
import connessioni.service.api.event.ConnessioneCreatedEvent;


@Service
public class ConnessioniDomainEventConsumer implements DomainEventConsumer {
	
	@Autowired
	private ConnessioniRepository connessioniRepository;
	
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
		
		connessioniRepository.save(connessione);
		

	}
	
}