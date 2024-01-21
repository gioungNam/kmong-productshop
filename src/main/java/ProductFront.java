import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import data.Maker;
import data.MakerDao;

@WebServlet("/product")
public class ProductFront extends HttpServlet {
    MakerDao makerDao = new MakerDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Maker> makers = makerDao.getAllMakerNames();

        request.setAttribute("makers", makers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("productFront.jsp");
        dispatcher.forward(request, response);
    }

    // doPost 메소드는 필요에 따라 구현
}