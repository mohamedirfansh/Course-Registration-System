package controls;
import entities.Users;
import entities.Staff;
import entities.Hash;
import java.util.ArrayList;
import java.util.List;

import java.security.NoSuchAlgorithmException;

public class createStaffData {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		ArrayList<Staff> staffData = new ArrayList();
		Staff staff1 = new Staff("Miao Chunyan", "Miao123", Hash.encode("MiaoPassword"), "Female", "Chinese", "SCSE", "S239723M");

		Staff staff2 = new Staff("Luke Kang Kwong", "Luke123", Hash.encode("LukePassword"), "Male", "Singaporean", "SSS", "S073284L");
		Staff staff3 = new Staff("Christina Soh", "Christina123", Hash.encode("ChristinaPassword"), "Female", "Singaporean", "NBS", "S091210C");
		staffData.add(staff1);
		staffData.add(staff2);
		staffData.add(staff3);

		try {
			SerializeDB sdb = new SerializeDB();

			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject("staff.dat", staffData);
		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}

