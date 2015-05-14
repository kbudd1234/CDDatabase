/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cddatabase;

import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.genre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Artist other = (Artist) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Artist{" + "id=" + id + ", name=" + name + ", genre=" + genre + '}';
    }
    
    
    
}
