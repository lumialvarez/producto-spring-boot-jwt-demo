package com.lmalvarez.demo.producto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmalvarez.demo.exception.CustomNotFoundException;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository productoRepository;

	public ProductoService() {
		super();
	}
	
	public List<Producto> getProductos(){
		return productoRepository.findAll();
	}
	
	public Producto getProductoById(Long id){
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new CustomNotFoundException("Producto con id " + id + " no existe"));
		return producto;
	}
	
	public void nuevoProducto(Producto producto) {
		productoRepository.save(producto);
	}

	public void eliminarProducto(Long id) {
		boolean exists = productoRepository.existsById(id);
		if (!exists) {
			throw new CustomNotFoundException("Producto con id " + id + " no existe");
		}
		productoRepository.deleteById(id);

	}
	
	@Transactional
	public void actualizarProducto(Long id, String nombre, BigDecimal precio) {
		Producto producto = getProductoById(id);

		if (nombre != null && nombre.length() > 0 && !Objects.equals(producto.getNombre(), nombre)) {
			producto.setNombre(nombre);
		}

		if (!Objects.equals(producto.getPrecio(), precio)) {
			producto.setPrecio(precio);
		}

	}

}
