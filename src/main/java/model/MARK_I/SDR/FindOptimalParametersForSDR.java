package main.java.model.MARK_I.SDR;

import main.java.model.MARK_I.connectTypes.SensorCellsToRegionRectangleConnect;
import main.java.model.MARK_I.ColumnPosition;
import main.java.model.MARK_I.SpatialPooler;
import main.java.model.MARK_I.connectTypes.SensorCellsToRegionConnectInterface;
import main.java.model.MARK_I.Region;
import main.java.model.Retina;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Set;
import java.io.IOException;

/**
 * Why: There are about a dozen parameters that are very important to how the
 * neurons in the model interact with each other. We need to find the best value
 * for these parameters to allow the brain algorithms to work efficiently.
 *
 * What: This class contains many different ways a partial brain model can be
 * constructed.
 *
 * How: A optimization algorithm will call 1 of the methods below over and over
 * until it has found parameters that produce the best score.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Apr 10, 2014
 */
public class FindOptimalParametersForSDR {

    /**
     * Builds a simple 1 Retina to 1 Region model with given parameters, runs
     * the spatial pooling algorithm once, computes a score based on the output
     * of the spatial pooling algorithm.
     *
     * @param percentMinimumOverlapScore
     * @param desiredLocalActivity
     * @param desiredPercentageOfActiveColumns
     * @param locationOfFileWithFileNameToSaveScore
     * @return The SDR score.
     *
     * @throws IOException
     */
    public static double printToFileSDRScoreFor1RetinaTo1RegionModel(
	    double percentMinimumOverlapScore, double desiredLocalActivity,
	    double desiredPercentageOfActiveColumns,
	    String locationOfFileWithFileNameToSaveScore) throws IOException {
	Retina retina = new Retina(66, 66);
	Region region = new Region("Region", 8, 8, 1,
		percentMinimumOverlapScore, (int) desiredLocalActivity);

	SensorCellsToRegionConnectInterface retinaToRegion = new SensorCellsToRegionRectangleConnect();
	retinaToRegion.connect(retina.getVisionCells(), region, 0, 0);

	SpatialPooler spatialPooler = new SpatialPooler(region);
	spatialPooler.setLearningState(true);

	retina.seeBMPImage("2.bmp");

	spatialPooler.performSpatialPoolingOnRegion(); // 11 active columns
	Set<ColumnPosition> columnActivityAfterSeeingImage2 = spatialPooler
		.getActiveColumnPositions();
	// = (6,5)(6, 3)(6, 2)(5, 3)(3, 5)(2, 2)(1, 3)(1, 2)(2, 5)(1, 5)(4, 4)

	int totalNumberOfColumnsInRegion = region.getXAxisLength()
		* region.getYAxisLength();
	SDRScoreCalculator sdrScoreCalculator = new SDRScoreCalculator(
		columnActivityAfterSeeingImage2,
		desiredPercentageOfActiveColumns, totalNumberOfColumnsInRegion);

	double SDRScore = sdrScoreCalculator.getSDRScore();

	// print SDRScore to file
	try {
	    BufferedWriter out2 = new BufferedWriter(new FileWriter(
		    locationOfFileWithFileNameToSaveScore));
	    out2.write(Double.toString(SDRScore));
	    out2.close();
	} catch (IOException e) {

	}

	return SDRScore;
    }
}
