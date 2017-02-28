package app.com.example.android.hotfilm.dao;

class FilmsPersistenceContract {
    static abstract class FilmsEntry {
        static final String TABLE_NAME = "films";
        static final String COLUMN_NAME_ID = "_id";
        static final String COLUMN_NAME_OF_FILM = "_nameOfFilm";
        static final String COLUMN_DATA_OF_RELEASE = "_dataOfRelease";
        static final String COLUMN_KIND_OF_LIKE = "_kindOfLike";
    }
}
