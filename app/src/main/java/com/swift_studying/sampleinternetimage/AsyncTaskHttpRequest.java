package com.swift_studying.sampleinternetimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskHttpRequest extends AsyncTask<Uri.Builder, Void, Bitmap>{
    private ImageView imageView;

    public AsyncTaskHttpRequest(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Uri.Builder... builder){
        // 受け取ったbuilderでインターネット通信する
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        Bitmap bitmap = null;
        OutputStream os = null;
        int status = 0;
        // POSTパラメータの設定
        //final String message = "ぽけもん";

        try{
            URL url = new URL(builder[0].toString()); //
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            //connection.setInstanceFollowRedirects(false);
            //connection.setDoInput(true);
            //connection.setDoOutput(true);
            //connection.setChunkedStreamingMode(0);
           // connection.setRequestProperty("Content-Type", "text/plain; charset=\"utf8\"");
            //connection.setRequestProperty("message");
            connection.connect();

            // POSTパラメータを設定 OutputStreamを利用
            //os = connection.getOutputStream();
            //PrintStream ps = new PrintStream(os);
            //ps.print(message); //データをPOSTする2
            //ps.close();


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
            bitmap = BitmapFactory.decodeStream(inputStream);

        }catch (MalformedURLException exception){

        }catch (IOException exception){

        }finally {
            if (connection != null){
                connection.disconnect();
            }
            try{
                if (inputStream != null){
                    inputStream.close();
                }
            }catch (IOException exception){
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result){
        this.imageView.setImageBitmap(result);
    }
}
