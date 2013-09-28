package controllers;

import java.io.IOException;
import java.util.Collection;

import com.google.common.base.Optional;

import models.Activity;
import models.User;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

public class Main {
	PacemakerAPI paceApi = new PacemakerAPI();

	@Command(description = "Create a new User")
	public void createUser(@Param(name = "first name") String firstName,
			@Param(name = "last name") String lastName,
			@Param(name = "email") String email,
			@Param(name = "password") String password) {
		paceApi.createUser(firstName, lastName, email, password);
	}

	@Command(description = "Get User by Email")
	public void getUser(@Param(name = "email") String email) {
		User user = paceApi.getUserByEmail(email);
		System.out.println(user);
	}

	@Command(description = "Get User by Email")
	public void getUserByID(@Param(name = "id") Long id) {
		User user = paceApi.getUserById(id);
		System.out.println(user);
	}
	
	@Command(description = "List Users")
	public void getUsers() {
		Collection<User> users = paceApi.getUsers();
		System.out.println(users);
	}

	@Command(description = "Delete a User")
	public void deleteUser(@Param(name = "email") String email) {
		Optional<User> user = Optional.fromNullable(paceApi
				.getUserByEmail(email));
		if (user.isPresent()) {
			paceApi.deleteUser(user.get().id);
		}
	}

	@Command(description = "Add Activity")
	public void addActivity(@Param(name = "user id") Long id,
			@Param(name = "type") String type,
			@Param(name = "location") String location,
			@Param(name = "distance") double distance,
			@Param(name = "date") String date,
			@Param(name = "duration") String duration) throws Exception {
		 Optional<User> user = Optional.fromNullable(paceApi.getUserById(id));
		    if (user.isPresent())
		    {
		      paceApi.createActivity(id, type, location, distance, date, duration);
		    }
	}
	
	@Command(description = "Add Location")
	public void addLocation(@Param(name = "activity id") Long id,
			@Param(name = "latidude") float latitude, 
			@Param(name = "longitude") float longitude) {
		 Optional<Activity> activity = Optional.fromNullable(paceApi.getActivity(id));
		    if (activity.isPresent())
		    {
		      paceApi.addLocation(activity.get().id, latitude, longitude);
		    }
	}
	
//	@Command(description = "Store to XML")
//	public void storeToXML() throws IOException {
//		paceApi.storeXML();
//	}
//	
//	@Command(description = "Store to Json")
//	public void storeToJson() throws IOException {
//		paceApi.storeJson();
//	}
//	
//	@Command(description = "Load Json")
//	public void loadJson() {
//		paceApi.loadJson();
//	}
	
	public static void main(String[] args) throws IOException {
		Shell shell = ShellFactory.createConsoleShell("pc",
				"Welcome to pcemaker-console - ?help for instructions",
				new Main());
		shell.commandLoop();
	}
}
