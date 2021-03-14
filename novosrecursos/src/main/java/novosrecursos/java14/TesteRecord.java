package novosrecursos.java14;

public class TesteRecord {

	public static void main(String[] args) {
		var produto = new Produto("cod1","Desc1");
		
		System.out.println(produto.fullProduct());
		System.out.println(produto.descricacaoMaiusculo());

	}

}

