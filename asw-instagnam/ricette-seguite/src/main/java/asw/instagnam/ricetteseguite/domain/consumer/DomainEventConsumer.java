package asw.instagnam.ricetteseguite.domain.consumer;

import asw.instagnam.common.api.event.DomainEvent;

public interface DomainEventConsumer {
	
	public void onEvent(DomainEvent event); 

}