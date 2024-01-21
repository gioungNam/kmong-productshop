

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.User;
import data.UserDao;

/**
 * Servlet implementation class Judgment
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userID") != null) {
            String userID = (String) session.getAttribute("userID");
            
            if ("admin".equals(userID)) {
                response.sendRedirect(request.getContextPath() + "/main");
            } else {
                response.sendRedirect(request.getContextPath() + "/FrontServlet");
            }
        } else {
            request.getRequestDispatcher("/main.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");
		
		String strId = request.getParameter("ID");
		String strPw = request.getParameter("password");
		
		
		if(strId.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			request.setAttribute("MESSAGE","id未入力です");
			rd.forward(request, response);
		}else if (strPw.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			request.setAttribute("MESSAGE"," パスワード未入力です");
			rd.forward(request, response);
		}
			
		
		if(strId.length()>10) {
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			request.setAttribute("MESSAGE","IDは10桁以内");
			rd.forward(request, response);
			
		}else if(strPw.length()>10 || strPw.length()<5) {
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			request.setAttribute("MESSAGE", "パスワードは5桁～10桁以内");
			rd.forward(request, response);
			
		}		

		// 어드민 유저
		if(strId.equals("admin")|| strPw.equals("admin")){
			// 세션 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("userID", "admin");
			session.setAttribute("userName", "admin");

			RequestDispatcher rd = request.getRequestDispatcher("/AdminMain.jsp");
			rd.forward(request, response);
		}


		// 일반 사용자 인증
		UserDao userDao = new UserDao();
		User user = userDao.authenticate(strId, strPw);

		if (user != null) {
			// 세션에 사용자 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("userID", user.getId());
			session.setAttribute("userName", user.getName());

			// 로그인 성공시 /FrontServlet URL로 리디렉션
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/FrontServlet");
		} else {
			// 로그인 실패
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			request.setAttribute("MESSAGE", "IDまたはパスワードが正しくありません");
			rd.forward(request, response);
		}
	}

}
