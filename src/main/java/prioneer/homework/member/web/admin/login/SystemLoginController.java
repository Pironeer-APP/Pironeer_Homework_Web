package prioneer.homework.member.web.admin.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
@Slf4j
public class SystemLoginController {

    private final MemberRepository memberRepository;

    @GetMapping("/system")
    public String getSystemLogin(Model model,
                                 @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                 Member loginMember) {

        // 민수야 이거 보고 감을 익히렴 이게 기본 구조임

        if(loginMember == null){
            return "로그인 안 된 홈 html";
        }

        Optional<Member> findMember = memberRepository.findMemberId(loginMember);
        if (findMember.isPresent()) {
            Member member = findMember.get();
            model.addAttribute("member", member);
            return "로그인 된 홈 html";
        } else {
            return null;
        }
    }
}
