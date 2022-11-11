package az.company.security.exception;

import lombok.Getter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Getter
public class UsernameNotFoundExceptionResponse extends UsernameNotFoundException {
    private final String code;

    public UsernameNotFoundExceptionResponse(String msg, String code) {
        super(msg);
        this.code = code;
    }
}
