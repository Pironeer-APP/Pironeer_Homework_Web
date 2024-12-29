package prioneer.homework.member.web.admin.system;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;

@RequiredArgsConstructor
@Controller
@Slf4j
public class SystemController {

    private final MemberRepository memberRepository;
    @GetMapping("/system/management")
    public String getManagement( @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                     Member loginMember) {

        if (loginMember.getRole().equals("MASTER")) {
            return "admin/master_start";
        }

        if (loginMember.getRole().equals("ADMIN")) {
            return "admin/admin_start";
        }

        return "redirect:/";

    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 00:00:00에 실행
    public void executeOrderTask() {
        memberRepository.listOrder(); // getOrder()의 로직을 실행
        log.info("00시 작업 실행 완료");
    }
}
