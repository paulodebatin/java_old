package novosrecursos.java08;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class ExemploFunctions {
	
	static Function<Integer, Integer> add1 = x -> x + 1;
	static BinaryOperator<Integer> sum = (a, b) -> a + b;
	static Function<Person2, String> funcEmpToString = (Person2 p) -> {
		return p.getName().toUpperCase();
	};

	
	public static void main(String[] args) {
		System.out.println(add1.apply(10));
		System.out.println(sum.apply(11222, 2));
		System.out.println(funcEmpToString.apply(new Person2("Paulo", 42)));
	}

}

class Person {

	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

