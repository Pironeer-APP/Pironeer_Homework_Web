package prioneer.homework.member.web.admin.join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;
import prioneer.homework.member.service.admin.AdminMemberService;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SystemJoinController {

    private final AdminMemberService adminMemberService;

    // GET 관리자 회원가입
    @GetMapping("/system/join")
    public String join(Model model) {
        model.addAttribute("member", new Member());
        return "admin/admin_join";

    }

    // POST 관리자 회원가입
    @PostMapping("/system/join")
    public String join(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        String save = adminMemberService.join(member);
        if (Objects.equals(save, "phone")) {
            bindingResult.reject("joinFail", "존재하는 전화번호가 있습니다");
            return "admin/admin_join";
        }
        if (Objects.equals(save, "password")) {
            bindingResult.reject("joinFail", "비밀번호가 일치하지 않습니다");
            return "admin/admin_join";
        }

        return "redirect:/system/login";
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
