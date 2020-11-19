package boundaries;
import entities.School;
import java.util.ArrayList;
import java.util.List;

import java.security.NoSuchAlgorithmException;

public class createSchoolData {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		ArrayList<School> schoolData = new ArrayList();
		School school1 = new School("School of Computer Science and Engineering", "SCSE");

		School school2 = new School("School of Social Sciences", "SSS");
		School school3 = new School("Nanyang Business School", "NBS");
		schoolData.add(school1);
		schoolData.add(school2);
		schoolData.add(school3);

		try {
			SerializeDB sdb = new SerializeDB();

			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject("school.dat", schoolData);
		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}

