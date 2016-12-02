package com.swift_studying.sampleinternetimage;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class HttpPostTask extends AsyncTask<URL, Void, Void> {
    @Override
    protected Void doInBackground(URL... urls) {

        final URL url = urls[0];
        HttpURLConnection con = null;
        // POSTパラメータ
        String message = "ぽけもん";
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "multipart/form-data; message=" + message);
            con.setChunkedStreamingMode(0);
            con.connect();

            // POSTデータ送信処理
            PrintStream ps = new PrintStream(con.getOutputStream());
            ps.print(message);
            ps.close();

            /* OutputStream out = null;
            try {
                out = con.getOutputStream();
                out.write(message.getBytes("UTF-8"));
                out.flush();
            } catch (IOException e) {
                // POST送信エラー
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
            } */

            final int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                // 正常
                // レスポンス取得処理を実行
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }
}

/* package com.swift_studying.sampleinternetimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskHttpPost extends AsyncTask<Uri.Builder, Void, Void> {
    private ImageView imageView;

    @Override
    protected Bitmap doInBackground(Uri.Builder... builder) {
        // 受け取ったbuilderでインターネット通信する
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        Bitmap bitmap = null;
        OutputStream os = null;
        int status = 0;
        // POSTパラメータの設定
        final String message = "ぽけもん";

        try {
            URL url = new URL(builder[0].toString()); //
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            //connection.setInstanceFollowRedirects(false);
            //connection.setDoInput(true);
            connection.setDoOutput(true);
            //connection.setChunkedStreamingMode(0);
            connection.setRequestProperty("Content-Type", "multipart/form-data; message=" + message);
            //connection.setRequestProperty("message");
            connection.connect();

            // POSTパラメータを設定 OutputStreamを利用
            os = connection.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.print(message); //データをPOSTする2
            ps.close();


            //os.write(message.getBytes());
            //os.flush();
            //os.close();

            // POSTパラメータを設定 OutputStreamを利用
            //os = new OutputStreamWriter( connection.getOutputStream() );
            //os.write(message);
            //os.flush();
            //os.close();

            //OutputStreamWriter ow1 = new OutputStreamWriter(connection.getOutputStream());
            //BufferedWriter bw1 = new BufferedWriter(ow1);
            // bw1.write(message);
            // bw1.close();
            //ow1.close();

            inputStream = connection.getInputStream(); //POSTした結果を取得
            //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            //bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException exception) {
            }
        }
        return inputStream;
    }
}

    /* @Override
     protected void onPostExecute(Bitmap result){
        this.imageView.setImageBitmap(result);
    }
} */
