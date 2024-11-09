package prioneer.homework.member.web.admin.system;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final MemberRepository memberRepository;

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
            @ModelAttribute Member member,
            BindingResult bindingResult) {
        try {
            memberRepository.updateToAdmin(member, "admin");

        } catch (IllegalArgumentException e) {
            bindingResult.reject("updateToAdminFail", "권한 변경 중 오류 발생");
        }
        return "redirect:/system/master/list";
    }

    // preadmin 삭제
    @PostMapping("/system/master/{id}")
    public String deletePreadmin(
            Member member,
            BindingResult bindingResult) {
        try {
            adminMemberService.deletePreadmin(member);

        } catch (IllegalArgumentException e) {
            bindingResult.reject("deletePreadminFail", "preadmin 삭제 중 오류 발생");

        }
        return "redirect:/system/master/list";
    }


}
