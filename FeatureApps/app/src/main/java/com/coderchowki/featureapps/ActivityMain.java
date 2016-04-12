package com.coderchowki.featureapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

public class ActivityMain extends AppCompatActivity {

    FirebaseListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list_message = (ListView) findViewById(R.id.list_message);
        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://woodroom.firebaseio.com/msg");

        mAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, android.R.layout.two_line_list_item, ref) {
            @Override
            protected void populateView(View view, ChatMessage chatMessage, int position) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(chatMessage.getName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(chatMessage.getMessage());

            }
        };
        list_message.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
