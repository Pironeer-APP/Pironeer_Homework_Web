package prioneer.homework.member.web.admin.join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;
import prioneer.homework.member.service.admin.AdminMemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SystemJoinController {

    private final AdminMemberService adminMemberService;

    // GET 관리자 회원가입
    @GetMapping("/system/join")
    public String join(Model model) {
        model.addAttribute("member", new Member());
        return "회원가입 페이지 html 페이지";

    }

    // POST 관리자 회원가입
    @PostMapping("/system/join")
    public String join(@ModelAttribute Member member, Model model) {
        try {
            adminMemberService.join(member);
            model.addAttribute("member", member);
            return "회원가입 완료 알림 html 페이지";
        } catch (Exception e) {
            model.addAttribute("message", "회원가입 도중 에러 발생");
            return "회원가입 폼 html 페이지";
        }
    }

    // 관리자 삭제, update는 구현 안함
    @PostMapping("system/delete")
    public String delete(@ModelAttribute Member member, Model model) {

        try {
            adminMemberService.deleteMember(member.getPhone());
            return "삭제 완료 페이지 html || redirect:/ 회원가입 폼 html 페이지";
        } catch (Exception e) {
            model.addAttribute("message", "관리자 삭제 중 에러 발생");
            return "관리자 목록 페이지 html";
        }
    }

}
