package ru.agiletech.teammate.service.infrastructure.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.agiletech.teammate.service.infrastructure.persistence")
public class PersistenceConfig {
}
