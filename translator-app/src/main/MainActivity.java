package com.example.translatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    private Translator translatorGerman;
    private Translator translatorArabic;
    private Translator translatorKorean;

    private Boolean booleanGerman = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);


        TranslatorOptions translatorOptionsGerman = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.GERMAN)
                .build();

        TranslatorOptions translatorOptionsArabic = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.ARABIC)
                .build();

        TranslatorOptions translatorOptionsKorean = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.KOREAN)
                .build();

        translatorGerman = Translation.getClient(translatorOptionsGerman);
        translatorArabic = Translation.getClient(translatorOptionsArabic);



        translatorKorean = Translation.getClient(translatorOptionsKorean);

        downloadModel();
    }

    private void downloadModel(){
        DownloadConditions downloadConditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        translatorGerman.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(unused -> booleanGerman = true)
                .addOnFailureListener(e -> booleanGerman = false);

        translatorArabic.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(unused -> {
                })
                .addOnFailureListener(e -> {
                });

        translatorKorean.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(unused -> {
                })
                .addOnFailureListener(e -> {
                });
    }

    public void buttonDownloadModel(View view){
        downloadModel();
    }

    public void buttonGerman(View view){

        if (booleanGerman){
            translatorGerman.translate(editText.getText().toString())
                    .addOnSuccessListener(s -> textView.setText(s))
                    .addOnFailureListener(e -> textView.setText(e.toString()));
        }
    }

    public void buttonArabic(View view){

        if (booleanGerman){
            translatorArabic.translate(editText.getText().toString())
                    .addOnSuccessListener(s -> textView.setText(s))
                    .addOnFailureListener(e -> textView.setText(e.toString()));
        }
    }

    public void buttonKorean(View view){
        if (booleanGerman){
            translatorKorean.translate(editText.getText().toString())
                    .addOnSuccessListener(s -> textView.setText(s))
                    .addOnFailureListener(e -> textView.setText(e.toString()));
        }
    }
}
