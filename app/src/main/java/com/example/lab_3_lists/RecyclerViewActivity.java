package com.example.lab_3_lists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        String[][] data = null; //Get information from the ListViewActivity
        Object[] objectArray = (Object[]) Objects.requireNonNull(getIntent().getExtras()).getSerializable("data");
        if (objectArray != null){
            data = new String[objectArray.length][];
            for (int i = 0; i < objectArray.length; i++)
                data[i]=(String[]) objectArray[i];
        }
        RecyclerView rv = findViewById(R.id.recycler);
        rv.setHasFixedSize(true);

        //Syncing RecyclerView with the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));
        rv.setAdapter(new MyAdapter(this, data));

        findViewById(R.id.switchButton).setOnClickListener(v -> { //Turn to ListView
            Intent intent = new Intent(RecyclerViewActivity.this, ListViewActivity.class);
            startActivity(intent);
            finish();
        });
    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.BandViewHolder> {
        String[][] data;
        MyAdapter(Context context, String[][] data) {
            this.data = data;
        }

        @NonNull
        @Override
        public BandViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item, viewGroup, false);
            return new BandViewHolder(v);
        }

        @Override // Data initialization of items which got from a RecyclerView
        public void onBindViewHolder(@NonNull BandViewHolder BandViewHolder, int i) {
            BandViewHolder.name.setText(data[i][0]);
            BandViewHolder.genre.setText(data[i][1]);
            BandViewHolder.origin.setText(data[i][2]);
            BandViewHolder.description.setText(data[i][3]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        static class BandViewHolder extends RecyclerView.ViewHolder {
            TextView name, genre, origin, description;

            BandViewHolder(@NonNull final View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                genre = itemView.findViewById(R.id.genre);
                origin = itemView.findViewById(R.id.origin);
                description = itemView.findViewById(R.id.description);

                itemView.setOnClickListener(v -> { //Selection items of the list
                    Intent intent = new Intent(v.getContext(), BandViewActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("genre", genre.getText().toString());
                    intent.putExtra("origin", origin.getText().toString());
                    intent.putExtra("description", description.getText().toString());
                    v.getContext().startActivity(intent);
                });
            }
        }
    }
}


