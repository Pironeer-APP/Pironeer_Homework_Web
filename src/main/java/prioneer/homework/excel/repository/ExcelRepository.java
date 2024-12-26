package prioneer.homework.excel.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import prioneer.homework.board.domain.Board;
import prioneer.homework.excel.domain.Excel;
import prioneer.homework.member.domain.Member;

import java.util.*;

import javax.swing.text.html.parser.Entity;

@Transactional
@Repository
@RequiredArgsConstructor
@Slf4j
public class ExcelRepository {
    private final EntityManager em;

    public List<Member> getAllMember(){
        return em.createQuery("select m from Member m where m.role='User' order by m.name", Member.class)
                .getResultList();
    }

    public List<Excel> getAllInfo(List<Member> members){
        List<Excel> excels =new ArrayList<>();
        for (Member member : members) {
            Excel excel=new Excel();
            List<Board> boards = em.createQuery("select b from Board b where b.userMember.memberId = :memberId order by b.id", Board.class)
                    .setParameter("memberId", member.getMemberId())
                    .getResultList();

            List<Board> newBoards=new ArrayList<>();
            for (Board board : boards) {
                Board newBoard=new Board();

                if(board.isFlag()){
                    if(board.getResult().equals("성공")){
                        newBoard.setResult("O");
                    }
                    else if(board.getResult().equals("미흡")){
                        newBoard.setResult("△");
                    }
                    else if(board.getResult().equals("실패")){
                        newBoard.setResult("X");
                    }
                }else{
                    newBoard.setResult("?");
                }

                newBoards.add(newBoard);
            }
            excel.setMember(member);
            excel.setBoardExcels(newBoards);
            excels.add(excel);

        }

        return excels;
    }
}
