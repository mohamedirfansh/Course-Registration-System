package data;
import controls.*;
import entities.*;
import java.util.ArrayList;
import java.util.List;

import java.security.NoSuchAlgorithmException;

/**
 * Class used to pre-load course data into the database.
 */
public class createCourseData {
	
/* 	static final String COURSE = System.getProperty("user.dir") + "/src/data/course.dat";
 *  	*/

	static final String COURSE = System.getProperty("user.dir") + "/course.dat";

	public static void main(String[] args) throws NoSuchAlgorithmException {
		ArrayList<Course> courseData = new ArrayList();

		/* SCSE Courses */
		Course course1 = new Course("Data Stuctures", "CZ1007", "School of Computer Science and Engineering", 3);
		WorkingHours course1LectureHours = new WorkingHours("08:30", "09:30", 2);
		course1.addCourseLecture("LT2A", course1LectureHours);
		course1.addIndex("CZ1007SS1", "SS1");
		course1.addIndex("CZ1007SS2", "SS2");

		WorkingHours SS1TutorialHours = new WorkingHours("09:30", "10:30", 2);
		WorkingHours SS1LabHours = new WorkingHours("13:30", "15:30", 2);
		course1.findIndex("CZ1007SS1").addLesson("TUT", "TR+77", SS1TutorialHours);
		course1.findIndex("CZ1007SS1").addLesson("LAB", "SPL", SS1LabHours);
		course1.findIndex("CZ1007SS1").registerStudent("U190123A");
		course1.findIndex("CZ1007SS1").registerStudent("U234234D");

		WorkingHours SS2TutorialHours = new WorkingHours("09:30", "10:30", 2);
		WorkingHours SS2LabHours = new WorkingHours("13:30", "15:30", 2);
		course1.findIndex("CZ1007SS2").addLesson("TUT", "TR+77", SS2TutorialHours);
		course1.findIndex("CZ1007SS2").addLesson("LAB", "SPL", SS2LabHours);
		course1.findIndex("CZ1007SS2").registerStudent("U234760H");
		course1.findIndex("CZ1007SS2").registerStudent("U084287J");
		course1.findIndex("CZ1007SS2").registerStudent("U092367P");

		Course course2 = new Course("Algorithms", "CZ2001", "School of Computer Science and Engineering", 3);
		WorkingHours course2LectureHours = new WorkingHours("08:30", "09:30", 3);
		course2.addCourseLecture("LT2A", course2LectureHours);
		course2.addIndex("CZ2001SS1", "SS1");
		course2.addIndex("CZ2001SS2", "SS2");
		course1.findIndex("CZ1007SS2").registerStudent("U234234D");

		WorkingHours CZ2001SS1TutorialHours = new WorkingHours("09:30", "10:30", 3);
		WorkingHours CZ2001SS1LabHours = new WorkingHours("13:30", "15:30", 3);
		course2.findIndex("CZ2001SS1").addLesson("TUT", "TR+77", CZ2001SS1TutorialHours);
		course2.findIndex("CZ2001SS1").addLesson("LAB", "SPL", CZ2001SS1LabHours);
		course2.findIndex("CZ2001SS1").registerStudent("U190123A");
		course2.findIndex("CZ2001SS1").registerStudent("U234234D");
		course2.findIndex("CZ2001SS1").registerStudent("U234760H");
		course2.findIndex("CZ2001SS1").registerStudent("U084287J");
		course2.findIndex("CZ2001SS1").registerStudent("U092367P");
		course2.findIndex("CZ2001SS1").registerStudent("U092134A");
		course2.findIndex("CZ2001SS1").registerStudent("U093154J");
		course2.findIndex("CZ2001SS1").registerStudent("U973243E");
		course2.findIndex("CZ2001SS1").registerStudent("U998342C");
		course2.findIndex("CZ2001SS1").registerStudent("U198752A");


		WorkingHours CZ2001SS2TutorialHours = new WorkingHours("09:30", "10:30", 3);
		WorkingHours CZ2001SS2LabHours = new WorkingHours("13:30", "15:30", 3);
		course2.findIndex("CZ2001SS2").addLesson("TUT", "TR+77", CZ2001SS2TutorialHours);
		course2.findIndex("CZ2001SS2").addLesson("LAB", "SPL", CZ2001SS2LabHours);

		/* NBS Courses */
		Course course3 = new Course("Business Finance", "BU8201", "Nanyang Business School", 3);
		WorkingHours course3LectureHours = new WorkingHours("08:30", "09:30", 2);
		course3.addCourseLecture("LT1A", course3LectureHours);
		course3.addIndex("BU8201BU1", "BU1");
		course3.addIndex("BU8201BU2", "BU2");

		WorkingHours BU8201BU1TutorialHours = new WorkingHours("09:30", "10:30", 2);
		course3.findIndex("BU8201BU1").addLesson("TUT", "TR+77", BU8201BU1TutorialHours);
		course3.findIndex("BU8201BU1").registerStudent("U092134A");
		course3.findIndex("BU8201BU1").registerStudent("U093154J");

		WorkingHours BU8201BU2TutorialHours = new WorkingHours("09:30", "10:30", 3);
		course3.findIndex("BU8201BU2").addLesson("TUT", "TR+77", BU8201BU2TutorialHours);
		course3.findIndex("BU8201BU2").registerStudent("U973243E");
		course3.findIndex("BU8201BU2").registerStudent("U998342C");
		course3.findIndex("BU8201BU2").registerStudent("U198752A");


		// SSS Courses
		Course course5 = new Course("Introduction to Psychology", "HP1000", "School of Social Sciences", 3);
		WorkingHours course5LectureHours = new WorkingHours("08:30", "09:30", 4);
		course5.addCourseLecture("LT2A", course5LectureHours);
		course5.addIndex("HP1000HP1", "HP1");
		course5.addIndex("HP1000HP2", "HP2");

		WorkingHours HP1000HP1TutorialHours = new WorkingHours("09:30", "10:30", 4);
		course5.findIndex("HP1000HP1").addLesson("TUT", "TR+77", HP1000HP1TutorialHours);
		course5.findIndex("HP1000HP1").registerStudent("U912734G");
		course5.findIndex("HP1000HP1").registerStudent("U193274P");

		WorkingHours HP1000HP2TutorialHours = new WorkingHours("09:30", "10:30", 4);
		course5.findIndex("HP1000HP2").addLesson("TUT", "TR+77", HP1000HP2TutorialHours);
		course5.findIndex("HP1000HP2").registerStudent("U298733J");
		course5.findIndex("HP1000HP2").registerStudent("U129248J");
		course5.findIndex("HP1000HP2").registerStudent("U193844K");
		

		courseData.add(course1);
		courseData.add(course2);
		courseData.add(course3);
		courseData.add(course5);
		

		try {
			SerializeDB sdb = new SerializeDB();
			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject(COURSE, courseData);
		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}

