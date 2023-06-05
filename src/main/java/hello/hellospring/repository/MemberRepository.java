package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //회원이 저장소에 저장됨
    Member save(Member member);
    //저장소에서 findById, findByName 으로 찾아올 수 있음
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //findAll 으로 지금까지 저장된 모든 회원 리스트를 반환해줌
    List<Member> findAll();
}
