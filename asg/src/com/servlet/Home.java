package com.servlet;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.dao.FavoriteDAO;
import com.dao.VideoDAO;
import com.entity.Video;
import com.entity.Favorite;

@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO videoDAO;
	private FavoriteDAO favoriteDAO;
	public Home() {
		super();
		this.videoDAO = new VideoDAO();
		this.favoriteDAO= new FavoriteDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageStr = request.getParameter("page");

		int page = pageStr == null ? 1 : Integer.parseInt(pageStr), limit = 6, offset = 0;
		if (page < 1) {
			page = 1;
		} else {
			offset = limit * (page - 1);
		}
		List<Video> listVideo = this.videoDAO.showHome(offset, limit);
		request.setAttribute("list", listVideo);
		request.setAttribute("page", page);
		String view = "/views/home.jsp";
		request.setAttribute("view", view);
		request.getRequestDispatcher("/layout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		int videoId= Integer.parseInt(request.getParameter("videoId"));
		Date dateNow= new Date();
		Favorite entity= new Favorite();
		entity.setUserId(userId);
		entity.setVideoId(videoId);
		entity.setLikeDate(dateNow);
		this.favoriteDAO.insert(entity);
		System.out.print("done!");
		response.sendRedirect("/asg/user/favorite");
		
	}

}
