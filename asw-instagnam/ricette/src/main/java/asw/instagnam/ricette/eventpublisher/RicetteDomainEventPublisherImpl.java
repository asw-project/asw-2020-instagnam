package asw.instagnam.ricette.eventpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import asw.instagnam.ricette.domain.RicetteDomainEventPublisher;
import asw.instagnam.common.api.event.DomainEvent;
import ricette.service.api.event.RicetteServiceEventChannel;


@Component
public class RicetteDomainEventPublisherImpl implements RicetteDomainEventPublisher {

	@Autowired
	private KafkaTemplate<String, DomainEvent> template;

	private String channel = RicetteServiceEventChannel.channel;

	@Override
	public void publish(DomainEvent event) {
		template.send(channel, event);
	}

}