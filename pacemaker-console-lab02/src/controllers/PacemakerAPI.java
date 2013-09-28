package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Activity;
import models.Location;
import models.User;

public class PacemakerAPI {
	private Map<Long, User> userIndex = new HashMap<>();
	private Map<String, User> emailIndex = new HashMap<>();
	public Map<Long, Activity> activities = new HashMap<>();

	public Collection<User> getUsers() {
		return userIndex.values();
	}

	public void deleteUsers() {
		userIndex.clear();
		emailIndex.clear();
	}

	public User createUser(String firstName, String lastName, String email,
			String password) {
		User user = new User(firstName, lastName, email, password);
		userIndex.put(user.id, user);
		emailIndex.put(email, user);
		return user;
	}

	public User getUserByEmail(String email) {
		return emailIndex.get(email);
	}

	public User getUserById(Long id) {
		return userIndex.get(id);
	}

	public void deleteUser(Long id) {
		User user = userIndex.remove(id);
		emailIndex.remove(user.email);
	}

	public void createActivity(Long id, String type, String location,
			double distance, String dateStr, String duration) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy hh:mm:ss");
		Date date = sdf.parse(dateStr);
		Activity activity = new Activity(type, location, distance, date,
				duration);
		Optional<User> user = Optional.fromNullable(userIndex.get(id));
		if (user.isPresent()) {
			user.get().activities.put(activity.id, activity);
			activities.put(activity.id, activity);
		}
	}

	public Activity getActivity(Long id) {
		return activities.get(id);
	}

	public void addLocation(Long id, float latitude, float longitude) {
		Optional<Activity> activity = Optional.fromNullable(activities.get(id));
		if (activity.isPresent()) {
			activity.get().route.add(new Location(latitude, longitude));
		}

	}

	// public void storeXML() throws IOException {
	// XStream xstream = new XStream(new DomDriver());
	// xstream.alias("user", User.class);
	// ObjectOutputStream out = xstream
	// .createObjectOutputStream(new FileWriter("users.xml"),"users");
	// out.writeObject(userIndex);
	// out.close();
	// }
	//
	// public void storeJson() throws IOException {
	// XStream xStream = new XStream(new JettisonMappedXmlDriver());
	// xStream.setMode(XStream.NO_REFERENCES);
	// xStream.alias("user", User.class);
	// String json = xStream.toXML(userIndex);
	// FileWriter file = new FileWriter("users.json");
	// file.write(json);
	// file.flush();
	// file.close();
	// }
	// public void loadJson(){
	//
	//
	//
	// XStream xstream = new XStream(new JettisonMappedXmlDriver());
	// xstream.alias("user", User.class);
	// //User user = (User)xstream.fromXML(json);
	// //userIndex=(Map)xstream.fromXML(json);
	// System.out.println(user.email);
	// }

}
