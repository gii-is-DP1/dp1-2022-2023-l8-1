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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class UserController {

	private static final String VIEWS_OWNER_CREATE_FORM = "users/createUserForm";

	private final UserService userService;


	@Autowired
	public UserController(UserService userService, AuthoritiesService authoritiesService) {
		this.userService = userService;
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {

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
			this.userService.saveUser(user);
			return "redirect:/";
		}
	}
	@GetMapping("/admin")
	public ModelAndView admin() {
		ModelAndView mav = new ModelAndView("admins/admin");
		return mav;
	}

	@GetMapping(value = "/admins/users")
	public String getUsers(@RequestParam(defaultValue = "0") int page, ModelMap model) {
		List<User> usersList =(List<User>) userService.findUsers();

		if(page<0){
			page = 0;
		}
		
        Pageable pageable = PageRequest.of(page, 4);
        Page<User> usersPage = userService.getAll(pageable);

        if (!usersList.isEmpty() && usersPage.isEmpty()) {
            page = usersList.size() / 4;
            pageable = PageRequest.of(page, 4);
            usersPage = userService.getAll(pageable);
        }
        model.put("isNext", usersPage.hasNext());
        model.addAttribute("users", usersPage.getContent());
        model.put("page", page);
        model.put("size", usersPage.getTotalPages());
        
        return "admins/usersLists";
    }

	
	@GetMapping(value="/admins/users/{userId}")
	public ModelAndView showUser(@PathVariable("userId")int userId) {
		ModelAndView model = new ModelAndView("admins/userDetails");
		model.addObject(this.userService.findUser(userId).get());
		return model;
	}
	
	@GetMapping(value="/admins/users/delete/{userId}")
	public String deleteUser(@PathVariable("userId") int userId, ModelMap model) {
		Optional<User> user = userService.findUser(userId);
		if(user.isPresent()) {
			userService.delete(user.get()); //También estaría bien añadir que aparezca botón de confirmación
			model.addAttribute("message", "User successfully deleted");//no se muestra, no sé por qué
		}else {
			model.addAttribute("message", "User not found");
		}
		return "redirect:/admins/users";
	}

	@GetMapping(value="/findUser")
    public String findUsers(ModelMap modelMap, @PathParam("username") String username){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if(a!=null){
            if(a.getPrincipal().toString() != "anonymousUser"){
                System.out.println(a.getName());
                User usuario = userService.getByUsername(a.getName());
                System.out.println(usuario);
                String vista = "users/findUser";
                System.out.println(username);
                if(username.length() == 0){
                    modelMap.addAttribute("message", "Debe tener al menos un caracter para realizar la busqueda");
                    return vista;
                }
                List<User> users = userService.findByUsernameContaining(username);
                System.out.println(users);
                if (users.size() == 0){
                   modelMap.addAttribute("message", "No hay ningun jugador con ese nombre");
                   return vista;
                }else{
                    modelMap.addAttribute("users", users);
					modelMap.addAttribute("user", usuario);
                return vista;
                }
            }
        }return "redirect:/login";
    }
}
