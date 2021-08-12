package com.servlet;

import java.io.IOException;
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

import com.dao.UserDAO;
import com.entity.User;


@WebServlet("/forgot")
public class forgotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO; 
    
    public forgotServlet() {
        super();
        this.userDAO= new UserDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/auth/forgotPassword.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userName="duonglxph12508@fpt.edu.vn";
		final String password="duongle9212";
		
		String user= request.getParameter("user");
		String email= request.getParameter("email");
		
		System.out.print("aaaaa"+user+" ,"+email+"\n");
		
		User entity= this.userDAO.forgot(user, email);
		System.out.print("22222222222222222222");
		
		if (entity==null) {
			request.setAttribute("error", "Sai tên đăng nhập hoặc eamil");
			request.getRequestDispatcher("/views/auth/forgotPassword.jsp").forward(request, response);;
		}else {
			System.out.print("Sedn......\n");
			//Gửi mail lấy lại password
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
			
			try {
				Message message= new MimeMessage(session);
				message.setFrom(new InternetAddress(userName));
				message.setRecipients(
						Message.RecipientType.TO,
						InternetAddress.parse(email)
				);
				message.setSubject("Forgot Password by duonglx");
				message.setText("User Name: "+entity.getFullName()+" , Password: "+entity.getPassword());
				message.setReplyTo(message.getFrom());
				Transport.send(message);
				
				System.out.print("done");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("auth", entity);
			response.sendRedirect("/asg/Login");
		}
		

	}

}
