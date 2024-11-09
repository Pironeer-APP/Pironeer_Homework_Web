package prioneer.homework.member.service.admin;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.repository.MemberRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminMemberService {
    private final MemberRepository memberRepository;

    private static final long INITIAL_DEPOSIT = 120000L;
    private static final long INITIAL_DEPOSIT_DEPEND = 0L;
    private static final String MEMBER_ROLE = "user";

    // 회원가입
    public void join(Member member) {
        try {
            // 이미 있는 회원인지 확인
            validateDuplicateMember(member);

            // 초기 설정: preadmin
            member.setRole("preadmin");

            memberRepository.save(member);
        } catch (Exception e) {
            throw new IllegalStateException("회원가입 중 오류 발생");
        }
    }

    // 중복 회원가입 검증
    public void validateDuplicateMember(Member member) {
        if (memberRepository.existsByPhone(member.getPhone())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 로그인 시도와 회원 정보 간 일치 조회
    public Member getLoginMember(Member loginMember) {
        if (loginMember == null) {
            throw new IllegalStateException("로그인 정보가 없습니다");
        }

        return memberRepository.findMemberById(loginMember)
                .orElseThrow(() -> new IllegalStateException("회원 정보를 찾을 수 없습니다."));
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

    // preadmin 목록 조회
    public List<Member> getPreadminList() {
        List<Member> preadminList = memberRepository.findByRole("preadmin");
        return preadminList;
    }

    // 역할 변경 가능 여부 검증
    public void validateRoleChange(Member member, String newRole) {
        if (!"preadmin".equals(member.getRole())) {
            throw new IllegalStateException("예비 관리자만 권한 변경이 가능합니다.");
        }

        if (!"admin".equals(newRole)) {
            throw new IllegalStateException("권한은 admin으로만 변경 가능합니다.");
        }
    }

    // preadmin의 삭제
    public void deletePreadmin(Member member) {
        try {
            // 우선 preadmin인지 확인
            Member preadminMember = memberRepository.findMemberById(member)
                    .orElseThrow(() -> new IllegalStateException("회원을 찾을 수 없습니다."));

            if (!"preadmin".equals(preadminMember.getRole())) {
                throw new IllegalStateException("예비 관리자만 삭제 가능합니다.");
            }

            memberRepository.deleteByPhone(preadminMember.getPhone());
        } catch (Exception e) {
            throw new IllegalStateException("삭제 중 에러 발생");
        }
    }

    // 부원 리스트 가져오기
    public List<Member> getMemberList() {
        List<Member> memberList = memberRepository.findByRole("member");
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
        } catch (Exception e) {
            throw new IllegalStateException("회원 등록 중 오류 발생");
        }
    }

}
