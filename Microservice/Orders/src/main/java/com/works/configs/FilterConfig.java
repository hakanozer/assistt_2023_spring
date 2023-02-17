package com.works.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final DiscoveryClient discoveryClient;
    final RestTemplate restTemplate;
    final Tracer tracer;

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("SERVER UP");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Span Id
        String spanid = tracer.currentSpan().context().spanId();
        res.setHeader("spanid", spanid);

        String url = req.getRequestURI();
        String token = req.getHeader("token");
        if ( !url.equals("/loginError") ) {
            if (token == null || token.isEmpty()) {
                res.sendRedirect("/loginError");
            } else {
                List<ServiceInstance> ls = discoveryClient.getInstances("login");
                ServiceInstance instance = ls.get(0);
                String gotoLoginUrl = instance.getUri().toString();
                gotoLoginUrl = gotoLoginUrl + "/customer/status";
                System.out.println(token);
                System.out.println( gotoLoginUrl );

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                httpHeaders.add("token", token);
                HttpEntity httpEntity = new HttpEntity("", httpHeaders);
                ResponseEntity<Login> responseEntity = restTemplate.postForEntity(gotoLoginUrl, httpEntity, Login.class );
                Login login = responseEntity.getBody();
                if ( login.getStatus() ) {
                    chain.doFilter(req, res);
                }else {
                    res.sendRedirect("/loginError");
                }
            }
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
        System.out.println("SERVER DOWN");
    }
}


class Login {
    private Boolean status;
    private Object result;
    private Object errors;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
