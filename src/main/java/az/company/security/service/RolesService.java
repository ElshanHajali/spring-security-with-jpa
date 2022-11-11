package az.company.security.service;

import az.company.security.dao.entity.RolesEntity;
import az.company.security.dao.repository.AuthoritiesRepository;
import az.company.security.dao.repository.RolesRepository;
import az.company.security.dto.request.AuthoritiesRequest;
import az.company.security.dto.request.RolesRequest;
import az.company.security.dto.constant.ExceptionConstants;
import az.company.security.exception.UsernameNotFoundExceptionResponse;
import az.company.security.mapper.factory.AuthorityFactory;
import az.company.security.mapper.factory.RoleFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RolesService {
    private final RolesRepository rolesRepository;
    private final AuthoritiesRepository authoritiesRepository;

    public void saveRole(RolesRequest request) {
        log.info("ActionLog.saveRole.start");
        RolesEntity role = RoleFactory.mapRequestToEntity(request);

        if (request.getAuthorityNames() != null &&
                !request.getAuthorityNames().isEmpty()) {
            var authorities =
                    authoritiesRepository.findByNameIn(request.getAuthorityNames());

            role.setAuthorities(authorities);
        }

        rolesRepository.save(role);
        log.info("ActionLog.saveRole.success");
    }

    public void addAuthoritiesToRole(String roleName, Set<String> authorityNames) {
        log.info("ActionLog.addAuthoritiesToRole.start");
        RolesEntity role = fetchByRoleName(roleName);

        role.setAuthorities(authoritiesRepository
                .findByNameIn(authorityNames.stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet())
                ));

        rolesRepository.save(role);
        log.info("ActionLog.addAuthoritiesToRole.success");
    }

    public void saveAuthority(AuthoritiesRequest request) {
        log.info("ActionLog.saveAuthority.start");
        authoritiesRepository.save(AuthorityFactory.mapRequestToEntity(request));
        log.info("ActionLog.saveAuthority.success");
    }

    @Transactional
    public void deleteRole(String roleName) {
        log.info("ActionLog.deleteRole.start");
        rolesRepository.delete(fetchByRoleName(roleName));
        log.info("ActionLog.deleteRole.success");
    }

    private RolesEntity fetchByRoleName(String roleName) {
        return rolesRepository.findByName(roleName.toUpperCase())
                .orElseThrow(() -> new UsernameNotFoundExceptionResponse(
                        String.format(ExceptionConstants.NOT_FOUND_MESSAGE, roleName),
                        ExceptionConstants.NOT_FOUND_CODE
                ));
    }
}












