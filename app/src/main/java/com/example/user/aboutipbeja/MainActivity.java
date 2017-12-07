package com.example.user.aboutipbeja;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    boolean isOpen = false;
    FloatingActionButton fab_menu;
    ImageView  fab_call, fab_map, fab_web;
    Animation fabOpen, fabClose, fabRright, fabRleft;
    Intent call;
    static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);

        fab_menu = (FloatingActionButton) findViewById(R.id.menuFloating);
        fab_call = (ImageView) findViewById(R.id.call_image);
        fab_map = (ImageView) findViewById(R.id.map_image);
        fab_web = (ImageView) findViewById(R.id.web_image);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_close);
        fabRright = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right);
        fabRleft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_left);

        fab_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    fab_menu.startAnimation(fabRleft);
                    fab_call.startAnimation(fabClose);
                    fab_map.startAnimation(fabClose);
                    fab_web.startAnimation(fabClose);
                    fab_call.setClickable(false);
                    fab_map.setClickable(false);
                    fab_web.setClickable(false);
                    isOpen = false;
                }else{
                    fab_menu.startAnimation(fabRright);
                    fab_call.startAnimation(fabOpen);
                    fab_map.startAnimation(fabOpen);
                    fab_web.startAnimation(fabOpen);
                    fab_call.setClickable(true);
                    fab_map.setClickable(true);
                    fab_web.setClickable(true);
                    isOpen = true;
                }
            }
        });

        fab_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:+351 284 315 000");
                call = new Intent(Intent.ACTION_CALL, number);
                if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    call = new Intent(Intent.ACTION_DIAL, number);
                }
                startActivityForResult(call, PICK_CONTACT_REQUEST);
            }
        });

        fab_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("http://www.ipbeja.pt");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });

        fab_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri location = Uri.parse("geo:38.0135135,-7.8734867?z=17");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
            }
        });

        int displaymode = getResources().getConfiguration().orientation;
        if (displaymode == 1) { //portrait mode
            Toast.makeText(this, "Portrait!", Toast.LENGTH_SHORT).show();
            if (findViewById(R.id.fragment_container) != null) {
                if (savedInstanceState != null) {
                    return;
                }
                FragmentHeadlines firstFragment = new FragmentHeadlines();
                // In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments
                firstFragment.setArguments(getIntent().getExtras());
                // Add the fragment to the 'fragment_container' FrameLayout
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, firstFragment).commit();
            }

        } else {//landscape
            Toast.makeText(this, "Landscape!", Toast.LENGTH_SHORT).show();
        }
    }
}

