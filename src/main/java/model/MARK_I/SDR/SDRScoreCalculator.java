package main.java.model.MARK_I.SDR;

import main.java.model.MARK_I.ColumnPosition;
import java.util.Set;

/**
 * Uses the equation found here https://github.com/quinnliu/WalnutiQ/issues/21
 * for computating a score for the SDR created by spatial pooling.
 *
 * Input from constructor: set of active column positions produced by spatial
 * pooling.
 *
 * Output from calling public method: a score where the larger the score
 * represents a better sparse distributed representation.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Apr 10, 2014
 */
public class SDRScoreCalculator {
    private double xAverage;
    private double yAverage;

    private double sparsityScore;
    private double numberOfActiveColumnsScore;

    private double sparseDistributedRepresentationScore;

    /**
     * Computes the SDR score for the given set of column positions.
     *
     * @param columnActivityAfterSeeingImage
     */
    public SDRScoreCalculator(
	    Set<ColumnPosition> columnActivityAfterSeeingImage,
	    double desiredPercentageOfActiveColumns,
	    int totalNumberOfColumnsInRegion) {
	this.computeXYAverages(columnActivityAfterSeeingImage);

	this.sparsityScore = 0;
	for (ColumnPosition columnPosition : columnActivityAfterSeeingImage) {
	    this.sparsityScore += Math.pow(columnPosition.getX()
		    - this.xAverage, 2)
		    + Math.pow(columnPosition.getY() - this.yAverage, 2);
	}

	this.sparsityScore = Math.sqrt(this.sparsityScore);

	this.numberOfActiveColumnsScore = 0;

	int desiredNumberOfActiveColumns = (int) (totalNumberOfColumnsInRegion
		* desiredPercentageOfActiveColumns / 100);

	int actualNumberOfActiveColumns = columnActivityAfterSeeingImage.size();
	double difference = Math.abs(desiredNumberOfActiveColumns
		- actualNumberOfActiveColumns);
	if (difference < 0.1 && difference > -0.1) {
	    difference += 0.1; // 1 / 0.1 = 10 which is a great score
			       // as it should be since desired was = to actual
	}

	this.numberOfActiveColumnsScore = 1 / difference;

	this.sparseDistributedRepresentationScore = sparsityScore
		+ numberOfActiveColumnsScore;
    }

    private void computeXYAverages(
	    Set<ColumnPosition> columnActivityAfterSeeingImage) {
	this.xAverage = 0;
	this.yAverage = 0;

	for (ColumnPosition columnPosition : columnActivityAfterSeeingImage) {
	    // find xAverage & yAverage
	    this.xAverage += columnPosition.getX();
	    this.yAverage += columnPosition.getY();
	}
	this.xAverage = this.xAverage / columnActivityAfterSeeingImage.size();
	this.yAverage = this.yAverage / columnActivityAfterSeeingImage.size();
    }

    /**
     * @return SDR score computed from given set of active column positions.
     */
    public double getSDRScore() {
	return this.sparseDistributedRepresentationScore;
    }
}
