package test.java.model.MARK_I;

import main.java.model.MARK_I.Column;
import main.java.model.MARK_I.connectTypes.RegionToRegionRectangleConnect;
import main.java.model.MARK_I.connectTypes.RegionToRegionConnectInterface;
import main.java.model.MARK_I.Region;
import main.java.model.MARK_I.Neocortex;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 13, 2013
 */
public class NeocortexTest extends junit.framework.TestCase {
    private Neocortex neocortex;

    public void setUp() {
	Region rootRegion = new Region("rootRegion", 6, 8, 4, 20, 3);
	RegionToRegionConnectInterface connectType = new RegionToRegionRectangleConnect();
	this.neocortex = new Neocortex(rootRegion, connectType);
    }

    public void test_addToCurrentRegion() {
	Region childRegion = new Region("childRegion", 48, 64, 4, 20, 3);

	Column[][] columns = this.neocortex.getCurrentRegion().getColumns();

	assertEquals(0, columns[0][0].getProximalSegment().getSynapses().size());

	this.neocortex.addToCurrentRegion(childRegion);

	assertEquals(64, columns[0][0].getProximalSegment().getSynapses()
		.size());
    }
}
