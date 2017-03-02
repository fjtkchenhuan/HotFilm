package app.com.example.android.hotfilm.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import app.com.example.android.hotfilm.R;
import app.com.example.android.hotfilm.activity.FilmLovedActivity;
import app.com.example.android.hotfilm.activity.SettingActivity;
import app.com.example.android.hotfilm.adapter.FilmAdapter;
import app.com.example.android.hotfilm.net.HotFilmAsyncTask;
import app.com.example.android.hotfilm.net.HttpUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private FilmAdapter adapter;
    private GridView gridView;

    public MainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.main_fragment_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            initFilmDate();
            return true;
        }
        if (id == R.id.action_setting) {
            Intent intent = new Intent(getContext(), SettingActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_kind) {
            Intent intent = new Intent(getContext(), FilmLovedActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.gridView);

//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
//        String filmClasses = sharedPrefs.getString(getContext().getString(R.string.pref_kind_label),getContext().getString(R.string.pref_popular_label));

        ProgressDialog progressDialog = ProgressDialog.show(getContext(), "请稍等...", "获取数据中...", true);
        adapter = new FilmAdapter(getContext(),progressDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        initFilmDate();
    }

    private void initFilmDate() {
        if (HttpUtils.isOnline(getContext())){
            HotFilmAsyncTask task = new HotFilmAsyncTask(getContext(),adapter);
            task.execute();
            gridView.setAdapter(adapter);
        }
    }
}
