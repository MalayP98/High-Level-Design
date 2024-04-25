package main;

//import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import load_balancer.LoadBalancer;
import load_balancer.stretagy.consisten_hashing.ConsistentHashing;
import lombok.extern.slf4j.Slf4j;

import systems.server.Server;
import systems.server.ServerService;
import systems.server.SimpleServer;
import utils.Constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ConsistentHashingTest {

    private static void logServerDetails(List<Server> servers){
        String initMessage = Constants.LOG_STARS + " Registered Servers " + Constants.LOG_STARS;
        log.info(initMessage);
        servers.forEach(server -> log.info(server.toString()));
        log.info(String.join("", Collections.nCopies(initMessage.length(), "*")));
    }
    public static void main(String[] args) {

//        Producer<String, String> producer = KafkaUtil.getProducer();
//        for(int i=0; i<10; i++){
//            producer.send(new ProducerRecord<>("first_topic", "hi" + i));
//        }
//        KafkaUtil.getConsumer().poll(Duration.ofMillis(3000)).forEach(a -> System.out.println("\n\n ----> " + a.value() + "\n\n"));


        List<Server> servers = Arrays.asList(new SimpleServer(), new SimpleServer(), new SimpleServer());
        logServerDetails(servers);

        ConsistentHashing<Server> consistentHashing = new ConsistentHashing<>(3);
        LoadBalancer loadBalancerServer = new LoadBalancer(consistentHashing);
        loadBalancerServer.registerAll(servers);
        ServerService.bombardServers(loadBalancerServer);

    }
}
