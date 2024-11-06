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
import prioneer.homework.member.service.admin.AdminMemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MasterListController {
    // 마스터 관리자는 들어온 관리자 회원가입 리스트에서 셀렉트 박스를 통해 admin 권한을 부여한다
    // 처음 회원가입한 관리자들은 모두 preadmin이다
    // url은 /system/master/list

    private final AdminMemberService adminMemberService;

    // 관리자 신청 확인 명단 페이지를 보여줌
    @GetMapping("/system/master/list")
    public String masterList(Model model) {
        List<Member> preAdminList = adminMemberService.getPreadminList();
        model.addAttribute("preAdminList", preAdminList);
        return "system/master/list";
    }

    // 관리자 권한 부여
    @PostMapping("/system/master/updateToAdmin")
    public String updateToAdmin(
            @RequestParam("phone") String phone,
            @RequestParam("role") String role,
            RedirectAttributes redirectAttributes) {
        try {
            adminMemberService.updateToAdmin(phone, role);
            redirectAttributes.addFlashAttribute("message", "권한 변경 완료");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", "권한 변경 중 오류 발생");
        }
        return "redirect:/system/master/list";
    }

    // preadmin 삭제
    @PostMapping("/system/master/deletePreadmin")
    public String deletePreadmin(
            @RequestParam("phone") String phone,
            RedirectAttributes redirectAttributes) {
        try {
            adminMemberService.deleteMember(phone);
            redirectAttributes.addFlashAttribute("message", "preadmin 삭제 완료");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", "preadmin 삭제 중 오류 발생");
        }
        return "redirect:/system/master/list";
    }


}
