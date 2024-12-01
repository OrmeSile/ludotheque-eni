package dev.orme.ludotheque.configuration.initialization;

import dev.orme.ludotheque.entities.Role;
import dev.orme.ludotheque.objects.RoleType;
import dev.orme.ludotheque.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final Logger logger =  LoggerFactory.getLogger(DataLoader.class);
    @Autowired
    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        var roles = Arrays.asList(new Role(RoleType.ROLE_USER), new Role(RoleType.ROLE_CLERK),
                new Role(RoleType.ROLE_ADMIN));
        try {
            roleRepository.saveAll(roles);
        }catch (Exception ignored){
            logger.warn("entries {} already present in database.", Arrays.toString(roles.toArray()));
        }
    }
}
