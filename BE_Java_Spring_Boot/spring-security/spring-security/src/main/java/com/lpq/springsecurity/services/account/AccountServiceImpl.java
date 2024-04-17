package com.lpq.springsecurity.services.account;

import com.lpq.springsecurity.commons.PasswordHelper;
import com.lpq.springsecurity.entities.Roles;
import com.lpq.springsecurity.entities.Users;
import com.lpq.springsecurity.entities.enums.AccountStatus;
import com.lpq.springsecurity.entities.enums.ERole;
import com.lpq.springsecurity.exceptions.ResourceNotFoundException;
import com.lpq.springsecurity.payloads.request.CreateAccountRequest;
import com.lpq.springsecurity.payloads.response.CreateAccountResponse;
import com.lpq.springsecurity.repositories.IAccountRepository;
import com.lpq.springsecurity.repositories.IRolesRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService{

    private final IAccountRepository accountRepository;

    private final ModelMapper mapper; // config

    private final IRolesRepository rolesRepository;

    private final MessageSource messageSource;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {

        Users account = mapper.map(request, Users.class);

        account.setCreateAt(LocalDateTime.now());
        account.setFirstLogin(true);
        account.setStatus(AccountStatus.ACTIVE);
        account.setPassword(PasswordHelper.encode(request.getPassword()));

        Roles role = rolesRepository.findRolesByRoleName(ERole.ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("api.role.role-not-found", null,
                                "api.role.role-not-found", LocaleContextHolder.getLocale())));
        account.setRoles(new HashSet<>(Collections.singletonList(role)));

        accountRepository.save(account);// check nullable at entity before

        CreateAccountResponse createAccountResponse = mapper.map(account, CreateAccountResponse.class);
        createAccountResponse.setRolesAccountResponse(account.getRoles());

        return createAccountResponse;
    }
}
