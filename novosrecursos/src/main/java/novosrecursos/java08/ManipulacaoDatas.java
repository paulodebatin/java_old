package novosrecursos.java08;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ManipulacaoDatas {
	
public static void main(String[] args) {
		
		System.out.println("**************** GERAL **************************");
		// Data Atual
		LocalDate hoje = LocalDate.now();
		System.out.println("Data Atual: " + hoje);
		

		// Criando uma data passa os valores desejados
		LocalDate natal2014 = LocalDate.of(2014, Month.DECEMBER, 25);
		System.out.println("Natal de 2014: " + natal2014);

		// Criando uma data invlida 31 abril - No existe, pois abril s tem 30
		// dias
		try {
			LocalDate abril31_2014 = LocalDate.of(2014, Month.APRIL, 31);
			System.out.println(abril31_2014);
		} catch (DateTimeException e) {
			System.err.println(e.getMessage());
		}

		// Data atual no Japo, Veja todas a zonas disponve em ZoneId javadoc
		LocalDate toquio = LocalDate.now(ZoneId.of("Asia/Tokyo"));
		System.out.println("A data corrento em Tquio (JST) : " + toquio);

		// Avanando x dias a partir de uma data base 01/01/1970
		LocalDate dataBase = LocalDate.ofEpochDay(365);
		System.out.println("365 dias a partir da data base (01/01/1970): " + dataBase);

		// Obter o dia pelo nmero sequencial no ano.
		LocalDate centessimoDia2014 = LocalDate.ofYearDay(2014, 100);
		System.out.println("100 dia de 2014:" + centessimoDia2014);
		
		System.out.println("**************** ANO,MES,DIA, DIA DA SEMANA DE UMA DATA **************************");
		
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		System.out.println("Dia da semana: " + localDate.getDayOfWeek().name());
		System.out.println("Dia da semana: " + localDate.getDayOfWeek().ordinal());
		System.out.println("Mes: " + localDate.getMonthValue());
		System.out.println("Mes: " + localDate.getMonth().name());       
		System.out.println("Ano: " + localDate.getYear());
		
		System.out.println("******************INTERVALO E DURAO DE DATAS *************************");
		Instant iInicial = Instant.now();
		try {
		       Thread.sleep(1000);
		} catch (InterruptedException e) {
		       e.printStackTrace();
		}
		Instant iFinal = Instant.now();
		
		Duration duracao = Duration.between(iInicial, iFinal);
		System.out.println("Durao em nanos segundos: " + duracao.toNanos());
		System.out.println("Durao em minutos: " + duracao.toMinutes());
		System.out.println("Durao em horas: " + duracao.toHours());
		System.out.println("Durao em milisegundos: " + duracao.toMillis());
		System.out.println("Durao em dias: " + duracao.toDays());
		
		
		System.out.println("****************** COMPARAO DE DATAS ****************************");

		LocalDate localDateAntigo = LocalDate.of(2010, 3, 7);            
		LocalDate localDateNovo = LocalDate.of(2015, 3,5);  
		              
		System.out.println(localDateAntigo.isAfter(localDateNovo));
		System.out.println(localDateAntigo.isBefore(localDateNovo)); 
		System.out.println(localDateAntigo.isEqual(localDateNovo));
		              
		Period periodo = Period.between(localDateAntigo, localDateNovo);
		System.out.println(periodo.getYears() + " Anos " + periodo.getMonths() + " Meses " + periodo.getDays() + " Dias"); 
		System.out.println("Apenas meses: " + periodo.toTotalMonths());
		
		
		System.out.println("***************** OPERAES COM DATAS **************************************");
		
		
		LocalDate dataManipulacao = LocalDate.now();
		System.out.println("Mais 5 dias:" + dataManipulacao.plusDays(5));
		System.out.println("Mais 5 semanas:" + dataManipulacao.plusWeeks(5));
		System.out.println("Mais 5 anos:" + dataManipulacao.plusYears(5));
		System.out.println("Mais 2 meses:" + dataManipulacao.plusMonths(2));
		System.out.println("Menos 1 ano:" + dataManipulacao.minusYears(1));
		System.out.println("Menos 1 ms:" + dataManipulacao.minusMonths(1)); 
		System.out.println("Menos 3 dia: " + dataManipulacao.minusDays(3));
		System.out.println("Data Original:" + dataManipulacao);
		
		
		System.out.println("********************** FORMATAO DE DATAS *********************************");
		
		hoje = LocalDate.now();
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatadorTraco = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  
		System.out.println("Data com /: " + hoje.format(formatadorBarra));     
		System.out.println("Data com -: " + hoje.format(formatadorTraco));
		
		
		System.out.println("********************** ANO BISSEXTO, QTD DE DIAS DO MES/ANO, MENOR DATA/MAIOR DATA  *********************************");
		LocalDate data = LocalDate.now();
        
		System.out.println("Ano bissexto: " + data.isLeapYear());
		System.out.println("Nmero de dias do ms: " + data.lengthOfMonth());
		System.out.println("Nmero de dias do ano: " + data.lengthOfYear());
		System.out.println("Menor data: " + LocalDate.MIN);
		System.out.println("Maior data: " + LocalDate.MAX);



	}



}
