package prioneer.homework.member.service.admin;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import prioneer.homework.board.domain.Board;
import prioneer.homework.board.repository.HomeworkRepository;
import prioneer.homework.info.domain.Info;
import prioneer.homework.info.repository.InfoRepository;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminMemberService {
    private final MemberRepository memberRepository;
    private final HomeworkRepository homeworkRepository;
    private final InfoRepository infoRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final long INITIAL_DEPOSIT = 120000L;
    private static final long INITIAL_DEPOSIT_DEPEND = 0L;
    private static final String MEMBER_ROLE = "USER";

    // 회원가입
    public String join(Member member) {


        boolean flag = memberRepository.existsByPhone(member.getPhone());
        if (flag) {
            return "phone";
        }

        //비밀번호 체크
        if (!Objects.equals(member.getPassword(), member.getPasswordCheck())) {
            return "password";
        }

        // 초기 설정: preadmin
        member.setRole("PREADMIN");
        member.setMemberId(UUID.randomUUID().toString());
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(member);

        return null;

    }


    //비번 체크
    public Member passwordCheck(Member member) {
        return memberRepository.findByPhone(member.getPhone())
                .filter(m -> bCryptPasswordEncoder.matches(member.getPassword(), m.getPassword()))
                .orElse(null);
    }


    // 로그인 시도와 회원 정보 간 일치 조회
    public Optional<Member> getLoginMember(Member loginMember) {
        return memberRepository.findByPhone(loginMember.getPhone());
    }

    public Optional<Member> getNameMember(Member loginMember) {
        return memberRepository.findByName(loginMember.getName());
    }

    // 회원 삭제
    public void deleteMember(String phone) {
        if (!memberRepository.existsByPhone(phone)) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }

        try {
            memberRepository.deleteByPhone(phone);
        } catch (Exception e) {
            throw new IllegalStateException("회원 삭제 중 오류 발생");
        }
    }

    // 보증금, 갯수 업데이트
    public void updateDeposit(Member member) {

        try {
            memberRepository.update(member);
        } catch (Exception e) {
            throw new IllegalStateException("회원 삭제 중 오류 발생");
        }
    }


    // preadmin 목록 조회
    public List<Member> getPreadminList() {
        List<Member> preadminList = memberRepository.findByRole2();
        return preadminList;
    }

//    // 역할 변경 가능 여부 검증
//    public void validateRoleChange(Member member, String newRole) {
//        if (!"preadmin".equals(member.getRole())) {
//            throw new IllegalStateException("예비 관리자만 권한 변경이 가능합니다.");
//        }
//
//        if (!"admin".equals(newRole)) {
//            throw new IllegalStateException("권한은 admin으로만 변경 가능합니다.");
//        }
//    }

    // preadmin의 삭제
    public void deletePreadmin(String id) {
        Optional<Member> findMember = memberRepository.findMemberById(id);
        if (findMember.isPresent()) {
            memberRepository.remove(findMember.get());
        } else {
            throw new IllegalStateException("삭제 중 에러 발생");
        }
    }

    // 부원 리스트 가져오기
    public List<Member> getMemberList() {
        List<Member> memberList = memberRepository.findByRole(MEMBER_ROLE);
        return memberList;
    }

    // 신규 부원 등록
    public void registerNewMember(Member member) {

        try {
            if (memberRepository.existsByPhone(member.getPhone())) {
                throw new IllegalStateException("이미 등록된 전화번호입니다.");
            }

            // 신규 부원 초기값
            member.setRole(MEMBER_ROLE);
            member.setDeposit(INITIAL_DEPOSIT);
            member.setDepositDepend(INITIAL_DEPOSIT_DEPEND);
            memberRepository.save(member);


            //신규 부원 생성했으면 과제 18개??도 같이 만들어줘야됨
            // ㅋㅋㅋ 서비스 계층에서 for문이라 신비롭네
            for (int i = 1; i < 19; i++) {
                Optional<Info> info = infoRepository.findById((long) i);
                if (info.isPresent()) {
                    Info f_info = info.get();
                    Board board = new Board();
                    board.setUserMember(member);
                    board.setInfo(f_info);
                    board.setFlag(false);
                    board.setResult("O");
                    homeworkRepository.save(board);

                }
            }

        } catch (Exception e) {
            throw new IllegalStateException("회원 등록 중 오류 발생");
        }
    }

}
