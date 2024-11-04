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

@Controller
@RequiredArgsConstructor
public class UserNewController {
    // 22기 부원들 신규 등록
    // 회원 정보 입력
    // url은 /system/user

    private final MemberRepository memberRepository;

    @GetMapping("/system/user")
    public String showNewUserForm(Model model) {
        model.addAttribute("member", new Member());
        return "system/user";
    }

    @PostMapping("/system/user")
    public String registerNewUser(@ModelAttribute("member") Member member, RedirectAttributes redirectAttributes) {
        try {
            member.setRole("member");
            member.setDepositDepend(0L); // 초기 보증금 방어권 0개
            member.setDeposit(120000L); // 초기 보증금 12만원

            memberRepository.save(member);

            redirectAttributes.addFlashAttribute("message", "회원 등록 성공!");
            return "redirect:/system/user";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "회원 등록 실패");
            return "redirect:/system/user";
        }
    }
}
