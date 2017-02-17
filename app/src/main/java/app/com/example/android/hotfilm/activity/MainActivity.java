package app.com.example.android.hotfilm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.com.example.android.hotfilm.R;
import app.com.example.android.hotfilm.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.activity_main_container,new MainFragment()).commit();
        }

    }
}
