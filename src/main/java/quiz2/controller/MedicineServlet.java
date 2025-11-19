package quiz2.controller;

import quiz2.dao.MedicineDAO;
import quiz2.dao.CategoryDAO;
import quiz2.model.Medicine;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/medicines")
public class MedicineServlet extends HttpServlet {
    private MedicineDAO medicineDAO;
    private CategoryDAO categoryDAO;

    
    private static final Logger LOGGER = Logger.getLogger(MedicineServlet.class.getName());

    @Override
    public void init() {
        medicineDAO = new MedicineDAO();
        categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "delete":
                    int delete_id = Integer.parseInt(request.getParameter("id"));
                    medicineDAO.deleteMedicine(delete_id);
                    response.sendRedirect("medicines");
                    break;
                case "create":
                    request.setAttribute("categories", categoryDAO.getAllCategories());
                    request.getRequestDispatcher("medicine-form.jsp").forward(request, response);
                    break;

                case "edit":
                    int edit_id = Integer.parseInt(request.getParameter("id"));
                    Medicine medicine = medicineDAO.getMedicineById(edit_id);
                    request.setAttribute("medicine", medicine);
                    request.setAttribute("categories", categoryDAO.getAllCategories());
                    request.getRequestDispatcher("medicine-form.jsp").forward(request, response);
                    break;

                case "list":
                default:
                    String search = request.getParameter("search");
                    if (search == null) search = "";
                    request.setAttribute("medicines", medicineDAO.searchMedicines(search));
                    request.getRequestDispatcher("medicines.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception in GET request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error in GET request: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); 

        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stock");
        String categoryStr = request.getParameter("categoryId");
        String action = request.getParameter("action");

        if ("increase".equals(action) || "decrease".equals(action)) {
            try {
                int id = Integer.parseInt(idStr);

                if ("increase".equals(action)) {
                    medicineDAO.increaseStock(id);
                } else {
                    medicineDAO.decreaseStock(id);
                }

                response.sendRedirect("medicines");
                return;

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error updating stock", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating stock: " + e.getMessage());
                return;
            }
        }

        if (name == null || name.isEmpty() ||
            priceStr == null || priceStr.isEmpty() ||
            stockStr == null || stockStr.isEmpty() ||
            categoryStr == null || categoryStr.isEmpty()) {

            request.setAttribute("error", "All fields are required!");
            request.setAttribute("categories", categoryDAO.getAllCategories());
            request.getRequestDispatcher("medicine-form.jsp").forward(request, response);
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);
            int categoryId = Integer.parseInt(categoryStr);

            LOGGER.log(Level.INFO, "POST data - name: {0}, price: {1}, stock: {2}, categoryId: {3}",
                    new Object[]{name, price, stock, categoryId});

            
            if (categoryDAO.getCategoryById(categoryId) == null) {
                request.setAttribute("error", "Selected category does not exist!");
                request.setAttribute("categories", categoryDAO.getAllCategories());
                request.getRequestDispatcher("medicine-form.jsp").forward(request, response);
                return;
            }

            Medicine med = new Medicine();
            med.setName(name);
            med.setPrice(price);
            med.setStock(stock);
            med.setCategoryId(categoryId);

            if (idStr == null || idStr.isEmpty()) {
                LOGGER.info("Attempting to insert new medicine into DB");
                medicineDAO.createMedicine(med);
                LOGGER.info("Insert completed successfully");
            } else {
                med.setId(Integer.parseInt(idStr));
                boolean updated = medicineDAO.updateMedicine(med);
                LOGGER.log(Level.INFO, "Update completed: {0}", updated);
            }

            response.sendRedirect("medicines");

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Price and Stock must be valid numbers!");
            request.setAttribute("categories", categoryDAO.getAllCategories());
            request.getRequestDispatcher("medicine-form.jsp").forward(request, response);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception in POST request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request: " + e.getMessage());
        }
    }
}
