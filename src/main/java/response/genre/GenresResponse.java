package response.genre;

import com.google.gson.Gson;
import entity.Genre;
import response.IResponse;
import response.ResponseDecorator;

import java.util.ArrayList;
import java.util.List;

public class GenresResponse extends ResponseDecorator {

    public GenresResponse(IResponse response) {
        super(response);
    }

    public List<Genre> getBody() {
        return new Gson().fromJson(this.getBodyAsString(), ArrayList.class);
    }
}
