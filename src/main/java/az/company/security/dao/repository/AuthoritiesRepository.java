package az.company.security.dao.repository;

import az.company.security.dao.entity.AuthoritiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthoritiesRepository extends JpaRepository<AuthoritiesEntity, Long> {
    Set<AuthoritiesEntity> findByNameIn(Set<String> name);
}
