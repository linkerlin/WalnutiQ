package model.MARK_I.vision;

import main.java.model.*;
import main.java.model.MARK_I.*;
import main.java.model.MARK_I.connectTypes.*;
import main.java.model.util.*;

import com.google.gson.Gson;
import java.util.Set;
import java.io.IOException;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version April 12, 2014
 */
public class HowMARK_I_FitsInToBrainAnatomy extends junit.framework.TestCase {
    private NervousSystem partialNervousSystem;

    /**
     * For saving the Java NervousSystem object as a JSON file later on.
     */
    private Gson gson;

    public void setUp() throws IOException {
	this.partialNervousSystem = this.constructConnectedNervousSystem();

	this.gson = new Gson();
    }

    private NervousSystem constructConnectedNervousSystem() {
	Neocortex unconnectedNeocortex = new Neocortex(new Region("V1", 4, 4,
		4, 50, 3), new RegionToRegionRectangleConnect());

	LGN unconnectedLGN = new LGN(new Region("LGN", 8, 8, 1, 50, 3));

	Retina unconnectedRetina = new Retina(66, 66);

	NervousSystem nervousSystem = new NervousSystem(unconnectedNeocortex,
		unconnectedLGN, unconnectedRetina);

	// connect Retina to LGN
	Retina retina = nervousSystem.getPNS().getSNS().getRetine();
	LGN LGN = nervousSystem.getCNS().getBrain().getThalamus().getLGN();
	SensorCellsToRegionConnectInterface opticNerve = new SensorCellsToRegionRectangleConnect();
	opticNerve.connect(retina.getVisionCells(), LGN.getRegion(), 0, 0);

	// connect LGN to very small part of V1 Region of Neocortex
	Neocortex neocortex = nervousSystem.getCNS().getBrain().getCerebrum()
		.getCerebralCortex().getNeocortex();
	RegionToRegionConnectInterface regionToRegionConnect = new RegionToRegionRectangleConnect();
	regionToRegionConnect.connect(LGN.getRegion(),
		neocortex.getCurrentRegion(), 0, 0);

	return nervousSystem;
    }

    public void testHowToRunSpatialPoolingOnNervousSystem() throws IOException {
	Retina retina = partialNervousSystem.getPNS().getSNS().getRetine();
	Region LGNRegion = partialNervousSystem.getCNS().getBrain()
		.getThalamus().getLGN().getRegion();

	retina.seeBMPImage("2.bmp");

	SpatialPooler spatialPooler = new SpatialPooler(LGNRegion);
	spatialPooler.setLearningState(true);
	spatialPooler.performSpatialPoolingOnRegion();
	Set<ColumnPosition> LGNNeuronActivity = spatialPooler
		.getActiveColumnPositions();

	assertEquals(11, LGNNeuronActivity.size());

	// save partialNervousSystemObject object in JSON format
	String partialNervousSystemObject = this.gson
		.toJson(this.partialNervousSystem);
	JsonFileInputOutput
		.saveObjectToTextFile(partialNervousSystemObject,
			"./experiments/model/MARK_I/vision/PartialNervousSystemObject.json");
    }
}
