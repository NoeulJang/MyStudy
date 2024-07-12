package com.example.main;

import java.time.LocalDateTime;

import com.example.domain.Address;
import com.example.domain.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CrudMain {
    public static void main1(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            //저장
            saveNewUser(em, "user@naver.com");
            saveNewUser(em, "user11@naver.com");
            //조회
            User user = getUserService(em, "user11@naver.com");
            if(user == null){
                System.out.println("해당 유저는 없습니다.");
            }else{
                System.out.println("해당 유저의 이메일은 " + user.getEmail() + " 입니다.");
            }

            removeUser(em, user);

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
    public static void saveNewUser(EntityManager em, String email) throws Exception{
        Address addr = new Address("주소1", "주소2", "12345");
        User user = new User(email, "user", LocalDateTime.now(), addr);
        em.persist(user);
    }

    //조회
    public static User getUserService(EntityManager em, String email) throws Exception{
        //엔티티 타입, 식별자(PK)의 타입이 맞아야 익셉션이 발생하지 않음
        User findUser = em.find(User.class, email);
        return findUser;
    }

     //삭제
    public static void removeUser(EntityManager em, User user) throws Exception{
        em.remove(user);
    }
}
