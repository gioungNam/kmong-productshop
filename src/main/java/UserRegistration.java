import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserDao;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDao();


    public UserRegistration() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setCharacterEncoding("UTF-8");

                String userId = request.getParameter("id");
                String userName = request.getParameter("name");
                String password = request.getParameter("pw");
                String gender = request.getParameter("gender");
        
                String errorMessage = validateInputs(userId, userName, password, gender);
        
                if (errorMessage != null) {
                    request.setAttribute("errorMessage", errorMessage);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("UserRegistration.jsp");
                    dispatcher.forward(request, response);
                    return;
                } 


                boolean isRegistered = userDao.registerUser(userId, userName, password, gender);

                // 성공시 완료페이지
                if (isRegistered) {
                    String contextPath = request.getContextPath();
			        response.sendRedirect(contextPath + "/complete?type=userRegistration");
                } else {
                    // 실패시
                    request.setAttribute("errorMessage", "会員登録中にエラーが発生しました。 もう一度お試しください。");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("UserRegistration.jsp");
                    dispatcher.forward(request, response);
                }

    }

    /**
     * 회원가입 입력값 체크
     * @param userId
     * @param userName
     * @param password
     * @param gender
     * @return
     */
    private String validateInputs(String userId, String userName, String password, String gender) {


        if (userId == null || userId.length() > 10 || userId.isBlank()) {
            return "ユーザーIDは10文字以内で入力してください。";
        }

        // 유저 id가 이미 존재하는 경우
        if (userDao.isUserIdExists(userId)) {
            return "既に登録されているユーザーIDです。";
        }

        if (userName == null || userName.length() > 20 || userName.isBlank()) {
            return "ユーザー名は20文字以内で入力してください。";
        }
        if (password == null || password.length() < 5 || password.length() > 10) {
            return "パスワードは5文字以上10文字以下で入力してください。";
        }
        if (gender == null || (!gender.equals("male") && !gender.equals("female"))) {
            return "性別を選択してください。";
        }
        return null;
    }
}