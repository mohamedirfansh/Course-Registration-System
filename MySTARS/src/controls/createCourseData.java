package controls;
import entities.Course;
import java.util.ArrayList;
import java.util.List;

import java.security.NoSuchAlgorithmException;

public class createCourseData {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		ArrayList<Course> courseData = new ArrayList();
		Course course1 = new Course("Data Stuctures", "CZ1007", "School of Computer Science and Engineering", 3);

		Course course2 = new Course("Algorithms", "CZ2001", "School of Computer Science and Engineering", 3);
		Course course3 = new Course("Business Finance", "BU8201", "Nanyang Business School", 3);

		Course course4 = new Course("Business Law", "AB1301", "Nanyang Business School", 3);
		Course course5 = new Course("Introduction to Psychology", "HP1000", "School of Social Sciences", 3);
		Course course6 = new Course("Principles of Economics", "HE9091", "School of Social Sciences", 3);

		courseData.add(course1);
		courseData.add(course2);
		courseData.add(course3);
		courseData.add(course4);
		courseData.add(course5);
		courseData.add(course6);

		try {
			SerializeDB sdb = new SerializeDB();

			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject("course.dat", courseData);
		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}

