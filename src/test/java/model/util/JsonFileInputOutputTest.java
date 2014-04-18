package model.util;

import com.google.gson.Gson;
import model.MARK_I.Column;
import model.MARK_I.Region;
import model.MARK_I.SpatialPooler;
import model.MARK_I.connectTypes.SensorCellsToRegionConnectInterface;
import model.MARK_I.connectTypes.SensorCellsToRegionRectangleConnect;
import model.Retina;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Feb 1, 2014
 */
public class JsonFileInputOutputTest {
    private Gson gson;

    @Before
    public void setUp() {
	this.gson = new Gson();
    }

    @Test
    public void test_saveRegionObject() throws IOException {
	Region LGNRegion = new Region("LGN", 8, 8, 1, 50, 3);

	Retina retina = new Retina(66, 66);

	SensorCellsToRegionConnectInterface retinaToLGN = new SensorCellsToRegionRectangleConnect();
	retinaToLGN.connect(retina.getVisionCells(), LGNRegion, 0, 0);

	// run spatial pooling on a image
	SpatialPooler spatialPooler = new SpatialPooler(LGNRegion);
	spatialPooler.setLearningState(true);

	retina.seeBMPImage("2.bmp");
	Set<Column> LGNNeuronActivity = spatialPooler
		.performSpatialPoolingOnRegion();

	assertEquals(11, LGNNeuronActivity.size());

	Gson gson2 = new Gson();
	Region trainedLGNRegion = spatialPooler.getRegion();
	String regionObject = gson2.toJson(trainedLGNRegion);

	JsonFileInputOutput.saveObjectToTextFile(regionObject,
		"./src/test/java/model/util/test_saveRegionObject.txt");
    }

    @Test
    public void test_openRegionObject() throws IOException {
	String regionAsString = JsonFileInputOutput
		.openObjectInTextFile("./src/test/java/model/util/test_saveRegionObject.txt");

	Gson gson2 = new Gson();
	Region trainedLGNRegion = gson2.fromJson(regionAsString, Region.class);
	assertEquals("LGN", trainedLGNRegion.getBiologicalName());
    }
}
