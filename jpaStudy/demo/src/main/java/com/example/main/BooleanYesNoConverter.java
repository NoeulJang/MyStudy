package com.example.main;

import java.util.Objects;

import jakarta.persistence.AttributeConverter;

//AttributeConverter(타입 변환기)
//예를 들어 서버에서 true값 일 때 DB에 "Y"라는 문자열로 저장하고 싶을 때 사용
public class BooleanYesNoConverter implements AttributeConverter<Boolean, String>{

    //Entity에서 DB에 넣을 때 값 지정
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return Objects.equals(Boolean.TRUE, attribute) ? "Y" : "N";
    }

    //DB에서 Entity로 불러올 때 값 지정
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData) ? true : false;
    }

}