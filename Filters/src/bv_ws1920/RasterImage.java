// BV Ue1 WS2019/20 Vorgabe
//
// Copyright (C) 2019 by Klaus Jung
// All rights reserved.
// Date: 2019-09-28

package bv_ws1920;

import java.io.File;
import java.util.Arrays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import java.util.Random;

public class RasterImage {
	
	private static final int gray  = 0xffa0a0a0;

	public int[] argb;	// pixels represented as ARGB values in scanline order
	public int width;	// image width in pixels
	public int height;	// image height in pixels
	
	public RasterImage(int width, int height) {
		// creates an empty RasterImage of given size
		this.width = width;
		this.height = height;
		argb = new int[width * height];
		Arrays.fill(argb, gray);
	}
	
	public RasterImage(RasterImage src) {
		// copy constructor
		this.width = src.width;
		this.height = src.height;
		argb = src.argb.clone();
	}
	
	public RasterImage(File file) {
		// creates an RasterImage by reading the given file
		Image image = null;
		if(file != null && file.exists()) {
			image = new Image(file.toURI().toString());
		}
		if(image != null && image.getPixelReader() != null) {
			width = (int)image.getWidth();
			height = (int)image.getHeight();
			argb = new int[width * height];
			image.getPixelReader().getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), argb, 0, width);
		} else {
			// file reading failed: create an empty RasterImage
			this.width = 256;
			this.height = 256;
			argb = new int[width * height];
			Arrays.fill(argb, gray);
		}
	}
	
	public RasterImage(ImageView imageView) {
		// creates a RasterImage from that what is shown in the given ImageView
		Image image = imageView.getImage();
		width = (int)image.getWidth();
		height = (int)image.getHeight();
		argb = new int[width * height];
		image.getPixelReader().getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), argb, 0, width);
	}
	
	public void setToView(ImageView imageView) {
		// sets the current argb pixels to be shown in the given ImageView
		if(argb != null) {
			WritableImage wr = new WritableImage(width, height);
			PixelWriter pw = wr.getPixelWriter();
			pw.setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), argb, 0, width);
			imageView.setImage(wr);
		}
	}
	
	
	// image point operations to be added here
	
	public void convertToGray() {
		for(int i = 0; i < argb.length; i++) {
			int c = argb[i];
			int r = (c >> 16) & 0xff;
			int g = (c >>  8) & 0xff;
			int b =  c        & 0xff;
			r = b = g = (r+g+b)/3;
			argb[i] = (0xFF << 24) | (r << 16) | (g << 8) | b;
		}
	}
	
	/**
	 * @param quantity The fraction of pixels that need to be modified: 0-1
	 * @param strength The brightness to be added or subtracted from a pixel's gray level
	 */
	public void addNoise(double quantity, int strength) {
		Random rg = new Random();
		for(int i = 0; i < argb.length; i++) {
			if(rg.nextFloat() <= quantity) {
				int c = argb[i] & 0xff;;
				c += rg.nextFloat() <= 0.5 ? strength : -strength;
				c = Math.max(0, Math.min(c, 255));
				argb[i] = (0xFF << 24) | (c << 16) | (c << 8) | c;
			}
		}
	}
	

}
