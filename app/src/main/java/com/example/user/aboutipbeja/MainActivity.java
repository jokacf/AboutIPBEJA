package com.example.user.aboutipbeja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);

        int displaymode = getResources().getConfiguration().orientation;
        if (displaymode == 1) { //portrait mode
            Toast.makeText(this, "Portrait!", Toast.LENGTH_SHORT).show();

        } else {//landscape
            Toast.makeText(this, "Landscape!", Toast.LENGTH_SHORT).show();
            if (findViewById(R.id.fragment_container) != null) {

            }
        }
    }
}
