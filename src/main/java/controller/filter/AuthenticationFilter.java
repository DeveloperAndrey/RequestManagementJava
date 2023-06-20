package controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Get the current session
        HttpSession session = req.getSession(false);

        // Check if the user is authenticated
        boolean isAuthenticated = (session != null && session.getAttribute("user") != null);

        // Check if the requested page is a protected page
        boolean isProtectedPage = !(req.getRequestURI().endsWith("login") || req.getRequestURI().endsWith("registration"));

        if (isAuthenticated || !isProtectedPage) {
            // Allow access to the page
            chain.doFilter(request, response);
        } else {
            // Redirect to the login page
            resp.sendRedirect("login");
        }
    }
}
