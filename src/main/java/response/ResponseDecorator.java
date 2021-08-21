package response;

import com.google.gson.Gson;

// response decorator which will allow decorating response body (through overriding getBody method)
public abstract class ResponseDecorator implements IResponse {
    private IResponse response;

    public ResponseDecorator(IResponse response) {
        this.response = response;
    }

    public int getStatusCode() {
        return this.response.getStatusCode();
    }

    public Object getHeaders() {
        return this.response.getHeaders();
    }

    protected String getBodyAsString() {
        Gson g = new Gson();
        return g.toJson(this.response.getBody());
    }
}
