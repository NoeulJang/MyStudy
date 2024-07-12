package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EntityMappingMemberShip {


    //연관 고려 사항
    //연관 대신에 id 값으로 참조 고려
    //조회는 전용쿼리나 구현 사용 고려(CQRS)
    //엔티티가 아닌 벨류인지 확인
    //1-N 보다는 N-1
    //양방향사용은 지양

    @Id
    private String number;


    //Join때 사용할 컬럼을 @JoinColumn으로 지정 하며, 객체는 조인하는 테이블의 엔티티로 한다.
    
    //1:1 단방향 매핑
    @OneToOne
    //식별자 공유 방식일 경우, 즉, pk가 같을 경우
    //PrimaryKeyJoinColumn(name = "user_email")

    //N:1 단방향 매핑 (게시글에 달린 댓글들은 게시글의 아이디를 참조한다.)
    //@ManyToOne

    //1:N 단방향 매핑
    //@OneToMany
    //객체 타입에 Set<EntityMappingUser> owner = new HashSet<>(); 으로 컬렉션 타입 설정
    //List의 경우 @OrderColumn 설정 필수.
    //Map의 경우 @MapKeyColumn 설정 필수.

    //영속성 전파
    //  EX) EntityMappingUser 객체를 두개 생성하여 Set에 담아 MemberShip을 persist할 경우, error발생.
    //      이러할 때, @OneToMany(cascade = CascadeType.PERSIST)속성을 이용하면 에러 없이 저장
    //      ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH 등 여러가지가 있음
    //      단, 특수한 이유가 없으면 사용하지 않는것을 추천.


    @JoinColumn(name = "user_email")
    private EntityMappingUser owner;

    public String getNumber() {
        return number;
    }

    public EntityMappingUser getOwner() {
        return owner;
    }

    public EntityMappingMemberShip() {
    }

    public EntityMappingMemberShip(String number, EntityMappingUser owner) {
        this.number = number;
        this.owner = owner;
    }

}
