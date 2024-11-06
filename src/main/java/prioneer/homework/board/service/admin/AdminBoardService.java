package prioneer.homework.board.service.admin;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prioneer.homework.board.domain.Board;
import prioneer.homework.board.repository.HomeworkRepository;
import prioneer.homework.member.domain.Member;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class AdminBoardService {

    private final HomeworkRepository homeworkRepository;

    // 관리자 여부 확인
    public static boolean isAdmin(Member loginMember) {
        return loginMember != null && loginMember.getRole().equals("admin");
    }

    // 회원 과제 목록 조회
    public List<Board> getMemberHomework(Long memberid) {
        List<Board> homeworkList = homeworkRepository.findByMemberId(memberid);
        return homeworkList;
    }

    // 과제 채점
    public void gradeHomework(Long boardId, String result, String comment) {
        Board homework = homeworkRepository.findByBoardId(boardId)
                .orElseThrow(() -> new IllegalArgumentException("과제를 찾을 수 없습니다"));

        homework.setResult(result);
        homework.setComment(comment);
        homeworkRepository.save(homework);

    }




}
