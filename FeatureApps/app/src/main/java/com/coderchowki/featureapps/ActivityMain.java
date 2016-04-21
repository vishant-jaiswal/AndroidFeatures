package com.coderchowki.featureapps;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ActivityMain extends AppCompatActivity implements MovieQuoteAdapter.Callback {

    MovieQuoteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEditDialog(null);
            }
        });

        mAdapter = new MovieQuoteAdapter(this,this);
        RecyclerView view = (RecyclerView) findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setHasFixedSize(true);
        view.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void showAddEditDialog(final MovieQuote movieQuote) {
        DialogFragment df = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(movieQuote == null ? R.string.dialog_add_title : R.string.dialog_edit_title));
                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add, null, false);
                builder.setView(view);
                final EditText quoteEditText = (EditText) view.findViewById(R.id.dialog_add_quote_text);
                final EditText movieEditText = (EditText) view.findViewById(R.id.dialog_add_movie_text);
                if (movieQuote != null) {
                    // pre-populate
                    quoteEditText.setText(movieQuote.getQuote());
                    movieEditText.setText(movieQuote.getMovie());

                    TextWatcher textWatcher = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            // empty
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            // empty
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String quote = quoteEditText.getText().toString();
                            String movie = movieEditText.getText().toString();
                            mAdapter.update(movieQuote, quote, movie);
                        }
                    };

                    quoteEditText.addTextChangedListener(textWatcher);
                    movieEditText.addTextChangedListener(textWatcher);
                }

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (movieQuote == null) {
                            String quote = quoteEditText.getText().toString();
                            String movie = movieEditText.getText().toString();
                            mAdapter.add(new MovieQuote(quote, movie));
                        }
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);

                return builder.create();
            }
        };
        df.show(getSupportFragmentManager(), "add");
    }

    @Override
    public void onEdit(MovieQuote movieQuote) {
        showAddEditDialog(movieQuote);
    }
}
