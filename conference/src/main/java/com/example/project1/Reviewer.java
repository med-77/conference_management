package com.example.project1;
public class Reviewer extends User {
    
    private String name;
    private String specialisedField;
    private int yearsOfReviewingExp;

    @Override
    public String toString() {
        return "Reviewer{" + "name=" + name + ", specialisedField=" + specialisedField + ", yearsOfReviewingExp=" + yearsOfReviewingExp + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialisedField() {
        return specialisedField;
    }

    public void setSpecialisedField(String specialisedField) {
        this.specialisedField = specialisedField;
    }

    public int getYearsOfReviewingExp() {
        return yearsOfReviewingExp;
    }

    public void setYearsOfReviewingExp(int yearsOfReviewingExp) {
        this.yearsOfReviewingExp = yearsOfReviewingExp;
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

    public Reviewer(String name, String specialisedField, int yearsOfReviewingExp) {
        this.name = name;
        this.specialisedField = specialisedField;
        this.yearsOfReviewingExp = yearsOfReviewingExp;
    }

    Reviewer(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Reviewer(String text, String text0, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void openPaper (){
        
    } 
    
    public void scoreAndCommentPaper (){
        
    }
    
    public void msgToOtherUser () {
        
    }
    
    public boolean acceptOrRejectPaper () {
        boolean paperStatus = false;
        
     return paperStatus;   
    }
}
