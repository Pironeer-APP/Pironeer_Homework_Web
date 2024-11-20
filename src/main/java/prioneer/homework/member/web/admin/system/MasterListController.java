package prioneer.homework.member.web.admin.system;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;
import prioneer.homework.member.service.admin.AdminMemberService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MasterListController {
    // 마스터 관리자는 들어온 관리자 회원가입 리스트에서 셀렉트 박스를 통해 admin 권한을 부여한다
    // 처음 회원가입한 관리자들은 모두 preadmin이다
    // url은 /system/master/list

    private final AdminMemberService adminMemberService;
    private final MemberRepository memberRepository;

    // 관리자 신청 확인 명단 페이지를 보여줌
    @GetMapping("/system/master/list")
    public String masterList(Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
    Member loginMember) {

        if (loginMember.getRole().equals("USER")) {
            return "redirect:/";
        }
        if (loginMember.getRole().equals("ADMIN")) {
            return "redirect:/system";
        }
        List<Member> preAdminList = adminMemberService.getPreadminList();


        model.addAttribute("preAdminList", preAdminList);
        return "admin/master_manage";
    }

    // 관리자 권한 부여
    @PostMapping("/system/master/updateadmin/{id}")
    public ResponseEntity<?> updateToAdmin(
            @PathVariable("id") String memberId,
            @RequestBody Map<String, String> requestBody) {

        String role = requestBody.get("role");

        // Role 업데이트 처리
        try {
            memberRepository.updateToAdmin(memberId, role);
            return ResponseEntity.ok().body(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }


    // preadmin 삭제
    @PostMapping("/system/master/{id}")
    public ResponseEntity<Map<String, Object>> deletePreadmin (@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            adminMemberService.deletePreadmin(id);
            response.put("success", true);
            response.put("message", "삭제가 완료되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "삭제 중 오류 발생: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


}
