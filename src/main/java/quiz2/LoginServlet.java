package quiz2;

import quiz2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/login")
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
