package main.java.model;

import main.java.model.MARK_I.VisionCell;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Input to Retina: images of different possible formats.
 *
 * Output of Retina: activity of Cells within VisionCells.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Feb 3, 2014
 */
public class Retina {
    private VisionCell[][] visionCells;

    public Retina(int numberOfVisionCellsAlongYAxis,
	    int numberOfVisionCellsAlongXAxis) {
	this.visionCells = new VisionCell[numberOfVisionCellsAlongYAxis][numberOfVisionCellsAlongXAxis];

	for (int currentRow = 0; currentRow < numberOfVisionCellsAlongYAxis; currentRow++) {
	    for (int currentColumn = 0; currentColumn < numberOfVisionCellsAlongXAxis; currentColumn++) {
		this.visionCells[currentRow][currentColumn] = new VisionCell();
	    }
	}
    }

    public VisionCell[][] getVisionCells() {
	return this.visionCells;
    }

    /**
     * Update the state of the Retine with the given .bmp file name.
     *
     * @param BMPFileName
     * @throws IOException
     */
    public void seeBMPImage(String BMPFileName) throws IOException {
	BufferedImage image = ImageIO.read(getClass().getResource(BMPFileName));

	int numberOfRows = this.visionCells.length;
	int numberOfColumns = this.visionCells[0].length;

	for (int currentColumn = 0; currentColumn < numberOfColumns; currentColumn++) {
	    for (int currentRow = 0; currentRow < numberOfRows; currentRow++) {
		int RGBcolor = image.getRGB(currentColumn, currentRow);

		if (RGBcolor == Color.BLACK.getRGB()) {
		    this.visionCells[currentRow][currentColumn]
			    .setActiveState(true);
		} else {
		    this.visionCells[currentRow][currentColumn]
			    .setActiveState(false);
		}
	    }
	}
    }

    /**
     * @param image
     *            Any element in the 2D array with a value of zero will mean the
     *            corresponding VisionCell 2D array element will have
     *            activeState set to false. Any other number sets activeState to
     *            true.
     */
    public void see2DIntArray(int[][] image) {
	int numberOfRows = this.visionCells.length;
	int numberOfColumns = this.visionCells[0].length;

	for (int currentColumn = 0; currentColumn < numberOfColumns; currentColumn++) {
	    for (int currentRow = 0; currentRow < numberOfRows; currentRow++) {
		if (image[currentColumn][currentRow] != 0) {
		    this.visionCells[currentRow][currentColumn]
			    .setActiveState(true);
		} else {
		    this.visionCells[currentRow][currentColumn]
			    .setActiveState(false);
		}
	    }
	}
    }
}
