package org.springframework.samples.notimeforheroes.friends;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FriendsServiceTest {
	
    @Autowired
    protected FriendsService friendService;
    @Autowired
    protected UserService userService;
    
    @Test
    public void FriendPositive() { //SOLO DA BIEN SI COMENTAS EL FRIENDNEGATIVE POR ALGUNA RAZÓN QUE SABE DIOS
    	Integer n = friendService.findFriends().size();
    	Friends friends = new Friends();
    	friends.setFriendState(FriendState.ACCEPTED);
    	friends.setUser1(userService.findUser(1).get());
    	friends.setUser2(userService.findUser(4).get());
    	friendService.saveFriend(friends);
    	assertTrue(friendService.findFriend(friends.getId()).isPresent());
    }
    
    @Test
    public void FriendNegative() {//amigo conmigo mismo
    	Integer n = friendService.findFriends().size();
    	Friends friends = new Friends();
    	friends.setFriendState(FriendState.ACCEPTED);
    	friends.setUser1(userService.findUser(1).get());
    	friends.setUser2(userService.findUser(1).get());
    	friendService.saveFriend(friends);
    	assertFalse(friendService.findFriend(n+1).isPresent());
    }
    @Test
    public void createFriendPositive() { //la petición está pending
    	Integer n = friendService.findFriends().size();
    	Friends friends = new Friends();
    	friends.setFriendState(FriendState.PENDING);
    	friends.setUser1(userService.findUser(2).get());
    	friends.setUser2(userService.findUser(4).get());
    	friendService.saveFriend(friends);
    	FriendState state = friendService.findFriend(n+1).get().getFriendState();
    	assertTrue(FriendState.PENDING == state);
    }
    @Test
    public void createFriendNegative() { //petición a usuario no existente
    	Friends friends = new Friends();
    	friends.setFriendState(FriendState.PENDING);
    	friends.setUser1(userService.findUser(2).get());
    	assertThrows(NoSuchElementException.class,()->friends.setUser2(userService.findUser(100).get()));
    }
    @Test
    public void acceptFriendPositive() { //aceptar solicitud que estaba pending
    	Friends friends = friendService.findFriend(1).get();
    	friends.setFriendState(FriendState.ACCEPTED);
    	friendService.saveFriend(friends);
    	FriendState state = friendService.findFriend(1).get().getFriendState();
    	assertTrue(FriendState.ACCEPTED == state);
    }
}