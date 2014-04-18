package model.util;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * Objects are saved as JSON files because JSON files are human readable and
 * it is very efficient.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 26, 2013
 */
public class JsonFileInputOutput {

    public static void saveObjectToTextFile(String jsonObject,
	    String textFileName) throws IOException {
	BufferedWriter bw = null;
	try {
	    bw = new BufferedWriter(new FileWriter(new File(textFileName)));
	    bw.write(jsonObject);
	} finally {
	    try {
		bw.close();
	    } catch (Exception e) {

	    }
	}
    }

    public static String openObjectInTextFile(String textFileName)
	    throws IOException {
	BufferedReader br = null;
	try {
	    br = new BufferedReader(new FileReader(new File(textFileName)));
	    return br.readLine();
	} finally {
	    try {
		br.close();
	    } catch (Exception e) {

	    }
	}
    }
}
