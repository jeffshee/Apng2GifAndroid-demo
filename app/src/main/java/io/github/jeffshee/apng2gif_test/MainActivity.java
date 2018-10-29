package io.github.jeffshee.apng2gif_test;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.github.jeffshee.apng2gif.Apng2Gif;

public class MainActivity extends AppCompatActivity {

    File input, output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The output file will be stored at app's internal storage, will need root access to read
        // Sorry but I'm too lazy to request permission >_<'''
        // APNG is from LINE Store's preview, all rights go to its respective author
        input = new File(getFilesDir(), "apng.png");
        output = new File(getFilesDir(), "gif.gif");

        Apng2Gif apng2Gif = new Apng2Gif();
        loadAsset();
        apng2Gif.start(input, output);

        Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show();
    }

    private void loadAsset() {
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("apng/test_apng.png");
            OutputStream outputStream = new FileOutputStream(input);
            byte[] buffer = new byte[4 * 1024]; // or other buffer size
            int read;

            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
