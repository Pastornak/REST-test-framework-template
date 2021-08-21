package response;

public interface IResponse {

    int getStatusCode();
    Object getHeaders();
    Object getBody();
}
