package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GrantedPermission {

    @Column(name = "perm")
    private String permission;
    public String getPermission() {
        return permission;
    }

    private String grantor;

    public String getGrantor() {
        return grantor;
    }

    protected GrantedPermission(){
    }

    public GrantedPermission(String permission, String grantor){
        this.permission = permission;
        this.grantor = grantor;
    }
}
