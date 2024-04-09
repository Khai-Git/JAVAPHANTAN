package utils;

import java.net.URI;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

public class AppUtils {
	public static Driver initDriver() {
		URI uri = URI.create("neo4j://localhost:7687");
		String user = "neo4j";
		String password = "123456";
		return GraphDatabase.driver(uri,AuthTokens.basic(user, password));
	}
}
