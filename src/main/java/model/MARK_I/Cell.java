package main.java.model.MARK_I;

/**
 * Provides the minimal behaviors for a Cell.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Michael Cogswell (cogswell@vt.edu)
 * @version July 22, 2013
 */
public class Cell {
    protected boolean isActive; // biologically equivalent to generating 1 or
				// more spikes
    protected boolean wasActive;

    public Cell() {
	this.isActive = false;
	this.wasActive = false;
    }

    public boolean getActiveState() {
	return this.isActive;
    }

    public void setActiveState(boolean isActive) {
	this.isActive = isActive;
    }

    public boolean getPreviousActiveState() {
	return this.wasActive;
    }

    public void setPreviousActiveState(boolean wasActive) {
	this.wasActive = wasActive;
    }
}
