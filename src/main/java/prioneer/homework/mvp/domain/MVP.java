package prioneer.homework.mvp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import prioneer.homework.member.domain.Member;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class MVP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mvpId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mvp_member_id")
    private Member mvpMember;

    // 주차들 mvp 클릭 못하게 또는 하게 할려고 넣음
    private boolean flag;

    // 해당 주차의 MVP 22기 부원이 화면에 들어오면 계속 팝업창이 뜸 따라서 1주일만 뜨게 하고
    // false로 바꾸면 안뜨게 함 true하면 뜨고
    private boolean visual;

    private String week;

}
