package prioneer.homework.info.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import prioneer.homework.info.domain.Info;
import prioneer.homework.member.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Slf4j
@RequiredArgsConstructor
public class InfoRepository {

    private final EntityManager em;

    public void save(Info info){
        em.persist(info);
    }


    public Optional<Info> findById(Long id){
        try {
            return Optional.ofNullable(em.createQuery("select m from Info m where m.id = :id ", Info.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Info> infoALl(){
        return em.createQuery("select i from Info i order by i.id",Info.class)
                .getResultList();
    }
}
