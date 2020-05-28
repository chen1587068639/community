package com.chen.springboot.Controller;

import ch.qos.logback.core.net.server.Client;
import com.chen.springboot.dto.AccessTokenDTO;
import com.chen.springboot.dto.GithubUser;
import com.chen.springboot.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.Client.id}")
    private String clientId;
    @Value("${github.Client.secret}")
    private String githubClientSecret;
    @Value("github.Redirect.uri")
    private String githubRedirectUri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                            @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(githubClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(githubRedirectUri);
        accessTokenDTO.setState(state);
        try {
            String accessToken = githubProvider.getAccessToken(accessTokenDTO);
            GithubUser user = githubProvider.getUser(accessToken);
            System.out.println(user.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
