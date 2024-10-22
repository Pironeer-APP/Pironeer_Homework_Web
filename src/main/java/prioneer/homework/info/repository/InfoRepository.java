package prioneer.homework.info.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import prioneer.homework.info.domain.Info;

@Repository
@Transactional
@Slf4j
@RequiredArgsConstructor
public class InfoRepository {

    private final EntityManager em;

    public void save(Info info){
        em.persist(info);
    }
}
