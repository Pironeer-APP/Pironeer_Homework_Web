package prioneer.homework.member.web.admin.system;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;
import prioneer.homework.member.service.admin.AdminMemberService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserNewController {
    // 22기 부원들 신규 등록
    // 회원 정보 입력
    // url은 /system/user

    private final MemberRepository memberRepository;
    private final AdminMemberService adminMemberService;

    @GetMapping("/system/user")
    public String showNewUserForm(Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
    Member loginMember) {

        if (loginMember.getRole().equals("USER")) {
            return "redirect:/";
        }
        model.addAttribute("member", new Member());
        return "admin/admin_register";
    }

    @PostMapping("/system/user")
    public String registerNewUser(@Validated @ModelAttribute("member") Member member,
                                  BindingResult bindingResult) {
        
        try {
            // 성공
            adminMemberService.registerNewMember(member);

            return "redirect:/system/user";

        } catch (IllegalStateException e) {
            // 에러 발생
            bindingResult.reject("registerNewUserFail", "이미 존재하는 번호가 있습니다.");
            return "admin/admin_register";
        }
    }
}
