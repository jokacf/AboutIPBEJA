package com.example.user.aboutipbeja;

import android.Manifest;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends AppCompatActivity {
    WebView webView;
    boolean isOpen = false;
    FloatingActionButton fab_menu;
    ImageView fab_call, fab_map, fab_web;
    Animation fabOpen, fabClose, fabRright, fabRleft, fabOpenSmall, fabCloseSmall;
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
        fabOpenSmall = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_open_small);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_close);
        fabCloseSmall = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_close);
        fabRright = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right);
        fabRleft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_left);

        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_NORMAL || (getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_SMALL) {
            fab_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isOpen) {
                        fab_menu.startAnimation(fabRleft);
                        fab_call.startAnimation(fabCloseSmall);
                        fab_map.startAnimation(fabCloseSmall);
                        fab_web.startAnimation(fabCloseSmall);
                        fab_call.setClickable(false);
                        fab_map.setClickable(false);
                        fab_web.setClickable(false);
                        isOpen = false;
                    } else {
                        fab_menu.startAnimation(fabRright);
                        fab_call.startAnimation(fabOpenSmall);
                        fab_map.startAnimation(fabOpenSmall);
                        fab_web.startAnimation(fabOpenSmall);
                        fab_call.setClickable(true);
                        fab_map.setClickable(true);
                        fab_web.setClickable(true);
                        isOpen = true;
                    }
                }
            });
        } else {
            fab_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isOpen) {
                        fab_menu.startAnimation(fabRleft);
                        fab_call.startAnimation(fabClose);
                        fab_map.startAnimation(fabClose);
                        fab_web.startAnimation(fabClose);
                        fab_call.setClickable(false);
                        fab_map.setClickable(false);
                        fab_web.setClickable(false);
                        isOpen = false;
                    } else {
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
        }


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
        if (displaymode == 1) { //portrait
            if (findViewById(R.id.fragment_container) != null) {

                    FragmentHeadlines firstFragment = new FragmentHeadlines();
                    FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, firstFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

            }
            } else {//landscape
                if (findViewById(R.id.fragment_container) != null && findViewById(R.id.fragment_container_land_headline) != null) {
                        FragmentHeadlines firstFragment = new FragmentHeadlines();
                        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container_land_headline, firstFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                if(savedInstanceState == null) {
                    FragmentArticle article = new FragmentArticle();
                    article.setArguments(getIntent().getExtras());
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment_container, article).commit();
                }
                }
        }
    }
}