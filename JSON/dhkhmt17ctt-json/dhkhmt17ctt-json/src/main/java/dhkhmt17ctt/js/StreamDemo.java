package dhkhmt17ctt.js;

import java.util.List;

import dhkhmt17ctt.entity.Address;
import dhkhmt17ctt.entity.Person;

public class StreamDemo {
	public static void main(String[] args) {
		
		List<Person> list = List.of(
				new Person("Ti", "Tran", 20),
				new Person("Teo", "Tran", 25, new Address("12 Nguyen Van Bao", "P4", "GV HCM", 100000), null),
				new Person("Teo", "Tran", 25, new Address("12 Nguyen Van Bao", "P4", "GV HCM", 100000), null),
				new Person("Teo", "Tran", 25, new Address("12 Nguyen Van Bao", "P4", "GV HCM", 100000), null),
				new Person("Teo", "Tran", 25, new Address("12 Nguyen Van Bao", "P4", "GV HCM", 100000), null),
				new Person("Teo", "Tran", 25, new Address("12 Nguyen Van Bao", "P4", "GV HCM", 100000), null),
				new Person("Teo", "Tran", 25, new Address("12 Nguyen Van Bao", "P4", "GV HCM", 100000), null),
				new Person("Tung", "Le", 27)
			);
		
		String json = JsonStreamHandler.toJson(list);
		
		System.out.println(json);
		
	}
}
