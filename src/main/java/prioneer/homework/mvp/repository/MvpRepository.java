package prioneer.homework.mvp.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import prioneer.homework.mvp.domain.MVP;

import java.util.List;

@Repository
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MvpRepository {

    private final EntityManager em;


    public List<MVP> findAll() {
        return em.createQuery("select m from MVP m order by m.mvpId", MVP.class)
                .getResultList();
    }
}
