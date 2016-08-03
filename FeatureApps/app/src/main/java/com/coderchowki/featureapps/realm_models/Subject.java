package com.coderchowki.featureapps.realm_models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vishant on 03-08-2016.
 */
public class Subject extends RealmObject {
    @PrimaryKey
    private String Id;
    private String Name;
    private String TeacherName;

    public Subject(String id, String name, String teacherName) {
        Id = id;
        Name = name;
        TeacherName = teacherName;
    }

    public Subject() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }


}
