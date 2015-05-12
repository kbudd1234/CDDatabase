/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cddatabase;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author kevinbudd
 */
public class CDDataBaseFXMLController implements Initializable {
    
    @FXML
    private Button btnConnect;
    @FXML
    private TextField txtConnection;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtArtistName;
    @FXML
    private TextField txtArtistGenre;
    @FXML
    private TextField txtAlbum;
    @FXML
    private TextField txtAlbumConsoleArtist;
    @FXML
    private TextField txtReleaseDate;
    @FXML
    private TableView<String> tableviewArtist;
    @FXML
    private TableView<String> tableviewAlbum;
    @FXML
    private TableView<String> tableviewBorrower;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;
    
    private ObservableList artistData;
    private ObservableList albumData;
    private ObservableList borrowerData;
    private ObservableList borrowListData;
    
    private ObservableList artistNamesData;
    private ObservableList albumNamesData;
    private ObservableList borrowerNameData;
    
    private static Connection connection;
    private String connectionString;
    private String username;
    private String password;
    @FXML
    private Button btnArtist;
    @FXML
    private Button btnAddNewArtist;
    @FXML
    private Button btnAlbum;
    @FXML
    private Button btnAddNewAlbum;
    @FXML
    private Button btnBorrower;
    @FXML
    private Button btnAddNewBorrower;
    @FXML
    private Tab mnuBorrowList;
    @FXML
    private ListView<String> lstArtist;
    
    private String selectedArtist;
    private String selectedAlbum;
    private String selectedBorrower;
    
    @FXML
    private ListView<String> lstAlbums;
    @FXML
    private Button btnBorrow;
    @FXML
    private TableView<String> tableviewBorrowList;
    @FXML
    private ListView<String> lstBorrowerName;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CDDataBaseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lstArtist.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            selectedArtist = newValue;
            System.out.println(selectedArtist);
            
            try {
                populateAlbumListview(selectedArtist);
            } catch (SQLException ex) {
                Logger.getLogger(CDDataBaseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        });
        
        lstAlbums.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            selectedAlbum = newValue;
            System.out.println(selectedAlbum);
            
        });
        
        lstBorrowerName.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            selectedBorrower = newValue;
            System.out.println(selectedBorrower);
            
        });
        
    }
    
    public void populateAlbumListview(String artist) throws SQLException{
        
        albumNamesData = FXCollections.observableArrayList();
        
        Statement statement;

        statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select AlbumName from Album where Artist = '" + artist + "';");
        
        while (rs.next()) {
            albumNamesData.add(rs.getString("AlbumName"));
        }
        
        lstAlbums.setItems(albumNamesData);
        
    }
    
    public void buildData(String sql, ObservableList data, TableView<String> tableview) {
          
        try{
            
            ResultSet rs = connection.createStatement().executeQuery(sql);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                tableview.getColumns().addAll(col); 
                
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
            
          }catch(Exception e){
              System.out.println("Error on Building Data");             
          }
      }

    @FXML
    private void btnConnect_Click(ActionEvent event) throws SQLException {
        
        if("Connect".equals(btnConnect.getText()))
        {
            connectionString = txtConnection.getText();
            username = txtUserName.getText();
            password = txtPassword.getText();

            try{
            connection = DriverManager.getConnection(connectionString , username, password);
            
            btnConnect.setText("Disconnect");
            } 
            catch (Exception ex) {
                System.out.println(ex);
            }

        }
        else
        {
            connection.close();
            btnConnect.setText("Connect");
        }
        
    
    }
    

    @FXML
    private void btnArtist_Click(ActionEvent event) throws SQLException {
        
        artistData = FXCollections.observableArrayList();
       
        Statement statement = connection.createStatement();
        
        String SQL = "select * from Artist";

        buildData(SQL, artistData, tableviewArtist);

        }

    @FXML
    private void btnAlbum_Click(ActionEvent event) throws SQLException {
        
        albumData = FXCollections.observableArrayList();
       
        Statement statement = connection.createStatement();
        
        String SQL = "select * from Album";

        buildData(SQL, albumData, tableviewAlbum);

    }

    @FXML
    private void btnBorrower_Click(ActionEvent event) throws SQLException {
        
        borrowerData = FXCollections.observableArrayList();
       
        Statement statement = connection.createStatement();
        
        String SQL = "select * from Borrower";

        buildData(SQL, borrowerData, tableviewBorrower);
    }


    @FXML
    private void btnAddNewArtist_Click(ActionEvent event) throws SQLException {
        
        // Create a statement
        Statement statement = connection.createStatement();

        // Execute an insert statement
        statement.execute("INSERT INTO Artist(id, Name, Genre) VALUES (NULL, '" + txtArtistName.getText() 
            + "', '" + txtArtistGenre.getText() + "');");
        
        // Refresh tableview with new data
        artistData.removeAll(artistData);
        String SQL = "select * from Artist";
        buildData(SQL, artistData, tableviewArtist);
        
    }

    @FXML
    private void btnAddNewAlbum_Click(ActionEvent event) throws SQLException {
        
        // Create a statement
        Statement statement = connection.createStatement();
        
        // Execute an insert statement
        statement.execute("INSERT INTO Album(id, AlbumName, Artist, ReleaseDate, Status) VALUES (NULL, '" +
                txtAlbum.getText() + "', '" + txtAlbumConsoleArtist.getText() + "', '" + txtReleaseDate.getText() + "', DEFAULT);");
        
        // Refresh tableview
        albumData.removeAll(albumData);
        String SQL = "select * from Album";
        buildData(SQL, albumData, tableviewAlbum);
        
    }

    @FXML
    private void btnAddNewBorrower_Click(ActionEvent event) throws SQLException {
        
        // Create a statement
        Statement statement = connection.createStatement();
        
        // Execute an insert statement
        statement.execute("INSERT INTO Borrower(id, Name, Address, Phone) VALUES (NULL, '" +
                txtName.getText() + "', '" + txtAddress.getText() + "', '" + txtPhone.getText() + "');");
        
        // Refresh tableview
        borrowerData.removeAll(borrowerData);
        String SQL = "select * from Borrower";
        buildData(SQL, borrowerData, tableviewBorrower);
    }

    @FXML
    private void mnuBorrowList_Clicked(Event event) throws SQLException {
        
        borrowListData = FXCollections.observableArrayList();
       
        Statement statement = connection.createStatement();
        
        String SQL = "select * from BorrowList";

        buildData(SQL, borrowListData, tableviewBorrowList);
        
        artistNamesData = FXCollections.observableArrayList();
        
        Statement st;

        st = connection.createStatement();

        ResultSet rs = st.executeQuery("select Name from Artist");
        
        while (rs.next()) {
            artistNamesData.add(rs.getString("Name"));
        }
        
        lstArtist.setItems(artistNamesData);
        
        borrowerNameData = FXCollections.observableArrayList();
        
        Statement state;
        
        state = connection.createStatement();
        
        ResultSet results = state.executeQuery("select Name from Borrower");
        
        while (results.next()) {
            borrowerNameData.add(results.getString("Name"));
        }
        
        lstBorrowerName.setItems(borrowerNameData);
    }

    @FXML
    private void btnBorrow_Click(ActionEvent event) throws SQLException {
        
        // Create a statement
        Statement statement = connection.createStatement();

        // Execute an insert statement
        statement.execute("INSERT INTO BorrowList(id, AlbumName, BorrowerName, BorrowDate, DueDate) VALUES" +
                "(NULL, '" + selectedAlbum  + "', '" + selectedBorrower + "', '2015-05-12', '2015-05-26');");
        
        // Refresh tableview with new data
        borrowListData.removeAll(borrowListData);
        String SQL = "select * from BorrowList";
        buildData(SQL, borrowListData, tableviewBorrowList);
        
    }
    
}
