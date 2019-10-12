package life.majiang.community;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;

@Controller
public class AuthorizeController {

	@Autowired
	private GithubProvider githubProvider;

	@Value("${github.client.id}")
	private String clientId;
	@Value("${github.client.secret}")
	private String clientSecret;
	@Value("${github.redirect.uri}")
	private String redirectUri;

	@GetMapping("/callback")
	public String callback(@RequestParam(name="code")String code,
	                       @RequestParam(name="state")String state) {
		/*
		* 点击登录，获取用户信息
		* Explain：
		* 1. 点击登录，电泳GitHub的authorize接口，GitHub跳转到callback地址并携带code返回
		* 2. 拿到code，再调用GitHub的access_token接口，获取access_token
		* 3. 拿到access_token后，再调用GitHub的user接口，返回user的具体信息。
		* */
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri(redirectUri);
		accessTokenDTO.setState(state);
		String accessToken=githubProvider.getAccessToken(accessTokenDTO);
		GithubUser user=githubProvider.getUser(accessToken);
		System.out.println(user.getName());
		return "index";
	}
}
