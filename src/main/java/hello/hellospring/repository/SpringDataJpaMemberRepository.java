package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스가 인터페이스를 상속받을 때는 implements가 아니라 extends로 상속받음
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {


    //fineByName => JPQL select m from Member m where m.name = ? 을 알아서 해줌
    @Override
    Optional<Member> findByName(String name);
}
