package prioneer.homework.board.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import prioneer.homework.board.domain.Board;
import prioneer.homework.member.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HomeworkRepository {

    private final EntityManager em;

    //신규회원 추가될때 과제 18개 저장
    public void save(Board board){
        em.persist(board);
    }

    public List<Board> findMemberHomework(Member member){
        return em.createQuery("select b from Board b where b.userMember.memberId= :id order by b.id", Board.class)
                .setParameter("id",member.getMemberId())
                .getResultList();
    }

    public List<Board> findByMemberId(String memberId) {
        return em.createQuery("select b from Board b where b.userMember.memberId = :id", Board.class)
                .setParameter("id", memberId)
                .getResultList();
    }

    public Optional<Board> findByBoardId(Long boardId) {
        try {
            Board findMember = em.createQuery("select m from Board m where m.boardId = :phone", Board.class)
                    .setParameter("phone", boardId)
                    .getSingleResult();
            return Optional.of(findMember);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    // 과제 채점
    public void gradeHomework(Board board,Member adminMember) {

        Board homework = em.find(Board.class, board.getBoardId());

        log.info(String.valueOf(board.getDeposit()));
        log.info(board.getResult());
        homework.setResult(board.getResult());
        homework.setFlag(board.isFlag());
        homework.setAdminMember(adminMember);
        homework.setDeposit(board.getDeposit());


    }
}
