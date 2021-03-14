package novosrecursos.java08;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListaMap {

	public static void main(String[] args) {
		Empresa empresa1 = new Empresa("Empresa1");
		Empresa empresa2 = new Empresa("Empresa2");

		List<Unidade> lUnidades = new ArrayList<>();
		lUnidades.add(new Unidade(empresa2, "Unidade 2.4"));
		lUnidades.add(new Unidade(empresa1, "Unidade 1.1"));
		lUnidades.add(new Unidade(empresa2, "Unidade 2.2"));
		lUnidades.add(new Unidade(empresa1, "Unidade 1.2"));
		lUnidades.add(new Unidade(empresa2, "Unidade 2.1"));
		lUnidades.add(new Unidade(empresa1, "Unidade 1.3"));
		lUnidades.add(new Unidade(empresa2, "Unidade 2.3"));

		// for
		lUnidades.stream().forEach(u -> System.out.println(u.getNome()));

		System.out.println("*************************************************************************");

		// agrupamento
		Map<Empresa, List<Unidade>> unidadesPorEmpresa = lUnidades.stream()
				.collect(Collectors.groupingBy(Unidade::getEmpresa));
		unidadesPorEmpresa.forEach((k, v) -> {
			System.out.println("Empresa: " + k.getNome() + " - Unidades: " + v);
		});

		System.out.println("*************************************************************************");

		// filtros
		List<Unidade> lUnidadesEmpresa1 = lUnidades.stream().filter(u -> u.getEmpresa().getNome().equals("Empresa1"))
				.collect(Collectors.toList());
		lUnidadesEmpresa1.forEach(u -> System.out.println(u.getNome()));

		System.out.println("*************************************************************************");

		Map<Empresa, Long> totalUnidadesPorEmpresa = lUnidades.stream()
				.collect(Collectors.groupingBy(Unidade::getEmpresa, Collectors.counting()));
		totalUnidadesPorEmpresa.forEach((k, v) -> {
			System.out.println("Empresa: " + k.getNome() + " - Qtd. Unidades: " + v);
		});

		System.out.println("*************************************************************************");
		System.out.println("*************************************************************************");
		System.out.println("*************************************************************************");

		empresa1.getUnidades().add(new Unidade(empresa1, "Unidade 1.1"));
		empresa1.getUnidades().add(new Unidade(empresa1, "Unidade 1.2"));
		empresa1.getUnidades().add(new Unidade(empresa1, "Unidade 1.3"));
		empresa1.getUnidades().add(new Unidade(empresa1, "Unidade 1.4"));

		empresa2.getUnidades().add(new Unidade(empresa1, "Unidade 2.1"));
		empresa2.getUnidades().add(new Unidade(empresa1, "Unidade 2.2"));
		empresa2.getUnidades().add(new Unidade(empresa1, "Unidade 2.3"));
		empresa2.getUnidades().add(new Unidade(empresa1, "Unidade 2.4"));

		List<Empresa> empresas = new ArrayList<>();
		empresas.add(empresa1);
		empresas.add(empresa2);

		// percorrendo lista dentro de lista
		List<String> nomeUnidades = empresas.stream().map(e -> e.getUnidades()).flatMap(List::stream)
				.map(u -> u.getNome()).collect(Collectors.toList());
		nomeUnidades.forEach(n -> System.out.println(n));

		System.out.println("*************************************************************************");

		// filtrando lista dentro de lista
		List<Empresa> empresasQueTemUmNomeDeUnidade = empresas.stream().map(e -> e.getUnidades()).flatMap(List::stream)
				.filter(u -> u.getNome().equals("Unidade 2.3")).map(u -> u.getEmpresa()).collect(Collectors.toList());
		empresasQueTemUmNomeDeUnidade.forEach(e -> System.out.println(e.getNome()));
	}
}

// ************************* DEFINICAO DAS CLASSES ********************************************

class Empresa {
	private String nome;
	private List<Unidade> unidades = new ArrayList<>();

	public Empresa(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}
}

class Unidade {
	private Empresa empresa;
	private String nome;

	public Unidade(Empresa empresa, String nome) {
		this.empresa = empresa;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
