package novosrecursos.java11;

import java.util.stream.Collectors;

public class NovidadesNoString {

	public static void main(String[] args) throws Exception {

		// ***************** isBlank()
		System.out.println(" ".isBlank()); // true

		String s = "Anupam";
		System.out.println(s.isBlank()); // false
		String s1 = "";
		System.out.println(s1.isBlank()); // true

		// ***************** lines()
		String str = "JD\nJD\nJD";
		System.out.println(str);
		System.out.println(str.lines().collect(Collectors.toList()));

		// ***************** strip()
		str = " JD ";
		System.out.print("Start");
		System.out.print(str.strip());
		System.out.println("End");

		System.out.print("Start");
		System.out.print(str.stripLeading());
		System.out.println("End");
		
		System.out.print("Start");
		System.out.print(str.stripTrailing());
		System.out.println("End");
		
		// ***************** repeat()
		str = "=".repeat(2);
        System.out.println(str); //prints ==

	

	}

}
