package load_balancer;

import systems.Request;
import systems.server.Server;

import java.util.List;

public interface LoadBalancingStrategy<T extends Server> {

    /**
     * {@link LoadBalancer} will call this method whenever a node is registered or de-registered.
     * This method will also be called before {@link LoadBalancer} starts taking any request.
     */
    default void processNodes(List<T> nodes){}

    Server chooseServer(List<T> nodes, Request request);
}
