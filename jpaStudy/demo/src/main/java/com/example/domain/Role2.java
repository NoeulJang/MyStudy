package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="test_role") 
public class Role2 {
    @Id
    private String id;
    public String getId() {
        return id;
    }

    private String name;
    public String getName() {
        return name;
    }

    //@ElementCollection(fetch = FetchType.EAGER)   -- 이거 라고 읽음ㅋ
    //해당 방식으로 선언 시 Role 데이터를 읽어올 때 조인으로 role_perm테이블을 한번에 읽어옴, 다만 추천하지 않음
    //fetch = lazy 기본값
    @ElementCollection
    @CollectionTable(
        name = "role_perm",
        joinColumns = @JoinColumn(name = "role_id")
    )
    private Set<GrantedPermission> permissions = new HashSet<>();
    public Set<GrantedPermission> getPermissions() {
        return permissions;
    }

    public Role2(){}

    public Role2(String id, String name, Set<GrantedPermission> permissions){
        this.id = id;
        this.name = name;
        this.permissions = permissions;

    }
}
