package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member).getId();
    }

    /**
     * 회원가입 이름 중복 조회
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체회원 리스트 조회
     * @return
     */
    public List<Member> findMemberList(){
        return memberRepository.findByAll();
    }

    /**
     * 회원 키값(ID)로 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findMember(Long memberId){
        return memberRepository.findById(memberId);
    }

}
