package model.MARK_I;

import model.MARK_I.connectTypes.RegionToRegionConnectInterface;

/**
 * Neocortex is a undirected tree of Regions. Creating a Neocortex with multiple
 * Regions is similar to creating a file system. You can change where you are
 * within the Neocortex tree with the changeCurrentRegion(Region) and then add a
 * child Region to the currentRegion with addRegion(Region).
 *
 * Input to Neocortex: activity of Cells within VisionCellLayer, AudioCellLayer,
 * etc.
 *
 * Output from Neocortex: activity of Cells/Columns within root Region.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Michael Cogswell (cogswell@vt.edu)
 * @version June 8, 2013
 */
public class Neocortex {
    private Region rootRegion;
    private Region currentRegion; // analogous to current directory
    private RegionToRegionConnectInterface connectType;

    public Neocortex(Region rootRegion,
	    RegionToRegionConnectInterface neocortexRegionToNeocortexRegion) {
	if (rootRegion == null) {
	    throw new IllegalArgumentException(
		    "rootRegion in Neocortex constructor cannot be null");
	} else if (neocortexRegionToNeocortexRegion == null) {
	    throw new IllegalArgumentException(
		    "connectType in Neocortex constructor cannot be null");
	}
	this.rootRegion = rootRegion;
	this.currentRegion = this.rootRegion;
	this.connectType = neocortexRegionToNeocortexRegion;
    }

    /**
     * Sets the parameter newCurrentRegion as the currentRegion within
     * neocortex.
     *
     * @param newCurrentRegion
     *            The Region you would like to add child region(s) to.
     */
    public void changeCurrentRegion(Region newCurrentRegion) {
	if (newCurrentRegion == null) {
	    throw new IllegalArgumentException(
		    "newCurrentRegion in Neocortex method changeCurrentRegion cannot be null");
	}
	// check if newCurrentRegion exists within neocortex
	this.currentRegion = newCurrentRegion;
    }

    public Region getCurrentRegion() {
	return this.currentRegion;
    }

    /**
     * @param childRegion
     *            The Region to be added to the currentRegion.
     */
    public void addToCurrentRegion(Region childRegion) {
	if (childRegion == null) {
	    throw new IllegalArgumentException(
		    "childRegion in Neocortex method addToCurrentRegion cannot be null");
	}
	this.currentRegion.addChildRegion(childRegion);
	// connect currentRegion to childRegion
	this.connectType.connect(childRegion, this.currentRegion, 0, 0);
    }

    // post-order traversal with running learning algorithm
}
