package quiz2.controller;

import quiz2.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        if (username != null && !username.isEmpty()) {
            User user = new User(0, username, null, null);  

            request.getSession().setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
