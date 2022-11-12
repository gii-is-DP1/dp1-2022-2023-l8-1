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
package org.springframework.samples.notimeforheroes.user;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.owner.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class UserController {

	private static final String VIEWS_OWNER_CREATE_FORM = "users/createUserForm";

	//private final OwnerService ownerService;
	private final UserService userService;

//	@Autowired
//	public UserController(OwnerService clinicService) {
//		this.ownerService = clinicService;
//	}
	@Autowired
	public UserController(UserService userService, AuthoritiesService authoritiesService) {
		this.userService = userService;
	}

//	@InitBinder
//	public void setAllowedFields(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("id");
//	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
//		Owner owner = new Owner();
//		model.put("owner", owner);
		User user = new User();
		model.put("user", user);
		return VIEWS_OWNER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_FORM;
		}
		else {
			//creating owner, user, and authority
			//this.ownerService.saveOwner(owner);
			this.userService.saveUser(user);
			return "redirect:/";
		}
	}
	
	@GetMapping(value = "/admins/users")
	public String showUserList(Map<String, Object> model) {
		Users users = new Users();
		users.getUserList().addAll(this.userService.findUsers());
		model.put("users", users);
		return "admins/usersLists";
	}
	@GetMapping(value = "/admins/users.xml")
	public @ResponseBody Users showResourcesUserList() {
		Users users = new Users();
		users.getUserList().addAll(this.userService.findUsers());
		return users;
	}
//	@GetMapping(value = { "/vets" })
//	public String showVetList(Map<String, Object> model) {
//		// Here we are returning an object of type 'Vets' rather than a collection of Vet
//		// objects
//		// so it is simpler for Object-Xml mapping
//		Vets vets = new Vets();
//		vets.getVetList().addAll(this.vetService.findVets());
//		model.put("vets", vets);
//		return "vets/vetList";
//	}

//	@GetMapping(value = { "/vets.xml"})
//	public @ResponseBody Vets showResourcesVetList() {
//		// Here we are returning an object of type 'Vets' rather than a collection of Vet
//		// objects
//		// so it is simpler for JSon/Object mapping
//		Vets vets = new Vets();
//		vets.getVetList().addAll(this.vetService.findVets());
//		return vets;
//	}
//
}
