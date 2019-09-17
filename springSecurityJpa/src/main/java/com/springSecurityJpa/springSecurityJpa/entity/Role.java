package com.springSecurityJpa.springSecurityJpa.entity;

public enum Role {
    USER("User"),SUPERVISOR("Supervisor"),PRACTICE_HEAD("Practice Head"),ADMIN("Admin");

    private String value;

    Role(String value){
        this.value=value;
    }

}
