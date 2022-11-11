package az.company.security.service;

import az.company.security.dao.entity.UsersEntity;
import az.company.security.dao.repository.UsersRepository;
import az.company.security.dto.constant.ExceptionConstants;
import az.company.security.dto.response.UsersResponse;
import az.company.security.exception.UsernameNotFoundExceptionResponse;
import az.company.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ActionLog.loadUserByUsername.start");
        UsersEntity user = fetchUserByUsername(username);

        UsersResponse userResponse = UserMapper.mapEntityToResponse(user);

        log.info("ActionLog.loadUserByUsername.success");
        return UserMapper.mapResponseToSecurityUser(userResponse, grantedAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> grantedAuthorities(UsersEntity user) {
        log.info("ActionLog.grantedAuthorities.start");
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));

            role.getAuthorities().forEach(authority -> {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
            });
        });
        log.info("ActionLog.grantedAuthorities.success");
        return simpleGrantedAuthorities;
    }

    private UsersEntity fetchUserByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundExceptionResponse(
                        String.format(ExceptionConstants.NOT_FOUND_MESSAGE, username),
                        ExceptionConstants.NOT_FOUND_CODE
                ));
    }
}
