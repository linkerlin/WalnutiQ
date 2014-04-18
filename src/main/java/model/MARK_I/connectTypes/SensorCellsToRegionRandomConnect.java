package model.MARK_I.connectTypes;

import model.MARK_I.Cell;
import model.MARK_I.Synapse;
import model.MARK_I.Column;
import model.MARK_I.Region;
import model.MARK_I.SensorCell;

import java.awt.Point;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version July 18, 2013
 */
public class SensorCellsToRegionRandomConnect implements
	SensorCellsToRegionConnectInterface {

    /**
     * In order to explain overlapping Synapses more concisely I will be using
     * the abbreviated variable names in the below formula:
     * numberOfColumnsToOverlapAlongXAxisOfSensorCells = C_X
     * numberOfColumnsToOverlapAlongYAxisOfSensorCells = C_Y sensorCells.length
     * = SC_X sensorCells[0].length = SC_Y regionXAxisLength = R_X
     * regionYAxisLength = R_Y
     *
     * [(C_X + SC_X) * (C_Y + SC_Y)] / (R_X * R_Y) = # of Synapses created to
     * connect a SensorCellLayer to a leaf Region Column.
     */
    @Override
    public void connect(SensorCell[][] sensorCells, Region region,
	    int numberOfSynapsesToOverlapAlongXAxisOfSensorCells,
	    int numberOfSynapsesToOverlapAlongYAxisOfSensorCells) {

	this.checkParameters(sensorCells, region,
		numberOfSynapsesToOverlapAlongXAxisOfSensorCells,
		numberOfSynapsesToOverlapAlongYAxisOfSensorCells);

	Column[][] regionColumns = region.getColumns();
	int regionXAxisLength = regionColumns.length; // 8
	int regionYAxisLength = regionColumns[0].length; // 8

	int sensorCellsXAxisLength = sensorCells.length; // 66
	int sensorCellsYAxisLength = sensorCells[0].length; // 66

	List<Point> allSynapsePositions = new ArrayList<Point>(
		sensorCellsXAxisLength * sensorCellsYAxisLength);
	// generate all possible Synapse positions
	for (int x = 0; x < sensorCellsXAxisLength; x++) {
	    for (int y = 0; y < sensorCellsYAxisLength; y++) {
		allSynapsePositions.add(new Point(x, y));
	    }
	}
	Collections.shuffle(allSynapsePositions);

	// ((2+66) * (2+66))/(8*8) = 68*68/64 = 72.25 > 66
	int numberOfSynapsesToAddToColumn = ((numberOfSynapsesToOverlapAlongXAxisOfSensorCells + sensorCellsXAxisLength) * (numberOfSynapsesToOverlapAlongYAxisOfSensorCells + sensorCellsYAxisLength))
		/ (regionXAxisLength * regionYAxisLength);

	for (int columnX = 0; columnX < regionXAxisLength; columnX++) {
	    for (int columnY = 0; columnY < regionYAxisLength; columnY++) {
		Column column = regionColumns[columnX][columnY];

		// add first numberOfSynapsesToAddToColumn to this Column
		for (int i = 0; i < numberOfSynapsesToAddToColumn; i++) {
		    int randomSynapseXPosition = allSynapsePositions.get(i).x;
		    int randomSynapseYPosition = allSynapsePositions.get(i).y;

		    column.getProximalSegment()
			    .addSynapse(
				    new Synapse<Cell>(
					    sensorCells[randomSynapseXPosition][randomSynapseYPosition],
					    randomSynapseXPosition,
					    randomSynapseYPosition));
		}
		Collections.shuffle(allSynapsePositions);
	    }
	}
    }

    private void checkParameters(SensorCell[][] sensorCells, Region region,
	    int numberOfSynapsesToOverlapAlongXAxisOfSensorCells,
	    int numberOfSynapsesToOverlapAlongYAxisOfSensorCells) {
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
	} else if (numberOfSynapsesToOverlapAlongXAxisOfSensorCells < 0) {
	    throw new IllegalArgumentException(
		    "numberOfSynapsesToOverlapAlongXAxisOfSensorCells in connect method cannot be < 0");
	} else if (numberOfSynapsesToOverlapAlongYAxisOfSensorCells < 0) {
	    throw new IllegalArgumentException(
		    "numberOfSynapsesToOverlapAlongYAxisOfSensorCells in connect method cannot be < 0");
	}
    }
}
