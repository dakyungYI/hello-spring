package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
직접 스프링 빈 등록하기
 */
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired //생성자가 1개인 경우에는 생략해도 됨
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //스프링컨테이너에서 MemberRepository를 찾음. 그런데 등록한 게 없음. 하지만 SpringDataJpaMemberRepository 인터페이스가 있음
    //JpaRepository(스프링데이터가 제공하는 것)가 인터페이스에 대한 구현체를 자기가 가진 기술을 가지고 만들어 냄.
    // 그리고 스프링 빈에 등록을 함. 그래서 인젝션으로 받을 수 있음

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
