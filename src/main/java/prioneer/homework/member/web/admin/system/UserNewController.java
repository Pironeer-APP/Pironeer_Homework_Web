package prioneer.homework.member.web.admin.system;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;
import prioneer.homework.member.service.admin.AdminMemberService;

@Controller
@RequiredArgsConstructor
public class UserNewController {
    // 22기 부원들 신규 등록
    // 회원 정보 입력
    // url은 /system/user

    private final MemberRepository memberRepository;
    private final AdminMemberService adminMemberService;

    @GetMapping("/system/user")
    public String showNewUserForm(Model model) {
        model.addAttribute("member", new Member());
        return "system/user";
    }

    @PostMapping("/system/user")
    public String registerNewUser(@ModelAttribute("member") Member member, RedirectAttributes redirectAttributes) {
        try {
            adminMemberService.registerNewMember(member);
            redirectAttributes.addFlashAttribute("message", "회원 등록 성공");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("message", "회원 등록 중 에러 발생");
        }
        return "redirect:/system/user";
    }
}
