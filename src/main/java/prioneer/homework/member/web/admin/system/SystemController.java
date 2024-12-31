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

    @Scheduled(fixedRate = 3600000) // 1시간(3600000ms)마다 실행
    public void executeOrderTask() {
        memberRepository.listOrder();
        log.info("1시간 간격으로 작업 실행");
    }

}
