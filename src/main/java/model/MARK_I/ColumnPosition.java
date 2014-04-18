package model.MARK_I;

/**
 * Represents the position of a Column within a Region.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 18, 2013
 */
public class ColumnPosition {
    private int x; // x position within Region
    private int y; // y position within Region

    public ColumnPosition(int x, int y) {
	if (x < 0) {
	    throw new IllegalArgumentException(
		    "x in ColumnPosition class constructor cannot be < 0");
	} else if (y < 0) {
	    throw new IllegalArgumentException(
		    "y in ColumnPosition class constructor cannot be < 0");
	}
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return this.x;
    }

    public int getY() {
	return this.y;
    }

    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("\n=======================");
	stringBuilder.append("\n--ColumnPosition Info--");
	stringBuilder.append("\n(x, y): ");
	stringBuilder.append("(" + this.x + ", " + this.y + ")");
	stringBuilder.append("\n=======================");
	String visionCellInformation = stringBuilder.toString();
	return visionCellInformation;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + this.x;
	result = prime * result + this.y;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	ColumnPosition otherColumnPosition = (ColumnPosition) obj;
	if (this.x != otherColumnPosition.x)
	    return false;
	if (this.y != otherColumnPosition.y)
	    return false;
	return true;
    }
}
