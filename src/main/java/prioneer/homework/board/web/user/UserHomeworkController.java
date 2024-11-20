package prioneer.homework.board.web.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import prioneer.homework.board.domain.Board;
import prioneer.homework.board.repository.HomeworkRepository;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserHomeworkController {
    private final HomeworkRepository homeworkRepository;

    @GetMapping("/homework")
    public String getAdmin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                           Member loginMember, Model model) {

        if(!loginMember.getRole().equals("USER")){
            return "redirect:/";
        }
        List<Board> memberHomework = homeworkRepository.findMemberHomework(loginMember);
        model.addAttribute("member",loginMember);
        model.addAttribute("homework1",memberHomework.get(0));
        model.addAttribute("homework2",memberHomework.get(1));
        model.addAttribute("homework3",memberHomework.get(2));
        model.addAttribute("homework4",memberHomework.get(3));
        model.addAttribute("homework5",memberHomework.get(4));
        model.addAttribute("homework6",memberHomework.get(5));
        model.addAttribute("homework7",memberHomework.get(6));
        model.addAttribute("homework8",memberHomework.get(7));
        model.addAttribute("homework9",memberHomework.get(8));
        model.addAttribute("homework10",memberHomework.get(9));
        model.addAttribute("homework11",memberHomework.get(10));
        model.addAttribute("homework12",memberHomework.get(11));
        model.addAttribute("homework13",memberHomework.get(12));
        model.addAttribute("homework14",memberHomework.get(13));
        model.addAttribute("homework15",memberHomework.get(14));
        model.addAttribute("homework16",memberHomework.get(15));
        model.addAttribute("homework17",memberHomework.get(16));
        model.addAttribute("homework18",memberHomework.get(17));


        return "user/assignment_user";
    }
}
