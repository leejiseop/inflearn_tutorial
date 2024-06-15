package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        // 딱 하나만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 고객 요청이 올 때 마다 생성, 트랜잭션 단위로 만들어서 사용 -> close
        // 절대로!! 쓰레드 간 공유하면 안된다!!
        // em 은 사용하고 버리는 것
        EntityManager em = emf.createEntityManager();
        //code

        // tx start
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member findmember1 = em.find(Member.class, 101L);
            System.out.println("findmember1 = " + findmember1);
            em.clear();
            Member findmember2 = em.find(Member.class, 101L);
            System.out.println("findmember2 = " + findmember2);
            findmember2 = em.find(Member.class, 101L);
            System.out.println("findmember2 = " + findmember2);

            // tx commit
            tx.commit(); // commit 시점에 변경점을 감지하여 update 한다
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 안닫으면 db connection 을 계속 물고있다
        }

        emf.close(); // 전제 애플리케이션이 끝나면 팩토리도 닫아준다 -> WAS 가 내려갈 때
    }
}
