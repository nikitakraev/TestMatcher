package no.kitttn.testmatcher.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author kitttn
 */
public class Person extends RealmObject {
	@PrimaryKey
	private int id = 1;
	private String location = "Berlin";
	private String photo = "http://www.bestmotherofthegroomspeeches.com/wp-content/themes/thesis/rotator/sample-1.jpg";
	private String status = "none";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "{id :" + id + " @ " + location + "; status: " + status + "}";
	}
}
