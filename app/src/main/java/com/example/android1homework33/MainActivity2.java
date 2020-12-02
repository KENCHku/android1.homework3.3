package com.example.android1homework33;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity  {

    ImageView imageView2;
    EditText editText2;
    Button backButton;

    public static final String KEY = "0";
    public static final String IMAGE_KEY = "4";
    String uriSaver;

    int REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView2 = (ImageView) findViewById(R.id.imageView2);
        editText2 = (EditText) findViewById(R.id.editText);
        backButton = (Button) findViewById(R.id.backButton);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override//for text
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(KEY, editText2.getText().toString());
                intent.putExtra(IMAGE_KEY, uriSaver);
                setResult(RESULT_OK, intent);

                finish();
            }
        });


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override//for image
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                // указываем действие
                intent.setType("image/*");
                // указываем тип, то есть Image
                startActivityForResult(intent, REQUEST_CODE);
                // тут уже переходит в галлерею
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            Uri uri = data.getData();
            uriSaver = String.valueOf(uri);//uri надо перевести в стринг чтобы перевести через интент
            imageView2.setImageURI(uri);
        }
    }



}