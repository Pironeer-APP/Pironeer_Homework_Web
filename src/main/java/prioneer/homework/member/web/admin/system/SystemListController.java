package prioneer.homework.member.web.admin.system;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.service.admin.AdminMemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SystemListController {
    // 일반 관리자가 보는 명단 화면
    // 부원들의 이름, 보증금 현황, 방어권 개수를 볼 수 있고 회원삭제 가능
    // url은 /system/list

    private final AdminMemberService adminMemberService;

    // 명단 페이지
    @GetMapping("/system/list")
    public String systemList(Model model) {
        try {
            List<Member> memberList = adminMemberService.getMemberList();
            model.addAttribute("memberList", memberList);
            return "system/list";
        } catch (Exception e) {
            model.addAttribute("message", "회원 목록 조회 중 에러 발생");
            return "Error";
        }
    }

    // 회원 삭제
    @PostMapping("/system/deleteMember/{phone}")
    public String deleteMember(@PathVariable("phone") String phone,
                               BindingResult bindingResult) {
        try {
            adminMemberService.deleteMember(phone);

        } catch (Exception e) {
            bindingResult.reject("memberDeleteFail", "회원 삭제 중 오류 발생");
        }
        return "redirect:/system/list";
    }
}
