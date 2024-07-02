package org.example;

public enum Role {
    ADMIN(0),
    CUSTOMER(1),
    WORKER(2);

    private int roleNum;
    private Role(int num){
        this.roleNum = num;
    }
}
