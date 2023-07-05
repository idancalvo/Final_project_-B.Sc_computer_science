package com.MG_IC.model;

import java.util.zip.DataFormatException;

public class Movie {

	private static final int MAX_NAME_LENGTH = 50;
	private static final int MAX_YEAR = 5000;
	private static final int MAX_GENRE_LENGTH = 30;
	private static final int MAX_SUMMARY_LENGTH = 500;
	private static final int MAX_DIRECTOR_LENGTH = 50;
	private static final int MAX_LENGTH = 500;
	private static final int MAX_TRAILER_LENGTH = 500;

	private int movieId;
	private String name;
	private int year;
	private String genre;
	private String Summary;
	private String director;
	private int length;
	private int imageId;
	private String trailer;

	public Movie(int movieId, String name, int year, String genre, String Summary, String director, int length,
			int imageId, String trailer) throws DataFormatException {
		super();
		setMovieId(movieId);
		setName(name);
		setYear(year);
		setGenre(genre);
		setSummary(Summary);
		setDirector(director);
		setLength(length);
		setImageId(imageId);
		setTrailer(trailer);
	}

	public Movie(String name, int year, String genre, String Summary, String director, int length, int imageId ,String trailer)
			throws DataFormatException {
		this(0, name, year, genre, Summary, director, length, imageId, trailer);
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", name=" + name + ", year=" + year + ", genre=" + genre + ", Summary="
				+ Summary + ", director=" + director + ", length=" + length + ", imageId=" + imageId + trailer + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Movie) {
			Movie otherMoviee = (Movie) other;
			if (movieId == otherMoviee.getMovieId()) {
				if (name.equals(otherMoviee.getName())) {
					if (year == otherMoviee.getYear()) {
						if (genre.equals(otherMoviee.getGenre())) {
							if (Summary.equals(otherMoviee.getSummary())) {
								if (director.equals(otherMoviee.getDirector())) {
									if (length == otherMoviee.getLength()) {
										if (imageId == otherMoviee.getImageId()) {
											if (trailer == otherMoviee.getTrailer()) {
												return true;
											}
										}
									}
								}
							}
						}
					}

				}
			}
		}
		return false;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) throws DataFormatException {
		if (trailer.length() > MAX_TRAILER_LENGTH) {
			throw new DataFormatException("Invalid 'trailer' value [Movie]");
		}
		this.trailer = trailer;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) throws DataFormatException {
		if (movieId < 0 || movieId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'movieId' value [Movie]");
		}
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws DataFormatException {
		if (name.length() > MAX_NAME_LENGTH) {
			throw new DataFormatException("Invalid 'name' value [Movie]");
		}
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) throws DataFormatException {
		if (year < 0 || year > MAX_YEAR) {
			throw new DataFormatException("Invalid 'year' value [Movie]");
		}
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) throws DataFormatException {
		if (genre.length() > MAX_GENRE_LENGTH) {
			throw new DataFormatException("Invalid 'genre' value [Movie]");
		}
		this.genre = genre;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) throws DataFormatException {
		if (summary.length() > MAX_SUMMARY_LENGTH) {
			throw new DataFormatException("Invalid 'summary' value [Movie]");
		}
		Summary = summary;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) throws DataFormatException {
		if (director.length() > MAX_DIRECTOR_LENGTH) {
			throw new DataFormatException("Invalid 'director' value [Movie]");
		}
		this.director = director;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) throws DataFormatException {
		if (length < 0 || length > MAX_LENGTH) {
			throw new DataFormatException("Invalid 'length' value [Movie]");
		}
		this.length = length;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) throws DataFormatException {
		if (imageId < 0 || imageId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'imageId' value [Movie]");
		}
		this.imageId = imageId;
	}

}
