package practice1;

public class DefaultMethods {
	public static void main(String[] args) {  
		Student student = new Student();
		//"John Q. Public"
		System.out.println(student.getName());
		MathStudent mathStudent = new MathStudent();
		System.out.println(mathStudent.getName());
		System.out.println(((Persistent)mathStudent).getName());//Still call base class method
		Student1 student1 = new Student1();
		System.out.println(student1.getName());
	}
}

interface Person {
   long getId();
   default String getName() { return "John Q. Public"; }
}

interface Persistent {
   default String getName() { return getClass().getName() + "_" + hashCode(); }
}

class Student implements Person, Persistent {
   public long getId() { return 42; }
   public String getName() { return Person.super.getName(); }//call super method
}

//super class Student will win(call Student.getName)Student1
class MathStudent extends Student implements Persistent{ //call super student method
	
}

class Student1 implements Persistent, Person {
	public long getId() { return 40; }
	public String getName(){return Persistent.super.getName();};
}
