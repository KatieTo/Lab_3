package com.example.lab_3_lists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Structure of data with input information
        String[][] data = new String[5][4];
        data[0][0] = "Queen"; data[0][1] = "Rock"; data[0][2] = "London, England"; data[0][3] = "Queen are a British rock band formed in London in 1970. Their classic line-up was Freddie Mercury, Brian May, Roger Taylor and John Deacon";
        data[1][0] = "Pink Floyd"; data[1][1] = "Rock"; data[1][2] = "London, England"; data[1][3] = "Pink Floyd were an English rock band formed in London in 1965. Gaining a following as a psychedelic rock group, they were distinguished for their extended compositions";
        data[2][0] = "Black Sabbath"; data[2][1] = "Heavy metal"; data[2][2] = "Birmingham, England"; data[2][3] = "Black Sabbath were an English rock band formed in Birmingham in 1968 by guitarist Tony Iommi, drummer Bill Ward, bassist Geezer Butler and vocalist Ozzy Osbourne";
        data[3][0] = "Deep Purple"; data[3][1] = "Hard rock"; data[3][2] = "Hertfordshire, England"; data[3][3] = "Deep Purple are an English rock band formed in Hertfordshire in 1968.The band is considered to be among the pioneers of heavy metal and modern hard rock";
        data[4][0] = "Whitesnake"; data[4][1] = "Hard rock"; data[4][2] = "Cleveland, England"; data[4][3] = "Whitesnake are a hard rock band formed in England in 1978 by David Coverdale, after his departure from his previous band Deep Purple";

        MyAdapter adapter = new MyAdapter(this, data); // adapter object
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener((parent, view, position, id) -> { //Items list for selection
            Intent intent = new Intent(ListViewActivity.this, BandViewActivity.class);
            intent.putExtra("name", data[position][0]);
            intent.putExtra("genre", data[position][1]);
            intent.putExtra("origin", data[position][2]);
            intent.putExtra("description", data[position][3]);
            startActivity(intent);
        });

        findViewById(R.id.switchButton).setOnClickListener(v -> { //Turn to RecyclerView
            Intent intent = new Intent(ListViewActivity.this, RecyclerViewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",  data);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });
    }

    public static class MyAdapter extends BaseAdapter {
        private String[][] data;
        private LayoutInflater inflater;

        MyAdapter(Context context, String[][] data) {
            this.data = data;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = inflater.inflate(R.layout.activity_item, parent, false);

            TextView name = convertView.findViewById(R.id.name); //Here we can access the tree widgets
            TextView genre = convertView.findViewById(R.id.genre);
            TextView origin = convertView.findViewById(R.id.origin);
            TextView description = convertView.findViewById(R.id.description);

            name.setText(data[position][0]); //Swap data of widget
            genre.setText(data[position][1]);
            origin.setText(data[position][2]);
            description.setText(data[position][3]);

            return convertView;
        }
    }
}


