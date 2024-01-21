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
import data.ProductDao;

@WebServlet("/ProductAdmin")
public class ProductAdmin extends HttpServlet {
    private MakerDao makerDao = new MakerDao();
    private ProductDao productDao = new ProductDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Maker> makers = makerDao.getAllMakerNames();

        request.setAttribute("makers", makers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProductAdmin.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                request.setCharacterEncoding("UTF-8");
                String productName = request.getParameter("productName");
                String makerIdStr = request.getParameter("makerId");
                String priceStr = request.getParameter("price");
                String errorMessage = null;
        
                try {
                    int makerId = Integer.parseInt(makerIdStr);
                    int price = Integer.parseInt(priceStr);
        
                    if (productName == null || productName.length() > 20 || productName.isBlank() ||
                        makerId <= 0 || price <= 0) {
                        errorMessage = "入力値が不正です。もう一度確認してください。";
                    } else {
                        boolean isAdded = productDao.addProduct(productName, makerId, price);
                        if (isAdded) {
                            response.sendRedirect(request.getContextPath() + "/complete?type=productRegistration");
                            return;
                        } else {
                            errorMessage = "商品登録中にエラーが発生しました。";
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    errorMessage = "価格は数値で入力してください。";
                }
        
                if (errorMessage != null) {
                    request.setAttribute("errorMessage", errorMessage);

                    // 메이커 리스트 조회
                    List<Maker> makers = makerDao.getAllMakerNames();
                    request.setAttribute("makers", makers);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("ProductAdmin.jsp");
                    dispatcher.forward(request, response);
                }
    }
}