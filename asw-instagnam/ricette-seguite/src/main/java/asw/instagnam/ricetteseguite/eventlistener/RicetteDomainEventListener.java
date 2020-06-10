package asw.instagnam.ricetteseguite.eventlistener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import asw.instagnam.common.api.event.DomainEvent;
import asw.instagnam.ricetteseguite.domain.consumer.RicetteDomainEventConsumer;
import ricette.service.api.event.RicetteServiceEventChannel;

@Component
public class RicetteDomainEventListener implements DomainEventListener{

	@Autowired
	private RicetteDomainEventConsumer ricetteDomainEventConsumer;

	@KafkaListener(topics = RicetteServiceEventChannel.channel)
	public void listen(ConsumerRecord<String, DomainEvent> record) {
		DomainEvent event = record.value();
		ricetteDomainEventConsumer.onEvent(event);
	}

}