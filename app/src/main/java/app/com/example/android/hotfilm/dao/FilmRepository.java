package app.com.example.android.hotfilm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static app.com.example.android.hotfilm.dao.FilmsPersistenceContract.FilmsEntry;

/**
 * Created by FJTK on 2017/2/21.
 */

public class FilmRepository {

    private FilmDbHelper filmDbHelper;
    private Context context;
    private static FilmRepository filmRepository;

    public FilmRepository(Context context) {
        filmDbHelper = new FilmDbHelper(context);
    }

    public static synchronized FilmRepository getFilmRepository(Context context) {
        if (filmRepository == null) {
            filmRepository = new FilmRepository(context);
        }
        return filmRepository;
    }

    //添加数据
   public void insertIntoFilmsTable(List<FilmRecord> filmRecords) {
        SQLiteDatabase db = filmDbHelper.getWritableDatabase();
        for (FilmRecord record : filmRecords) {
            ContentValues values = new ContentValues();
            values.put(FilmsEntry.COLUMN_NAME_ID,record.id);
            values.put(FilmsEntry.COLUMN_DATA_OF_RELEASE,record.filmDate);
            values.put(FilmsEntry.COLUMN_KIND_OF_LIKE,record.kind);
            values.put(FilmsEntry.COLUMN_NAME_OF_FILM,record.filmName);

            db.insert(FilmsEntry.TABLE_NAME,null,values);
        }
        db.close();
    }

    //删除数据
    private void deleteAll() {
        SQLiteDatabase db = filmDbHelper.getWritableDatabase();
        db.delete(FilmsEntry.TABLE_NAME,null,null);
        db.close();
    }

    //通过指定id删除数据
    private void deleteById(String id) {
        SQLiteDatabase db = filmDbHelper.getWritableDatabase();
        String selection = FilmsEntry.COLUMN_NAME_ID + "LIKE ?";
        String[] selectionArgs = {id};
        db.delete(FilmsEntry.TABLE_NAME,selection,selectionArgs);
        db.close();
    }

    //查询数据
    public List<FilmRecord> queryFilmTable(String kind) {
        SQLiteDatabase db = filmDbHelper.getWritableDatabase();
        List<FilmRecord> filmRecords = new ArrayList<>();

        String[] columns = new String[]{
                FilmsEntry.COLUMN_KIND_OF_LIKE,
                FilmsEntry.COLUMN_DATA_OF_RELEASE,
                FilmsEntry.COLUMN_NAME_OF_FILM,
                FilmsEntry.COLUMN_NAME_ID};

        String selection = FilmsEntry.COLUMN_KIND_OF_LIKE + " LIKE ?";
        String[] selectionArgs = {kind};

        Cursor cursor = db.query(FilmsEntry.TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        while (cursor.moveToNext()) {
            filmRecords.add(new FilmRecord(cursor.getString(cursor.getColumnIndex(FilmsEntry.COLUMN_NAME_OF_FILM)),
                    cursor.getString(cursor.getColumnIndex(FilmsEntry.COLUMN_DATA_OF_RELEASE)),
                    cursor.getString(cursor.getColumnIndex(FilmsEntry.COLUMN_NAME_ID)),
                    cursor.getString(cursor.getColumnIndex(FilmsEntry.COLUMN_KIND_OF_LIKE))));

        }

        cursor.close();
        db.close();
        return filmRecords;
    }
}
