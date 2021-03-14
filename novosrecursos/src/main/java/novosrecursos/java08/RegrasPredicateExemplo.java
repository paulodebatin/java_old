package novosrecursos.java08;

import java.util.function.Predicate;

public class RegrasPredicateExemplo {
	public static void main(String[] args) {
		Cliente cliente = new Cliente("John Snow", 5000.0, 2, false, true);

		Predicate<Cliente> regraSalario = cli -> cli.getSalario() > 2000.0;
		Predicate<Cliente> regraTempoEmprego = cli -> cli.getTempoEmpregado() > 0;
		Predicate<Cliente> regraPagamentoAtraso = cli -> !cli.temPagamentosAtraso();
		Predicate<Cliente> regraSPC = cli -> !cli.temRestricoesSPC();
		

		avaliar(cliente, regraSalario.and(regraSPC));
		avaliar(cliente, regraSalario.and(regraTempoEmprego).and(regraSPC));
		avaliar(cliente, regraSPC.and(regraTempoEmprego).and(regraPagamentoAtraso));
		avaliar(cliente, regraSPC.and(regraSalario).and(regraTempoEmprego).and(regraPagamentoAtraso));
	}
	
	public static void avaliar(Cliente cli, Predicate<Cliente> regra) {
		System.out.println("Atendeu: " + regra.test(cli));
	}


}

class Cliente {

	private String nome;
	private double salario;
	private int tempoEmpregado;
	private boolean restricoesSPC;
	private boolean pagamentosAtraso;

	public Cliente(String nome, double salario, int tempoEmpregado, boolean restricoesSPC, boolean pagamentosAtraso) {
		this.nome = nome;
		this.salario = salario;
		this.tempoEmpregado = tempoEmpregado;
		this.restricoesSPC = restricoesSPC;
		this.pagamentosAtraso = pagamentosAtraso;
	}

	public String getNome() {
		return nome;
	}

	public double getSalario() {
		return salario;
	}

	public int getTempoEmpregado() {
		return tempoEmpregado;
	}

	public boolean temRestricoesSPC() {
		return restricoesSPC;
	}

	public boolean temPagamentosAtraso() {
		return pagamentosAtraso;
	}
}
