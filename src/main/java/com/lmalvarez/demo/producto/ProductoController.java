package com.lmalvarez.demo.producto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/producto")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public List<Producto> getProductos() {
		return productoService.getProductos();
	}
	
	@GetMapping(path = "{productoId}")
	public Producto getProductoById(@PathVariable("productoId") Long productoId) {
		return productoService.getProductoById(productoId);
	}

	@PostMapping
	public void nuevoProducto(@Valid @RequestBody Producto producto) {
		productoService.nuevoProducto(producto);
	}

	@DeleteMapping(path = "{productoId}")
	public void eliminarProducto(@PathVariable("productoId") Long productoId) {
		productoService.eliminarProducto(productoId);
	}

	@PutMapping(path = "{productoId}")
	public void updateStudent(@PathVariable("productoId") Long productoId, @RequestParam(required = false) String nombre,
			@RequestParam(required = false) BigDecimal precio) {
		productoService.actualizarProducto(productoId, nombre, precio);
	}

}
