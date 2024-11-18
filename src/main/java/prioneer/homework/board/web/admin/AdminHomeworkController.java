package prioneer.homework.board.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prioneer.homework.board.domain.Board;
import prioneer.homework.board.repository.HomeworkRepository;
import prioneer.homework.board.service.admin.AdminBoardService;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminHomeworkController {
    // 관리자의 부원 과제 페이지
    // 부원 이름을 누르면 해당 부원의 과제를 채점하거나 코멘트를 달 수 있음
    // url은 /homework/{memberId}

    private final AdminBoardService adminBoardService;
    private final HomeworkRepository homeworkRepository;

    @GetMapping("/homework/{memberId}")
    public String getMemberHomework(@PathVariable String memberId,
                                    Model model,
                                    @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                        Member loginMember) {
        if (loginMember == null || !AdminBoardService.isAdmin(loginMember)) {
            return "redirect:/"; // 홈 화면으로 리다이렉트
        }

        List<Board> homeworkList = adminBoardService.getMemberHomework(memberId);
        model.addAttribute("homeworkList", homeworkList);
        model.addAttribute("memberId", memberId);

        return "관리자 부원 과제 페이지 html";

    }

    // 과제 채점
    @PostMapping("/homework/{memberId}")
    public String gradeHomework(@PathVariable String memberId,
                                @ModelAttribute Board board,
                                @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                    Member loginMember) {
        try {
            homeworkRepository.gradeHomework(board, loginMember);

            return "redirect:/homework/" + memberId;

        } catch (IllegalArgumentException e) {
            // 과제 채점 실패
            return "redirect:/homework/" + memberId;
        }
    }

}