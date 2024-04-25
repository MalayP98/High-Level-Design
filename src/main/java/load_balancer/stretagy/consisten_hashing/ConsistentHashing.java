package load_balancer.stretagy.consisten_hashing;

import load_balancer.LoadBalancingStrategy;
import lombok.extern.slf4j.Slf4j;
import systems.Request;
import systems.server.Server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ConsistentHashing<T extends Server> implements LoadBalancingStrategy<T> {

    private final List<T> nodes = new ArrayList<>();

    private final Integer HASH_VALUE_LIMIT = 100;
    private final Integer nodeReplications;

    public ConsistentHashing(Integer nodeReplications){
        this.nodeReplications = nodeReplications;
    };

    private int normalizeHash(int hash){
        return Math.abs(hash)%HASH_VALUE_LIMIT;
    }

    @Override
    public void processNodes(List<T> nodes){
        this.nodes.addAll(nodes
                .stream()
                .sorted(Comparator.comparingInt(node -> Hashing.hash(node.getIP())%HASH_VALUE_LIMIT))
                .collect(Collectors.toList()));
    }

    @Override
    public Server chooseServer(List<T> servers, Request request) {
        if(servers.isEmpty()) return null;
        return searchNearestServer(normalizeHash(request.getPayload().hashCode()));
    }

    private Server searchNearestServer(int hash){
        int low = 0, high = nodes.size()-1;
        while(low <= high){
            int mid = low + (high-low)/2;
            int val = normalizeHash(Hashing.hash(nodes.get(mid).getIP()));
            if(val <= hash){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        return nodes.get(low);
    }
}
