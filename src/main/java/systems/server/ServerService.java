package systems.server;

import systems.Request;
import utils.Constants;
import utils.Random;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerService {

    public static void bombardServers(Server server){
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        new Thread(() -> {
            while(true){
                threadPool.submit(() -> server.request(new Request(null, Constants.OBJECT_MAPPER.writeValueAsString(
                        new HashMap<String, Object>(){
                            {put("request", "Test Request - " + Random.getRandom().nextInt(1000));}
                        }))));
            }
        }).start();
    }
}
