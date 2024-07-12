package com.example.main;


import java.util.List;

import com.example.domain.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CriteriaMain {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            
            //테스트 데이터 20개 입력
            newReview(em, transaction);


            //Criteria 장점
            //타입에 안전한 코드
            //동적인 검색 조건 지정 가능

            //Criteria 단점
            //JPQL과 비슷하게
            //여러 테이블 조인에 알맞지 않음
            //DBMS에 특화된 쿼리에 알맞지 않음
            //서브 쿼리에 알맞지 않음
            //통계, 대량 데이터 조회/처리에 알맞지 않음

            //CriteriaBuilder 생성
            CriteriaBuilder cb = em.getCriteriaBuilder();

            //CriteriaQuery 생성 컬렉션타입은 다룰 엔티티로
            CriteriaQuery<Review> cq = cb.createQuery(Review.class);

            //from으로 엔티티 지정
            Root<Review> root = cq.from(Review.class);

            //cq의 쿼리 실행 메소드를 이용하여 쿼리 실행문을 구현한다.
            //cb의 쿼리 조건 메소드를 이용하여 쿼리 조건을 선택한다. 여기서 Root객체의 속성들을 이용한다.

            //select 실행할거다.
            cq.select(root);

            //where 조건을 입력한다.
            //cq.where(cb.equal(root.get("hotelId"), "AAA"));
            //아래와 같이 두개의 조건을 조합 할 수 있다.
            // Predicate p1 = cb.equal(root.get("hotelId"), "AAA");
            // Predicate p2 = cb.greaterThan(root.get("created"), LocalDateTime.now().minusDays(10));
            // Predicate predicate = cb.and(p1,p2);
            // cq.where(predicate);

            //orderby 조건을 지정한다.
            cq.orderBy(cb.asc(root.get("id")), cb.desc(root.get("hotelId")));
            
            TypedQuery<Review> query = em.createQuery(cq);

            //페이징처리
            //6 부터 조회
            query.setFirstResult(5);

            //10개의 데이터를 가져온다.
            query.setMaxResults(10);

            List<Review> resultList = query.getResultList();

            for(Review r : resultList){
                System.err.println("result :: " + r.toString());
            }
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
