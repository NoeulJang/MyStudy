package com.example.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity //엔티티 클래스 설정, 필수요소
//@Id 필수
//기본 생성자 필수. 이때, 기본 생성자는 public이나 protected 이여야함
//최상위 클래스여야 함
//final이면 안됨
@Table(name="test_user")    //매핑 테이블 지정
                            //name 속성 = 테이블 이름, 테이블명과 클래스파일명이 동일하면 생략 가능
                            //catalog 속성 = 카탈로그이름, 예, MySQL DB이름
                            //schema 속성 = 스키마 이름
public class User {

    //식별자 생성방식 4가지
    //1. 직접 생성 방식 - @Id 설정 대상에 직접 값 설정. 저장전에 생성자 할당, 보통 생성 시점에 전달.

    //2. 식별 칼럼 방식 - DB의 식별 칼럼에 매핑(ex, AutoIncrement 같은 자동 증가 컬럼), 기본 키 생성을 데이터베이스에 위임, 객체 생성시에 식별값을 설정하지 않음
    //                   @Id가 붙은 컬럼에 @GeneratedValue(strategy = GenerationType.IDENTITY)
    //                   Insert 쿼리를 실행해야 식별자를 알 수 있음. EntityManager.persist()호출 시점에 Insert 쿼리 실행, 이 때 객체에 식별자 값 할당됨
    //                   따라서, persist() 이후에 User.getEmail()같은 식별자 관련 메소드들 사용 가능

    //3. 시퀀스 사용 방식 - JPA가 식별자 생성 처리, 객체 생성시 식별값을 설정하지 않음
    //                     @SequenceGenerator로 시퀀스 생성기 설정, @GeneratedValue의 generator로 시퀀스 생성기 지정
    //                     EntityManager.persist() 호출 시점에 시퀀스 사용. persist() 실행할 때 객체에 식별자 값이 할당되며, Insert쿼리는 실행하지 않음
    //ex)
    //@Id
    //@SequenceGenerator(
    //  name = "log_seq_gen",
    //  sequenceName = "activity_seq",
    //  schema = "crm",
    //  allocationSize = 1)         <- 1이 아닌 다른 값으로 설정 시 시퀀스 값이 중복되는 경우가 있다고 함
    //@GeneratedValue(generator = "log_seq_gen")
    //private Long id;

    //4. 테이블 사용 방식 - 테이블을 시퀀스 처럼 사용함. 테이블에 엔티티를 위한 키를 보관하며 해당 테이블을 이용하여 다음 식별자 생성
    //                     @TableGenerator로 테이블 생성기 설정, @GeneratedValue의 generator로 테이블 생성기 지정
    //                     EntityManager.persist() 호출 시점에 테이블 사용. persist() 실행할 때 테이블을 이용하여 식별자를 구하고 이를 엔티티에 할당, Insert쿼리는 실행하지 않음

    @Id //식별자(pk) 속성에 설정, 필수
    private String email;
    public String getEmail() {
        return email;
    }

    private String name;
    public String getName() {
        return name;
    }

    //엔티티가 아닌 타입을 한개 이상의 필드와 매핑할 때 사용
    //즉 객체를 풀어서 컬럼값으로 넣을때, 사용한다.
    //같은 @Embeddable 타입 필드가 두개면 에러가 나므로 @AttributeOverride로 설정 재정의
    @Embedded
    private Address address;

    public Address getAddress() {
        return address;
    }

    @Column(name="create_date") //매핑할 컬럼명 지정, 지정하지 않으면 필드명/프로퍼티명 사용
    private LocalDateTime createDate;

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    protected User(){}

    public User(String email, String name, LocalDateTime createDate, Address address){
        this.email = email;
        this.name = name;
        this.createDate = createDate;
        this.address = address;
    }

}
