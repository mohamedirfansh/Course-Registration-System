package entities;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Staff extends Users{
	private static HashMap<String, Staff> listOfStaff = new HashMap<>();
	
	public Staff(String name, String userID, String userPW,
			String gender, String nationality, String schoolID, 
			String identificationKey) throws NoSuchAlgorithmException {
		super(name, userID, userPW, gender, nationality, schoolID, identificationKey);
		Object temp = (Object) this;
		setAdminPrivileges(temp);
		Staff staff1 = (Staff) temp;
		String u_id = staff1.getUserID(true);
		if (!listOfStaff.containsKey(u_id))
			listOfStaff.put(u_id, staff1);
		else
			System.out.println("Error");
	}
	
	// intermediary testing
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Object staff1 = new Staff("Snek", "Snek19232", "HarrowPolice", "Snek", "Martian", "SCSE", "385912");
		Object staff2 = new Staff("Snek", "Snek19222", "HarrowPolice", "Snek", "Martian", "SCSE", "378513");
		Object staff3 = new Staff("Snek", "Snek19232", "HarrowPolice", "Snek", "Martian", "SCSE", "385912");
		
		Staff temp = (Staff) staff1;
		temp.login();
		
		System.out.println("Number of staffs " + Integer.toString(listOfStaff.size()));
	}
}