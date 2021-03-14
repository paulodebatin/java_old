package novosrecursos.java08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LeituraArquivoComFilesFilterMapCollect {

	public static void main(String[] args) throws IOException {
		Path arquivo = Paths.get("clientes.csv");

		List<TelefoneCliente> telefones = Files.lines(arquivo)
					.map(linha -> linha.split(";"))
					.filter(arr -> arr[1].equals("19"))
					.map(arr -> new TelefoneCliente(arr[0], arr[1], arr[2]))
					.collect(Collectors.toList());

		telefones.stream().forEach(System.out::println);
	}

}

class TelefoneCliente {
	private String nome;
	private String codigoArea;
	private String telefone;

	public TelefoneCliente(String nome, String codigoArea, String telefone) {
		this.nome = nome;
		this.codigoArea = codigoArea;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCodigoArea() {
		return codigoArea;
	}

	@Override
	public String toString() {
		return "[nome=" + nome + ", telefone=(" + codigoArea + ") " + telefone + "]";
	}

}
