import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.CartDao;
import data.CartItem;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userID");
        if (userId == null) {
            // 유저가 로그인하지 않았을 경우 로그인 페이지로 리디렉션
            response.sendRedirect("login.jsp");
            return;
        }

        CartDao cartDao = new CartDao();
        List<CartItem> cartItems = cartDao.getCartItems(userId);

        request.setAttribute("cartItems", cartItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
        dispatcher.forward(request, response);
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
                
        HttpSession session = request.getSession(false);
        String userId = session.getAttribute("userID").toString();
        int productNo = Integer.parseInt(request.getParameter("productNo"));
        int quantity = 1; // 기본 수량 설정

        CartDao cartDao = new CartDao();
        boolean success = cartDao.addToCart(userId, productNo, quantity);

        if (success) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("error");
        }
    }
}