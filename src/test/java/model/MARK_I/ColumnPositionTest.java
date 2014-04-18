package model.MARK_I;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 18, 2013
 */
public class ColumnPositionTest {
    private ColumnPosition columnPosition69;
    private ColumnPosition columnPosition_69;
    private ColumnPosition columnPosition96;

    @Before
    public void setUp() {
	this.columnPosition69 = new ColumnPosition(6, 9);
	this.columnPosition_69 = new ColumnPosition(6, 9);
	this.columnPosition96 = new ColumnPosition(9, 6);
    }

    @Test
    public void test_equals() {
	assertTrue(this.columnPosition69.equals(this.columnPosition_69));
	assertFalse(this.columnPosition69.equals(this.columnPosition96));
    }
}
