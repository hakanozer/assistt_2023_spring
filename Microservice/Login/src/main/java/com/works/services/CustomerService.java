package com.works.services;

import com.works.entities.Customer;
import com.works.entities.CustomerRedis;
import com.works.entities.DBSession;
import com.works.repositories.CustomerRedisRepository;
import com.works.repositories.CustomerRepository;
import com.works.repositories.DBSessionRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final TinkEncDec tinkEncDec;
    final HttpSession httpSession;
    final DBSessionRepository dbSessionRepository;
    final CustomerRedisRepository customerRedisRepository;

    public ResponseEntity register(Customer customer) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        boolean status = customerRepository.existsByEmailEqualsIgnoreCase(customer.getEmail());
        if ( status ) {
            hm.put(REnum.status, false);
            hm.put(REnum.errors, "This email in use : " + customer.getEmail());
            return new ResponseEntity( hm, HttpStatus.BAD_REQUEST );
        }else {
            customer.setPassword( tinkEncDec.encrypt(customer.getPassword()) );
            customerRepository.save(customer);
            hm.put(REnum.status, true);
            hm.put(REnum.result, customer);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
    }

    public ResponseEntity login( Customer customer ) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (optionalCustomer.isPresent() ) {
            Customer c = optionalCustomer.get();
            String dbPass = tinkEncDec.decrypt(c.getPassword());
            if ( dbPass.equals( customer.getPassword() ) ) {
                hm.put(REnum.status, true);
                hm.put(REnum.result, "Login Success");
                httpSession.setAttribute("user", c);
                String token = httpSession.getId();
                hm.put(REnum.token, token);

                // Redis Start
                Date date = new Date();
                CustomerRedis customerRedis = new CustomerRedis();
                customerRedis.setCustomerID(c.getCid());
                customerRedis.setEmail(c.getEmail());
                customerRedis.setStartTime(date.getTime());
                long endTime = 1000 * 60 * 10;
                endTime = date.getTime() + endTime;
                customerRedis.setEndTime( endTime );
                customerRedisRepository.save(customerRedis);
                System.out.println(customerRedis);
                // Redis End

                boolean dbControl = dbSessionRepository.existsBySessionID(token);
                if ( !dbControl ) {
                    DBSession dbSession = new DBSession();
                    dbSession.setSessionID(token);
                    dbSession.setStatus(true);
                    dbSessionRepository.save(dbSession);
                }

                return new ResponseEntity(hm, HttpStatus.OK);
            }
        }
        hm.put(REnum.status, false);
        hm.put(REnum.errors, "Email or Password Fail");
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity status(HttpServletRequest req) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        String token = req.getHeader("token");
        Optional<DBSession> optionalDBSession = dbSessionRepository.findBySessionIDEqualsAndStatusEquals(token, true);
        //String sessionID = req.getSession().getId();
        if ( token != null && optionalDBSession.isPresent() ) {
            hm.put(REnum.status, true);
            Customer c = (Customer)  req.getSession().getAttribute("user");
            hm.put(REnum.result, c );
            return new ResponseEntity(hm, HttpStatus.OK);
        }
        hm.put(REnum.status, false);
        hm.put(REnum.errors, "Token Error, Please Login");
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity logout() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        httpSession.invalidate();
        hm.put(REnum.status, true);
        hm.put(REnum.message, "Logout Success");
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
