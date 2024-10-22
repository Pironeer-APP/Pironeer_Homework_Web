package prioneer.homework.board.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import prioneer.homework.info.domain.Info;
import prioneer.homework.member.domain.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_member_id")
    private Member userMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_member_id")
    private Member adminMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "info_id")
    private Info info;

    // 과제 결과 커멘트 - 관리자가 달고 22기 부원들이 볼 수 있음
    private String comment;

    // 과제 결과 (통과, 미흡, 불성실) - 관리자가 달고 22기 부원들이 볼 수 있음
    private String result;

    // 과제 보여줄지 안보여줄지 - 관리자, 체크박스는 22기 부원들에게 보여주면 안됨
    private boolean flag;


}