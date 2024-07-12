package com.example.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.domain.Choice;
import com.example.domain.PropValue;
import com.example.domain.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CrudHardMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            //저장
            saveRole(em);
            
            //조회
            Role role = findRole(em);

            //아래 for 문 실행 시 select 문 실행 -> lazy 로딩 방식 조회
            // EAGER 방식 조회는 Role.java에 설명
            for(String perm : role.getPermissions()){
                System.out.println("perm?? : " + perm);
            }

            //안됨
            // role_perm 추가
            // role.getPermissions().add("F3");
            // role_perm 제거
            // role.getPermissions().remove("F1");
            // role_perm 제거후 새로 추가
            //role.setPermissions(Set.of("F4", "F5"));
            //안됨
            // role_perm clear
            //role.revokeAll();



            //System.out.println("delete!!! ");
            //em.remove(role);
            
            transaction.commit();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            transaction.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }

    //저장
    public static void saveRole(EntityManager em) throws Exception{

        List<Choice> choiceList = new ArrayList<Choice>();
        choiceList.add(new Choice("AAA"));
        choiceList.add(new Choice("BBB"));

        
        //단순 값 매핑 방식
        // Map<String, String> props = new HashMap<>();
        // props.put("p1", "v1");
        // props.put("p2", "v2");
        //Role role = new Role("ROLE1", "user1", Set.of("F1", "F2"), List.of("보기1", "보기2"), props);
        
        //embeddable 방식
        Map<String, PropValue> props = new HashMap<>();
        props.put("p1", new PropValue("pv1"));
        props.put("p2", new PropValue("pv2"));
        Role role = new Role("ROLE1", "user1", Set.of("F1", "F2"), choiceList, props);
        
        em.persist(role);
    }
    
    //조회
    public static Role findRole(EntityManager em) throws Exception{
        Role role = em.find(Role.class, "ROLE1");
        return role;
    }

}
