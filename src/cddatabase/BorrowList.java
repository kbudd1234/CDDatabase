/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cddatabase;

import java.util.Objects;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kevinbudd
 */
public class BorrowList {
    private final StringProperty id = new SimpleStringProperty(this, "id", "0");
    private final StringProperty albumName = new SimpleStringProperty(this, "albumName", "");
    private final StringProperty borrowerName = new SimpleStringProperty(this, "borrowerName", "");
    private final StringProperty borrowDate = new SimpleStringProperty(this, "borrowDate", "");
    private final StringProperty dueDate = new SimpleStringProperty(this, "dueDate", "");

    public BorrowList() {
    }

    public BorrowList(BorrowList borrowList) {
        this.id.set(borrowList.getId());
        this.albumName.set(borrowList.getAlbumName());
        this.borrowerName.set(borrowList.getBorrowerName());
        this.borrowDate.set(borrowList.getBorrowDate());
        this.dueDate.set(borrowList.getDueDate());
        
    }
    
    public String getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(String value) {
        dueDate.set(value);
    }

    public StringProperty dueDateProperty() {
        return dueDate;
    }
    

    public String getBorrowDate() {
        return borrowDate.get();
    }

    public void setBorrowDate(String value) {
        borrowDate.set(value);
    }

    public StringProperty borrowDateProperty() {
        return borrowDate;
    }
    

    public String getBorrowerName() {
        return borrowerName.get();
    }

    public void setBorrowerName(String value) {
        borrowerName.set(value);
    }

    public StringProperty borrowerNameProperty() {
        return borrowerName;
    }
    

    public String getAlbumName() {
        return albumName.get();
    }

    public void setAlbumName(String value) {
        albumName.set(value);
    }

    public StringProperty albumNameProperty() {
        return albumName;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.albumName);
        hash = 17 * hash + Objects.hashCode(this.borrowerName);
        hash = 17 * hash + Objects.hashCode(this.borrowDate);
        hash = 17 * hash + Objects.hashCode(this.dueDate);
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
        final BorrowList other = (BorrowList) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.albumName, other.albumName)) {
            return false;
        }
        if (!Objects.equals(this.borrowerName, other.borrowerName)) {
            return false;
        }
        if (!Objects.equals(this.borrowDate, other.borrowDate)) {
            return false;
        }
        if (!Objects.equals(this.dueDate, other.dueDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BorrowList{" + "id=" + id + ", albumName=" + albumName + ", borrowerName=" + borrowerName + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate + '}';
    }

    
}
