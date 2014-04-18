package main.java.model;

import main.java.model.MARK_I.Neocortex;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 5, 2013
 */
public class Cerebrum {
    private CerebralCortex cerebralCortex;
    // private BasalGanglia basalGanglia;
    // private Hippocampus hippocampus;
    // private Amygdala amygdala;

    public Cerebrum(Neocortex neocortex) {
	this.cerebralCortex = new CerebralCortex(neocortex);
	// this.basalGanglia = null;
	// this.hippocampus = null;
	// this.amygdala = null;
    }

    public CerebralCortex getCerebralCortex() {
	return this.cerebralCortex;
    }
}
