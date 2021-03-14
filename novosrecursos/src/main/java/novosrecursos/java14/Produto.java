package novosrecursos.java14;

public record Produto(String code, String descricao) {

	private static final String SEPARATOR = "-";

	public String fullProduct() {

		return code + SEPARATOR + descricao;

	}
	
	public String descricacaoMaiusculo() {
		return descricao.toUpperCase();
				
	}
}
