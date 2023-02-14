package com.works.services;

import com.works.entities.Login;
import com.works.repositories.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    final LoginRepository loginRepository;
    final TinkEncDec tinkEncDec;

    // Register
    public boolean register(Login login) {
        try {
            login.setPassword( tinkEncDec.encrypt( login.getPassword()) );
            loginRepository.save(login);
            System.out.println(login);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }


    public boolean login( Login login ) {
        Optional<Login> optionalLogin = loginRepository.findByEmailEqualsIgnoreCase(login.getEmail());
        if (optionalLogin.isPresent()) {
            Login lgn = optionalLogin.get();
            String pass = tinkEncDec.decrypt(lgn.getPassword());
            if ( login.getPassword().equals(pass) ) {
                return true;
            }
        }
        return false;
    }


}
