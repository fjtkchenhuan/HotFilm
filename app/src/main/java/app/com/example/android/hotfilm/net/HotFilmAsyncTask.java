package app.com.example.android.hotfilm.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.com.example.android.hotfilm.BuildConfig;
import app.com.example.android.hotfilm.R;
import app.com.example.android.hotfilm.adapter.FilmAdapter;
import app.com.example.android.hotfilm.bean.FilmIfo;

/**
 * Created by FJTK on 2017/2/16.
 */

public class HotFilmAsyncTask extends AsyncTask<Void,Void,List<FilmIfo.ResultsBean>> {
    private Context context;
    private FilmAdapter adapter;
    private String kind;

    public HotFilmAsyncTask(Context context, FilmAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    protected List<FilmIfo.ResultsBean> doInBackground(Void... voids) {
        List<FilmIfo.ResultsBean> results = new ArrayList<>();
        String language = "zh";
        String api_key = BuildConfig.HOT_FILM_API_KEY;


        String FILM_BASEURL = "http://api.themoviedb.org/3/movie/popular";
        final String FILM_LANGUAGE = "language";
        final String API_KEY = "api_key";

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        kind = sharedPrefs.getString(context.getString(R.string.pref_kind_key),context.getString(R.string.pref_popular_value));

        Log.d("HotFilmAsyncTask", kind);
        if ("top_rated".equals(kind)) {
            FILM_BASEURL = "http://api.themoviedb.org/3/movie/top_rated";
        }

        Uri buildUri = Uri.parse(FILM_BASEURL).buildUpon()
                .appendQueryParameter(FILM_LANGUAGE,language)
                .appendQueryParameter(API_KEY,api_key)
                .build();


        String jsonString = HttpUtils.getJsonString(buildUri.toString());
        Gson gson = new Gson();
        if (jsonString != null){
            FilmIfo filmIfo = gson.fromJson(jsonString, FilmIfo.class);
            results = filmIfo.results;
        }
        return results;
    }


    @Override
    protected void onPostExecute(List<FilmIfo.ResultsBean> film) {
        super.onPostExecute(film);
        adapter.getDate(film);
        adapter.notifyDataSetChanged();
    }
}
