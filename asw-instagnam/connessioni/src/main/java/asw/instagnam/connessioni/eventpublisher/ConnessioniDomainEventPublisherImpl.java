package asw.instagnam.connessioni.eventpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import asw.instagnam.common.api.event.DomainEvent;
import asw.instagnam.connessioni.domain.ConnessioniDomainEventPublisher;
import connessioni.service.api.event.ConnessioniServiceEventChannel;

@Component
public class ConnessioniDomainEventPublisherImpl implements ConnessioniDomainEventPublisher {

	@Autowired
	private KafkaTemplate<String, DomainEvent> template;

	private String channel = ConnessioniServiceEventChannel.channel;

	@Override
	public void publish(DomainEvent event) {
		template.send(channel, event);
	}

}