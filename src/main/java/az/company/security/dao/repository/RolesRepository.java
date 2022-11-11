package az.company.security.dao.repository;

import az.company.security.dao.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    Set<RolesEntity> findByNameIn(Set<String> roleNames);

    Optional<RolesEntity> findByName(String name);
}
