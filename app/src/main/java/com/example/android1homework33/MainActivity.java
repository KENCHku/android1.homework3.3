package com.example.android1homework33;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    final int REQUEST_CODE = 1;


    ImageView imageView;
    TextView textView;
    Button nextButton;
    Button transitionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        nextButton = (Button) findViewById(R.id.button2);
        transitionButton = (Button) findViewById(R.id.button);


    }

    public void transitionButton(View view) {
        openApp();
    }

    public void nextButton(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String text = data.getStringExtra(MainActivity2.KEY);
            textView.setText(text);

            String image = data.getStringExtra(MainActivity2.IMAGE_KEY);
            Uri uri = Uri.parse(image);
            //parse is for - from string to uri
            imageView.setImageURI(uri);

        }
    }

    private void openApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textView.getText().toString()); // текст отправки
        startActivity(Intent.createChooser(intent, "Share with"));
    }


}