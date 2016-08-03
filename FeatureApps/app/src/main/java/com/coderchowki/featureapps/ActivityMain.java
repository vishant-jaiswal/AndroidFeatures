package com.coderchowki.featureapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.coderchowki.featureapps.realm_models.Student;
import com.coderchowki.featureapps.realm_models.Subject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;

public class ActivityMain extends AppCompatActivity implements RealmChangeListener , View.OnClickListener {

    EditText  mEt_Name;
    EditText mEt_Standard;
    EditText mEt_School;
    CheckBox mChk_English;
    CheckBox mChk_Hindi;
    CheckBox mChk_Math;
    CheckBox mChk_Science;

    Button mBtn_Save;

    RealmConfiguration configuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configuration = new RealmConfiguration.Builder(this)
                .name("realmdb.realm")
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .build();

        addSubjects();

        mEt_Name = (EditText) findViewById(R.id.et_name);
        mEt_School = (EditText) findViewById(R.id.et_school);
        mEt_Standard = (EditText) findViewById(R.id.et_standard);
        mChk_English = (CheckBox) findViewById(R.id.chk_english);
        mChk_Hindi = (CheckBox) findViewById(R.id.chk_hindi);
        mChk_Math = (CheckBox) findViewById(R.id.chk_math);
        mChk_Science = (CheckBox) findViewById(R.id.chk_science);
        mBtn_Save = (Button) findViewById(R.id.btn_save);
        mBtn_Save.setOnClickListener(this);


    }

    private void addSubjects() {
        /*Realm.setDefaultConfiguration(RealmApplication.getConfiguration());*/
        Realm realm = Realm.getInstance(configuration);
        Subject subject1 = new Subject("1","English","SBose");
        Subject subject2 = new Subject("2","Hindi","Nisha");
        Subject subject3 = new Subject("3","Math","Pradhan");
        Subject subject4 = new Subject("4","Science","Singh");
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(subject1);
        realm.copyToRealmOrUpdate(subject2);
        realm.copyToRealmOrUpdate(subject3);
        realm.copyToRealmOrUpdate(subject4);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void onChange() {

    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.btn_save:
                saveStudentToRealm();
                break;
        }

    }

    private void saveStudentToRealm() {
        Realm realm = Realm.getInstance(configuration);
        Student student = realm.createObject(Student.class);
        student.setName(mEt_Name.getText().toString());
        student.setSchool(mEt_School.getText().toString());
        student.setStandard(mEt_Standard.getText().toString());

        if(mChk_English.isChecked()){
            Subject result = realm.where(Subject.class)
                    .equalTo("Name", "English")
                    .findFirst();
            student.getSubjects().add(result);
        }

        if(mChk_Hindi.isChecked()){
            Subject result = realm.where(Subject.class)
                    .equalTo("Name", "Hindi")
                    .findFirst();
            student.getSubjects().add(result);
        }

        if(mChk_Math.isChecked()){
            Subject result = realm.where(Subject.class)
                    .equalTo("Name", "Math")
                    .findFirst();
            student.getSubjects().add(result);
        }

        if(mChk_Science.isChecked()){
            Subject result = realm.where(Subject.class)
                    .equalTo("Name", "Science")
                    .findFirst();
            student.getSubjects().add(result);
        }

        realm.beginTransaction();
        realm.copyToRealm(student);
        realm.commitTransaction();
        realm.close();



    }
}
