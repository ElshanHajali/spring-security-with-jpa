package az.company.security.controller;

import az.company.security.dto.request.UsersRequest;
import az.company.security.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UsersRequest request) {
        usersService.registerUser(request);
    }

    @PatchMapping("{userName}/roles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRolesToUser(@PathVariable String userName,
                               @RequestParam(name = "role-names") Set<String> roleNames) {
        usersService.addRolesToUser(userName, roleNames);
    }

    @DeleteMapping("{userName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userName) {
        usersService.deleteUser(userName);
    }
}
