package test.java.model.MARK_I.SDR;

import main.java.model.MARK_I.SDR.FindOptimalParametersForSDR;
import java.io.IOException;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Apr 10, 2014
 */
public class FindOptimalParametersForSDRTest extends junit.framework.TestCase {

    public void setUp() {
	// not necessary since all methods in class FindOptimalParametersForSDR
	// are static(meaning you can directly call them without creating an
	// object)
    }

    /**
     * Place a description of your method here.
     *
     * @throws IOException
     */
    public void testGetSpatialPoolingScoreWithGivenParametersForMarkNullaModel()
	    throws IOException {
	assertEquals(
		8.027,
		FindOptimalParametersForSDR
			.printToFileSDRScoreFor1RetinaTo1RegionModel(50, 3, 10.0,
				"./tests/model/MARK_I/SDR/currentSDRScore.txt"),
		.001);
    }
}
