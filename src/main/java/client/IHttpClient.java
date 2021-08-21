package client;

import response.BaseResponse;
import response.Header;

import java.util.List;

public interface IHttpClient {

    BaseResponse get(String endpoint);

    BaseResponse post(String endpoint, String body);
    BaseResponse post(String endpoint, List<Header> headers, String body);

    BaseResponse put(String endpoint, String body);
    BaseResponse put(String endpoint, List<Header> headers, String body);

    BaseResponse delete(String endpoint);
}
