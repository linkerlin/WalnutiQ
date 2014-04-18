package test.java.model.MARK_I;

import main.java.model.MARK_I.ColumnPosition;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 18, 2013
 */
public class ColumnPositionTest extends junit.framework.TestCase {
    private ColumnPosition columnPosition69;
    private ColumnPosition columnPosition_69;
    private ColumnPosition columnPosition96;

    public void setUp() {
	this.columnPosition69 = new ColumnPosition(6, 9);
	this.columnPosition_69 = new ColumnPosition(6, 9);
	this.columnPosition96 = new ColumnPosition(9, 6);
    }

    public void test_equals() {
	assertTrue(this.columnPosition69.equals(this.columnPosition_69));
	assertFalse(this.columnPosition69.equals(this.columnPosition96));
    }
}
