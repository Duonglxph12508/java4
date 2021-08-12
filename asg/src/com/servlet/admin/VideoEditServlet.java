package com.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.dao.VideoDAO;
import com.entity.User;
import com.entity.Video;


@WebServlet("/admin/video/edit")
public class VideoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VideoDAO videoDAO;
    
    public VideoEditServlet() {
        super();
        this.videoDAO= new VideoDAO();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
int id = Integer.parseInt(request.getParameter("id"));
		
		Video videoEntity = this.videoDAO.findById(id);
		
		request.setAttribute("video", videoEntity);
		String view="/views/admin/editVideo.jsp";
		request.setAttribute("view", view);
		request.getRequestDispatcher("/layout.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video videoEntity= new Video();

		try {
			BeanUtils.populate(videoEntity,request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(videoEntity.getId()<=0) {
			this.videoDAO.insert(videoEntity);
		}else {
			this.videoDAO.update(videoEntity);
		}
		response.sendRedirect("/asg/admin/video");
	}

}
