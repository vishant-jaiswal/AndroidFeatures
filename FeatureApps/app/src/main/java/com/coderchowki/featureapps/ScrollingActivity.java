package com.coderchowki.featureapps;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    CollapsingToolbarLayout collapsingToolbarLayout;
    View cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(this);

        cardView = findViewById(R.id.card_view);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Funds");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.childDrawableStateChanged(cardView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        State state = null;
        if (verticalOffset == 0) {
            if (state != State.EXPANDED) {
                Toast.makeText(ScrollingActivity.this, "is expanding", Toast.LENGTH_SHORT).show();
                cardView.setVisibility(View.VISIBLE);
            }
            state = State.EXPANDED;
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (state != State.COLLAPSED) {
                Toast.makeText(ScrollingActivity.this, "is collapsing", Toast.LENGTH_SHORT).show();
                cardView.setVisibility(View.GONE);
            }
            state = State.COLLAPSED;
        } else {
            if (state != State.IDLE) {
                Toast.makeText(ScrollingActivity.this, "is idle", Toast.LENGTH_SHORT).show();
            }
            state = State.IDLE;
        }
    }
}
