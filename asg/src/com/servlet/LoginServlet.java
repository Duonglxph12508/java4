package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.entity.User;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    
    public LoginServlet() {
        super();
        this.userDAO= new UserDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user= request.getParameter("user");
		String password= request.getParameter("password");
		
		User entity= this.userDAO.login(user, password);
	
		
		if (entity==null) {
			request.setAttribute("error", "Đăng nhập thất bại");
			request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);;
		}else {
			request.getSession().setAttribute("auth", entity);
			request.getSession().setAttribute("userId", entity.getId());
			response.sendRedirect("/asg/Home");
		}
	}

}
