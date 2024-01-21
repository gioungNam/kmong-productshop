package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/","/AdmingMainServlet", "/main","/MakerSql","/ProductAdmin","/UserRegistration"}) // 어드민 관련 URL에 대해서만 필터 적용
public class AuthFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
        // 필터 초기화
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // 어드민 유저 로그인 여부
        boolean loggedIn = session != null && "admin".equals(session.getAttribute("userID"));
        
        if (loggedIn) {
            chain.doFilter(request, response); // 로그인한 경우, 요청 처리 계속 진행
        } else {
            res.sendRedirect(req.getContextPath() + "/LoginServlet"); // 로그인하지 않은 경우, 로그인 페이지로 리디렉션
        }
    }

    public void destroy() {
        // 필터 제거
    }
}