package model.MARK_I.connectTypes;

import model.MARK_I.Column;
import model.MARK_I.Region;
import model.MARK_I.SensorCell;
import model.MARK_I.VisionCell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version July 14th, 2013
 */
public class SensorCellsToRegionRandomConnectTest {

    private SensorCellsToRegionRandomConnect connectType;

    @Before
    public void setUp() {
	this.connectType = new SensorCellsToRegionRandomConnect();
    }

    @Test
    public void test_connect() {
	Region leafRegion = new Region("leafRegion", 8, 8, 4, 20, 3);
	SensorCell[][] sensorCells = new VisionCell[66][66];
	for (int x = 0; x < sensorCells.length; x++) {
	    for (int y = 0; y < sensorCells[0].length; y++) {
		sensorCells[x][y] = new VisionCell();
	    }
	}

	this.connectType.connect(sensorCells, leafRegion, 2, 2);
	Column[][] columns = leafRegion.getColumns();
	for (int parentColumnX = 0; parentColumnX < leafRegion.getXAxisLength(); parentColumnX++) {
	    for (int parentColumnY = 0; parentColumnY < leafRegion
		    .getYAxisLength(); parentColumnY++) {
		assertEquals(72, columns[parentColumnX][parentColumnY]
			.getProximalSegment().getSynapses().size());
	    }
	}
    }
}
