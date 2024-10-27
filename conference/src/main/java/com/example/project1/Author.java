package com.example.project1;

public class Author extends User {
    
    private String name;
    private String address;
    private String FieldOfStudy;
    public int paperID;

    public Author(String name, String address, String FieldOfStudy, int paperID) {
        this.name = name;
        this.address = address;
        this.FieldOfStudy = FieldOfStudy;
        this.paperID = paperID;
    }

    Author() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Author(String text, String text0, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    

}
