package controls;
import entities.Student;
import entities.Hash;
import entities.Users;
import controls.SerializeDB;
import java.util.ArrayList;
import java.util.List;
import java.security.NoSuchAlgorithmException;

public class createStudentData {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		ArrayList<Users> studentData = new ArrayList();
		Users student1 = new Student("Tom", "Tom1998", Hash.encode("TomPassword"), "Male", "Singaporean", "SCSE", "U190123A");

		Users student2 = new Student("Dick", "Dick1998", Hash.encode("DickPassword"), "Male", "Singaporean", "SCSE", "U234234D");
		Users student3 = new Student("Harry", "Harry1998", Hash.encode("HarryPassword"), "Male", "Singaporean", "SCSE", "U234760H");

		Users student4 = new Student("John", "John1997", Hash.encode("JohnPassword"), "Male", "Malaysian", "SCSE", "U084287J");
		Users student5 = new Student("Peter", "Peter1997", Hash.encode("PeterPassword"), "Male", "Chinese", "SCSE", "U092367P");

		Users student6 = new Student("Alexander", "Alexander1997", Hash.encode("AlexanderPassword"), "Male", "Indian", "NBS", "U092134A");

		Users student7 = new Student("Jane", "Jane1999", Hash.encode("JanePassword"), "Female", "Malaysian", "NBS", "U093154J");
		Users student8 = new Student("Elizabeth", "Elizabeth1999", Hash.encode("ElizabethPassword"), "Female", "Indonesian", "NBS", "U973243E");
		Users student9 = new Student("Catherine", "Catherine1999", Hash.encode("CatherinePassword"), "Female", "Vietnamnese", "NBS", "U998342C");

		Users student10 = new Student("Adeline", "Adeline1999", Hash.encode("AdelinePassword"), "Female", "Malaysian", "NBS", "U198752A");

		Users student11 = new Student("George", "George1999", Hash.encode("GeorgePassword"), "Male", "Indonesian", "SSS", "U912734G");
		Users student12 = new Student("Paul", "Paul1999", Hash.encode("PaulPassword"), "Male", "Singaporean", "SSS", "U193274P");
		Users student13 = new Student("Jack", "Jack1998", Hash.encode("JackPassword"), "Male", "Malaysian", "SSS", "U298733J");
		Users student14 = new Student("Jacqueline", "Jacqueline1999", Hash.encode("JacquelinePassword"), "Female", "Singaporean", "SSS", "U129248J");
		Users student15 = new Student("Kenny", "Kenny1999", Hash.encode("KennyPassword"), "Male", "Singaporean", "SSS", "U193844K");

		studentData.add(student1);
		studentData.add(student2);
		studentData.add(student3);
		studentData.add(student4);
		studentData.add(student5);
		studentData.add(student6);
		studentData.add(student7);
		studentData.add(student8);
		studentData.add(student9);
		studentData.add(student10);
		studentData.add(student11);
		studentData.add(student12);
		studentData.add(student13);
		studentData.add(student14);
		studentData.add(student15);


		try {
			SerializeDB sdb = new SerializeDB();

			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject("student.dat", studentData);
		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}

