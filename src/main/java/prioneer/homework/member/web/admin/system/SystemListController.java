package prioneer.homework.member.web.admin.system;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SystemListController {
    // 일반 관리자가 보는 명단 화면
    // 부원들의 이름, 보증금 현황, 방어권 개수를 볼 수 있고 회원삭제 가능
    // url은 /system/list

    private final MemberRepository memberRepository;

    // 명단 페이지
    @GetMapping("/system/list")
    public String systemList(Model model) {
        List<Member> memberList = memberRepository.findByRole("member");
        model.addAttribute("memberList", memberList);
        return "system/list";
    }

    // 회원 삭제
    @PostMapping("/system/deleteMember")
    public String deleteMember(@RequestParam("phone") String phone,
                               RedirectAttributes redirectAttributes) {
        try {
            if (!memberRepository.existsByPhone(phone)) {
                redirectAttributes.addFlashAttribute("message", "존재하지 않는 회원입니다.");
                return "redirect:/system/list";
            }

            memberRepository.deleteByPhone(phone);
            redirectAttributes.addFlashAttribute("message", "회원 삭제 성공");
            return "redirect:/system/list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "회원 삭제 중 오류 발생");
            return "redirect:/system/list";
        }
    }
}
