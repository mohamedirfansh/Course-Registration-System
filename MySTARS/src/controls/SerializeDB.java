package controls;
import java.io.IOException;
import entities.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The SerializeDB class provides the the static methods that handles the serialization and deserialization of Objects in the background.
 *
 * When methods in the DatabaseControl class requires access to information stored in a particular Object, SerializeDB static methods will be
 * called to read in and deserialize the Object for usage. Likewise when DatabaseControl wants to update an Object with new information,
 * static methods of SerializeDB are called to write these new changes and serialized the Object into their binary file database
 *
 */

public class SerializeDB
{

	/**
	 * readSerializedObject() is used to read in the serialized objects and store them as a list of objects which is then returned for usage by the program
	 *
	 * @param filename, which is the file name of the file you are trying to read and deserialize objects from
	 * @return The list of objects that have been deserialized
	 */
	public static List readSerializedObject(String filename) {
		List pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			//System.out.println("reading from " + filename);
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
	}

	public static List readSerializedStudentObject(String filename) {
		ArrayList<Student> pDetails = new ArrayList<Student>(); 
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			//System.out.println("reading from " + filename);
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (ArrayList) in.readObject();
			in.close();
			fis.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
	}

	/**
	 * readSerializedMapObject() is used specifically to read in a HashMap of userIDs to passwords from a Password binary file
	 *
	 * @return The HashMap that contains the userIDs and the passwords mapped to these userIDs
	 */
	
	public static HashMap<String, String> readSerializedMapObject(String filename) {
		HashMap<String, String> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			//System.out.println("reading from " + filename);
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			pDetails = (HashMap) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
	}

	/*
	 * There are two overloaded versions of writeSerializedObject()
	 *
	 * This version is used to write the HashMap of userIDs mapped to their passwords into the desired binary file
	 *
	 * @param filename, which is the name of file (includes filepath) in which we are trying to write to
	 * @param map, which is the HashMap we are writing into the binary file for storage
	 * @return void
	 */
	public static void writeSerializedObject(String filename, HashMap<String, String> map) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			
			//System.out.println("writing into " + filename);
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(map);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/*
	 * This second version of writeSerializedObject is used to write objects(Staff, Student, School, Course) into the desired binary file
	 *
	 * @param filename, which is the name of the file (includes filepath) in which we are writing to
	 * @param list, which is the list of Objects(Staff, Student, School, Course) which we are writing to
	 * @return void
	 */
	
	public static void writeSerializedObject(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			//System.out.println("writing into " + filename);

			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			fos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
