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

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
//            member.changeTeam(team); // set 보다는 연관관계 편의 메서드 만들어서 사용 (team.addMember(member);)
            em.persist(member);

//            team.getMembers().add(member); // 안해줘도 된다

            em.flush();
            em.clear();

            System.out.println("em.find");
            Team findTeam = em.find(Team.class, team.getId());
            System.out.println("members");
            List<Member> members = findTeam.getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

//            Member findMember = em.find(Member.class, member.getId()); // 1차 캐시
//            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
//
//            Team team2 = new Team();
//            team2.setName("TeamA");
//            em.persist(team2); // ????? 어젠 왜 안됐지
//            findMember.setTeam(team2);

            System.out.println("before commit");
            tx.commit(); // commit 시점에 변경점을 감지하여 update 한다
            System.out.println("after commit");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 안닫으면 db connection 을 계속 물고있다
        }

        emf.close(); // 전제 애플리케이션이 끝나면 팩토리도 닫아준다 -> WAS 가 내려갈 때
    }
}
