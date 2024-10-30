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

@Controller
@RequiredArgsConstructor
@Slf4j
public class SystemJoinController {

    private final MemberRepository memberRepository;

    @GetMapping("/system/join")
    public String join(Model model) {
        model.addAttribute("member", new Member());
        return "회원가입 페이지 html 파일";

    }

    @PostMapping("/system/join")
    public String join(@ModelAttribute Member member, Model model) {
        // 회원 정보가 이미 존재하는 지 확인
        if (memberRepository.existsByPhone(member.getPhone())) {
            model.addAttribute("errorMessage", "이미 존재하는 회원 ID 입니다.");
            return "회원가입 페이지 html 페이지";
        }

        // 회원 정보 저장
        memberRepository.save(member);
        log.info("회원가입 성공: {}", member);

        // 회원 가입 완료 페이지로 이동
        model.addAttribute("member", member);
        return "회원가입 완료 알림 html 페이지";
    }

}
