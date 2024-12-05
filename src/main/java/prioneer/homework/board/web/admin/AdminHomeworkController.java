package prioneer.homework.board.web.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prioneer.homework.board.domain.Board;
import prioneer.homework.board.repository.HomeworkRepository;
import prioneer.homework.board.service.admin.AdminBoardService;
import prioneer.homework.config.session.SessionConst;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;
import prioneer.homework.member.service.admin.AdminMemberService;

import java.text.NumberFormat;
import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminHomeworkController {
    // 관리자의 부원 과제 페이지
    // 부원 이름을 누르면 해당 부원의 과제를 채점하거나 코멘트를 달 수 있음
    // url은 /homework/{memberId}

    private final AdminBoardService adminBoardService;
    private final HomeworkRepository homeworkRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/homework/{memberId}")
    public String getMemberHomework(@PathVariable String memberId,
                                    Model model,
                                    @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                        Member loginMember) {
        if (loginMember == null) {
            return "redirect:/"; // 홈 화면으로 리다이렉트
        }
        if(loginMember.getRole().equals("USER")){
            return "redirect:/";
        }




        Optional<Member> findMember = memberRepository.findMemberById(memberId);
        if(findMember.isPresent()){
            model.addAttribute("member",findMember.get());
        }else{
            return null;
        }

        List<Board> memberHomework = adminBoardService.getMemberHomework(memberId);

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
        int sum=0;
        for(int i=0; i<18; i++){
            sum+=memberHomework.get(i).getDeposit();
        }
        NumberFormat numberFormat=NumberFormat.getInstance(Locale.KOREA);
        String format = numberFormat.format(120000 + sum);

        model.addAttribute("sum",format);

        return "admin/assignment_admin";

    }

    // 과제 채점
    @PostMapping("/homework/{memberId}/{boardId}")
    @ResponseBody // JSON 응답 반환
    public ResponseEntity<Map<String, Object>> gradeHomework(
            @PathVariable String memberId,
            @PathVariable Long boardId,
            @ModelAttribute Board board,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {
        try {
            board.setBoardId(boardId);
            Optional<Member> findMember = memberRepository.findMemberById(memberId);
            Optional<Board> findBoard = homeworkRepository.findByBoardId(boardId);

            if (findMember.isPresent() && findBoard.isPresent()) {
                int money = 0;
                if (board.getResult().equals("실패")) {
                    money = -20000;
                    board.setDeposit(money);
                    board.setResult("실패");
                } else if (board.getResult().equals("미흡")) {
                    money = -10000;
                    board.setDeposit(money);
                    board.setResult("미흡");
                } else {
                    money = 0;
                    board.setDeposit(money);
                    board.setResult("성공");
                }

                homeworkRepository.gradeHomework(board, loginMember);

                List<Board> memberHomework = adminBoardService.getMemberHomework(memberId);
                int sum = memberHomework.stream().mapToInt(Board::getDeposit).sum();
                Long deposit = memberRepository.updateDeposit(findMember.get(), 120000 + (long) sum);

                // JSON 데이터 반환
                Map<String, Object> response = new HashMap<>();
                response.put("message", "업데이트 성공");
                response.put("deposit", deposit); // 현재 deposit 값

                return ResponseEntity.ok(response); // JSON 응답
            }
            return ResponseEntity.badRequest().body(Map.of("message", "유효하지 않은 요청입니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "서버 오류"));
        }
    }



}