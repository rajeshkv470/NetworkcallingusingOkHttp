package com.example.jakku.networkcallingusingokhttp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String URL = "https://unsplash.com/photos/sY6yvE0AUZo/download?force=true";
    ImageView image;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       image = (ImageView) findViewById(R.id.imageView);
        Button but =(Button) findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calling Asynk Task to download file in backgroung
                new Downloadfile().execute(URL);
                //new Downloadfile.execute(URL);
            }
        });
    }

    private class Downloadfile extends AsyncTask<String, Void, Bitmap>{

        protected void onPreExecute(){
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Downloading");
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bit = null;
            try{
                InputStream input = new java.net.URL(imageURL).openStream();
                bit = BitmapFactory.decodeStream(input);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), " Failed to Downloaded", Toast.LENGTH_SHORT).show();
            }
            return bit;
        }

        protected void onPostExecute(Bitmap result){
            image.setImageBitmap(result);
            mProgressDialog.dismiss();

        }
    }
}
