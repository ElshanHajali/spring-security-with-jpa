package az.company.security.mapper.factory;

import az.company.security.dao.entity.RolesEntity;
import az.company.security.dto.request.RolesRequest;

public interface RoleFactory {

    static RolesEntity mapRequestToEntity(RolesRequest request) {
        return RolesEntity.builder()
                .name(request.getName().toUpperCase())
                .build();
    }
}
