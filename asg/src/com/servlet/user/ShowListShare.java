package com.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ShareDAO;
import com.entity.Share;
import com.entity.Video;

@WebServlet({"/user/showShare","/user/showShare/delete"})
public class ShowListShare extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ShareDAO shareDAO;

	public ShowListShare() {
		super();
		this.shareDAO = new ShareDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		
		String pageStr = request.getParameter("page");

		int page = pageStr == null ? 1 : Integer.parseInt(pageStr),
				limit = 6, 
				offset = 0;
		if(page<1) {
			page=1;
		}else {
			offset=limit * (page - 1);
		}
		
		List<Share> list = this.shareDAO.ShowShare(userId, offset, limit);
		request.setAttribute("videoSh", list);
		request.setAttribute("page", page);
		
		String view = "/views/user/listShare.jsp";
		request.setAttribute("view", view);
		request.getRequestDispatcher("/layout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		int vidId= Integer.parseInt(request.getParameter("idVideoUS"));
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaa"+vidId);
		System.out.println(userId);
		this.shareDAO.UnShare(vidId, userId);
		
		response.sendRedirect("/asg/user/showShare");
	}

}
