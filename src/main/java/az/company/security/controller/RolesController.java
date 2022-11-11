package az.company.security.controller;

import az.company.security.dto.request.AuthoritiesRequest;
import az.company.security.dto.request.RolesRequest;
import az.company.security.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/roles")
@RequiredArgsConstructor
public class RolesController {
    private final RolesService rolesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRole(@RequestBody RolesRequest request) {
        rolesService.saveRole(request);
    }

    @PostMapping("authorities")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAuthority(@RequestBody AuthoritiesRequest request) {
        rolesService.saveAuthority(request);
    }

    @PatchMapping("{roleName}/authorities")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addAuthoritiesToRole(
            @PathVariable String roleName,
            @RequestParam(name = "authority-names") Set<String> authorityNames) {
        rolesService.addAuthoritiesToRole(roleName, authorityNames);
    }

    @DeleteMapping("{roleName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable String roleName) {
        rolesService.deleteRole(roleName);
    }
}
