package com.coderchowki.featureapps;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vishant on 16-04-2016.
 */
public class DatabaseModel extends RealmObject {


    @PrimaryKey
    private String Id;
    private String Name;
    private String school;

    public DatabaseModel() {
    }

    public DatabaseModel(String id, String name, String school) {
        Id = id;
        Name = name;
        this.school = school;
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
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
