package client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import response.BaseResponse;
import response.Header;

import java.util.List;

public class RestTemplateClient implements IHttpClient {
    private RestTemplate rt = new RestTemplate();

    public BaseResponse get(String endpoint) {
        return new BaseResponse(this.rt.getForEntity(endpoint, Object.class));
    }

    public BaseResponse post(String endpoint, String body) {
        HttpEntity<String> entity = new HttpEntity<>(body);
        return new BaseResponse(this.rt.postForEntity(endpoint, entity, Object.class));
    }

    public BaseResponse post(String endpoint, List<Header> headers, String body) {
        HttpHeaders httpHeaders = this.populateHeaders(headers);
        HttpEntity<String> entity = new HttpEntity<>(body, httpHeaders);
        return new BaseResponse(this.rt.postForEntity(endpoint, entity, Object.class));
    }

    public BaseResponse put(String endpoint, String body) {
        HttpEntity<String> entity = new HttpEntity<>(body);
        return new BaseResponse(this.rt.exchange(endpoint, HttpMethod.PUT, entity, Object.class));
    }

    public BaseResponse put(String endpoint, List<Header> headers, String body) {
        HttpHeaders httpHeaders = this.populateHeaders(headers);
        HttpEntity<String> entity = new HttpEntity<>(body, httpHeaders);
        return new BaseResponse(this.rt.exchange(endpoint, HttpMethod.PUT, entity, Object.class));
    }

    public BaseResponse delete(String endpoint) {
        HttpEntity<String> entity = new HttpEntity<>("");
        return new BaseResponse(this.rt.exchange(endpoint, HttpMethod.DELETE, entity, Object.class));
    }

    private HttpHeaders populateHeaders(List<Header> headers) {
        HttpHeaders result = new HttpHeaders();
        headers.forEach(h -> result.add(h.name, h.value));
        return result;
    }
}
