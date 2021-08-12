package com.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FavoriteDAO;
import com.entity.Favorite;
import com.entity.User;
import com.entity.Video;


@WebServlet({"/user/favorite","/user/favorite/delete"})
public class favoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    FavoriteDAO frDAO;
    public favoriteServlet() {
        super();
        this.frDAO= new FavoriteDAO();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		List<Video> list= this.frDAO.ShowFavorite(userId, offset, limit);
		request.setAttribute("videoFr", list);
		request.setAttribute("page", page);
		String view = "/views/user/favorite.jsp";
		request.setAttribute("view", view);
		request.getRequestDispatcher("/layout.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {;

		int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		int videoId= Integer.parseInt(request.getParameter("idVideoUL"));
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaa"+videoId);
		System.out.println(userId);
		this.frDAO.UnLike(videoId, userId);
		
		response.sendRedirect("/asg/user/favorite");
	}

}
