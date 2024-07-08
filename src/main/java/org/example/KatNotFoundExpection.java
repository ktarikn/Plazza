package org.example;

public class KatNotFoundExpection extends Exception{
    public KatNotFoundExpection(String errorMessage){
        super(errorMessage);
    }
    public KatNotFoundExpection(){
        super("Kat not found");
    }
    public String getMessage(){
        return "The Kat you searched for does not exist, if it where to exist it would either be flying or have dirt between the previous floor";

    }
    public String getMessageSimplified(){
        return "yourKat < minimumKat-1 or yourKat > maximumkat +1";
    }

}
