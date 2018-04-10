/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlmybusa;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBased {

    Connection conn = null;


    
    
    
    public DataBased() { 
    }
    
    public void connectionBase(String URL, String Drive_name){
        try {
            Class.forName(Drive_name);
        } catch (ClassNotFoundException ex) {
            System.out.println("Don't have SQL driver!");
        }
    
        Enumeration<Driver> drvList = DriverManager.getDrivers();
        while (drvList.hasMoreElements()) {            
            System.out.println(drvList.nextElement());
        }
        

        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            System.out.println("Cannot connect to DataBased!");
            return;
        }
    } 
    public void createDataBase(String NameOfDataBase){
        try{
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE DATABASE " + NameOfDataBase); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }  
    public void chooseDataBase(String NameOfDataBase){
        try{
            Statement st = conn.createStatement();
            st.executeUpdate("USE " + NameOfDataBase); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void deleteTable(String NameOfTable){
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("DROP TABLE " + NameOfTable);      
            System.out.println("Delete");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    public void createTable(String NameOfTable){
        try {
            Statement st;
            st = conn.createStatement();        
            st.executeUpdate("CREATE TABLE " + NameOfTable +" (id int(11) NOT NULL AUTO_INCREMENT, name varchar(64), surname varchar(64), dateOfBirthday date NULL, class varchar(10), PRIMARY KEY (id))");
            System.out.println("create");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }    
    
    public ObservableList<person> seeInformationFromTable(String NameOfTable, String Information){
        
        ObservableList<person> persons = FXCollections.observableArrayList();
        
        try {
            Statement st;
            st = conn.createStatement();        
            ResultSet rs = st.executeQuery("SELECT" + Information + " FROM " + NameOfTable);
            System.out.println("found");
            while (rs.next()){
                person pr1=new person(rs.getString("id"), rs.getString("name"), rs.getString("surname"), rs.getString("dateOfBirthday"), rs.getString("class"));
                persons.add(pr1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }   
        return persons;
    }
    
    public ObservableList<person> findId(String NameOfTable, String Information, String ID){
        
        ObservableList<person> persons = FXCollections.observableArrayList();
        
         try {
            Statement st;
            st = conn.createStatement();        
            ResultSet rs = st.executeQuery("SELECT" + Information + " FROM " + NameOfTable + " WHERE " + ID);
            System.out.println("foundID");
            while (rs.next()){
                person pr1=new person(rs.getString("id"), rs.getString("name"), rs.getString("surname"), rs.getString("dateOfBirthday"), rs.getString("class"));
                persons.add(pr1);
                System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("surname") + " " + rs.getString("dateOfBirthday") + " " + rs.getString("class"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }         
         return persons;
    }
    
    public void addInformation(String NameOfTable, String AddInformation){
         try {
            Statement st;
            st = conn.createStatement();        
            st.executeUpdate("INSERT INTO " + NameOfTable + "(name,surname, dateOfBirthday, class) VALUES " + AddInformation);
            System.out.println("Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }         
    }
    }
    
