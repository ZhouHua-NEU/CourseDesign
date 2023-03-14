package MyInterceptors;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class TimeInterceptor implements ProducerInterceptor<String, String> {
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return new ProducerRecord<String, String>(
                producerRecord.topic(),
                producerRecord.key(),
                System.currentTimeMillis() + ", " + producerRecord.value()
        );
    }
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
    }
    @Override
    public void close() {
        System.out.println("MyInterceptors.TimeInterceptor closed");
    }
    @Override
    public void configure(Map<String, ?> map) {
        System.out.println("kafka Server deploy at:" + map.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
    }
}
