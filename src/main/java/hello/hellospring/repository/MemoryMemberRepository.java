package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;  //sequence : 0,1,2 키 값을 생성해줌

    @Override
    public Member save(Member member) {  //save 하기 전에 name은 넘어 온 상태
        member.setId(++sequence);  //store에 넣기 전 member에 id값을 세팅해줌 (map에 저장됨)
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //store에서 꺼냄. get한 결과가 없으면 null이 됨. null이 반환될 가능성이 있다면 Optional으로 감싸줌.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  //member.getName과 파라미터로 넘어온 name이 같은지 비교. 같은 경우에만 필터링됨. 필터링 된 값에서 찾으면 반환이 됨.
                .findAny();  //findAny : 하나라도 찾는 것. Optional 루프를 돌면서 하나라도 찾아지면 반환을 하는 것. 끝까지 돌렸는데 없으면 Optional에 null이 포함되서 반환됨.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  //store에 있는 values가 member로 반환됨
    }

    //store를 비우는 역할
    public void clearStore() {
        store.clear();
    }
}
