package gradle_spring_webmvc_study.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		// 세션이 존재하면 => 인증된 정보가 있다면 그 정보를 얻어오고 페이지 진입할 수 있도록 true
		if(session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if(authInfo != null) {
				return true;
			}
		}
		// 인증된 정보가 없는 경우, 세션이 없는 경우 => login 페이지로 리디렉션
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}

	
	
}
