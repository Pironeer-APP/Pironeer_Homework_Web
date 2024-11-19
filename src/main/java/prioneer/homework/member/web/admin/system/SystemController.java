package prioneer.homework.member.web.admin.system;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;

@RequiredArgsConstructor
@Controller
@Slf4j
public class SystemController {

    @GetMapping("/system/management")
    public String getManagement( @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                     Member loginMember) {

        if (loginMember.getRole().equals("MASTER")) {
            return "admin/admin_start";
        }

        if (loginMember.getRole().equals("ADMIN")) {
            return "admin/master_start";
        }

        return "redirect:/";

    }
}
