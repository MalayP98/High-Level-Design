package systems;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class Request {

    private final Map<String, String> headers;

    private final String payload;
}
