package com.example.project1;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class Admin extends User implements CanManageUserProfile {
    
    public void addUser ()
    {
      
    }
   
    @Override
    public void removeUser ()
    {
        
    }

    
    public void showAllConference () { }
    
    public void generateCountryReport () { }
    
    public void generateTopicReport () { }

    public void adduser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
