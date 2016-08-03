package com.coderchowki.featureapps.realm_models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Vishant on 16-04-2016.
 */
public class Student extends RealmObject {



    private String Name;
    private String Standard;
    private String school;
    private RealmList<Subject> subjects;




    public void setStandard(String standard) {
        Standard = standard;
    }


    public String getStandard() {
        return Standard;
    }

    public RealmList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(RealmList<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student() {
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

}
