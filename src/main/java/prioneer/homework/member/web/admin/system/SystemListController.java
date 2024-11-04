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
    @ResponseBody
    public ResponseEntity<String> deleteMember(@RequestParam("phone") String phone) {
        try {
            if (!memberRepository.existsByPhone(phone)) {
                return ResponseEntity.badRequest().body("존재하지 않는 회원입니다.");
            }

            memberRepository.deleteByPhone(phone);
            return ResponseEntity.ok().body("회원 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원 삭제 중 오류 발생");
        }
    }
}
