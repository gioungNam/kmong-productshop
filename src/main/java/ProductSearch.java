import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.Product;
import data.ProductDao;
import com.google.gson.Gson;

@WebServlet("/prdsearch")
public class ProductSearch extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String productName = request.getParameter("productName");
        String makerName = request.getParameter("makerName");
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");

        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.searchProducts(productName, makerName, minPriceStr, maxPriceStr);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new Gson().toJson(products, response.getWriter());
    }
}