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

    private boolean flag;

    private String week;

}
