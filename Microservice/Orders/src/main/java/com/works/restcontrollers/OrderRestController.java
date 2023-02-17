package com.works.restcontrollers;

import com.google.gson.Gson;
import com.works.IFeigns.INews;
import com.works.IFeigns.IProduct;
import com.works.IFeigns.Idummy;
import com.works.props.JWTLogin;
import com.works.props.JWTUser;
import com.works.props.JmsData;
import com.works.props.News;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderRestController {

    final IProduct iProduct;
    final HttpServletRequest req;
    final INews iNews;
    final Idummy idummy;
    final JmsTemplate jmsTemplate;

    @GetMapping("/order")
    public Object order() {
        String token = req.getHeader("token");
        System.out.println("order Token : " + token);
        Runnable rn = () -> {
            for (int i = 0; i < 10000; i++) {
                JmsData jmsData = new JmsData();
                jmsData.setPid( (long)i );
                jmsData.setPrice(i * 10);
                jmsData.setTitle("TV -" + i);
                Gson gson = new Gson();
                String stData = gson.toJson(jmsData);
                System.out.println(stData);
                jmsTemplate.convertAndSend(stData);
            }
        };
        new Thread(rn).start();

        return iProduct.single(token, 1l);
    }


    @GetMapping("/news")
    public News news() {
        String apiKey = "38a9e086f10b445faabb4461c4aa71f8";
        return iNews.news(apiKey);
    }

    @GetMapping("/jwt")
    public JWTLogin jwt() {
        JWTUser user = new JWTUser();
        user.setUsername("kminchelle");
        user.setPassword("0lelplR");
        return idummy.login(user);
    }


}
