package com.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VideoDAO;
import com.entity.Video;

@WebServlet({ "/admin/video", "/admin/video/delete" })
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO videoDAO;

	public VideoServlet() {
		super();
		this.videoDAO = new VideoDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageStr = request.getParameter("page");

		int page = pageStr == null ? 1 : Integer.parseInt(pageStr), limit = 10, offset = 0;
		if (page < 1) {
			page = 1;
		} else {
			offset = limit * (page - 1);
		}

		List<Video> listVideo = this.videoDAO.paginate(offset, limit);
		request.setAttribute("list", listVideo);
		request.setAttribute("page", page);
		String view = "/views/admin/video.jsp";
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
		
		Video entity= new Video();
		
		entity.setId(id);

		this.videoDAO.delete(entity);
		
		response.sendRedirect("/asg/admin/video");
	}

}
