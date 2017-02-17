package app.com.example.android.hotfilm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.example.android.hotfilm.R;
import app.com.example.android.hotfilm.bean.FilmIfo;
import app.com.example.android.hotfilm.net.HttpUtils;
import it.sephiroth.android.library.picasso.Callback;
import it.sephiroth.android.library.picasso.Picasso;

public class FilmDetailActivity extends Activity {

    private TextView tvFilmTitle;
    private ImageView imFilmImg;
    private TextView tvFilmName;
    private TextView tvFilmTime;
    private TextView tvFilmCountry;
    private TextView tvFilmPopularity;
    private TextView tvFilmVoteCount;
    private TextView tvFilmVoteAverage;
    private TextView tvFilmOverView;
    private FilmIfo.ResultsBean filmInfo;

    private String imgUrl;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 0) {
                if (HttpUtils.isOnline(getBaseContext())){
                    Picasso.with(getBaseContext())
                            .load(imgUrl)
                            .placeholder(R.mipmap.ic_launcher)
                            .into(imFilmImg, new Callback() {
                                @Override
                                public void onSuccess() {
                                    Log.d("FilmDetailActivity", "success");
                                }

                                @Override
                                public void onError() {
                                    Log.d("FilmDetailActivity", "fail");
                                }
                            });
                }

            }
            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        Bundle bundle = getIntent().getBundleExtra("data");
        filmInfo = bundle.getParcelable("bean");

        initView();

    }

    private void initView() {
        tvFilmTitle = (TextView) findViewById(R.id.film_title);
        imFilmImg = (ImageView) findViewById(R.id.film_img);
        tvFilmName = (TextView) findViewById(R.id.film_name);
        tvFilmTime = (TextView) findViewById(R.id.film_time);
        tvFilmCountry = (TextView) findViewById(R.id.film_country);
        tvFilmPopularity = (TextView) findViewById(R.id.film_popularity);
        tvFilmVoteCount = (TextView) findViewById(R.id.film_vote_count);
        tvFilmVoteAverage = (TextView) findViewById(R.id.film_vote_average);
        tvFilmOverView = (TextView) findViewById(R.id.film_overview);

        tvFilmTitle.setText(filmInfo.title);
        tvFilmName.setText(getString(R.string.film_original_title) + " "+filmInfo.original_title);
        tvFilmTime.setText(getString(R.string.film_release_date)+ " "+ filmInfo.release_date);
        tvFilmCountry.setText(getString(R.string.film_original_language) + " "+ filmInfo.original_language);
        tvFilmPopularity.setText(getString(R.string.film_popularity) + " "+ filmInfo.popularity);
        tvFilmVoteCount.setText(getString(R.string.film_vote_count) + " "+ filmInfo.vote_count);
        tvFilmVoteAverage.setText(getString(R.string.film_vote_average) +" "+ filmInfo.vote_average);
        tvFilmOverView.setText(filmInfo.overview);

        String imgBaseUrl = "https://image.tmdb.org/t/p/w185//";
        String poster_path = filmInfo.poster_path;
        imgUrl = imgBaseUrl + poster_path;
        handler.sendEmptyMessage(0);
    }
}
