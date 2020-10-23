package com.guillaumeyan.bugspringtest.config;

import com.guillaumeyan.bugspringtest.model.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.annotation.RequestScope;

import static java.util.Optional.of;


@Configuration
@EntityScan(basePackages = {"com.guillaumeyan.bugspringtest.domain"})
@EnableJpaRepositories(basePackages = {"com.guillaumeyan.bugspringtest.repos"})
@EnableTransactionManagement
@EnableJpaAuditing
public class DomainConfig {

    @Bean
    @RequestScope
    public User user() {
        return new User();
    }

    @Bean
    public AuditorAware<String> auditorProvider(User user) {
        return () -> of(user.getName());
    }
}
