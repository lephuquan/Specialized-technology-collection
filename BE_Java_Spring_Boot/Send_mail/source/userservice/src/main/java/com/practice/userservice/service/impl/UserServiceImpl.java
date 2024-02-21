package com.practice.userservice.service.impl;

import com.practice.userservice.domain.Confirmation;
import com.practice.userservice.domain.User;
import com.practice.userservice.repository.ConfirmationRepository;
import com.practice.userservice.repository.UserRepository;
import com.practice.userservice.service.EmaiService;
import com.practice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmaiService emaiService;


    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation  = new Confirmation(user);
        confirmationRepository.save(confirmation);

        /* TODO send mail to user with token */
//        emaiService.sendSimpleMailMessage(user.getName(), user.getEmail(), confirmation.getToken());
//        emaiService.sendMineMessageWithAttachments(user.getName(), user.getEmail(), confirmation.getToken());
//        emaiService.sendMineMessageWithEmbeddedFiles(user.getName(), user.getEmail(), confirmation.getToken());
//        emaiService.sendHtmlEmail(user.getName(), user.getEmail(), confirmation.getToken());
        emaiService.sendHtmlEmailWithEmbeddedFiles(user.getName(), user.getEmail(), confirmation.getToken());

        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        confirmationRepository.delete(confirmation);
        return Boolean.TRUE;
    }
}
