package com.works.services;

import com.works.entities.Login;
import com.works.repositories.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    final LoginRepository loginRepository;

    // Register
    public boolean register(Login login) {
        try {
            loginRepository.save(login);
            System.out.println(login);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }


}
