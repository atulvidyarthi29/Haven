package com.start.haven;

import com.start.haven.users.dao.HavenUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = HavenUserRepository.class)
public class HavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(HavenApplication.class, args);
    }

}
