/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cddatabase;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kevinbudd
 */
public class Artist {
    
    private final StringProperty id = new SimpleStringProperty(this, "id", "0");
    private final StringProperty name = new SimpleStringProperty(this, "name", "name");
    private final StringProperty genre = new SimpleStringProperty(this, "genre", "genre");
    
    public Artist() {
        
    }
    
    public Artist(Artist artist){
        this.id.set(artist.getId());
        this.name.set(artist.getName());
        this.genre.set(artist.getGenre());
    }
    

    public String getId() {
        return id.get();
    }

    public void setId(String value) {
        id.set(value);
    }

    public StringProperty idProperty() {
        return id;
    }
    
     public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    
    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String value) {
        genre.set(value);
    }

    public StringProperty genreProperty() {
        return genre;
    }
    
}
