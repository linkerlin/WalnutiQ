package model.MARK_I.connectTypes;

import model.MARK_I.Cell;
import model.MARK_I.Synapse;
import model.MARK_I.Column;
import model.MARK_I.Region;
import model.MARK_I.SensorCell;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 13, 2013
 */
public class SensorCellsToRegionRectangleConnect implements
	SensorCellsToRegionConnectInterface {
    @Override
    public void connect(SensorCell[][] sensorCells, Region region,
	    int numberOfColumnsToOverlapAlongXAxisOfSensorCells,
	    int numberOfColumnsToOverlapAlongYAxisOfSensorCells) {

	this.checkParameters(sensorCells, region,
		numberOfColumnsToOverlapAlongXAxisOfSensorCells,
		numberOfColumnsToOverlapAlongYAxisOfSensorCells);

	Column[][] regionColumns = region.getColumns();
	int regionXAxisLength = regionColumns[0].length;
	int regionYAxisLength = regionColumns.length;

	int sensorCellsXAxisLength = sensorCells[0].length;
	int sensorCellsYAxisLength = sensorCells.length;

	int connectingRectangleXAxisLength = Math
		.round((sensorCellsXAxisLength
			+ numberOfColumnsToOverlapAlongXAxisOfSensorCells
			* regionXAxisLength - numberOfColumnsToOverlapAlongXAxisOfSensorCells)
			/ regionXAxisLength);
	int connectingRectangleYAxisLength = Math
		.round((sensorCellsYAxisLength
			+ numberOfColumnsToOverlapAlongYAxisOfSensorCells
			* regionYAxisLength - numberOfColumnsToOverlapAlongYAxisOfSensorCells)
			/ regionYAxisLength);

	int shiftAmountXAxis = connectingRectangleXAxisLength
		- numberOfColumnsToOverlapAlongXAxisOfSensorCells;
	int shiftAmountYAxis = connectingRectangleYAxisLength
		- numberOfColumnsToOverlapAlongYAxisOfSensorCells;
	for (int columnX = 0; columnX < regionXAxisLength; columnX++) {
	    for (int columnY = 0; columnY < regionYAxisLength; columnY++) {

		int xStart = columnX * shiftAmountXAxis;
		int yStart = columnY * shiftAmountYAxis;

		int xEnd = xStart + connectingRectangleXAxisLength;
		int yEnd = yStart + connectingRectangleYAxisLength;

		Column column = regionColumns[columnX][columnY];

		for (int sensorCellX = xStart; sensorCellX < xEnd; sensorCellX++) {
		    for (int sensorCellY = yStart; sensorCellY < yEnd; sensorCellY++) {
			// # of Synapses connected/add to this proximal Segment
			// = connectingRectangleXAxisLength *
			// connectingRectangleYAxisLength
			column.getProximalSegment().addSynapse(
				new Synapse<Cell>(
					sensorCells[sensorCellX][sensorCellY],
					sensorCellX, sensorCellY));
		    }
		}
	    }
	}
    }

    private void checkParameters(SensorCell[][] sensorCells, Region region,
	    int numberOfColumnsToOverlapAlongXAxisOfSensorCells,
	    int numberOfColumnsToOverlapAlongYAxisOfSensorCells) {
	if (region == null) {
	    throw new IllegalArgumentException(
		    "region in SensorCellsToRegionRectangleConnect class"
			    + "connect method cannot be null");
	} else if (sensorCells == null) {
	    throw new IllegalArgumentException(
		    "sensorCells in SensorCellsToRegionRectangleConnect class"
			    + "connect method cannot be null");
	} else if (sensorCells.length <= region.getXAxisLength()
		|| sensorCells[0].length <= region.getYAxisLength()) {
	    throw new IllegalArgumentException(
		    "sensorCells in connect method cannot be smaller in X or Y "
			    + "dimentions than the region");
	} else if (numberOfColumnsToOverlapAlongXAxisOfSensorCells < 0) {
	    throw new IllegalArgumentException(
		    "numberOfColumnsToOverlapAlongXAxisOfSensorCells in connect method cannot be < 0");
	} else if (numberOfColumnsToOverlapAlongYAxisOfSensorCells < 0) {
	    throw new IllegalArgumentException(
		    "numberOfColumnsToOverlapAlongYAxisOfSensorCells in connect method cannot be < 0");
	}
    }
}
