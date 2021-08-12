package com.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/admin/user/edit")
public class UserEditSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public UserEditSevlet() {
		super();

		this.userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		User entity = this.userDAO.findById(id);
		
		request.setAttribute("user", entity);
		String view="/views/admin/editUser.jsp";
		request.setAttribute("view", view);
		request.getRequestDispatcher("/layout.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User entity= new User();

		try {
			BeanUtils.populate(entity,request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(entity.getId()<=0) {
			this.userDAO.insert(entity);
		}else {
			this.userDAO.update(entity);
		}
		response.sendRedirect("/asg/admin/user");

	}

}
