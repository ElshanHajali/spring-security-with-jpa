package az.company.security.service;

import az.company.security.dao.entity.RolesEntity;
import az.company.security.dao.entity.UsersEntity;
import az.company.security.dao.repository.RolesRepository;
import az.company.security.dao.repository.UsersRepository;
import az.company.security.dto.request.UsersRequest;
import az.company.security.dto.constant.ExceptionConstants;
import az.company.security.exception.UsernameNotFoundExceptionResponse;
import az.company.security.mapper.factory.UserFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UsersRequest request) {
        log.info("ActionLog.registerUser.start");
        UsersEntity user = UserFactory.mapRequestToEntity(request, passwordEncoder);

        if (request.getRoleNames() != null && !request.getRoleNames().isEmpty()) {
            var roles = rolesRepository.findByNameIn(
                    request.getRoleNames().stream()
                            .map(String::toUpperCase)
                            .collect(Collectors.toSet())
            );

            user.setRoles(roles);
        }

        usersRepository.save(user);
        log.info("ActionLog.registerUser.success");
    }

    public void addRolesToUser(String userName, Set<String> roleNames) {
        log.info("ActionLog.addRolesToUser.start");
        UsersEntity user = fetchUserByUsername(userName);

        Set<RolesEntity> rolesByNameIn =
                rolesRepository.findByNameIn(
                        roleNames.stream()
                                .map(String::toUpperCase)
                                .collect(Collectors.toSet())
                );

        user.setRoles(rolesByNameIn);

        usersRepository.save(user);
        log.info("ActionLog.addRolesToUser.success");
    }

    public void deleteUser(String username) {
        log.info("ActionLog.deleteUser.start");
        usersRepository.delete(fetchUserByUsername(username));
        log.info("ActionLog.deleteUser.success");
    }

    private UsersEntity fetchUserByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundExceptionResponse(
                        String.format(ExceptionConstants.NOT_FOUND_MESSAGE, username),
                        ExceptionConstants.NOT_FOUND_CODE
                ));
    }
}
