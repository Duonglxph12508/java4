package com.servlet.user;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ShareDAO;
import com.entity.Share;
import com.entity.Video;


@WebServlet("/user/share")
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ShareDAO shareDAO;
    
    public ShareServlet() {
        super();
        this.shareDAO= new ShareDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int videoId= Integer.parseInt(request.getParameter("videoId"));
		System.out.print("idvideo "+videoId);
		Video entity= this.shareDAO.findById(videoId);
		
		request.setAttribute("video", entity);
		String view = "/views/user/share.jsp";
		request.setAttribute("view", view);
		request.getRequestDispatcher("/layout.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userName="duonglxph12508@fpt.edu.vn";
		final String password="duongle9212";
		int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		int videoId= Integer.parseInt(request.getParameter("videoId"));
		String email= request.getParameter("email");
		Date dateNow= new Date();
		
		Properties prop= new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.host", "smtp.gmail.com");
		prop.setProperty("mail.smtp.port", "587");
		
		
		Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		//đăng nhập email
		
		String emailTo= request.getParameter("email");
		
		
		try {
			Message message= new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(
					Message.RecipientType.TO,
					InternetAddress.parse(emailTo)
			);
			message.setSubject("Share video có id là  "+videoId);
			message.setText("http://localhost:8080/asg/Home");
			message.setReplyTo(message.getFrom());
			Transport.send(message);
			
			System.out.print("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		System.out.print("User"+ userId+ "  video "+videoId +" email "+email + " date "+ dateNow);
		
		Share entity= new Share();
		entity.setUserId(userId);
		entity.setVideoId(videoId);
		entity.setEmail(email);
		entity.setShareDate(dateNow);
		
		shareDAO.insert(entity);
		System.out.print("done!");
		response.sendRedirect("/asg/user/showShare");
	}

	
	
}
