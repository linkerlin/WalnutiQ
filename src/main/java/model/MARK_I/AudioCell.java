package model.MARK_I;

/**
 * A data structure that represents a single audio cell within the cochlea.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 8, 2013
 */
public class AudioCell extends SensorCell {

    public AudioCell()
    {
	super();
    }

    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("\n==================");
	stringBuilder.append("\n--AudioCell Info--");
	stringBuilder.append("\nisActive: ");
	stringBuilder.append(getActiveState());
	stringBuilder.append("\n==================");
	String sensorCellInformation = stringBuilder.toString();
	return sensorCellInformation;
    }
}
