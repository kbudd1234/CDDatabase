/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cddatabase;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevinbudd
 */
public class CDDataBaseFXMLController implements Initializable {
    
    @FXML
    private Button btnArtist;
    @FXML
    private Button btnAlbum;
    @FXML
    private Button btnBorrower;
    @FXML
    private Button btnBorrowList;
    @FXML
    private Button btnConnect;
    @FXML
    private TextField txtConnection;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    
    private String connectionString;
    private String username;
    private String password;
    
    private static Connection connection;  
    @FXML
    private TextArea txtArtistConsole;
    @FXML
    private TextArea txtAlbumConsole;
    @FXML
    private TextArea txtBorrowerConsole;
    @FXML
    private TextArea txtBorrowListConsole;
    @FXML
    private TextField txtArtistName;
    @FXML
    private TextField txtArtistGenre;
    @FXML
    private Button btnAddNewArtist;
    @FXML
    private TextField txtAlbum;
    @FXML
    private TextField txtAlbumConsoleArtist;
    @FXML
    private TextField txtReleaseDate;
    @FXML
    private Button btnAddNewAlbum;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CDDataBaseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //txtArtistConsole.setText("Driver Loaded\n");
    }    

    @FXML
    private void btnConnect_Click(ActionEvent event) throws SQLException {
        
        if("Connect".equals(btnConnect.getText()))
        {
            connectionString = txtConnection.getText();
            username = txtUserName.getText();
            password = txtPassword.getText();

                // Connect to a database
            try{
            connection = DriverManager.getConnection(connectionString , username, password);
            //txtConsole.appendText("Database Connected\n");
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
        
        // Create a statement
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery
          ("select * from Artist");

        txtArtistConsole.clear();
        
        // Iterate through the result and print the artists
        while (resultSet.next())
          txtArtistConsole.appendText(resultSet.getString(1) + "\t" +
            resultSet.getString(2) + "\t" + resultSet.getString(3) + "\n");


        }

    @FXML
    private void btnAlbum_Click(ActionEvent event) throws SQLException {
        
        // Create a statement
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery
          ("select * from Album");

        txtAlbumConsole.clear();
        
        // Iterate through the result and print the albums
        while (resultSet.next())
          txtAlbumConsole.appendText(resultSet.getString(1) + "\t" +
                                resultSet.getString(2) + "\t" + 
                                resultSet.getString(3) + "\t" +
                                resultSet.getString(4) + "\t" +
                                resultSet.getString(5) + "\n");
                  
                  
    }

    @FXML
    private void btnBorrower_Click(ActionEvent event) throws SQLException {
        
        // Create a statement
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery
          ("select * from Borrower");
        
        txtBorrowerConsole.clear();

        // Iterate through the result and print the borrowers
        while (resultSet.next())
          txtBorrowerConsole.appendText(resultSet.getString(1) + "\t" +
                                resultSet.getString(2) + "\t" + 
                                resultSet.getString(3) + "\t" +
                                resultSet.getString(4) + "\n");
    }

    @FXML
    private void btnBorrowList_Click(ActionEvent event) {
    }

    @FXML
    private void btnAddNewArtist_Click(ActionEvent event) throws SQLException {
        
        // Create a statement
        Statement statement = connection.createStatement();

        // Execute an insert statement
        statement.execute("INSERT INTO Artist(id, Name, Genre) VALUES (NULL, '" + txtArtistName.getText() 
            + "', '" + txtArtistGenre.getText() + "');");
    }

    @FXML
    private void btnAddNewAlbum_Click(ActionEvent event) throws SQLException {
        
        // Create a statement
        Statement statement = connection.createStatement();
        
        //Execute an insert statement
        statement.execute("INSERT INTO Album(id, AlbumName, Artist, ReleaseDate, Status) VALUES (NULL, '" +
                txtAlbum.getText() + "', '" + txtAlbumConsoleArtist.getText() + "', '" + txtReleaseDate.getText() + "', DEFAULT);");
        
    }
    
}
