// BV Ue3 WS2019/20 Vorgabe
//
// Copyright (C) 2017 by Klaus Jung
// All rights reserved.
// Date: 2017-07-15

package bv_ws1920;

import java.util.Arrays;

public class MorphologicFilter {
	
	public enum FilterType { 
		DILATION("Dilation"),
		EROSION("Erosion");
		
		private final String name;       
	    private FilterType(String s) { name = s; }
	    public String toString() { return this.name; }
	};
	
	// filter implementations go here:
	
	public void copy(RasterImage src, RasterImage dst) {
		dst.argb = src.argb;
	}
	
	public void dilation(RasterImage src, RasterImage dst, double radius) {
		dst.argb = src.argb.clone(); //Bestehendes übernehmen, damit auch bei r = 0 was zu sehen ist
		int height = src.height, width = src.width;
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (src.argb[y * width + x] == 0xff000000) {
					int iRadius = (int) radius + 1; //Radius, aufgerundet
					for (int dx = -iRadius; dx < iRadius; dx++) {
						for (int dy = -iRadius; dy < iRadius; dy++) {
							int curX = x + dx,
								curY = y + dy;
							if (Math.pow(x - curX, 2) + Math.pow(y - curY, 2) <= radius*radius) //im Kreis?
								if(0 <= curX && curX < width && 0 <= curY && curY < height) //im Bild?
									dst.argb[curY * width + curX] = 0xff000000;
						}
					}
				}
			}
		}
	}
	
	public void erosion(RasterImage src, RasterImage dst, double radius) {
		src.invert();
		dilation(src, dst, radius);
		src.invert();
		dst.invert();
	}
	
	
	
	

}
