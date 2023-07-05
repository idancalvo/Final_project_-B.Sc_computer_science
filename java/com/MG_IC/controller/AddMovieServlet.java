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
import com.MG_IC.dao.MovieDao;
import com.MG_IC.dao.UserDao;
import com.MG_IC.model.Image;
import com.MG_IC.model.Movie;

@WebServlet("/AddMovie")
@MultipartConfig
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean success = false;
		System.out.println(request.getContextPath());

		try {
			String movieName = request.getParameter("MovieName");
			int movieYear = Integer.parseInt(request.getParameter("MovieYear"));
			String movieDirector = request.getParameter("MovieDirector");
			String movieGenre = request.getParameter("MovieGenre");
			int movieLength = Integer.parseInt(request.getParameter("MovieLength"));
			String summary = request.getParameter("Summary");
			String trailer = request.getParameter("Trailer");

			int imageId = 0;
			Part filePart = request.getPart("MovieImage");

			HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("UserId");
			if (UserDao.isAdmin(userId)) {

				if (filePart != null && filePart.getSize() > 0) {
					String oldFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
					String newFileName = movieName + "(" + movieYear + ")";
					imageId = UploadImage(request, filePart, fixFileName(oldFileName, newFileName));
				}
				success = MovieDao.insertMovie(new Movie(movieName, movieYear, movieGenre, summary, movieDirector,
						movieLength, imageId, trailer));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewMovies.jsp?msg=success");
		} else {
			response.sendRedirect("AdminPages/AddMovie.jsp?msg=fails");
		}

	}

	private String fixFileName(String oldFileName, String newFileName) {
		String fileFormat = oldFileName.substring(oldFileName.lastIndexOf('.'));
		newFileName = newFileName.replaceAll("[\\\\/:*?\"<>|]", "");
		return newFileName + fileFormat;
	}

	public static int UploadImage(HttpServletRequest request, Part part, String fileName)
			throws ServletException, IOException, DataFormatException {

		String path = request.getServletContext().getRealPath("") + "\\Images";
		File fileSaveDir = new File(path);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		part.write(path + "\\" + fileName);
		return ImageDao.insertImage(new Image("\\" + fileName));
	}

}
