package main.java.model;

/**
 * "Relay station" for all sensory information(except smell) which
 * is conveyed to the cortex.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 5, 2013
 */
public class Thalamus {
    private LGN LGN;

    public Thalamus(LGN LGN) {
	this.LGN = LGN;
    }

    public LGN getLGN() {
	return this.LGN;
    }
}
