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


import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FriendsService {
	//Repositorio de Friend como variable
	private FriendsRepository friendsRepository;

	//Asocia repositorio al servicio
	@Autowired
	public FriendsService(FriendsRepository friendsRepository) {
		this.friendsRepository = friendsRepository;
	}

	//Funcion save simple
	@Transactional
	public void saveFriend(Friends friends) throws DataAccessException {
		friendsRepository.save(friends);
	}

	//funcion eliminación simple
	public void deleteFriend(Friends friend) {
		friendsRepository.delete(friend);
	}

	//Encuentra Friend por id
	@Transactional(readOnly = true)
	public Optional<Friends> findFriend(Integer id) {
		return friendsRepository.findById(id);
	}

	//Encuentra todos los Friends
	@Transactional
	public Collection<Friends> findFriends(){
		return (Collection<Friends>) friendsRepository.findAll();
	}

	//Encuentra Friend dado un determinado receptor
	public Collection<Friends> findFriendByReceiver(User receiver) {
        return friendsRepository.getByFriendRP(receiver);
    }

	//Encuentra Friends a partir de un usuario
    public Collection<Friends> findFriendOfUser(User user) {
        return friendsRepository.getByFriendA(user);
    }
}
