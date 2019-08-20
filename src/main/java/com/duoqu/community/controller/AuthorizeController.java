package com.duoqu.community.controller;

import com.duoqu.community.dto.AccessTokenDto;
import com.duoqu.community.dto.GithubUser;
import com.duoqu.community.model.User;
import com.duoqu.community.provider.GithubProvider;
import com.duoqu.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    UserService userService;

    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setRedirect_url(redirectUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubuser = githubProvider.getUser(accessToken);
        if (githubuser != null) {
//         login success white session cookies
            User user = new User();
            user.setName(githubuser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(githubuser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            int i = userService.addUser(user);
            System.out.println(i);
            request.getSession().setAttribute("user",githubuser);
            return "redirect:/";

        }else {
//            login false
            return "redirect:/";
        }
    }
}
