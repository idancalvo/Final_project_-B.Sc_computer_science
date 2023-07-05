package com.MG_IC.model;

import java.util.zip.DataFormatException;

public class Image {

	private static final int MAX_PATH = 500;

	private int imageId;
	private String imagePath;

	public Image(int imageId, String imagePath) throws DataFormatException {
		super();
		setImageId(imageId);
		setImagePath(imagePath);
	}

	public Image(String imagePath) throws DataFormatException {
		this(0, imagePath);
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imagePath=" + imagePath + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Image) {
			Image otherImage = (Image) other;
			if (imageId == otherImage.getImageId()) {
				if (imagePath.equals(otherImage.getImagePath())) {
					return true;
				}
			}
		}
		return false;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) throws DataFormatException {
		if (imageId < 0 || imageId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'imageId' value [Image]");
		}
		this.imageId = imageId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) throws DataFormatException {
		if (imagePath.length() > MAX_PATH) {
			throw new DataFormatException("Invalid 'imagePath' value [Image]");
		}
		this.imagePath = imagePath;
	}

}
