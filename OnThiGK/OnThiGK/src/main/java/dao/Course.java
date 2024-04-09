package dao;

public class Course {
	private String courseId;
	private String name;
	private int Hours;
	public Course(String courseId, String name, int hours) {
		super();
		this.courseId = courseId;
		this.name = name;
		Hours = hours;
	}
	public Course() {
		super();
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHours() {
		return Hours;
	}
	public void setHours(int hours) {
		Hours = hours;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + ", Hours=" + Hours + "]";
	}
}
