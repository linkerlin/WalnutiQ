package model.MARK_I.connectTypes;

import model.MARK_I.Region;
import model.MARK_I.SensorCell;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 7, 2013
 */
public interface SensorCellsToRegionConnectInterface {
    public abstract void connect(SensorCell[][] sensorCells, Region region,
	    int numberOfColumnsToOverlapAlongXAxisOfSensorCells,
	    int numberOfColumnsToOverlapAlongYAxisOfSensorCells);

}
