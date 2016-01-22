/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import Model.User;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author mloda
 */
@WebFilter("/normal/*")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        if (session != null) {
            User user = (User) session.getAttribute("LoggedUser");
            if (user!= null && user.getType().equals("normal")) {
                chain.doFilter(req, res);
            } else {
                response.sendRedirect("/pr2/error.xhtml");
            }
        } else {
            response.sendRedirect("/pr2/error.xhtml");
        }
    }

    @Override
    public void destroy() {
    }

}
