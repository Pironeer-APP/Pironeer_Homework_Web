package prioneer.homework.member.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prioneer.homework.member.domain.Member;

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
        Member member1 = em.find(Member.class, member);
        member1.setMemberId("da"); // 이렇게만 하면 업데이트 됨
    }

    //회원 찾기
    public Optional<Member> findMemberId(Member member) {
        try {
            Member findMember = em.createQuery("select m from Member m where m.memberId = :id ", Member.class)
                    .setParameter("id", member.getMemberId())
                    .getSingleResult();
            return Optional.of(findMember);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    // 전화번호 정보로 회원 유무 찾기
    public boolean existsByPhone(String phone) {
        try {
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


}
