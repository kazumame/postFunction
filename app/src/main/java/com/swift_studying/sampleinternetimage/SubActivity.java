package com.swift_studying.sampleinternetimage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SubActivity extends Activity {
    EditText editText = null; // memo用EditText
    HttpURLConnection urlCon = null; // httpのコネクションを管理するクラス
    InputStream in = null; // URL連携した戻り値を取得して保持する用
    Bitmap bit = null;
    ImageView image = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }

    public void postButton(View view) {
        String urlString = "http://172.16.30.64:9292/message";
        try {
            // httpコネクションを確立し、urlを叩いて情報を取得
            URL url = new URL(urlString);
            HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
            urlCon.setRequestMethod("POST");
            urlCon.setDoOutput(true); // POSTによるデータ送信を可能にします
            urlCon.connect();

            // POSTパラメータ
            editText = (EditText) findViewById(R.id.editText);
            String message = editText.getText().toString();

            // POSTパラメータを設定（方法１） OutputStreamを利用
               OutputStream out = urlCon.getOutputStream();
               out.write(message.getBytes());
               out.flush();
               out.close();

            // POSTパラメータを設定（方法２）PrintStreamを利用
           // PrintStream ps = new PrintStream(urlCon.getOutputStream());
           // ps.print(message);
           // ps.close();

            // POSTパラメータを設定（方法３）OutputStreamWriteを利用
//                OutputStreamWriter osw = new OutputStreamWriter( urlCon.getOutputStream());
//                osw.write(postDataSample);
//                osw.flush();
//                osw.close();

            // データを取得
            InputStream in = urlCon.getInputStream(); //POST結果を取得
            bit = BitmapFactory.decodeStream(in);

            //post結果を表示
            image = (ImageView) findViewById(R.id.imageView2);
            image.setImageBitmap(bit);

            // InputStreamからbyteデータを取得するための変数
            //StringBuffer bufStr = new StringBuffer();
            //String temp = null;

            // 結果をテキストビューに設定
            //resultTv.setText(bufStr.toString());

        } catch (IOException ioe ) {
            Log.d(this.getClass().toString(),ioe.toString());
            Toast.makeText(this, "IOExceptionが発生しました。", Toast.LENGTH_SHORT).show();

        } /* finally {

            try {
                urlCon.disconnect();
                in.close();

            } catch (IOException ioe ) {
                ioe.printStackTrace();
            }
        } */

    }
}
