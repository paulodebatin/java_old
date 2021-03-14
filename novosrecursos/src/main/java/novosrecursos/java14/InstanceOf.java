package novosrecursos.java14;

public class InstanceOf {

	public static void main(String[] args) {
		Object nome = "Paulo";
		
		if  (nome instanceof String s) {
			System.out.println(s.toUpperCase());
		}
	}
}
