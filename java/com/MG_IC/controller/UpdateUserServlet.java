package com.MG_IC.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.MG_IC.dao.ImageDao;
import com.MG_IC.dao.UserDao;
import com.MG_IC.model.Image;
import com.MG_IC.model.User;

@WebServlet("/UpdateUser")
@MultipartConfig
public class UpdateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean success = false;
		int userId = -1;
		try {
			response.setContentType("text/html");

			userId = Integer.parseInt(request.getParameter("UserId"));

			User user = UserDao.getUserById(userId);
			user.setFirstName(request.getParameter("FirstName"));
			user.setLastName(request.getParameter("LastName"));
			user.setEmail(request.getParameter("Email"));
			user.setPhone(request.getParameter("Phone"));
			Part filePart = request.getPart("UserImage");

			HttpSession session = request.getSession();

			int userId2 = (int) session.getAttribute("UserId");
			if (UserDao.isAdmin(userId2) || userId2 == userId) {

				if (filePart != null && filePart.getSize() > 0) {
					String oldFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
					String newFileName = user.getPhone();
					UploadImage(user, request, filePart, fixFileName(oldFileName, newFileName));
				}
				success = UserDao.updateUser(user);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("UserPages/UserProfile.jsp?msg=success&UserId=" + userId);
		} else {
			response.sendRedirect("UserPages/UpdateUser.jsp?msg=fails&UserId=" + userId);
		}

	}

	private String fixFileName(String oldFileName, String newFileName) {
		String fileFormat = oldFileName.substring(oldFileName.lastIndexOf('.'));
		newFileName = newFileName.replaceAll("[\\\\/:*?\"<>|]", "");
		return newFileName + fileFormat;
	}

	public static void UploadImage(User user, HttpServletRequest request, Part part, String fileName)
			throws ServletException, IOException, DataFormatException {

		String path = request.getServletContext().getRealPath("") + "\\Images";
		File fileSaveDir = new File(path);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		part.write(path + "\\" + fileName);

		if (user.getImageId() == 0) {
			user.setImageId(ImageDao.insertImage(new Image("\\" + fileName)));
		} else {
			Image image = ImageDao.getImageById(user.getImageId());
			image.setImagePath("\\" + fileName);
			ImageDao.updateImage(image);
		}
	}

}
