package client;

import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import response.BaseResponse;
import response.Header;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class RestAssuredClient implements IHttpClient {

    public BaseResponse get(String endpoint) {
        return RestAssuredClient.sendRequest(Method.GET, endpoint);
    }

    public BaseResponse post(String endpoint, String body) {
        return RestAssuredClient.sendRequest(Method.POST, endpoint, body);
    }

    public BaseResponse post(String endpoint, List<Header> headers, String body) {
        return RestAssuredClient.sendRequest(Method.POST, endpoint, headers, body);
    }

    public BaseResponse put(String endpoint, String body) {
        return RestAssuredClient.sendRequest(Method.PUT, endpoint, body);
    }

    public BaseResponse put(String endpoint, List<Header> headers, String body) {
        return RestAssuredClient.sendRequest(Method.PUT, endpoint, headers, body);
    }

    public BaseResponse delete(String endpoint) {
        return RestAssuredClient.sendRequest(Method.DELETE, endpoint);
    }

    private static BaseResponse sendRequest(Method method, String endpoint) {
        return RestAssuredClient.sendRequest(method, endpoint, null, null);
    }

    private static BaseResponse sendRequest(Method method, String endpoint, String body) {
        return RestAssuredClient.sendRequest(method, endpoint, null, body);
    }

    private static BaseResponse sendRequest(Method method, String endpoint, List<Header> headers, String body) {
        String url = endpoint;
        RequestSpecification spec = given();
        if (headers != null) spec.headers(new Headers(headers.stream().map(h -> new io.restassured.http.Header(h.name, h.value)).collect(Collectors.toList())));
        if (body != null) spec.body(body);
        BaseResponse response = new BaseResponse(spec.request(method, url));
        return response;
    }
}
