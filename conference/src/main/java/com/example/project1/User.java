package com.example.project1;

abstract class User {

    protected String id;
    protected String password;



    User () {id=password=null;}

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

    public void verifyLogin ()
    {

    }

    public void showAllConference ()
    {

    }

    public void showConference (Conference confoID)
    {

    }

}




