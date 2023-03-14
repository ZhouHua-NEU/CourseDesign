import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {
    /**
     * 消费者主函数
     */
    public static void main(String[] args) {
        // 1.使用Properties定义消费者属性
        // 需要定义的属性有
        // a) kafka的broker连接地址
        // b) 与生产者类相反， 由于消费者从网络通信获取消息， 因此需要定义反序列化类，
        //   并且需要与Producer定义的序列话类对应上
        // c) 反序列化类包含Key的反序列化和Value的反序列化
        // d) 设置消费者组的id
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "cluster-master:9092,cluster-slave1:9092,cluster-slave2:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        // 2. 创建消费者对象
        KafkaConsumer<String, String> myConsumer = new KafkaConsumer<>(properties);
        // 3. 设置消费者消费的主题，主题可以包含多个
        myConsumer.subscribe(Arrays.asList("topictest"));
        // 4. 消费消息
        while(true){
            //通过Consumer对象的poll方法拉取对象
            ConsumerRecords<String, String> consumerRecords = myConsumer.poll(Duration.ofSeconds(10));
            for (ConsumerRecord<String, String> record : consumerRecords) {
                // 打印消息
                System.out.println(
                        "key: "+ record.key() + " value: "+ record.value()
                                + " partition: " + record.partition() + " offset: " + record.offset()
                                + " header: " + record.headers()
                );
            }
        }

    }
}
