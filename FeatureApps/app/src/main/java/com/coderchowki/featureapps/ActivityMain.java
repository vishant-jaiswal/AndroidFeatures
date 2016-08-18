package com.coderchowki.featureapps;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.google.android.gms.maps.model.LatLng;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_go_to_place_name:
                getPlaceNameOrAddress();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // The LatLng class is part of GooglePlayServices, which you will add when you add a MapsActivity.
    @SuppressLint("ValidFragment")
    private void createMarker(final LatLng latLng) {
        DialogFragment df = new DialogFragment() {
            @NonNull
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.add_marker_title, latLng.latitude, latLng.longitude));
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_marker, null, false);
                final EditText editTextTitle = (EditText) view.findViewById(R.id.dialog_add_marker_edit_text_title);
                final EditText editTextSnippet = (EditText) view.findViewById(R.id.dialog_add_marker_edit_text_snippet);
                builder.setView(view);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = editTextTitle.getText().toString();
                        String snippet = editTextSnippet.getText().toString();

                        // TODO: Add a marker at that location.

                    }
                });
                return builder.create();
            }
        };
        df.show(getSupportFragmentManager(), "create_marker");
    }


    @SuppressLint("ValidFragment")
    private void getPlaceNameOrAddress() {
        DialogFragment df = new DialogFragment() {
            @NonNull
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.dialog_get_place_name_title);
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_get_place_name, null, false);
                final EditText editText = (EditText) view.findViewById(R.id.dialog_get_place_name_edit_text);
                final ToggleButton toggleButtonZoom = (ToggleButton) view.findViewById(R.id.dialog_get_place_name_toggle_button);
                builder.setView(view);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String placeName = editText.getText().toString();
                        float zoomLevel = toggleButtonZoom.isChecked() ? 17.0f : 7.0f;
                        goToPlace(placeName, zoomLevel);
                    }
                });
                return builder.create();
            }
        };
        df.show(getSupportFragmentManager(), "create_marker");
    }

    private void goToPlace(String locationName, float zoomLevel) {
        // TODO: find this place using the GeoCoder and go there.

    }
}
