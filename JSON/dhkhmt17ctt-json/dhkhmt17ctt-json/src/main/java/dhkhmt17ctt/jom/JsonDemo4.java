package dhkhmt17ctt.jom;

import dhkhmt17ctt.entity.Address;
import dhkhmt17ctt.entity.Person;

public class JsonDemo4 {
	public static void main(String[] args) {
		
		Person p = new Person("asda", "sdf", 10, new Address("12 nguyen van bao", "ewe", "sdf", 10000));
		String json = JsonHandler.toJson(p );
		System.out.println(json);
	}
}
