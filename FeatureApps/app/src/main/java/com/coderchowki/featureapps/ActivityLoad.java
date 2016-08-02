package com.coderchowki.featureapps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ActivityLoad extends AppCompatActivity implements View.OnClickListener {

    Button mBtn_Load_Internal_Cache;
    Button mBtn_Load_External_Cache;
    Button mBtn_Load_External_Private;
    Button mBtn_Load_External_Public;
    Button mBtn_Load_Next;

    TextView mTv_File_Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        mBtn_Load_Internal_Cache = (Button) findViewById(R.id.bt_load_internal_cache);
        mBtn_Load_External_Cache = (Button) findViewById(R.id.bt_load_external_cache);
        mBtn_Load_External_Private = (Button) findViewById(R.id.bt_load_external_private);
        mBtn_Load_External_Public = (Button) findViewById(R.id.bt_load_external_public);
        mBtn_Load_Next = (Button) findViewById(R.id.bt_previous);

        mBtn_Load_Internal_Cache.setOnClickListener(this);
        mBtn_Load_External_Cache.setOnClickListener(this);
        mBtn_Load_External_Private.setOnClickListener(this);
        mBtn_Load_External_Public.setOnClickListener(this);
        mBtn_Load_Next.setOnClickListener(this);

        mTv_File_Content = (TextView) findViewById(R.id.tv_file_text);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt_load_internal_cache:
                loadInternalCache();
                break;
            case R.id.bt_load_external_cache:
                loadExternalCache();
                break;
            case R.id.bt_load_external_private:
                loadExternalPrivate();
                break;
            case R.id.bt_load_external_public:
                loadExternalPublic();
                break;
            case R.id.bt_previous:
                startActivityMain();
                break;
        }

    }

    private void loadExternalPublic() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder,"myfile4.txt");
        String data = loadData(myFile);
        if(data != null){
            mTv_File_Content.setText(data);
        }else {
            mTv_File_Content.setText("data not found");
        }


    }

    private void loadExternalPrivate() {
        File folder = getExternalFilesDir("Logs");
        File myFile = new File(folder,"myfile3.txt");
        String data = loadData(myFile);
        if(data != null){
            mTv_File_Content.setText(data);
        }else {
            mTv_File_Content.setText("data not found");
        }

    }

    private void loadExternalCache() {
        File folder = getExternalCacheDir();
        File myFile = new File(folder,"myfile2.txt");
        String data = loadData(myFile);
        if(data != null){
            mTv_File_Content.setText(data);
        }else {
            mTv_File_Content.setText("data not found");
        }
    }

    private void loadInternalCache() {
        File folder = getCacheDir();
        File myFile = new File(folder,"myfile1.txt");
        String data = loadData(myFile);
        if(data != null){
            mTv_File_Content.setText(data);
        }else {
            mTv_File_Content.setText("data not found");
        }


    }

    private String loadData(File myFile){
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            fileInputStream = new FileInputStream(myFile);
            int read = -1;
            while((read = fileInputStream.read()) != -1){
                stringBuffer.append((char)read);
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream!=null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void startActivityMain() {

        Intent intent = new Intent(this,ActivityMain.class);
        startActivity(intent);

    }
}
