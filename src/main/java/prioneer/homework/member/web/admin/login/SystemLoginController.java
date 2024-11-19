package prioneer.homework.member.web.admin.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.service.admin.AdminMemberService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SystemLoginController {

    private final AdminMemberService adminMemberService;


    @GetMapping("/system/login")
    public String getSystemLogin(@ModelAttribute("member") Member member
            , Model model) {


        model.addAttribute("member", member);
        return "admin/admin_login";


    }

    @PostMapping("/system/login")
    public String postSystemLogin(@Validated @ModelAttribute Member member, BindingResult bindingResult,
                                  HttpServletRequest request) {
        Optional<Member> findPhoneMember = adminMemberService.getLoginMember(member);
        if (findPhoneMember.isEmpty()) {
            bindingResult.reject("loginFail", "전화번호가 일치하지 않습니다");
            return "admin/admin_login";
        }

        Optional<Member> findNameMember = adminMemberService.getNameMember(member);
        if (findNameMember.isEmpty()) {
            bindingResult.reject("loginFail", "이름이 일치하지 않습니다");
            return "admin/admin_login";
        }

        Member passwordMember = adminMemberService.passwordCheck(member);
        if (passwordMember == null) {
            bindingResult.reject("loginFail", "비밀번호가 일치하지 않습니다");
            return "admin/admin_login";
        }

        if (passwordMember.getRole().equals("PREADMIN")) {
            bindingResult.reject("loginFail", "교육팀장님께 문의주세요");
            return "admin/admin_login";
        }

        //로그인 성공
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, passwordMember);

        return "redirect:/system";

    }
}
