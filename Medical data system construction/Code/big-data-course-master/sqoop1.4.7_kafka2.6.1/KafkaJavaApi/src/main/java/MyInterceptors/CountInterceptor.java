package MyInterceptors;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class CountInterceptor implements ProducerInterceptor {
    public int successRecords = 0;
    public int failureRecoreds = 0;
    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        return producerRecord;
    }
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null){
            successRecords += 1;
        } else {
            failureRecoreds += 1;
        }
    }
    @Override
    public void close() {
        System.out.println("success messages： " + successRecords);
        System.out.println("failed messages： " + failureRecoreds);
        System.out.println("MyInterceptors.CountInterceptor closed");
    }
    @Override
    public void configure(Map<String, ?> map) {

    }
}

