package asw.instagnam.connessioni.domain;
import asw.instagnam.common.api.event.DomainEvent;

public interface ConnessioniDomainEventPublisher {
	public void publish(DomainEvent event); 
}
