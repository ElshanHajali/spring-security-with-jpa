package az.company.security.dto.response;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class UsersResponse {

    private final String username;
    private final String password;

}
