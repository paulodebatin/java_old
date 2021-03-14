package novosrecursos.java08;

import java.util.Arrays;
import java.util.List;

public class LambdaExpressions {
	public static void main(String[] args) {
		List<String> novidades = Arrays.asList("Lambdas", "Default Method",
				"Stream API", "Date and Time API");
 
		// Antes do Java 8:
		for (String novidade : novidades) {
			System.out.println(novidade);
		}
		//   Como fica o código com uso de Lambda no Java 8:
		novidades.forEach(n -> System.out.println(n));
	}
}
