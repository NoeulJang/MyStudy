package com.example.domain;

import com.example.main.BooleanYesNoConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//변경된 칼럼만 UPDATE쿼리에 포함
//@DynamicInsert

//null이 아닌 칼러만 Insert 쿼리에 포함
//Default값이 있는 경우는 null로 넣어도 Default값이 들어감.
//@DynamicUpdate

//변경 추적 대상에서 제외 처리
//메모리 사용 감소, 조회 목적으로만 사용되는 엔티티 매핑에 사용된다.
//@Immutable

//select 결과를 엔티티로 매핑하고 싶을 때(With랑 비슷)
//조회목적이므로 Immutable과 함께 사용함
//@Subselect(""" select a.notice_id, a.title from Notice a """)

public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;
    public Long getId() {
        return id;
    }

    //SQL을 이용한 속성 매핑
    //주로 DB 함수 호출이나 서브 쿼리 결과를 매핑하며, 조회에서만 매핑기능이 동작한다.
    //@Formula("(select c.subTitle from category c where c.cat_id = cat)")
    private String title;
    public String getTitle() {
        return title;
    }

    private String content;

    public String getContent() {
        return content;
    }

    @Column(name = "open_yn")
    @Convert(converter = BooleanYesNoConverter.class)
    private boolean opened;

    @Override
    public String toString() {
        return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", opened=" + opened + "]";
    }

    public Notice(String title, String content, boolean opened) {
        this.title = title;
        this.content = content;
        this.opened = opened;
    }

    public Notice() {
    }
}
