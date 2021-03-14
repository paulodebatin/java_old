package novosrecursos.java08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Ordenacao {

	public static void main(String[] args) throws IOException {

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

		List<String> lNomes = Arrays.asList("Regina", "Paulo", "Lucas");

		// ordenao 1
		lNomes.sort((p1, p2) -> p1.compareTo(p2));
		lNomes.forEach(umNome -> {
			System.out.println(umNome);

		});

		// ordenao 2
		lUnidades.sort((Unidade u1, Unidade u2) -> u1.getNome().compareTo(u2.getNome()));

		// ordenao 3
		Comparator<String> comparador = (s1, s2) -> {
			return Integer.compare(s1.length(), s2.length());
		};
		List<String> palavras = Arrays.asList("rodrigo", "paulo", "caelum");
		palavras.sort(comparador);
		palavras.forEach(p -> System.out.println(p));

		// procurando em lista
		List<Person> persons = Arrays.asList(new Person("mkyong", 30), new Person("jack", 20),
				new Person("lawrence", 40));
		Person result1 = persons.stream().filter(x -> "jack".equals(x.getName())).findAny().orElse(null);

		System.out.println(result1.getName());

	}

}

class Person2 {

	private String name;
	private int age;

	public Person2(String name, int age) {
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

}
