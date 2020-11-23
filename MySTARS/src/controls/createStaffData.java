package controls;
import entities.User;
import entities.Staff;
import entities.Hash;
import java.util.ArrayList;
import java.util.List;

import java.security.NoSuchAlgorithmException;

public class createStaffData {

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
			sdb.writeSerializedObject("staff.dat", staffData);
			sdb.writeSerializedObject("staffPassword.dat", Password.getHashMap());

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

