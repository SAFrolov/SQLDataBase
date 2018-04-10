/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlmybusa;

import java.sql.Date;

/**
 *
 * @author s.frolov
 */
public class person {
    private String id;
    private String name;
    private String surname;
    private String date;
    private String classPer;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getDate() {
        return date;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getClassPer() {
        return classPer;
    }
    public void setClassPer(String classPer) {
        this.classPer = classPer;
    }

    public person(String id, String name, String surname, String date, String classPer) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.classPer = classPer;
    }
    
}
