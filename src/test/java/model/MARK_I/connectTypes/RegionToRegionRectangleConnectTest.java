package test.java.model.MARK_I.connectTypes;

import model.MARK_I.Column;
import model.MARK_I.Region;
import model.MARK_I.connectTypes.RegionToRegionRectangleConnect;
import model.MARK_I.connectTypes.RegionToRegionConnectInterface;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 13, 2013
 */
public class RegionToRegionRectangleConnectTest extends
	junit.framework.TestCase {
    private RegionToRegionConnectInterface connectType;

    public void setUp() {
	this.connectType = new RegionToRegionRectangleConnect();
    }

    public void test_Connect() {
	Region parentRegion = new Region("parentRegion", 8, 8, 4, 20, 3);
	Region childRegion = new Region("childRegion", 66, 66, 4, 20, 3);

	this.connectType.connect(childRegion, parentRegion, 2, 2);

	Column[][] columns = parentRegion.getColumns();
	for (int parentColumnX = 0; parentColumnX < parentRegion
		.getXAxisLength(); parentColumnX++) {
	    for (int parentColumnY = 0; parentColumnY < parentRegion
		    .getYAxisLength(); parentColumnY++) {
		assertEquals(100, columns[parentColumnX][parentColumnY]
			.getProximalSegment().getSynapses().size());
	    }
	}
    }
}
