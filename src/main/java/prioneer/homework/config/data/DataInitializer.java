package prioneer.homework.config.data;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import prioneer.homework.info.domain.Info;
import prioneer.homework.info.repository.InfoRepository;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;

import java.time.LocalDate;

//초기 데이터 생성했으면 component 바로 주석처리 해주세요.
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final InfoRepository infoRepository;
    private final MemberRepository memberRepository;


//    @Bean
//    public CommandLineRunner initDatabase() {
//        return args -> {
//            // 서버 시작 시 데이터베이스에 초기값을 삽입합니다.
//            initializeMembers();
//        };
//    }

    @PostConstruct
    public void init() {
        initializeInfo();
        //initializeNewMember();

    }

    // 신규 부원 초기화
    @PostConstruct
    public void initializeNewMember() {

        final String INITIAL_MEMBER_ROLE = "user";
        final Long INITIAL_DEPOSIT = 120000L;
        final Long INITIAL_DEPOSIT_DEPEND = 0L;

        Member defaultMember = new Member();

        defaultMember.setRole(INITIAL_MEMBER_ROLE);
        defaultMember.setDeposit(INITIAL_DEPOSIT);
        defaultMember.setDepositDepend(INITIAL_DEPOSIT_DEPEND);

    }

    @PostConstruct
    public void initializeInfo() {
        // 데이터베이스 초기화를 위한 코드 작성
        Info info1 = new Info("제로초27강","1");
        Info info2 = new Info("깃허브클론코딩","1");
        Info info3 = new Info("CSS반응형","1");
        Info info4 = new Info("코딩도장20강","1");
        Info info5 = new Info("팀플(ARSHA)","1");

        Info info6 = new Info("쇼핑몰 미니게임","2");
        Info info7 = new Info("숫자야구","2");
        Info info8 = new Info("JS 스톱워치","2");
        Info info9 = new Info("코딩도장 심화","2");
        Info info10 = new Info("파이썬 개인과제","2");
        Info info11 = new Info("팀플(파이썬 술게임)","2");

        Info info12 = new Info("장고걸스","3");
        Info info13 = new Info("나만의 영화","3");
        Info info14 = new Info("ERD","3");
        Info info15 = new Info("AJAX 선행강의","3");

        Info info16 = new Info("SWIDEA","4");
        Info info17 = new Info("팀플(카드게임)","4");
        Info info18 = new Info("AJAX","4");

        infoRepository.save(info1);
        infoRepository.save(info2);
        infoRepository.save(info3);
        infoRepository.save(info4);
        infoRepository.save(info5);
        infoRepository.save(info6);
        infoRepository.save(info7);
        infoRepository.save(info8);
        infoRepository.save(info9);
        infoRepository.save(info10);
        infoRepository.save(info11);
        infoRepository.save(info12);
        infoRepository.save(info13);
        infoRepository.save(info14);
        infoRepository.save(info15);
        infoRepository.save(info16);
        infoRepository.save(info17);
        infoRepository.save(info18);

    }

}