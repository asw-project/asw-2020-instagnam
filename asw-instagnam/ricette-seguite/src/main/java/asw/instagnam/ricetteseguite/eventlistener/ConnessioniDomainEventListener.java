package asw.instagnam.ricetteseguite.eventlistener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import asw.instagnam.common.api.event.DomainEvent;
import connessioni.service.api.event.ConnessioniServiceEventChannel;
import asw.instagnam.ricetteseguite.domain.consumer.ConnessioniDomainEventConsumer;


@Component
public class ConnessioniDomainEventListener implements DomainEventListener{

	@Autowired
	private ConnessioniDomainEventConsumer connessioniDomainEventConsumer;

	@KafkaListener(topics = ConnessioniServiceEventChannel.channel)
	public void listen(ConsumerRecord<String, DomainEvent> record) {
		DomainEvent event = record.value();
		connessioniDomainEventConsumer.onEvent(event);
	}

}