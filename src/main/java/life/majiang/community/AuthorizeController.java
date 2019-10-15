package life.majiang.community;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.util.UUID;

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

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/callback")
	public String callback(@RequestParam(name="code")String code,
	                       @RequestParam(name="state")String state,
	                       HttpServletRequest request,
	                       HttpServletResponse response) {
		/*
		* 点击登录，获取用户信息
		* Explain：
		* 1. 点击登录，调用GitHub的authorize接口，GitHub跳转到callback地址并携带code返回
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
		GithubUser githubUser=githubProvider.getUser(accessToken);
		//System.out.println(user.getName());
		/*
		* github登录后，获取用户信息，生成token，并放在user对象中存储到数据库中
		* 并将token放在cookies中
		* */
		if(githubUser !=null){
			User user = new User();
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			user.setName(githubUser.getName());
			user.setAccountid(String.valueOf(githubUser.getId()));
			user.setGmtcreate(System.currentTimeMillis());
			user.setGmtModified(user.getGmtcreate());
			userMapper.insert(user);
			response.addCookie(new Cookie("token",token));
			//登录成功，写cookies和session
			//request.getSession().setAttribute("user",githubUser);
			return "redirect:/";
		}
		else {
			return "redirect:/";
			//登录失败，重新登录
		}
	}
}