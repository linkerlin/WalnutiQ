package model.MARK_I.SDR;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Apr 10, 2014
 */
public class FindOptimalParametersForSDRTest {

    @Before
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
    @Test
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
