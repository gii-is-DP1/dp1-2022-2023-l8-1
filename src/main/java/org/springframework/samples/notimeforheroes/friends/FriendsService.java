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

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class FriendsService {

	private FriendsRepository friendsRepository;

	@Autowired
	public FriendsService(FriendsRepository friendsRepository) {
		this.friendsRepository = friendsRepository;
	}

	@Transactional
	public void saveFriend(Friends friends) throws DataAccessException {
		friendsRepository.save(friends);
	}

	public void deleteFriend(Friends friend) {
		friendsRepository.delete(friend);
	}

	@Transactional(readOnly = true)
	public Optional<Friends> findFriend(Integer id) {
		return friendsRepository.findById(id);
	}

	@Transactional
	public Collection<Friends> findFriends(){
		return (Collection<Friends>) friendsRepository.findAll();
	}

	public Collection<Friends> findFriendByReceiver(User receiver) {
        return friendsRepository.getByFriendRP(receiver);
    }

    public Collection<Friends> findFriendOfUser(User user) {
        return friendsRepository.getByFriendA(user);
    }
}
