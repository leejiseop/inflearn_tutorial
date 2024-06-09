package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 트랜잭션 단위로 만들어서 사용
        //code

        // tx start
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");

        em.persist(member);

        // tx commit
        tx.commit();

        em.close();
        emf.close();
    }
}
