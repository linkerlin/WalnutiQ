package model.MARK_I.vision;

import model.MARK_I.Region;
import model.Retina;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * -------------------------------Purpose---------------------------------------
 * To show the spatial pooling learning algorithm produces similar results even
 * after adding noise to the input data.
 *
 * ------------------------------Experiment-------------------------------------
 * Run the spatial pooling algorithm on 3 different bitmap images. The 3 images
 * are both of the same thing but 1 of the images has no noise, 1 image has some
 * noise, and 1 image has a lot of noise.
 *
 * ------------------------------Conclusion-------------------------------------
 * The spatial pooling algoithm does simple local computations on the image to
 * remove noise very efficiently up to a specific threshold that can vary
 * between locations in the input image.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version April 12, 2014
 */
public class NoiseInvarianceExperiment {
    private Retina retina;
    private model.MARK_I.Region region;
    private model.MARK_I.SpatialPooler spatialPooler;

    @Before
    public void setUp() {
	// images this retina will see in folder images/model/ are 66x66 pixels
	this.retina = new Retina(66, 66);

	this.region = new Region("Region", 8, 8, 1, 40, 3);

	model.MARK_I.connectTypes.SensorCellsToRegionConnectInterface retinaToRegion = new model.MARK_I.connectTypes.SensorCellsToRegionRectangleConnect();
	retinaToRegion.connect(this.retina.getVisionCells(), this.region, 0, 0);

	this.spatialPooler = new model.MARK_I.SpatialPooler(this.region);
	this.spatialPooler.setLearningState(true);
    }

    @Test
    public void test_runNoiseInvarianceExperiment() throws IOException {

	// --------------------------"2.bmp"-------------------------------
	this.retina.seeBMPImage("2.bmp");

	this.spatialPooler.performSpatialPoolingOnRegion();
	Set<model.MARK_I.ColumnPosition> columnActivityAfterSeeingImage2 = this.spatialPooler
		.getActiveColumnPositions();
	assertEquals(13, columnActivityAfterSeeingImage2.size());

	for (model.MARK_I.ColumnPosition columnPosition : columnActivityAfterSeeingImage2) {
	    System.out.println(columnPosition.toString());
	}

	// -------------------"2_with_some_noise.bmp"----------------------
	this.retina.seeBMPImage("2_with_some_noise.bmp");

	this.spatialPooler.performSpatialPoolingOnRegion();
	Set<model.MARK_I.ColumnPosition> columnActivityAfterSeeingImage2_with_some_noise = this.spatialPooler
		.getActiveColumnPositions();
	assertEquals(13, columnActivityAfterSeeingImage2_with_some_noise.size());

	assertTrue(columnActivityAfterSeeingImage2
		.containsAll(columnActivityAfterSeeingImage2_with_some_noise));

	// -------------------"2_with_alot_of_noise.bmp"------------------
	this.retina.seeBMPImage("2_with_alot_of_noise.bmp");

	this.spatialPooler.performSpatialPoolingOnRegion();
	Set<model.MARK_I.ColumnPosition> columnActivityAfterSeeingImage2_with_alot_of_noise = this.spatialPooler
		.getActiveColumnPositions();
	assertEquals(14,
		columnActivityAfterSeeingImage2_with_alot_of_noise.size());
    }
}
