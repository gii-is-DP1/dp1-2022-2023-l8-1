package org.springframework.samples.notimeforheroes.user;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
    @Autowired
    protected UserService userService;
    
    @Test
    @Transactional
    public void findIDTrue(){
        User user = userService.findUser(1).get();
        assertNotNull(user);
        assertEquals("admin@admin.com", user.getEmail());
    }
    
    @Test
    @Transactional
    public void findIDFalse(){
        User user = userService.findUser(2).get();
        assertNotNull(user);
        assertNotEquals("No", user.getUsername());
    }
    
    @Test
    @Transactional
    public void createUserPositive() {
    	Integer n = userService.findUsers().size();
    	User user = new User();
    	user.setUsername("prueba");
    	user.setPassword("prueba");
    	user.setEmail("prueba@prueba.com");
    	user.setBirthDate(LocalDate.of(2000, 01, 01));
    	user.setEnabled(true);
    	userService.saveUser(user);
    	assertEquals(user.getUsername(), userService.findUser(user.getId()).get().getUsername());
    }
    
    @Test
    @Transactional
    public void createUserNegativeUnique() {
    	User user = new User();
    	user.setUsername("user");
    	user.setPassword("user");
    	user.setEmail("prueba2@prueba.com");
    	user.setBirthDate(LocalDate.of(2000, 01, 01));
    	user.setEnabled(true);
    	assertThrows(DataIntegrityViolationException.class,() -> userService.saveUser(user), "is not unique");
    }
    
    @Test
    @Transactional
    public void createUserNegative() {
    	User user = new User();
    	user.setPassword("");
    	user.setEmail("");
    	user.setBirthDate(LocalDate.of(2000, 01, 01));
    	user.setEnabled(true);
    	assertThrows(ConstraintViolationException.class,() -> userService.saveUser(user), "Is not null");
    }
    
    @Test
    @Transactional
    public void deleteUserPositive() {
    	User user =userService.findUser(2).get();
    	userService.delete(user);
    	assertFalse(userService.findUser(2).isPresent());
    }
    
    @Test
    @Transactional
    public void deleteUserNegative() {
    	assertThrows(NoSuchElementException.class, ()->userService.delete(userService.findUser(100).get()));
    }
    
    @Test
    @Transactional
    public void userListPositive() {
    	Collection<User> ls= userService.findUsers();
        assertNotNull(ls);
        assertFalse(ls.isEmpty());
        assertEquals(9,ls.size());//modificar si metemos más users en los inserts
    }
    
}