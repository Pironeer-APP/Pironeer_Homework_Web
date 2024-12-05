package prioneer.homework.member.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prioneer.homework.member.domain.Member;
import prioneer.homework.member.service.admin.AdminMemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
@Slf4j
public class MemberRepository {

    private final EntityManager em;


    // 회원 저장
    public void save(Member member){
        em.persist(member);
    }

    // 회원 업데이트
    public void update(Member member){
        log.info("zzz");
        Member member1 = em.find(Member.class, member.getMemberId());
        member1.setDepositDepend(member.getDepositDepend());

    }


    //보증금 업데이트
    public Long updateDeposit(Member member, Long money){
        Member member1 = em.find(Member.class, member.getMemberId());
        member1.setDeposit(money);

        return money;
    }

    //회원 찾기
    public Optional<Member> findMemberById(String memberId) {
        try {
            Member findMember = em.createQuery("select m from Member m where m.memberId = :id ", Member.class)
                    .setParameter("id", memberId)
                    .getSingleResult();
            return Optional.of(findMember);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    //회원삭제
    public void remove(Member member){
        em.remove(member);
    }


    // 전화번호 정보로 회원 유무 찾기
    public boolean existsByPhone(String phone) {
        try {
            log.info(phone);
            em.createQuery("select m from Member m where m.phone = :phone ", Member.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
            return true; // 결과가 있으면 true 반환
        } catch (NoResultException e) {
            return false; // 결과가 없으면 false 반환
        }
    }

    // 전화번호로 회원 찾기
    public Optional<Member> findByPhone(String phone) {
        try {
            Member findMember = em.createQuery("select m from Member m where m.phone = :phone", Member.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
            return Optional.of(findMember);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    // 이름으로 회원 찾기
    public Optional<Member> findByName(String name) {
        try {
            Member findMember = em.createQuery("select m from Member m where m.name = :name", Member.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(findMember);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    // 역할 정보로 회원들 찾기
    public List<Member> findByRole(String role) {
        try {
            List<Member> findMembers = em.createQuery("select m from Member m where m.role = :role ", Member.class)
                    .setParameter("role", role)
                    .getResultList();
            return findMembers;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }


    public List<Member> findByRole2() {
        try {
            List<Member> findMembers = em.createQuery(
                            "select m from Member m where m.role = :role OR m.role = :role2 " +
                                    "order by case when m.role = 'PREADMIN' then 0 when m.role = 'ADMIN' then 1 else 2 end", Member.class)
                    .setParameter("role", "ADMIN")
                    .setParameter("role2", "PREADMIN")
                    .getResultList();
            return findMembers;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    //  전화번호 정보로 회원 삭제
    public void deleteByPhone(String phone) {
        try {
            Member findMember = em.createQuery("select m from Member m where m.phone = :phone ", Member.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
            em.remove(findMember);
            log.info("회원 삭제 완료: {}", phone);
        } catch (NoResultException e) {
            log.error("존재하지 않는 회원: {}", phone);
            throw e;
        }
    }

    // admin으로 승격
    public void updateToAdmin(String memberId,String role) {

        Member member = em.find(Member.class, memberId);
        member.setRole(role);
        log.info("ㅎㅇ");
    }
}
