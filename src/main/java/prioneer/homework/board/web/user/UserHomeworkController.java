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

        List<Board> memberHomework = homeworkRepository.findMemberHomework(loginMember);
        model.addAttribute("homework",memberHomework);
        return "user/assignment_user";
    }
}
