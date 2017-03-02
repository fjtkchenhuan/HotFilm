package app.com.example.android.hotfilm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.com.example.android.hotfilm.R;
import app.com.example.android.hotfilm.dao.FilmRecord;
import app.com.example.android.hotfilm.dao.FilmRepository;

public class FilmLovedActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView listView;
    private FilmRepository repository;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private List<FilmRecord> filmRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_loved);
        repository = FilmRepository.getFilmRepository(this);
        filmRecords = new ArrayList<>();

        btn1 = (Button) findViewById(R.id.btn_like);
        btn2 = (Button) findViewById(R.id.btn_normal);
        btn3 = (Button) findViewById(R.id.btn_not_like);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.loved_film_listView);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_like:
                filmRecords = repository.queryFilmTable("very_like");
                btn1.setBackgroundColor(getResources().getColor(R.color.select_btn_pressed));
                btn2.setBackgroundColor(getResources().getColor(R.color.translate_btn));
                btn3.setBackgroundColor(getResources().getColor(R.color.translate_btn));
                break;
            case R.id.btn_normal:
                filmRecords = repository.queryFilmTable("normal");
                btn2.setBackgroundColor(getResources().getColor(R.color.select_btn_pressed));
                btn1.setBackgroundColor(getResources().getColor(R.color.translate_btn));
                btn3.setBackgroundColor(getResources().getColor(R.color.translate_btn));
                break;
            case R.id.btn_not_like:
                filmRecords = repository.queryFilmTable("not_like");
                btn3.setBackgroundColor(getResources().getColor(R.color.select_btn_pressed));
                btn1.setBackgroundColor(getResources().getColor(R.color.translate_btn));
                btn2.setBackgroundColor(getResources().getColor(R.color.translate_btn));
                break;
        }
        setData();
    }

    private void setData() {
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < filmRecords.size(); i++) {
            titles.add(filmRecords.get(i).getFilmName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        listView.setAdapter(arrayAdapter);
    }

}
