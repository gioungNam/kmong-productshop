import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/complete")
public class CompleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String message = "";

        switch (type) {
            case "userRegistration":
                message = "ユーザー登録が完了しました。";
                break;
            case "productRegistration":
                message = "商品登録が完了しました。";
                break;
            case "makerRegistration":
                message = "メーカー登録が完了しました。";
                break;
            // 여기에 필요한 다른 케이스들을 추가할 수 있습니다.
            default:
                message = "作業が正常に完了しました。";
                break;
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Completion.jsp");
        dispatcher.forward(request, response);
    }
}