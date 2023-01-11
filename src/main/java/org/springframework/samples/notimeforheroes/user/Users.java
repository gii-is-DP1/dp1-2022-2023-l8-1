package org.springframework.samples.notimeforheroes.user;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {
	private List<User> users;
	
	@XmlElement
	public List<User> getUserList() {
		if (users==null) {
			users = new ArrayList<>();
		}
		return users;
	}
}
