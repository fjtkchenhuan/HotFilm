package app.com.example.android.hotfilm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import app.com.example.android.hotfilm.R;
import app.com.example.android.hotfilm.bean.FilmIfo;
import app.com.example.android.hotfilm.dao.FilmRecord;
import app.com.example.android.hotfilm.dao.FilmRepository;

public class CollectActivity extends Activity {

    private FilmIfo.ResultsBean filmIfo;
    private FilmRepository repository;
    private List<FilmRecord> filmRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        repository = FilmRepository.getFilmRepository(this);
        filmRecords = new ArrayList<>();
        filmIfo = getIntent().getBundleExtra("data").getParcelable("bean");
        intiView();

    }

    private void intiView() {

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = getResources().getDisplayMetrics();
        lp.width = (int) (d.widthPixels * 0.8);
        lp.height = (int) (d.heightPixels * 0.6);
        dialogWindow.setAttributes(lp);

        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String kind = null;
                int checkId = radioGroup.getCheckedRadioButtonId();
                switch (checkId) {
                    case R.id.very_like:
                        kind = "very_like";
                        break;
                    case R.id.normal:
                        kind = "normal";
                        break;
                    case R.id.not_like:
                        kind = "not_like";
                        break;
                }
                if (kind != null){
                    FilmRecord filmRecord = new FilmRecord(filmIfo.title,filmIfo.release_date,filmIfo.id+"",kind);
                    filmRecords.add(filmRecord);
                    repository.insertIntoFilmsTable(filmRecords);
                }
                finish();
            }
        });
    }
}
