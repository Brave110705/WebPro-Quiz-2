package quiz2.controller;

import quiz2.dao.CategoryDAO;
import quiz2.model.Category;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    @Override
    public void init() {
        categoryDAO = new CategoryDAO();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Category category = categoryDAO.getCategoryById(id);
            request.setAttribute("category", category);
            request.getRequestDispatcher("category-form.jsp").forward(request, response);
        } else if ("create".equals(action)) {
            request.getRequestDispatcher("category-form.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            categoryDAO.deleteCategory(id);
            response.sendRedirect("medicines");
        } else {
            request.setAttribute("categories", categoryDAO.getAllCategories());
            request.getRequestDispatcher("categories.jsp").forward(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Category category = new Category();
        category.setName(request.getParameter("name"));

        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            categoryDAO.createCategory(category);
        } else {
            category.setId(Integer.parseInt(id));
            categoryDAO.updateCategory(category);
        }

        response.sendRedirect("medicines");
    }
}
