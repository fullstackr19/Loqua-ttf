package com.example.loqua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button ttf_button;
    TextView logo;
    EditText text_field;
    ImageView illustration_icon;

    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ttf_button = findViewById(R.id.ttf_button);
        logo = findViewById(R.id.logo);
        text_field = findViewById(R.id.text_field);
        illustration_icon = findViewById(R.id.illustration_icon);

        textToSpeech =  new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        ttf_button.setOnClickListener(view -> {
            String text = text_field.getText().toString();
            Toast.makeText(getApplicationContext(), text,Toast.LENGTH_SHORT).show();
            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
        });
    }

    @Override
    protected void onPause() {
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}