/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlmybusa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author s.frolov
 */
public class DataBaseViewController implements Initializable {
    public static final String DRIVER_NAME="com.mysql.jdbc.Driver";
    public static String CONN_STRING = "jdbc:mysql://localhost:3306/?user=root&password=root&useSSL=false";
    public static String NAMEOFDATABASE = "students";
    public static String NAMEOFTABLE = "persons";
    
    DataBased newBasa = new DataBased(); 
    

    @FXML private TextField nameFind;
    @FXML private TextField surnameFind;
    @FXML private TextField dateFind;
    @FXML private TextField classFind;
    @FXML private TextField idFinder;
    
    @FXML private TableView<person> tablePerson;
    @FXML private TableColumn<person,String> idColl;
    @FXML private TableColumn<person,String> nameColl;
    @FXML private TableColumn<person,String> surnameCall;
    @FXML private TableColumn<person,String> dateColl;
    @FXML private TableColumn<person,String> classColl;

        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColl.setCellValueFactory(new PropertyValueFactory<person, String>("id"));        
        nameColl.setCellValueFactory(new PropertyValueFactory<person, String>("name"));        
        surnameCall.setCellValueFactory(new PropertyValueFactory<person, String>("surname"));               
        dateColl.setCellValueFactory(new PropertyValueFactory<person, String>("date"));        
        classColl.setCellValueFactory(new PropertyValueFactory<person, String>("classPer"));
    }      
    
    @FXML
    private void btnConnect(ActionEvent event){
        newBasa.connectionBase(CONN_STRING, DRIVER_NAME);
        newBasa.createDataBase(NAMEOFDATABASE);
        newBasa.chooseDataBase(NAMEOFDATABASE);
        System.out.println("Connected!"); 
    }

    @FXML
    private void btnCreat(ActionEvent event){
        newBasa.createTable(NAMEOFTABLE);
        System.out.println("Created!"); 
    }    
    
    @FXML
    private void btnDel(ActionEvent event){
        newBasa.deleteTable(NAMEOFTABLE);
        System.out.println("Deleted!"); 
    }       
    
    @FXML
    private void btnAdd(ActionEvent event){
        String ADDINFORMATION = "('"+ nameFind.getText() + "', '" + surnameFind.getText() + "', '" + dateFind.getText() + "', '" + classFind.getText() + "')";
        newBasa.addInformation(NAMEOFTABLE,ADDINFORMATION);
    }
    
    @FXML
    private void btnSee(ActionEvent event){
        
        ObservableList<person> persons = FXCollections.observableArrayList();
        persons=newBasa.seeInformationFromTable(NAMEOFTABLE,"*");
        tablePerson.setItems(persons);
        
    }
    
    @FXML
    private void findId (ActionEvent event){
    ObservableList<person> persons = FXCollections.observableArrayList();
        String ID = "id = " + idFinder.getText();
        persons = newBasa.findId(NAMEOFTABLE,"*", ID);  
        tablePerson.setItems(persons);
    }
    
  
}
