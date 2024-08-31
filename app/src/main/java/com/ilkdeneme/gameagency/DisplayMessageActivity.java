package com.ilkdeneme.gameagency;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Intent'ten alınan mesajı al
        String nameSurname = getIntent().getStringExtra("NAME_SURNAME");

        // TextView'i bul ve mesajı ayarla
        TextView textView = findViewById(R.id.textViewNameSurname);
        textView.setText(nameSurname);
    }
}
