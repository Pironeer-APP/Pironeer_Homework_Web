package prioneer.homework.member.web.user.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;
import prioneer.homework.member.service.user.UserMemberService;

import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
@Controller
public class UserLoginController {

    private final UserMemberService userMemberService;
    @GetMapping("/login")
    public String getUserLogin(Model model,
                                 @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                 Member loginMember) {

        return "/home/login";
    }

    @PostMapping("/login")
    public String postUserLogin(@Validated @ModelAttribute Member member, BindingResult bindingResult,
                            @RequestParam(defaultValue = "/") String redirectURL,
                            HttpServletRequest request) throws Exception {

        Optional<Member> findLoginMember = userMemberService.phoneCheck(member);
        if (findLoginMember.isEmpty()) {
            bindingResult.reject("loginFail", "전화번호가 존재하지 않습니다");
            return "home/login";
        }

        String message = userMemberService.nameCheck(member);

        if (message.equals("Fail")) {
            bindingResult.reject("loginFail", "이름이 존재하지 않습니다.");
            return "home/login";
        }

        //로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        return "redirect:" + redirectURL;
    }

}
