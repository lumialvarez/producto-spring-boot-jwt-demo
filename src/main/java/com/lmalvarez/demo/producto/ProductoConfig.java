package com.lmalvarez.demo.producto;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductoConfig {
	@Bean
	CommandLineRunner commandLineRunnerStudent(ProductoRepository productoRepository) {
		return args -> {
			Producto p = new Producto("PC", BigDecimal.valueOf(2700000));
			
			productoRepository.saveAll(List.of(p));
		};
	}
}
