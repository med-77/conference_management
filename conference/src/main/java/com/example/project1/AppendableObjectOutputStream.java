package com.example.project1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class AppendableObjectOutputStream extends ObjectOutputStream {

public AppendableObjectOutputStream(FileOutputStream fos) throws IOException {
    super(fos);
}


    //public AppendableObjectOutputStream(FileOutputStream fos) {
    //}
    
}
