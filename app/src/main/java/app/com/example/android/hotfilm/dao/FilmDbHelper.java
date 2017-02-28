package app.com.example.android.hotfilm.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FJTK on 2017/2/21.
 */

public class FilmDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "films.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FilmsPersistenceContract.FilmsEntry.TABLE_NAME + " (" +
                    FilmsPersistenceContract.FilmsEntry.COLUMN_NAME_ID + TEXT_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    FilmsPersistenceContract.FilmsEntry.COLUMN_NAME_OF_FILM + TEXT_TYPE + COMMA_SEP +
                    FilmsPersistenceContract.FilmsEntry.COLUMN_DATA_OF_RELEASE + TEXT_TYPE + COMMA_SEP +
                    FilmsPersistenceContract.FilmsEntry.COLUMN_KIND_OF_LIKE + TEXT_TYPE + ")";

    FilmDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
