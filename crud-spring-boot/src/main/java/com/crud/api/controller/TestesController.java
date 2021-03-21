package com.crud.api.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.service.EnviarEmailAssincronoService;

@CrossOrigin
@RestController
@RequestMapping("/teste")
public class TestesController {

    @Autowired
    private EnviarEmailAssincronoService enviarEmailAssincronoService;

    @GetMapping("/enviarEmail")
    public String enviarEmail() {
        enviarEmailAssincronoService.send1();
        enviarEmailAssincronoService.send2();
        return "OK";
    }

    @GetMapping("/calcular")
    public String calcular() {
        Stream.of(new Thread(new ProcessoA()), new Thread(new ProcessoB())).parallel().forEach(r -> r.run());
        ;
        return "OK";
    }

    @GetMapping("/calcular2")
    public String calcular2() {
        List<Integer> lista = montaNumeros(null);

        //        Instant startTime = Instant.now();
        //        lista.stream().forEach(i -> System.out.println(i));
        //        long segundosForeach = Duration.between(startTime, Instant.now()).getSeconds();
        //        System.out.println("Total de segundos forEach: " + segundosForeach); //19s

        Instant startTime = Instant.now();
        lista.parallelStream().forEach(i -> System.out.println(i));
        long segundosForeachParallel = Duration.between(startTime, Instant.now()).getSeconds();
        System.out.println("Total de segundos parallelStream: " + segundosForeachParallel); // 16s

        return "OK";

    }

    @GetMapping("/calcular3")
    public String calcular3() {
        Dados dados = new Dados();
        Stream.of(montaNumeros(dados), montaString(dados)).parallel();

        System.out.println(dados.getIdades().size());
        System.out.println(dados.getNomes().size());

        return "OK";

    }

    @GetMapping("/calcular4")
    public String calcular4() throws InterruptedException, ExecutionException {
        
        System.out.println("Future para lista de numeros");
        CompletableFuture<List<Integer>> future1 = CompletableFuture.supplyAsync(() -> montaNumeros(null));
        
        System.out.println("Future para lista de nome");
        CompletableFuture<List<String>> future2 = CompletableFuture.supplyAsync(() ->  montaString(null));

        System.out.println("Iniciando processo");
        CompletableFuture.allOf(future1, future2).get();

        Dados dados = new Dados();
        dados.setIdades(future1.get());
        dados.setNomes(future2.get());
        
        System.out.println(dados.getIdades().size());
        System.out.println(dados.getNomes().size());

        return "OK";

    }

    private List<Integer> montaNumeros(Dados dados) {
        List<Integer> lista = new ArrayList<Integer>();
        for (int i = 0; i < 9000000; i++) {
            lista.add(i);
        }
        if (dados != null) {
            dados.setIdades(lista);
        }
        return lista;
    }

    private List<String> montaString(Dados dados) {
        List<String> lista = new ArrayList<String>();
        for (int i = 0; i < 9000000; i++) {
            lista.add("Nome  " + i);
        }
        if (dados != null) {
            dados.setNomes(lista);
        }
        return lista;
    }

    public class Dados {
        private List<String> nomes = new ArrayList<String>();
        private List<Integer> idades = new ArrayList<Integer>();

        public List<String> getNomes() {
            return nomes;
        }

        public void setNomes(List<String> nomes) {
            this.nomes = nomes;
        }

        public List<Integer> getIdades() {
            return idades;
        }

        public void setIdades(List<Integer> idades) {
            this.idades = idades;
        }

    }

    public class ProcessoA implements Runnable {
        public void run() {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ProcessoA finalizado");

        }
    }

    public class ProcessoB implements Runnable {
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ProcessoB finalizado");

        }
    }

}
