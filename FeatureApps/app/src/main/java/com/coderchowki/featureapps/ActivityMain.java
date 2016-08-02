package com.coderchowki.featureapps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener{

    Button mBtn_Internal_Cache;
    Button mBtn_External_Cache;
    Button mBtn_External_Private;
    Button mBtn_External_Public;
    Button mBtn_Next;

    EditText mEt_File_Content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn_Internal_Cache = (Button) findViewById(R.id.bt_internal_cache);
        mBtn_External_Cache = (Button)findViewById(R.id.bt_external_cache);
        mBtn_External_Private = (Button) findViewById(R.id.bt_external_private);
        mBtn_External_Public = (Button) findViewById(R.id.bt_external_public);
        mBtn_Next = (Button) findViewById(R.id.bt_next);

        mBtn_Internal_Cache.setOnClickListener(this);
        mBtn_External_Cache.setOnClickListener(this);
        mBtn_External_Private.setOnClickListener(this);
        mBtn_External_Public.setOnClickListener(this);
        mBtn_Next.setOnClickListener(this);

        mEt_File_Content = (EditText) findViewById(R.id.et_file_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_internal_cache:
                saveInternalCache();
                break;
            case R.id.bt_external_cache:
                saveExternalCache();
                break;
            case R.id.bt_external_private:
                saveExternalPrivate();
                break;
            case R.id.bt_external_public:
                saveExternalPublic();
                break;
            case R.id.bt_next:
                startActivityLoad();
                break;
        }
    }

    private void saveExternalPublic() {
        String data = mEt_File_Content.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder,"myfile4.txt");
        writeData(myFile,data);


    }

    private void saveExternalPrivate() {
        String data = mEt_File_Content.getText().toString();
        File folder = getExternalFilesDir("Logs");
        File myFile = new File(folder,"myfile3.txt");
        writeData(myFile,data);

    }

    private void saveExternalCache() {
        String data = mEt_File_Content.getText().toString();
        File folder = getExternalCacheDir();
        File myFile = new File(folder,"myfile2.txt");
        writeData(myFile,data);
    }

    private void saveInternalCache() {
        String data = mEt_File_Content.getText().toString();
        File folder = getCacheDir();
        File myFile = new File(folder,"myfile1.txt");
        writeData(myFile,data);

    }

    private void writeData(File myFile,String data){
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileOutputStream!=null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startActivityLoad() {

        Intent intent = new Intent(this,ActivityLoad.class);
        startActivity(intent);

    }
}
