package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserService {
//create a userService
	public User createUser(String username, String password, String name) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		return user;
	}

	// create a fileReader method and store into an array of user objects
	public User[] readFile() {
		User[] users = new User[4];
		BufferedReader fileReader = null;
		try {
			// use bufferedReader to read from data.txt file
			fileReader = new BufferedReader(new FileReader("data.txt"));

			int i = 0;
			String line;
			while ((line = fileReader.readLine()) != null) {
				// parse each line by commas
				String[] str = line.split(",");
				//assigned value to each element
				User user = createUser(str[0], str[1], str[2]);
				users[i] = user;
				i++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Oops, the file was not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Oops, there was an IO Exception");
			e.printStackTrace();
		}try {
			//close reader
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	//validate if inputs match the file
	public User validate(String username, String password, User[] users) {
		for(User user : users ) {
			if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public User userLogin() {
		UserService userService = new UserService();
		User[] users = userService.readFile();
		User user = null;
		Scanner scan = new Scanner(System.in);
		int attempts = 0;
		
		while(attempts < 5) {
			System.out.println("Enter your email");
			String username = scan.nextLine();
			System.out.println("Enter your password");
			String password = scan.nextLine();
			user = userService.validate(username, password, users);
			
			if(user != null) {
				System.out.println("Welcome " + user.getName());
				break;
			}else if(user == null) {
				System.out.println("Invalid login, please try again");
				attempts++;
			}
		}if(attempts == 5) {
			System.out.println("Too many failed login attempts, you are now locked out");
		}scan.close();
		
		return user;
		
	}

}




