package prioneer.homework.member.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import prioneer.homework.board.domain.Board;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    String memberId;

    @JsonIgnore
    @OneToMany(mappedBy = "userMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> userBoard = new ArrayList<>();

    @OneToMany(mappedBy = "adminMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> adminBoard = new ArrayList<>();


    @Transient
    private String passwordCheck;

    @Column(columnDefinition = "varchar(255) default '0'")
    private String password;

    private String name;

    private String phone;

    // 현재 보증금
    @Column(columnDefinition = "bigint default '0'")
    private String deposit;

    // 보증금 방어 횟수
    @Column(columnDefinition = "bigint default '0'")
    private String depositDepend;

    //원래 enum으로 할려했는데 좀 복잡함 이게.... 그래서 그냥 이렇게 함
    private String role;
}