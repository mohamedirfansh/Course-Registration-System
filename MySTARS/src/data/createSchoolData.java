package data;
import entities.School;
import controls.SerializeDB;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.security.NoSuchAlgorithmException;

public class createSchoolData {
	
	static final String SCHOOL = System.getProperty("user.dir") + "/src/data/school.dat";

	public static void main(String[] args) throws NoSuchAlgorithmException {
		ArrayList<School> schoolData = new ArrayList();
		School school1 = new School("School of Computer Science and Engineering", "SCSE");

		School school3 = new School("School of Social Sciences", "SSS");
		School school2 = new School("Nanyang Business School", "NBS");

		school1.setSchoolID(11);
		school2.setSchoolID(21);
		school3.setSchoolID(31);

		ArrayList<String> school1Students = new ArrayList<String>(
				Arrays.asList("U190123A", "U234234D", "U234760H", "U084287J", "U092367P"));

		ArrayList<String> school3Students = new ArrayList<String>(
				Arrays.asList("U912734G", "U193274P", "U298733J", "U129248J", "U193844K")); 

		ArrayList<String> school2Students = new ArrayList<String>(
				Arrays.asList("U092134A", "U093154J", "U973243E", "U998342C", "U198752A")); 
		
		school1.setAllStudents(school1Students);
		school2.setAllStudents(school2Students);
		school3.setAllStudents(school3Students);


		ArrayList<String> school1Staff = new ArrayList<String>(
				Arrays.asList("S239723M"));
		ArrayList<String> school2Staff = new ArrayList<String>(
				Arrays.asList("S091210C"));
		ArrayList<String> school3Staff = new ArrayList<String>(
				Arrays.asList("S073284L"));

		school1.setAllStaff(school1Staff);
		school2.setAllStaff(school2Staff);
		school3.setAllStaff(school3Staff);

		ArrayList<String> school1Courses = new ArrayList<String>(
				Arrays.asList("CZ1007", "CZ2001"));
		school1.setAllCourses(school1Courses);

		ArrayList<String> school2Courses = new ArrayList<String>(
				Arrays.asList("BU8201"));
		school2.setAllCourses(school2Courses);

		ArrayList<String> school3Courses = new ArrayList<String>(
				Arrays.asList("HP1000"));
		school3.setAllCourses(school3Courses);

		school1.setAccessPeriod("20/11/2020 08:00", "25/11/2020 23:00"); 
		school2.setAccessPeriod("21/11/2020 08:00", "25/11/2020 23:00");
		school3.setAccessPeriod("22/11/2020 08:00", "25/11/2020 23:00");

		schoolData.add(school1);
		schoolData.add(school2);
		schoolData.add(school3);

		try {
			SerializeDB sdb = new SerializeDB();

			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject(SCHOOL, schoolData);
		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}

