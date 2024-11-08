package prioneer.homework.board.service.admin;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prioneer.homework.board.domain.Board;
import prioneer.homework.board.repository.HomeworkRepository;
import prioneer.homework.member.domain.Member;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor

public class AdminBoardService {

    private final HomeworkRepository homeworkRepository;

    // refactor: master 추가, 상수로 관리
    private static final Set<String> ADMIN_ROLES = Set.of("admin", "master");

    // 관리자 여부 확인
    public static boolean isAdmin(Member loginMember) {
        return loginMember != null && ADMIN_ROLES.contains(loginMember.getRole());
    }

    // 회원 과제 목록 조회
    public List<Board> getMemberHomework(Long memberid) {
        List<Board> homeworkList = homeworkRepository.findByMemberId(memberid);
        return homeworkList;
    }
}
