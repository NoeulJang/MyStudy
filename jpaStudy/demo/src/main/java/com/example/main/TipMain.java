package com.example.main;

import com.example.domain.Notice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TipMain {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            System.out.println("=================");
            
            Notice newNotice = new Notice("title","content",true);
            
            em.persist(newNotice);

            Notice notice = em.find(Notice.class, 1);

            System.out.println("notice :: " + notice.toString());

            System.out.println("=================");
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

}
