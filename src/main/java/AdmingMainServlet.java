

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdmingMainServlet
 */
@WebServlet("/AdmingMainServlet")
public class AdmingMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdmingMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	request.setCharacterEncoding("UTF8");
	
	 String action = request.getParameter("action");

     String contextPath = request.getContextPath();
     if ("UR".equals(action)) {
         // "ユーザー登録" 버튼이 클릭된 경우
         RequestDispatcher rd = request.getRequestDispatcher("/UserRegistration.jsp");
         rd.forward(request, response);
     } else if ("MR".equals(action)) {
         // "メーカー登録" 버튼이 클릭된 경우
         RequestDispatcher rd = request.getRequestDispatcher("/RegisterMaker.jsp");
         rd.forward(request, response);
     } else if ("MM".equals(action)) {
         // "商品管理" 버튼이 클릭된 경우
         response.sendRedirect(contextPath + "/ProductAdmin");

	
	}
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
