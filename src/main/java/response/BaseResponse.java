package response;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// base response to be returned by HTTP client and decorated later
public class BaseResponse implements IResponse {
    private int statusCode;
    private List<Header> headers;
    private Object body;

    public BaseResponse(io.restassured.response.Response restAssuredResponse) {
        this.statusCode = restAssuredResponse.getStatusCode();
        Iterator<io.restassured.http.Header> headerIterator = restAssuredResponse.getHeaders().iterator();
        List<Header> headers = new LinkedList<>();
        headerIterator.forEachRemaining(h -> headers.add(new Header(h.getName(), h.getValue())));
        this.headers = headers;
        this.body = restAssuredResponse.body().asString().equals("") ? null : restAssuredResponse.body().as(Object.class);
    }

    public BaseResponse(ResponseEntity restTemplateResponse) {
        this.statusCode = restTemplateResponse.getStatusCodeValue();
        this.headers = restTemplateResponse
                .getHeaders()
                .entrySet()
                .stream()
                .map((h) -> new Header(h.getKey(), h.getValue().get(0)))
                .collect(Collectors.toList());
        this.body = restTemplateResponse.getBody();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public List<Header> getHeaders() {return this.headers;}

    public Object getBody() {
        return this.body;
    }

    @Override
    public String toString() {
        // assuming all bodies are JSON
        return new Gson().toJson(this.getBody());
    }
}
