// BV Ue1 WS2019/20 Vorgabe
//
// Copyright (C) 2019 by Klaus Jung
// All rights reserved.
// Date: 2019-09-28

package bv_ws1920;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MaximumFilter implements Filter {
	private RasterImage sourceImage, destinationImage;
	private int kernelWidth, kernelHeight;

	@Override
	public void setSourceImage(RasterImage sourceImage) {
		this.sourceImage = sourceImage;
	}

	@Override
	public void setDestinationImage(RasterImage destinationImage) {
		this.destinationImage = destinationImage;
	}

	@Override
	public void setKernelWidth(int kernelWidth) {
		this.kernelWidth = kernelWidth;
	}

	@Override
	public void setKernelHeight(int kernelHeight) {
		this.kernelHeight = kernelHeight;
	}

	@Override
	public void apply() {
		destinationImage.argb = FilterAppController.applyFilter(sourceImage.argb, kernelWidth, kernelHeight, sourceImage.width, sourceImage.height, kernelWidth * kernelHeight - 1);
	}
}