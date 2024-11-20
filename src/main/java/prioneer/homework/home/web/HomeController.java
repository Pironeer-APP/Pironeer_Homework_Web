package prioneer.homework.home.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    // 22기 부원
    @GetMapping("/")
    public String home(Model model,
                       @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                       Member loginMember) {

        if (loginMember == null) {
            return "home/home_loginbefore";
        }
        if (!loginMember.getRole().equals("USER")) {
            return "redirect:/system";
        }



        return "home/home_loginafter";
    }


    // 운영진
    @GetMapping("/system")
    public String getSystem(Model model,
                            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                            Member loginMember) {

        if (loginMember == null) {
            return "admin/admin_home_loginbefore";
        }


        if (loginMember.getRole().equals("USER")) {
            return "redirect:/";
        }




        // 로그인 회원 정보 비교
        return "admin/admin_home_loginafter";


    }

}
