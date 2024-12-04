package prioneer.homework.mvp.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import prioneer.homework.mvp.domain.MVP;
import prioneer.homework.mvp.repository.MvpRepository;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MvpController {
    private final MvpRepository mvpRepository;

    @GetMapping("/mvp")
    public String getMvp(Model model,
                         @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                         Member loginMember){

        List<MVP> mvp = mvpRepository.findAll();
        model.addAttribute("mvp1",mvp.get(0));
        model.addAttribute("mvp2",mvp.get(1));
        model.addAttribute("mvp3",mvp.get(2));
        model.addAttribute("mvp4",mvp.get(3));
        model.addAttribute("mvp5",mvp.get(4));
        return "home/mvp";
    }
}
