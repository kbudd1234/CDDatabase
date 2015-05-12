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
    private final StringProperty artist = new SimpleStringProperty(this, "artist", "name");
    private final StringProperty genre = new SimpleStringProperty(this, "genre", "genre");
    
    public Artist() {
        
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
    
     public String getArtist() {
        return artist.get();
    }

    public void setArtist(String value) {
        artist.set(value);
    }

    public StringProperty artistProperty() {
        return artist;
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
