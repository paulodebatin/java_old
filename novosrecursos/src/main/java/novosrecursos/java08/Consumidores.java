package novosrecursos.java08;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;



public class Consumidores  {
	
	
	public static void main(String[] args) {
		
		List<String> palavras = new ArrayList<>();
		palavras.add("alura online");
		palavras.add("casa do código");
		palavras.add("caelum");
		
		Consumer<String> consumidor = new ConsumidorDeString();
		palavras.forEach(consumidor);
	}
	

}

class ConsumidorDeString implements Consumer<String> {
	public void accept(String s) {
		System.out.println(s);
	}
	
}
