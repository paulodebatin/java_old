package novosrecursos.java12;

public class NovidadesNoString {

	public static void main(String[] args) {

		// ************************* indent()
		var valor = "Olá Java\n12".indent(3);
		System.out.println(valor);

		// ************************* transform()
		var umaString = "Euclidio ".transform(s -> s + "Da Graca");
		System.out.println(umaString);
	}

}
