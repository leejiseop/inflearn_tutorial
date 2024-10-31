package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

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
            // 연관관계 편의 메서드 정리하기

//            Movie movie = new Movie();
//            movie.setDirector("aaaa");
//            movie.setActor("bbbb");
//            movie.setName("바람과함께사라지다");
//            movie.setPrice(10000);

            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());


            em.persist(member);

            // 조회할때는 jpa 가 알아서 join 해서 가져와준다
            em.flush();
            em.clear();

//            Movie findMovie = em.find(Movie.class, movie.getId());
//            // 변수 생성 command option v 게터세터생성자 command n
//            System.out.println("findMovie = " + findMovie);

            tx.commit(); // commit 시점에 변경점을 감지하여 update 한다
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 안닫으면 db connection 을 계속 물고있다
        }

        emf.close(); // 전제 애플리케이션이 끝나면 팩토리도 닫아준다 -> WAS 가 내려갈 때
    }
}
