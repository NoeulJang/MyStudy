package com.example.main;


import java.util.List;

import com.example.domain.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class JPQLMain {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            
            //테스트 데이터 20개 입력
            newReview(em, transaction);

            transaction.begin();
            
            //JPQL 고려사항
            //여러 테이블 조인에는 알맞지 않다.
            //서브 쿼리가 필요할 경우 알맞지 않다.
            //통계, 대량의 데이터 조회 처리는 알맞지 않다.
            //DBMS만의 특화된 쿼리가 필요한 경우는 알맞지 않다.

            //테이블 대신, 엔티티 이름, 속성을 사용한다.
            //where 절에 들어가는 :은 속성 이름을 기반으로 검색한다는 것이며, ?(위치기반)도 있다.
            //and(&쓰지않음), or(|쓰지않음), 비교연산자도 사용 가능하다.
            
            //TypedQuery<Review> query = em.createQuery("select r from Review as r where r.hotelId = :hotelId order by r.id desc", Review.class);
            //query.setParameter("hotelId", "H-001");
            
            TypedQuery<Review> query = em.createQuery("select r from Review as r", Review.class);
            
            //페이징
            //0부터 시작하며, 시작 행을 나타낸다.
            query.setFirstResult(4); //5번째 행 부터...
            //총 조회 개수를 나타낸다.
            query.setMaxResults(10); //10개를 조회한다.


            List<Review> reviews = query.getResultList();
            
            for(Review r : reviews){
                System.out.println(r.toString());
            }

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
    public static void newReview(EntityManager em, EntityTransaction transaction) throws Exception{

        transaction.begin();

        Review review = new Review("AAA");
        Review review2 = new Review("BBB");
        Review review3 = new Review("CCC");
        Review review4 = new Review("DDD");
        Review review5 = new Review("EEE");
        Review review6 = new Review("FFF");
        Review review7 = new Review("GGG");
        Review review8 = new Review("HHH");
        Review review9 = new Review("III");
        Review review10 = new Review("JJJ");
        Review review11 = new Review("KKK");
        Review review12 = new Review("LLL");
        Review review13 = new Review("NNN");
        Review review14 = new Review("MMM");
        Review review15 = new Review("OOO");
        Review review16 = new Review("PPP");
        Review review17 = new Review("QQQ");
        Review review18 = new Review("RRR");
        Review review19 = new Review("SSS");
        Review review20 = new Review("TTT");

        em.persist(review);
        em.persist(review2);
        em.persist(review3);
        em.persist(review4);
        em.persist(review5);
        em.persist(review6);
        em.persist(review7);
        em.persist(review8);
        em.persist(review9);
        em.persist(review10);
        em.persist(review11);
        em.persist(review12);
        em.persist(review13);
        em.persist(review14);
        em.persist(review15);
        em.persist(review16);
        em.persist(review17);
        em.persist(review18);
        em.persist(review19);
        em.persist(review20);

        transaction.commit();
    }
}
