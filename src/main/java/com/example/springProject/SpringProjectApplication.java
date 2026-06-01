package com.example.springProject;

import com.example.springProject.Entity.User;
import com.example.springProject.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(UserRepository repository) {
		return args -> {
			// Se la tabella è vuota, inseriamo due utenti di prova
			if (repository.count() == 0) {
				// Sfruttiamo il costruttore generato da AllArgsConstructor di Lombok
				User u1 = new User(null, "Luigi", "Bianchi", "luigi@esempio.com");
				User u2 = new User(null, "Mario", "Rossi", "mario@esempio.com");

				repository.save(u1);
				repository.save(u2);

				System.out.println("====== DATI DI PROVA INSERITI SU POSTGRES ======");
			}
		};
	}
}
