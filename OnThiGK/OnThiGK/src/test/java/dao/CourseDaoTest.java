package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.AppUtils;

public class CourseDaoTest {
	private static final String DB_NAME = "coursedb";
	private CourseDao courseDao;
	@BeforeEach
	public void setup() {
		courseDao = new CourseDao(AppUtils.initDriver(), DB_NAME );
	}
	
	
	@Test
	public void testAddCourse1() {
		Course course = new Course("CS202", "java program", 3);
		String id = courseDao.addCourse1(course);//trả dữ liệu về
		assertNotNull(id);
		String expected = "CS202";//giá trị mong muốn
		assertEquals(expected, id);//đọc cái id bên neo4j 
		
		
	}

	@Test
	public void testfindCourseByID() {
		String id = "CS202";
		Course course = courseDao.findCourseByID(id);
		System.out.println("tìm kiếm :");
		System.out.print(course.getCourseId());
		assertNotNull(course);
//		assertEquals(id, course.getCourseId());
//		assertEquals("java program", course.getName());
//		assertEquals(3, course.getHours());
	}
	 @Test
	    public void testListCourses() {
	        int n = 5; // Số lượng khóa học cần liệt kê
	        List<Course> courses = courseDao.listCourses(n);
	        
	        // Kiểm tra xem danh sách khóa học được trả về có đúng số lượng mong đợi không
	        assertEquals(n, courses.size());

	        // Kiểm tra xem mỗi khóa học trong danh sách có hợp lệ không
	        for (Course course : courses) {
	        	System.out.println(course.getCourseId());
	        	System.out.println(course.getName());
	        	System.out.println(course.getHours());
	            assertNotNull(course.getCourseId());
	            assertNotNull(course.getName());
	            assertNotNull(course.getHours());
	        }
	    }
	@Test
	public void testfindCourseByID_NotFound() {
		String id = "CS2023";//cho nó sai số 
		Course course = courseDao.findCourseByID(id);
		assertNull(course,"Not found");
		
	}
	 
		//cập nhập
	  @Test
	    public void testUpdateCourse() {
	       
	        // Cập nhật tên và số giờ của khóa học
	        String newCourseName = "JAVA Spring";
	        int newCourseHours = 5;
	        courseDao.updateCourse("CS202", newCourseName, newCourseHours);

	        // Kiểm tra xem khóa học đã được cập nhật thành công chưa
	        Course updatedCourse = courseDao.findCourseByID("CS202");
	        assertNotNull(updatedCourse);
	        assertEquals(newCourseName, updatedCourse.getName());
	        assertEquals(newCourseHours, updatedCourse.getHours());
	    }
	  //delete
	  @Test
	    public void testDeleteExistingCourse() {
	      
	        // Xóa khóa học đã thêm vào cơ sở dữ liệu
	        courseDao.deleteCourse("CS202");

	        // Kiểm tra xem khóa học đã được xóa thành công chưa
	        Course deletedCourse = courseDao.findCourseByID("CS202");
	        assertNull(deletedCourse);
	    }

	  
	  
	  
	  //sau khi test xong thì giải phóng nó
	@AfterEach
	public void teardown() {
		courseDao.close();
		
	}
}
