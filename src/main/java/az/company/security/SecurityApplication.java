package az.company.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(
//            UsersRepository usersRepository,
//            PasswordEncoder passwordEncoder
//    ) {
//        return args -> {
//
//            Set<String> roleIds = new HashSet<>();
//
//            UsersEntity tom = UserFactory.buildUserEntity(
//                    new UsersRequest("tom", "1234", roleIds),
//                    passwordEncoder
//            );
//
//            usersRepository.save(tom);
//        };
//    }
}
