package org.springframework.samples.notimeforheroes.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.notimeforheroes.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {
		  
		  List<Person> persons=new ArrayList<Person>();
		  Person person =new Person();
		  person.setFirstName("Miguel ");
		  person.setLastName("Ybarra");
		  persons.add(person);
		  
		  Person person2 =new Person();
		  person2.setFirstName("Jesus ");
		  person2.setLastName("Martin");
		  persons.add(person2);
		  
		  Person person3 =new Person();
		  person3.setFirstName("Jose Antonio ");
		  person3.setLastName("Reina");
		  persons.add(person3);
		  
		  Person person4 =new Person();
		  person4.setFirstName("Pablo ");
		  person4.setLastName("Nuñez");
		  persons.add(person4);
		  
		  Person person5 =new Person();
		  person5.setFirstName("Samuel ");
		  person5.setLastName("Albalat");
		  persons.add(person5);
		  
		  Person person6 =new Person();
		  person6.setFirstName("Raul ");
		  person6.setLastName("Montalban");
		  persons.add(person6);
		  
		  model.put("persons", persons);
		  model.put("title", "No Time For heroes");
		  model.put("group", "L8-1");
		  
	    return "welcome";
	  }
}
