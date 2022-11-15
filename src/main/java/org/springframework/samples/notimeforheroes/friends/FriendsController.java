/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.notimeforheroes.friends;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.user.AuthoritiesService;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.User;
/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class FriendsController {

	private final FriendsService friendService;

	@Autowired
	public FriendsController(UserService userService, AuthoritiesService authoritiesService, FriendsService friendService) {
		this.friendService = friendService;
	}

/* 	@GetMapping(value = "/social")
	public String showFriendList(Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
		System.out.println(currentUser.getUsername());
		FriendList friendList = new FriendList();
		friendList.getFriendList().addAll(this.friendService.findFriends());
		model.put("friendsL", friendList);
		return "users/friendsLists";
	} */

	@GetMapping(value = "/social")
	public String showFriendList(Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
		String username = currentUser.getUsername();
		FriendList friendList = new FriendList();
		friendList.getFriendList().addAll(this.friendService.findAllFriendsByUserName(username));
		model.put("friendsL", friendList);
		return "users/friendsLists";
	}
}