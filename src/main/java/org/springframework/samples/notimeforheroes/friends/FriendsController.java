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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class FriendsController {
	//servicio de Frined y servicio de User como variables
	@Autowired
	private FriendsService friendService;

	@Autowired
	private UserService userService;

	//controlador para la vista de todos los amigos asociados a un usuario
	@GetMapping("/friendList")
	public String listFriend(ModelMap modelMap) {
		String vista = "friends/friendList";
		//Iterable<Friend>  friend = friendService.findAll();
		//modelMap.addAttribute("friends", friend);
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if(a!=null){
        	if(a.getPrincipal().toString() != "anonymousUser"){
        		org.springframework.samples.notimeforheroes.user.User usuario = userService.findUserByUsername(a.getName());
        		Iterable<Friends>  friend = friendService.findFriendOfUser(usuario);
        		modelMap.addAttribute("friends", friend);
        		Iterable<Friends>  friendPending = friendService.findFriendByReceiver(usuario);
				
				modelMap.addAttribute("friendsPending", friendPending);
        	}
        }
		
		return vista;
	}

	//Controlador para la vista de solicitudes pendientes de amistad asociadas a un usuario
	@GetMapping("/friendList/Pending")
	public String listFriendPending(ModelMap modelMap) {
		String vista = "friends/friendList";
		//Iterable<Friend>  friend = friendService.findAll();
		//modelMap.addAttribute("friends", friend);
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if(a!=null){
        	if(a.getPrincipal().toString() != "anonymousUser"){
        		org.springframework.samples.notimeforheroes.user.User usuario = userService.findUserByUsername(a.getName());
        		Iterable<Friends>  friend = friendService.findFriendByReceiver(usuario);
        		modelMap.addAttribute("friendsPending", friend);

        	}

        }

		return vista;
	}


	//controlador para la invitación de un amigo asociado a un usuario a una partida
	@GetMapping("/friendList/invite/{friendId}")
	public String invitePlayer(@PathVariable("friendId") int friendId, ModelMap modelMap) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.samples.notimeforheroes.user.User usuario = userService.findUserByUsername(a.getName());
		Friends pareja = friendService.findFriend(friendId).get();
		org.springframework.samples.notimeforheroes.user.User usuario1 = pareja.getUser1();
		org.springframework.samples.notimeforheroes.user.User usuario2 = pareja.getUser2();

		if(usuario.equals(usuario1)) {
			pareja.setSender(usuario1);
			pareja.setReceiver(usuario2);
		}
		else {
			pareja.setSender(usuario2);
			pareja.setReceiver(usuario1);
		}
		friendService.saveFriend(pareja);
		return "redirect:/";
	}

	//controlador para la eliminación de la solicitud de invitación de un amigo asociado a un usuario a la partida
	@GetMapping("friendList/deleteInvitation/{friendId}")
	public String deleteInvitation(@PathVariable("friendId") int friendId, ModelMap modelMap) {
		//Optional<Friends> friend = friendService.findFriend(friendId);
		Friends pareja = friendService.findFriend(friendId).get();
		pareja.setSender(null);
		pareja.setReceiver(null);
		friendService.saveFriend(pareja);
		return "redirect:/friendList";
	}

	//controlador para la eliminación de un amigo asociado al usuario
	@GetMapping("/friendList/delete/{friendId}")
	public String deleteFriend(@PathVariable("friendId") int friendId, ModelMap modelMap) {
		//String view = "friends/friendList";
		Optional<Friends> friend = friendService.findFriend(friendId);
		if(friend.isPresent()) {
			friendService.deleteFriend(friend.get());
			modelMap.addAttribute("message", "Friend successfully deleted!");

		}else {
			modelMap.addAttribute("message", "Friend not found!");
		}
		return "redirect:/friendList";
	}

	//controlador para la vista de detalles de un amigo determinado
	@GetMapping("/friendList/details")
	public ModelAndView showFriends(ModelMap modelMap) {
		ModelAndView view = new ModelAndView("friends/friendDetails");
		//mav.addObject(this.friendService.findFriendOfPlayer(username));

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if(a!=null){
            if(a.getPrincipal().toString() != "anonymousUser"){


				 	org.springframework.samples.notimeforheroes.user.User usuario = userService.findUserByUsername(a.getName());
                 view.addObject(this.friendService.findFriendOfUser(usuario));
                 return view;
            }
            else {
            	return view;
            }

        }
        else {
        	return view;
        }

	}

	//Controlador para la vista de los detalles de una solicitud pendiente
	@GetMapping("/friendList/detailsPending")
	public ModelAndView showFriendsPending(ModelMap modelMap) {

		ModelAndView vista = new ModelAndView("friends/friendDetails");

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if(a!=null){
            if(a.getPrincipal().toString() != "anonymousUser"){


					org.springframework.samples.notimeforheroes.user.User usuario = userService.findUserByUsername(a.getName());
				vista.addObject(this.friendService.findFriendByReceiver(usuario));

                 return vista;

        }
            else {
            return vista;
            }
    }
        else {
        return vista;
        }
	}

	//controlador para editar la información de un amigo determinado
	@GetMapping("/friendList/edit/{friendId}")
	public String processUpdateFriendForm(
		@PathVariable("friendId") int friendId) {
		FriendState estado =FriendState.ACCEPTED;
		Optional<Friends> f=friendService.findFriend(friendId);
		if(f.isPresent()) {
            f.get().setFriendState(estado);
            this.friendService.saveFriend(f.get());
		}
		return "redirect:/friendList";
	}

	//controlador para la vista de solicitud de amistad para un usuario
	@GetMapping("friendList/new/{userId}")
	public String createNewFriend(ModelMap modelMap, @PathVariable("userId") int userId) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if(a!=null){
			if(a.getPrincipal().toString() != "anonymousUser"){
				Friends newFriend = new Friends();
				Friends newFriend2 = new Friends();
				
                org.springframework.samples.notimeforheroes.user.User usuario1 = userService.findUserByUsername(a.getName());
                org.springframework.samples.notimeforheroes.user.User usuario2 = userService.findUser(userId).get();

                newFriend2.setUser1(usuario2);
                newFriend2.setUser2(usuario1);
                newFriend2.setFriendState(FriendState.PENDING);

                Iterable<Friends> IF=friendService.findFriendByReceiver(usuario1);
                for(Friends fOIF:IF) {
                	if(fOIF.getUser1().equals(usuario2)) {

                		return "redirect:/friendList";
                	}
                }
                Iterable<Friends> IFF=friendService.findFriendOfUser(usuario1);
                for(Friends fOIF:IFF) {
                	if(fOIF.getUser1().equals(usuario2)||fOIF.getUser2().equals(usuario2)) {

                		return "redirect:/friendList";
                	}
                }

                modelMap.addAttribute("friend", newFriend);
                newFriend.setUser1(usuario1);
                newFriend.setUser2(usuario2);
                newFriend.setFriendState(FriendState.PENDING);

                friendService.saveFriend(newFriend);

				return "users/findUser" ;
			}else return "redirect:/login";
		}else return "redirect:/login";
	}
}