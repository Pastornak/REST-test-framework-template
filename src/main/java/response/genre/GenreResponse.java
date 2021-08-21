package response.genre;

import com.google.gson.Gson;
import entity.Genre;
import response.IResponse;
import response.ResponseDecorator;

public class GenreResponse extends ResponseDecorator {

    public GenreResponse(IResponse response) {
        super(response);
    }

    public Genre getBody() {
        return new Gson().fromJson(this.getBodyAsString(), Genre.class);
    }
}
