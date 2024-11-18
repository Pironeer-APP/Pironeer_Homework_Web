package prioneer.homework.member.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserMemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> phoneCheck(Member member){
        return memberRepository.findByPhone(member.getPhone());

    }

    public String nameCheck(Member member){
        Optional<Member> byPhone = memberRepository.findByPhone(member.getPhone());
        if(byPhone.isPresent()){
            Member member1 = byPhone.get();
            if(member1.getName().equals(member.getName())){
                return "OK";
            }
            return "Fail";
        }else{
            return null;
        }

    }
}
