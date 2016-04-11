package com.coderchowki.featureapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.FirebaseRecyclerAdapter;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.messages_recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerAdapter mAdapter = new FirebaseRecyclerAdapter<ChatMessage, CMViewHolder>(ChatMessage.class, android.R.layout.two_line_list_item, CMViewHolder.class, mRef) {
            @Override
            public void populateViewHolder(CMViewHolder chatMessageViewHolder, ChatMessage chatMessage, int position) {
                chatMessageViewHolder.nameText.setText(chatMessage.getName());
                chatMessageViewHolder.messageText.setText(chatMessage.getMessage());
            }
        };
        recycler.setAdapter(mAdapter);
    }
}
