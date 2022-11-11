package az.company.security.mapper.factory;

import az.company.security.dao.entity.AuthoritiesEntity;
import az.company.security.dto.request.AuthoritiesRequest;

public interface AuthorityFactory {

    static AuthoritiesEntity mapRequestToEntity(AuthoritiesRequest request) {
        return AuthoritiesEntity.builder()
                .name(request.getName())
                .build();
    }
}
