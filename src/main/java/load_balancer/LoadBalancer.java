package load_balancer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import systems.Request;
import systems.server.Server;
import utils.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
public class LoadBalancer implements Server {

    private final String SERVER_NAME = "SERVER-" + Random.getRandom().nextInt(100);

    private final String IP = Random.generateRandomIP();

    private final List<Server> NODES = new ArrayList<>();

    public LoadBalancer(List<Server> nodes, LoadBalancingStrategy<Server> loadBalancingStrategy){
        this(loadBalancingStrategy);
        registerAll(nodes);
    }

    public void start(){
        // check heartbeat
        loadBalancingStrategy.processNodes(NODES);
    }

    public void register(Server server){
        NODES.add(server);
    }

    public void registerAll(List<Server> servers){
        NODES.addAll(servers);
    }

    private final LoadBalancingStrategy<Server> loadBalancingStrategy;

    @Override
    public Map<String, Object> request(Request request) {
        Server selectedServer = loadBalancingStrategy.chooseServer(NODES, request);
        CompletableFuture.runAsync(() -> log.info("Request {} mapped to server {}", request, selectedServer.getServerName()));
        return selectedServer.request(request);
    }

    @Override
    public String getIP() {
        return IP;
    }

    @Override
    public String getServerName() {
        return SERVER_NAME;
    }
}
