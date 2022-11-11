package az.company.security.dto.response;

import az.company.security.dto.request.UsersRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class SecurityUser implements UserDetails {
    private final UsersResponse response;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    @Override
    public String getUsername() {
        return response.getUsername();
    }

    @Override
    public String getPassword() {
        return response.getPassword();
    }

}
