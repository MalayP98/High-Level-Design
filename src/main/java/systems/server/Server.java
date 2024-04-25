package systems.server;

import systems.Request;
import utils.Constants;
import java.util.Map;

public interface Server {

    Map<String, Object> request(Request request);

    String getIP();

    String getServerName();
}
