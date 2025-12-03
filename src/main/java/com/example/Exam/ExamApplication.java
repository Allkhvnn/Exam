package com.example.Exam;

import com.example.Exam.model.UserEntity;
import com.example.Exam.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class
ExamApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

	@Bean
	public CommandLineRunner initUsers(UserRepository userRepository,
									   PasswordEncoder encoder) {
		return args -> {
			if (userRepository.findByUsername("alihan").isEmpty()) {
				UserEntity u = new UserEntity();
				u.setUsername("alihan");
				u.setPassword(encoder.encode("123"));
				u.setRole("USER");
				userRepository.save(u);
			}
		};
	}
}
