package com.swift_studying.sampleinternetimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.net.Uri;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.InputStream;
import java.net.HttpURLConnection;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        //POST
        try {
            new HttpPostTask().execute(new URL("http://n302.herokuapp.com/memo"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        /* Uri uri = Uri.parse(""); //http://n302.herokuapp.com/
        Uri.Builder builder = uri.buildUpon();
        AsyncTaskHttpPost task = new AsyncTaskHttpPost(imageView);
        task.execute(builder); */

        //GET
        Uri uri = Uri.parse("http://n302.herokuapp.com/check"); //http://n302.herokuapp.com/
        Uri.Builder builder = uri.buildUpon();
        AsyncTaskHttpRequest task = new AsyncTaskHttpRequest(imageView);
        task.execute(builder);
    }

    public void writeButton(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.swift_studying.sampleinternetimage","com.swift_studying.sampleinternetimage.SubActivity");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
