package jpql;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {

        // 딱 하나만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 고객 요청이 올 때 마다 생성, 트랜잭션 단위로 만들어서 사용 -> close
        // 절대로!! 쓰레드 간 공유하면 안된다!!
        // em 은 사용하고 버리는 것
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);


            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());
            


            tx.commit(); // commit 시점에 변경점을 감지하여 update 한다
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 안닫으면 db connection 을 계속 물고있다
        }

        emf.close(); // 전제 애플리케이션이 끝나면 팩토리도 닫아준다 -> WAS 가 내려갈 때
    }
}
