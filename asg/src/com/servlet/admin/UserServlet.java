	package com.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.entity.User;

@WebServlet({"/admin/user","/admin/user/delete"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	public UserServlet() {
		super();
		this.userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageStr = request.getParameter("page");

		int page = pageStr == null ? 1 : Integer.parseInt(pageStr),
				limit = 10, 
				offset = 0;
		if(page<1) {
			page=1;
		}else {
			offset=limit * (page - 1);
		}

		List<User> listUser = this.userDAO.paginate(offset, limit);
		request.setAttribute("list", listUser);
		request.setAttribute("page", page);
		
		//cú pháp gọi đến jsp sử dụng nhúng layout
		String view="/views/admin/user.jsp";
		request.setAttribute("view", view);
		request.getRequestDispatcher("/layout.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		if (idStr == null) {
			// Bad request
		}
		
		int id = Integer.parseInt(idStr);
		
		User entity = new User();
		
		entity.setId(id);

		this.userDAO.delete(entity);
		
		response.sendRedirect("/asg/admin/user");
		
	}

}
