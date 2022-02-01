package io.github.jainaldo;

import io.github.jainaldo.domain.entity.Cliente;
import io.github.jainaldo.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Value("${application.name}")
    public String applicationName;

    @Cachorro
    private Animal animal;

    @Autowired
    private ClientesRepository repository;

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Criando clientes:");
            Cliente cliente1 = new Cliente("joao");
            Cliente cliente2 = new Cliente("pedro");

            System.out.println("Salvando clientes:");
            this.repository.salvar(cliente1);
            this.repository.salvar(cliente2);

            List<Cliente> todosClientes = this.repository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes:");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                this.repository.atualizar(c);
            });

            todosClientes = this.repository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscar clientes:");
            this.repository.buscarPorNome("joao").forEach(System.out::println);

            System.out.println("Deletando clientes:");
            this.repository.obterTodos().forEach(c -> {
                this.repository.deletar(c);
            });

            todosClientes = this.repository.obterTodos();

            if (todosClientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado.");
            } else {
                todosClientes.forEach(System.out::println);
            }





        };
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
