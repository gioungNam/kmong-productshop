import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.MakerDao;

@WebServlet("/MakerSql")
public class MakerSql extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MakerDao makerDao = new MakerDao();


    public MakerSql() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String makerName = request.getParameter("makerName");

        // 메이커명 빈값체크
        if ("".equals(makerName) || makerName.isBlank()) {
            request.setAttribute("errorMessage", "メーカー名を入力してください。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterMaker.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // 메이커명 길이 체크
        if (makerName.length() > 20) {
            request.setAttribute("errorMessage", "メーカー名は20文字以内で入力してください。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterMaker.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (makerDao.isMakerExists(makerName)) {
            request.setAttribute("errorMessage", "既に登録されているメーカー名です。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterMaker.jsp");
            dispatcher.forward(request, response);
        } else {
            boolean isAdded = makerDao.addMaker(makerName);
            if (isAdded) {
                response.sendRedirect("complete?type=makerRegistration");
            } else {
                request.setAttribute("errorMessage", "メーカー登録中にエラーが発生しました。");
                RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterMaker.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
