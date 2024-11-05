package prioneer.homework.board.repository;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import prioneer.homework.board.domain.Board;
import prioneer.homework.member.domain.Member;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HomeworkRepository {

    private final EntityManager em;

    public List<Board> findMemberHomework(Member member){
        return em.createQuery("select b from Board b where b.userMember.memberId= :id", Board.class)
                .setParameter("id",member.getMemberId())
                .getResultList();
    }
}
