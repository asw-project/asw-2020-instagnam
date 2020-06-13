package asw.instagnam.ricette.domain;

import asw.instagnam.common.api.event.DomainEvent;

public interface RicetteDomainEventPublisher {
	public void publish(DomainEvent event);
}