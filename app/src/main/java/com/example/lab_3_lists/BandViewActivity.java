package com.example.lab_3_lists;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BandViewActivity extends AppCompatActivity {

    @Override //Show data of the necessary item of the list
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band);

        TextView name = findViewById(R.id.name);
        TextView genre = findViewById(R.id.genre);
        TextView origin = findViewById(R.id.origin);
        TextView description = findViewById(R.id.description);

        String[] data = new String[4];
        data[0] = getIntent().getStringExtra("name");
        data[1] = getIntent().getStringExtra("genre");
        data[2] = getIntent().getStringExtra("origin");
        data[3] = getIntent().getStringExtra("description");

        name.setText(data[0]);
        genre.setText(data[1]);
        origin.setText(data[2]);
        description.setText(data[3]);

        findViewById(R.id.backButton).setOnClickListener(v -> {
            finish();
        });
    }
}


