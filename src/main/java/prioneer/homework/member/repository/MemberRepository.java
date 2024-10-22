package prioneer.homework.member.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prioneer.homework.member.domain.Member;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
@Slf4j
public class MemberRepository {

    private final EntityManager em;

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
}
