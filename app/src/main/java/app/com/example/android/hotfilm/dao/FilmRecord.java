package app.com.example.android.hotfilm.dao;

/**
 * Created by FJTK on 2017/2/21.
 */

public class FilmRecord {
     String filmName;
     String filmDate;
     String id;
     String kind;

    public FilmRecord(String filmName, String filmDate, String id, String kind) {
        this.filmName = filmName;
        this.filmDate = filmDate;
        this.id = id;
        this.kind = kind;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmDate() {
        return filmDate;
    }

    public void setFilmDate(String filmDate) {
        this.filmDate = filmDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
