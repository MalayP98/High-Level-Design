package systems.server;

import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import systems.Request;
import utils.Constants;
import utils.Random;

import java.util.Map;

@ToString
@Slf4j
public class SimpleServer implements Server{

    private final String SERVER_NAME = "SERVER-" + Random.getRandom().nextInt(100);

    private final String IP = Random.generateRandomIP();

    @SneakyThrows
    @Override
    public Map<String, Object> request(Request request) {
        logIncomingRequest(request);
        Thread.sleep(1000);
        Map<String, Object> response = Constants.DEFAULT_SUCCESS_RESPONSE;
        response.put("fromServer", SERVER_NAME);
        return response;
    }

    private void logIncomingRequest(Request request){
        log.info("Request {} coming to server {}", request, SERVER_NAME);
    }

    public String getIP() {
        return IP;
    }

    @Override
    public String getServerName() {
        return SERVER_NAME;
    }
}
