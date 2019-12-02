// BV Ue1 WS2019/20 Vorgabe
//
// Copyright (C) 2019 by Klaus Jung
// All rights reserved.
// Date: 2019-09-28

package bv_ws1920;

public interface Filter {

	public void setSourceImage(RasterImage sourceImage);

	public void setDestinationImage(RasterImage destinationImage);

	public void setKernelWidth(int kernelWidth);

	public void setKernelHeight(int kernelHeight);

	public void apply();
	
}
