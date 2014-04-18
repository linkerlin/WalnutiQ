package main.java.model.MARK_I;

/**
 * Simulates a cortical pyramidal cell.
 *
 * A Neuron has 3 different states?
 * 1) 3 spikes per second
 * 2) 10+ spikes per second
 * 3) 0 spikes per second
 * Support: https://db.tt/QFqA4Dta
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Michael Cogswell (cogswell@vt.edu)
 * @version July 22, 2013
 */
public class Neuron extends Cell {

    public Neuron() {
	super(); // Initializes isActive state
    }

    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("\n===========================");
	stringBuilder.append("\n--------Neuron Info--------");
	stringBuilder.append("\n           isActive: ");
	stringBuilder.append(this.isActive);
	stringBuilder.append("\n          wasActive: ");
	stringBuilder.append(this.wasActive);
	stringBuilder.append("\n       isPredicting: ");
	stringBuilder.append("\n===========================");
	String NeuronInformation = stringBuilder.toString();
	return NeuronInformation;
    }
}
