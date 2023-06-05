package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {  //메서드가 끝날 때마다 afterEach가 동작을 함
        repository.clearStore();  //테스트가 실행되고 끝날 때마다 저장소를 다 지우는 역할
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");  //이름 설정

        //repository에 save하기
        repository.save(member);

        //findById로 저장한 것을 get으로 꺼내기
        Member result = repository.findById(member.getId()).get(); //반환타입은 Optional. Optional에서 값을 꺼낼 때는 get()으로 꺼낼 수 있음. get으로 꺼내는 것이 좋은 방법은 아니지만 테스트코드에서는 사용해도 괜찮음

        //검증하기
        assertThat(member).isEqualTo(result);  //assertThat 해서 member가 result랑 똑같아! 라고 말하는 것
        //Assertions.assertThat(member).isEqualTo(result); -> Assertions에서 알트엔터 하면 없앨수있음. 위에 임포트 됨.(다음부터 바로 assertThat을 사용할 수 있음)
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //시프트 + f6 하면 바로 리네임 할 수 있음
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
