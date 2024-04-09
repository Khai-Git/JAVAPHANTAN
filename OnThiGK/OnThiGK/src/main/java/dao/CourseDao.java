package dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;


import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;

public class CourseDao {
	private Driver driver;// đối tượng Driver được sử dụng để thực hiện các hoạt động như tạo kết nối đến
							// cơ sở dữ liệu Neo4j, thực thi các truy vấn và thao tác với dữ liệu
	private SessionConfig sessionConfig;

	public CourseDao(Driver driver, String dbName) {//tạo contrustor

		this.driver = driver;
		sessionConfig = SessionConfig.builder().withDatabase(dbName).build();
	}

	public void addCourse(Course course) {
		String query = "CREATE (a:Course{courseID: $id ,name:$name,hours:$hours}) ";
		Map<String, Object> pars = Map.of("id", course.getCourseId(), "name", course.getName(), "hours",
				course.getHours());
		try (Session session = driver.session(sessionConfig)) {
			session.executeWrite(tx -> {
				return tx.run(query, pars).consume();
			});

		}
	}
	//hiển thị ra 
	public String addCourse1(Course course) {
		String query = "CREATE (a:Course{courseID: $id ,name:$name,hours:$hours}) " + "RETURN a.courseID ";

		Map<String, Object> pars = Map.of("id", course.getCourseId(), "name", course.getName(), "hours",
				course.getHours());
		try (Session session = driver.session(sessionConfig)) {
			return session.executeWrite(tx -> {
				Result result = tx.run(query, pars);
				return result.single().get("a.courseID").asString();
			});

		}
	}

	//tìm kiếm 
	public Course findCourseByID(String courseID) {
		String query = "MATCH (c:Course) "
				+ "WHERE c.courseID = $id "
				+ "RETURN c"; 
		Map<String, Object> pars = Map.of("id",courseID);
		try(Session session = driver.session(sessionConfig)){//sau khi truy van va cau hình ,muốn truy vấn đưa dữ liệu vào  được  thì thông qua session 
			
			return session.executeRead(tx -> {
				Result result = tx.run(query,pars);
				
				if(!result.hasNext()) {
					return null;
				}
				
				Record record = result.stream().findFirst().get();//lấy giá trị đầu và lấy theo luồng(chuỗi list)
				Node node = record.get("c").asNode();
				String id = node.get("courseID").asString();
				String name = node.get("name").asString();
				int hours = node.get("hours").asInt();
				
				return new Course(id, name, hours);
				
			});
		}
	}
	
	//tìm ra mọt danh sách 
	public List<Course> listCourses(int n){
		String query = "MATCH (c:Course) RETURN c limit $n";
		Map<String, Object> pars = Map.of("n",n);
		try(Session session = driver.session(sessionConfig)){
			return session.executeRead(tx -> {
				Result result = tx.run(query,pars);
				return result.stream().map(record -> {
					Node node = record.get("c").asNode();
					String id = node.get("courseID").asString();
					String name = node.get("name").asString();
					int hours =node.get("hours").asInt();
					return new Course(id, name, hours);
							
				}).toList();
			});
		}
	}
	
	//update 
//	String query = "MATCH (c:Course {courseID:$id}) "
//			+ "SET c.name = $name, c.hours = $hours";
	public void updateCourse(String courseId, String name, int hours) {
	    String query = "MATCH (c:Course {courseID: $id}) "
	                 + "SET c.name = $name, c.hours = $hours";
	    Map<String, Object> parameters = Map.of("id", courseId, "name", name, "hours", hours);
	    try (Session session = driver.session(sessionConfig)) {
	        session.executeWrite(tx -> tx.run(query, parameters).consume());
	    }
	}
	public void deleteCourse(String courseId) {
	    String query = "MATCH (c:Course {courseID: $id}) "
	                 + "DETACH DELETE c";
	    Map<String, Object> parameters = Collections.singletonMap("id", courseId);
	    try (Session session = driver.session(sessionConfig)) {
	        session.executeWrite(tx -> tx.run(query, parameters).consume());
	    }
	}

	
	
	
	
	public void close() {
		if(driver != null) {
			driver.close();
		}
	}

}
