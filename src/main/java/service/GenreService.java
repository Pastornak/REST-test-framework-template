package service;

import com.google.gson.Gson;
import config.ServiceConfig;
import entity.Genre;
import entity.ListOptions;
import response.*;
import response.genre.GenreResponse;
import response.genre.GenresResponse;
import utils.EndpointBuilder;

import java.util.Arrays;

public class GenreService {
    private static Gson g = new Gson();

    public GenreResponse getGenre(int genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return new GenreResponse(ServiceConfig.CLIENT.get(endpoint));
    }

    public GenresResponse getGenres(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("genre");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
            .queryParam("page", options.page)
            .queryParam("pagination", options.pagination)
            .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new GenresResponse(ServiceConfig.CLIENT.get(endpoint.get()));
    }

    public GenreResponse createGenre(Genre genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        return new GenreResponse(ServiceConfig.CLIENT.post(endpoint, Arrays.asList(new Header("content-type", "application/json")), g.toJson(genre)));
    }

    public GenreResponse updateGenre(Genre genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        return new GenreResponse(ServiceConfig.CLIENT.put(endpoint, Arrays.asList(new Header("content-type", "application/json")), g.toJson(genre)));
    }

    public BaseResponse deleteGenre(int genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return ServiceConfig.CLIENT.delete(endpoint);
    }
}
