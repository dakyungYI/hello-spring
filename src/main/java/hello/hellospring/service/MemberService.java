package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional  //jpa는 join 들어올 때 모든 데이터 변경이 다 Transactional 안에서 실행되어야 함
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
    //MemberService는 MemberRepository가 필요함.
    // 스프링이 뜰 때 @Service네 하고 컨테이너에 등록을 하면서 생성자를 호출함.
    // 이때 Autowired가 있으면 넌 MemberRepository가 필요하구나 하면서 컨테이너가 MemberRepository를 넣어줌.
    //MemberRepository의 구현체는 MemoryMemberRepository이기 때문에 MemoryMemberRepository를 구현해줌.
    public MemberService(MemberRepository memberRepository) {  //memberRepository를 new로 생성하는 것이 아니라 외부에서 넣어주게 만드는 것
        this.memberRepository = memberRepository;
    }
    /**
     * 회원 가입
     */
    public Long join(Member member) {

            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //같은 이름이 잇는 중복 회원x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        reslit.ifPresent(m -> {  //ifPresent : result가 null이 아니라 값이 있으면 메서드 바디의 로직이 동작을 하게 됨(Optional 이라서 가능). 과거에는 if문으로 작성했음.
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        //위의 코드를 아래처럼 정리할 수 있음
        //findByName을 해. 그 결과는 Optional<Member>니까 Optional<Member>.ifPresent해서 회원이 존재하는지 확인



//        ValidateDuplicateMember(member);  //중복 회원 검증  //join을 하면 중복 회원을 검증하고, 통과하면 저장하는구나
//        memberRepository.save(member);
//        return member.getId();
//    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {

        return memberRepository.findAll();  //findAll의 반환 타입은 List<Member>이기 때문에 단순히 반환해주면 됨
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
