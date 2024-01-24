package dhkhmt17ctt.js;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import dhkhmt17ctt.entity.Address;
import dhkhmt17ctt.entity.Person;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;

public class JsonStreamHandler {
	
	
	
	
	public static String toJson(List<Person> persons) {
		
		StringWriter st = null;
		try(JsonGenerator gen = Json.createGenerator(st = new StringWriter())){
			
			gen.writeStartArray();
			
			persons.forEach(p -> {
				
				Address add = p.getAddress();
				
				gen.writeStartObject()
				.write("firstName", p.getFirstName())
				.write("lastName", p.getLastName())
				.write("age", p.getAge());
				
				if(add != null)
				{
					gen.writeKey("address")
					.writeStartObject()
					.write("city", add.getCity())
					
					.writeEnd();
				}
				gen.writeEnd();
			});
			
			gen.writeEnd();
			
		}
		
		return st.toString();
	}

	public static Person fromJson(String json) {
		
		Person p = null;
		Address add = null;
		String keyName = "";
		
		try(JsonParser parser = Json.createParser(new StringReader(json))){
			
			while(parser.hasNext()) {
				Event event = parser.next();
				switch (event) {
				case START_OBJECT :
					if(keyName.equals("address"))
						add = new Address();
					else
						p = new Person();
					break;
				case END_OBJECT:
					p.setAddress(add);
					break;
					
				case START_ARRAY:
					JsonArray ja = parser.getArray();
					
					System.out.println(ja);
					
					break;
				case END_ARRAY:
					
					break;
				case KEY_NAME:
					keyName = parser.getString();
					break;
				case VALUE_STRING:
					String lastname = parser.getString();
					if ("lastName".equals(keyName)) {
                        p.setLastName(lastname);
                    }
//					if (keyName.equals("lastName")) {
//                        p.setLastName(lastname);
//                    }
					break;
				case VALUE_NUMBER:
					int value = parser.getInt();
//					if(keyName.equals("age"))
					if("age".equals(keyName))
						p.setAge(value);
					else 
						add.setPostalCode(value);
					break;
					
//				case VALUE_NULL:
//					break;
//				case VALUE_TRUE:
//					break;
//				case VALUE_FALSE:
//					break;
					
				default:
					break;
				}
			}
			
		}
		
		return p;
	}
	
}
