package com.example.serdar.smartsmsbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BlackList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button addButton;
    private EditText blackListEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_list);

        recyclerView = (RecyclerView) findViewById(R.id.blacklistRecyclerView);
        addButton = (Button) findViewById(R.id.addButton);
        blackListEditText = (EditText) findViewById(R.id.blackListEditText);

        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MainActivity.blackList.add(blackListEditText.getText().toString());
                Toast.makeText(BlackList.this, blackListEditText.getText().toString() + "added to blacklist!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter = new MyBlackListAdapter(MainActivity.blackList);
        recyclerView.setAdapter(mAdapter);


    }
}
