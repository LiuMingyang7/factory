package com.factory.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author chuangxing0615
 */
@Component
public class AppAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{


		@Override
		protected void handle(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication)
				throws IOException {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			String target=determineTargetUrl(authentication);
			out.write("{\"success\":\"true\",\"message\":\"认证成功\",\"url\":\""+target+"\"}");
			out.flush();
			out.close();
		}

	private boolean isUser(List<String> roles) {
		return roles.contains("ROLE_ADMIN");
	}

	protected String determineTargetUrl(Authentication authentication) {
		String url = "";

		// 获取当前登录用户的角色权限集合
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		// 将角色名称添加到List集合
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		// 判断不同角色跳转到不同的url
		if (isUser(roles)) {
			url = "/admin";
		} else {
			url = "/accessDenied";
		}
		System.out.println("url = " + url);
		return url;
	}

}
