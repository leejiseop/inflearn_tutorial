package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext // Spring 이 엔티티 매니저를 주입해준다
    // spring boot 의 spring data jpa 를 쓴다면 Autowired 로 대체 가능!
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); // 타입, PK
    }

    // sql 은 테이블을 대상으로 쿼리를 하는데, jpql 은 entity 객체를 대상으로 쿼리를 한다
    public List<Member> findAll() { // Member.class : 조회 타입
        return em.createQuery("select m from Member m", Member.class) // jpql
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
