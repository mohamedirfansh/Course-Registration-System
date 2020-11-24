package data;
import entities.User;
import entities.Staff;
import entities.Hash;
import controls.SerializeDB;
import controls.Password;
import java.util.ArrayList;
import java.util.List;

import java.security.NoSuchAlgorithmException;

/**
 * Class used to pre-load staff data into the database.
 */
public class createStaffData {
	
	static final String STAFF = System.getProperty("user.dir") + "/src/data/staff.dat";
	static final String STAFFPASSWORD = System.getProperty("user.dir") + "/src/data/staffPassword.dat";

	public static void createStaff() throws NoSuchAlgorithmException {
		ArrayList<Staff> staffData = new ArrayList();
		Staff staff1 = new Staff("Miao Chunyan", "Miao123", "Female", "Chinese", 11, "S239723M");
		Password.addNewPassword("Miao123", "MiaoPassword");

		Staff staff2 = new Staff("Luke Kang Kwong", "Luke123", "Male", "Singaporean", 31, "S073284L");
		Password.addNewPassword("Luke123", "LukePassword");
		Staff staff3 = new Staff("Christina Soh", "Christina123", "Female", "Singaporean", 21, "S091210C");
		Password.addNewPassword("Christina123", "ChristinaPassword");
		
		staffData.add(staff1);
		staffData.add(staff2);
		staffData.add(staff3);

		try {
			SerializeDB sdb = new SerializeDB();

			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject(STAFF, staffData);
			sdb.writeSerializedObject(STAFFPASSWORD, Password.getHashMap());

		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}

	public static void main(String[] args) throws NoSuchAlgorithmException  {
		createStaffData staffData = new createStaffData();
		staffData.createStaff();
	}
}

