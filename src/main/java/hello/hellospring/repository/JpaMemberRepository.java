package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;  //jpa를 쓰려면 EntityManager를 주입받아야 함

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  //find로 조회. (조회할 타입, 식별자 pk값) 넣어주기
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)  //m=member entity자체를 셀렉하는 것
                .getResultList();

        //Inline 컨알n
        //result 가 똑같은 게 있어서 합쳐줄 수 있음
//        List<Member> result = em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//        return result;
    }
}
