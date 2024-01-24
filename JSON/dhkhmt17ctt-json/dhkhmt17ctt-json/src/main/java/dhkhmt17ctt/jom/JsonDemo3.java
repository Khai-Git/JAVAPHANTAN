package dhkhmt17ctt.jom;

import java.util.Arrays;
import java.util.List;

import dhkhmt17ctt.entity.Address;
import dhkhmt17ctt.entity.Person;

public class JsonDemo3 {
	public static void main(String[] args) {
		
		List<Person> list = JsonHandler.getPersons("data/people.json");
		list.forEach(x -> System.out.println(x));
	}
}
