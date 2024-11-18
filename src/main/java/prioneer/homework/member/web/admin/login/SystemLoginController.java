package prioneer.homework.member.web.admin.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.service.admin.AdminMemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SystemLoginController {

    private final AdminMemberService adminMemberService;

    @GetMapping("/system")
    public String getSystemLogin(Model model,
                                 @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                 Member loginMember) {

        try {
            if (loginMember == null) {
                return "로그인 안 된 홈 html";
            }

        // 로그인 회원 정보 비교
        Member member = adminMemberService.getLoginMember(loginMember);
        model.addAttribute("member", member);

        return "로그인 된 홈 html";

        } catch (IllegalStateException e) {
            return "로그인 안 된 홈 html";
        }
    }
}
