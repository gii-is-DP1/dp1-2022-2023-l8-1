package org.springframework.samples.notimeforheroes.user;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FriendList {
	private List<Friends> friendsL;
	
	@XmlElement
	public List<Friends> getFriendList() {
		if (friendsL==null) {
			friendsL = new ArrayList<>();
		}
		return friendsL;
	}
}
