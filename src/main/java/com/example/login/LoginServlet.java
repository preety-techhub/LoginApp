package com.example.login;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login") // Servlet URL mapping
public class LoginServlet extends HttpServlet {

    // Optional: inject your UserService if you have one
    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Fetch username and password from form
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Validate user (replace with real validation if available)
        boolean isValid = true; // userService.validateUser(username, password);

        // Redirect based on login result
        if (isValid) {
            // Redirect to success page, using context path
            resp.sendRedirect(req.getContextPath() + "/success.html");
        } else {
            // Redirect back to login page with error flag
            resp.sendRedirect(req.getContextPath() + "/login.html?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Optional: forward GET requests to login page
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
