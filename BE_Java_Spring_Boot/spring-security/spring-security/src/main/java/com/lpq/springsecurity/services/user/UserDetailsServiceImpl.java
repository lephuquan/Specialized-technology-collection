package com.lpq.springsecurity.services.user;

import com.lpq.springsecurity.entities.Users;
import com.lpq.springsecurity.entities.enums.AccountStatus;
import com.lpq.springsecurity.repositories.IUserRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository iUserRepository;

    private final MessageSource messageSource;

    public UserDetailsServiceImpl(
            IUserRepository iUserRepository,
            MessageSource messageSource
    ) {
        this.iUserRepository = iUserRepository;
        this.messageSource = messageSource;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
//       Optional<Users>  user = iUserRepository.findUsersByEmailAndStatus(email, AccountStatus.ACTIVE); // check account locked
        Optional<Users>  user = iUserRepository.findUsersByEmail(email);
       if (user.isPresent()) {
           return UserDetailsImpl.build(user.get());
       }
       throw new UsernameNotFoundException(
               messageSource.getMessage("api.login.error.emailNotExists", null, LocaleContextHolder.getLocale()));
    }
}
