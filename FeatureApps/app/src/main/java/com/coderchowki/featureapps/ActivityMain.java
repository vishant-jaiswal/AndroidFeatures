package com.coderchowki.featureapps;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity {

    private static final String TAG = "SMSRead";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = new TextView(this);
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null,null);
        String sms = "";
        while (cur.moveToNext()) {
            sms += "From :" + cur.getString(2) + " : " + cur.getString(11)+"\n";
            Log.i(TAG,""+cur.getString(1)+":"
                    +cur.getString(2)+":"
                    +cur.getString(3)+":"
                    +cur.getString(4)+":"
                    +cur.getString(5)+":"
                    +cur.getString(6)+":"
                    +cur.getString(7)+":"
                    +cur.getString(8)+":"
                    +cur.getString(9)+":"
                    +cur.getString(10)+":"
                    +cur.getString(11)
                    +cur.getString(cur.getColumnIndexOrThrow("body"))+":"
                    +cur.getString(cur.getColumnIndexOrThrow("address"))+"\n");
        }
        view.setText(sms);
        setContentView(view);
    }
}
