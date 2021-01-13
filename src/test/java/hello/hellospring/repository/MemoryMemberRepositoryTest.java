package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 매 테스트마다 실행이 됨
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member result1 = repository.findById(member1.getId()).get();
        assertThat(member1).isEqualTo(result1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result2 = repository.findById(member2.getId()).get();
        assertThat(member2).isEqualTo(result2);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member result = repository.findByName(member1.getName()).get();
        assertThat(member1).isEqualTo(result);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result2 = repository.findByName("spring2").get();

        assertThat(result2).isEqualTo(member2);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> memberList = repository.findByAll();

        assertThat(2).isEqualTo(memberList.size());

    }
}
