package com.coderchowki.featureapps;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity implements MyDialogFragment.UserNameListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFinishUserDialog(String user) {
        Toast.makeText(this, "Hello, " + user, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        // close existing dialog fragments
        FragmentManager manager = getSupportFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
        switch (view.getId()) {
            case R.id.showCustomFragment:
                MyDialogFragment editNameDialog = new MyDialogFragment();
                editNameDialog.show(manager, "fragment_edit_name");
                break;
            case R.id.showAlertDialogFragment:
                MyAlertDialogFragment alertDialogFragment = new MyAlertDialogFragment();
                alertDialogFragment.show(manager, "fragment_edit_name");
                break;
        }
    }


}
