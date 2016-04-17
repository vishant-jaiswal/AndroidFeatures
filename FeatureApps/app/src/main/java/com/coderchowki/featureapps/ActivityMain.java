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
            sms += "From :" + cur.getString(2) + " : " + cur.getString(2)+"\n";
            Log.i(TAG,""+cur.getString(cur.getColumnIndexOrThrow("person"))+"<-person:"
                    +cur.getString(cur.getColumnIndexOrThrow("type"))+"<-type:"
                    +cur.getString(cur.getColumnIndexOrThrow("subject"))+"<-subject:"
                    +cur.getString(cur.getColumnIndexOrThrow("body"))+"<-body:"
                    +cur.getString(cur.getColumnIndexOrThrow("address"))+"\n");
        }
        view.setText(sms);
        setContentView(view);
    }
}
//TODO: following are the possible column value for SMS
//_id , thread_id, address, person, date, protocol, read, status, type, reply_path_present, subject, body, service_center, locked, error_code, seen