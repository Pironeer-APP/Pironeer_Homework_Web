package prioneer.homework.member.web.admin.system;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MasterListController {
    // 마스터 관리자는 들어온 관리자 회원가입 리스트에서 셀렉트 박스를 통해 admin 권한을 부여한다
    // 처음 회원가입한 관리자들은 모두 preadmin이다
    // url은 /system/master/list

    private final MemberRepository memberRepository;

    // 관리자 신청 확인 명단 페이지를 보여줌
    @GetMapping("system/master/list")
    public String masterList(Model model) {
        List<Member> preAdminList = memberRepository.findByRole("preadmin");
        // Enum이 아니라서 일단 이렇게 함,,, 나중에 수정하나?

        model.addAttribute("preAdminList", preAdminList);
        return "system/master/list";
    }

    // 관리자 권한 부여
    @PostMapping("system/master/updateToAdmin")
    @ResponseBody
    public ResponseEntity<String> updateToAdmin(
            @RequestParam("phone") String phone,
            @RequestParam("role") String role) {
        try {

            Member member = memberRepository.findByPhone(phone)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));

            member.setRole(role);
            memberRepository.save(member);

            return ResponseEntity.ok("권한 변경 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("권한 변경 중 오류 발생");
        }
    }

    // preadmin 삭제
    @PostMapping("system/master/deletePreadmin")
    @ResponseBody
    public ResponseEntity<String> deletePreadmin(
            @RequestParam("phone") String phone) {
        try {
            if (!memberRepository.existsByPhone(phone)) {
                return ResponseEntity.badRequest().body("사용자를 찾을 수 없습니다");
            }

            memberRepository.deleteByPhone(phone);
            return ResponseEntity.ok("회원 삭제 성공");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원 삭제 중 오류 발생");
        }
    }


}
