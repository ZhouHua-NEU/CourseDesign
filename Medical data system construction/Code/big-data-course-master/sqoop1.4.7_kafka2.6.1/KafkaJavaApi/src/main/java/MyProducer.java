import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MyProducer {
    /**
     * 生产者类的主函数
     * */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1.使用Properties定义生产者属性
        // 需要设置的有：
        // a) Kafka的broker地址, 202.206.19.34:9092,202.206.19.35:9092,202.206.19.48:9092
        // b) 由于消息通过网络传输到集群， 因此需要设置序列化类
        // c) 序列化类包含Key程序类和Value程序类
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        "cluster-master:9092,cluster-slave1:9092,cluster-slave2:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());

        //2.设置拦截器
        ArrayList<String> interceptors = new ArrayList<>();
        interceptors.add("MyInterceptors.TimeInterceptor");
        interceptors.add("MyInterceptors.CountInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        // 2. 配置一个producer实例
        KafkaProducer<String, String> myProducer = new KafkaProducer<>(properties);
        // 3. 发送消息到指定的主题中
        int i=1;
        while(i <= 50){
            // 使用ProducerRecord创建一条消息
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("topictest",
                    "" + i,
                    "this is a test message" + " id=" + i);
            // 调用producer实例的send函数发送消息
            myProducer.send(producerRecord);
            System.out.println(producerRecord.toString());
            Thread.sleep(100);
            i += 1;
        }
        myProducer.close();
    }
}
