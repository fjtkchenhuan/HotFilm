package app.com.example.android.hotfilm.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import app.com.example.android.hotfilm.R;
import app.com.example.android.hotfilm.activity.FilmDetailActivity;
import app.com.example.android.hotfilm.bean.FilmIfo;
import app.com.example.android.hotfilm.net.HttpUtils;
import it.sephiroth.android.library.picasso.Callback;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by FJTK on 2017/2/16.
 */

public class FilmAdapter extends BaseAdapter {
    private List<FilmIfo.ResultsBean> Films;
    private Context context;

    public FilmAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        int i = 0;
        if (Films != null && Films.size() > 0){
            i = Films.size();
        }
        return i;
    }

    @Override
    public FilmIfo.ResultsBean getItem(int i) {
        return Films.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_image_view,null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.item_image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final FilmIfo.ResultsBean film = getItem(i);
        String imgBaseUrl = "https://image.tmdb.org/t/p/w185//";
        String poster_path = film.poster_path;
        String imgUrl = imgBaseUrl+poster_path;

        if (HttpUtils.isOnline(context)) {
            Picasso.with(context)
                    .load(imgUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("FilmAdapter", "success");
                        }

                        @Override
                        public void onError() {
                            Log.d("FilmAdapter", "fail");
                        }
                    });
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FilmDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putParcelable("bean",film);
                intent.putExtra("data",bundle);
                context.startActivity(intent);
            }
        });

        return view;
    }

   private class ViewHolder {
        private ImageView imageView;
    }

    public void getDate(List<FilmIfo.ResultsBean> film) {
        Films = new ArrayList<>();
        Films.addAll(film);
    }
}
