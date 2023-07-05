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

@WebServlet("/UpdateMovie")
@MultipartConfig
public class UpdateMovieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean success = false;
		int movieId = -1;
		try {
			response.setContentType("text/html");

			movieId = Integer.parseInt(request.getParameter("MovieId"));

			Movie movie = MovieDao.getMovieById(movieId);
			movie.setName(request.getParameter("MovieName"));
			movie.setYear(Integer.parseInt(request.getParameter("MovieYear")));
			movie.setDirector(request.getParameter("MovieDirector"));
			movie.setGenre(request.getParameter("MovieGenre"));
			movie.setLength(Integer.parseInt(request.getParameter("MovieLength")));
			movie.setSummary(request.getParameter("Summary"));
			movie.setTrailer(request.getParameter("Trailer"));

			Part filePart = request.getPart("MovieImage");

			HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("UserId");
			if (UserDao.isAdmin(userId)) {

				if (filePart != null && filePart.getSize() > 0) {
					String oldFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
					String newFileName = movie.getName() + " (" + movie.getYear() + ")";
					UploadImage(movie, request, filePart, fixFileName(oldFileName, newFileName));
				}
				success = MovieDao.updateMovie(movie);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewMovies.jsp?msg=success");
		} else {
			response.sendRedirect("AdminPages/UpdateMovie.jsp?msg=fails&MovieId=" + movieId);
		}

	}

	private String fixFileName(String oldFileName, String newFileName) {
		String fileFormat = oldFileName.substring(oldFileName.lastIndexOf('.'));
		newFileName = newFileName.replaceAll("[\\\\/:*?\"<>|]", "");
		return newFileName + fileFormat;
	}

	public static void UploadImage(Movie movie, HttpServletRequest request, Part part, String fileName)
			throws ServletException, IOException, DataFormatException {

		String path = request.getServletContext().getRealPath("") + "\\Images";
		File fileSaveDir = new File(path);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		part.write(path + "\\" + fileName);

		if (movie.getImageId() == 0) {
			movie.setImageId(ImageDao.insertImage(new Image("\\" + fileName)));
		} else {
			Image image = ImageDao.getImageById(movie.getImageId());
			image.setImagePath("\\" + fileName);
			ImageDao.updateImage(image);
		}
	}
}
