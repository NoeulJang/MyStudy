package com.example.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="test_role") 
public class Role {
    @Id
    private String id;
    public String getId() {
        return id;
    }

    private String name;

    public String getName() {
        return name;
    }

    //Set매핑


    //@ElementCollection(fetch = FetchType.EAGER)   -- 이거 라고 읽음ㅋ
    //해당 방식으로 선언 시 Role 데이터를 읽어올 때 조인으로 role_perm테이블을 한번에 읽어옴, 다만 추천하지 않음
    //fetch = lazy 기본값
    @ElementCollection
    @CollectionTable(
        name = "role_perm",
        joinColumns = @JoinColumn(name = "role_id")
    )
    @Column(name = "perm")
    private Set<String> permissions = new HashSet<>();

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void revokeAll(){
        this.permissions.clear();
    }

    
    //List매핑
    
    //set과 비슷하지만 OrderColumn이라는 인덱스 지정이 필요한것과 따로 도메인 클래스를 만들지 않는다는 것이 차이점이다.
    //LAZY와 EAGER 방식의 동장 방식도 같다.
    @ElementCollection
    @CollectionTable(
        name = "question_choice",
        joinColumns = @JoinColumn(name = "question_id")
    )
    @OrderColumn(name = "idx")

    //단순 값 List매핑 방식
    // @Column(name = "text")
    // private List<String> choices;
    
    //Embeddable 방식
    private List<Choice> choices;




    //Map매핑
    
    //set과 비슷하지만 OrderColumn이라는 인덱스 지정이 필요한것과 따로 도메인 클래스를 만들지 않는다는 것이 차이점이다.
    //LAZY와 EAGER 방식의 동장 방식도 같다.
    @ElementCollection
    @CollectionTable(
        name = "doc_prop",
        joinColumns = @JoinColumn(name = "doc_id")
    )
    @MapKeyColumn(name = "name")
    
    //단순 값 매핑 방식
    // @Column(name = "value")
    // private Map<String, String> props = new HashMap<>();
    
    //Embeddable 방식
    private Map<String, PropValue> props = new HashMap<>();



    //단순 값 매핑 방식
    // public Role(String id, String name, Set<String> permissions, List<String> choices, Map<String, String> props){
    //     this.id = id;
    //     this.name = name;
    //     this.permissions = permissions;
    //     this.choices = choices;
    //     this.props = props;
    // }

    //Embeddable 방식
    public Role(String id, String name, Set<String> permissions, List<Choice> choices, Map<String, PropValue> props){
        this.id = id;
        this.name = name;
        this.permissions = permissions;
        this.choices = choices;
        this.props = props;
    }

    public Role(){}

}
