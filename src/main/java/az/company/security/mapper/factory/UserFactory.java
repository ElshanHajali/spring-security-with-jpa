package az.company.security.mapper.factory;

import az.company.security.dao.entity.UsersEntity;
import az.company.security.dto.request.UsersRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserFactory {
    static UsersEntity mapRequestToEntity(UsersRequest request, PasswordEncoder passwordEncoder) {
        return UsersEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }
}
