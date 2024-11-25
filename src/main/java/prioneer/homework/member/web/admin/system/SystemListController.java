package prioneer.homework.member.web.admin.system;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class SystemListController {
    // 일반 관리자가 보는 명단 화면
    // 부원들의 이름, 보증금 현황, 방어권 개수를 볼 수 있고 회원삭제 가능
    // url은 /system/list

    private final AdminMemberService adminMemberService;
    private final MemberRepository memberRepository;

    // 명단 페이지
    @GetMapping("/system/list")
    public String systemList(Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
    Member loginMember) {

        if (loginMember.getRole().equals("USER")) {
            return "redirect:/";
        }
        List<Member> memberList = adminMemberService.getMemberList();
        model.addAttribute("memberList", memberList);
        model.addAttribute("member",loginMember);
        return "admin/admin_assignment_list";

    }


    // 보증금 및 방어권 갯수 업데이트
    @PostMapping("/system/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMember(
            @PathVariable("id") String id,
            @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            int shields = Integer.parseInt(request.get("shields").toString());


            log.info(String.valueOf(shields));
            Member member = new Member();
            member.setMemberId(id);

            member.setDepositDepend((long) shields);

            memberRepository.update(member);

            response.put("success", true);
            response.put("message", "수정이 완료되었습니다.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "수정 중 오류 발생: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 회원 삭제
    @PostMapping("/system/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteMember(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Member> findMember = memberRepository.findMemberById(id);
            if (findMember.isPresent()) {
                memberRepository.remove(findMember.get());
            }

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
