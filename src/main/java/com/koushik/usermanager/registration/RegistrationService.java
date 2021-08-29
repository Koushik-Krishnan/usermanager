package com.koushik.usermanager.registration;

import com.koushik.usermanager.domain.User;
import com.koushik.usermanager.domain.UserRole;
import com.koushik.usermanager.registration.token.ConfirmationToken;
import com.koushik.usermanager.registration.token.ConfirmationTokenService;
import com.koushik.usermanager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public User register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail)
            throw new IllegalStateException("Email not valid");
        else {
            return userService.signUpUser(
                    new User(request.getFirstName(),
                            request.getLastName(),
                            request.getEmail(),
                            request.getPassword(),
                            UserRole.USER)
            );
        }
    }


    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));
            if (confirmationToken.getConfirmedAt() != null) {
                throw new IllegalStateException("email already confirmed");
            }
            LocalDateTime expiredAt = confirmationToken.getExpiredAt();

            if (expiredAt.isBefore(LocalDateTime.now())) {
                throw new IllegalStateException("token expired");
            }

            confirmationTokenService.setConfirmedAt(token);
            userService.enableUser(
                    confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
