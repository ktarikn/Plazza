package org.example;

public class KatNotFoundExpection extends Exception{
    public KatNotFoundExpection(String errorMessage){
        super(errorMessage);
    }
    public KatNotFoundExpection(){
        super("Kat not found");
    }

}
