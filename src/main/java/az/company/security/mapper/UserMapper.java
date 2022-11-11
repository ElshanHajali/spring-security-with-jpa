package az.company.security.mapper;

import az.company.security.dao.entity.UsersEntity;
import az.company.security.dto.response.SecurityUser;
import az.company.security.dto.response.UsersResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface UserMapper {

    static UsersResponse mapEntityToResponse(UsersEntity user) {
        return new UsersResponse(user.getUsername(), user.getPassword());
    }

    static UserDetails mapResponseToSecurityUser(
            UsersResponse userResponse,
            Collection<? extends GrantedAuthority> grantedAuthorities) {
        return new SecurityUser(userResponse, grantedAuthorities,
                true, true,
                true, true);
    }
}
