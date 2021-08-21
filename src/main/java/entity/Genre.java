package entity;

import com.google.gson.Gson;

public class Genre {
    public int genreId;
    public String genreName;
    public String genreDescription;

    public Genre(){}

    public Genre(int id, String name, String description) {
        this.genreId = id;
        this.genreName = name;
        this.genreDescription = description;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public boolean equals(Object target) {
        Genre gTarget =(Genre) target;
        return gTarget.genreId == this.genreId
                && gTarget.genreName.equals(this.genreName)
                && gTarget.genreDescription.equals(this.genreDescription);
    }
}
