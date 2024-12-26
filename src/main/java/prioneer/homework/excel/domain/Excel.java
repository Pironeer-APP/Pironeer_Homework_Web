package prioneer.homework.excel.domain;

import lombok.Getter;
import lombok.Setter;
import prioneer.homework.board.domain.Board;
import prioneer.homework.member.domain.Member;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Excel {

    private Member member;

    private List<Board> boardExcels =new ArrayList<>();
}
