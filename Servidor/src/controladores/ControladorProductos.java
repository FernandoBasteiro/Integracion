package controladores;

import java.util.ArrayList;

import dto.EmpleadoDTO;
import dto.ProductoDTO;

public class ControladorProductos {
	
	public void altaProducto(EmpleadoDTO supervisor, ProductoDTO p) {
		
	}
	
	public void modificacionProducto(EmpleadoDTO supervisor, ProductoDTO p) {
		
	}

	public void bajaProducto(EmpleadoDTO supervisor, ProductoDTO p) {
		
	}
	
	public void actualizarStock(EmpleadoDTO supervisor, ProductoDTO p) {
		
	}
	
	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO supervisor, ProductoDTO p) {
		//TODO ProductoDTO puede ser null, se devuelve todos los productos. 
		//Puede tener nombre, se devuelven todos los productos que coincidan con el nombre. 
		//O puede tener un codigo, te devuelve un array con el producto de ese codigo.
		return new ArrayList<ProductoDTO>();
	}
	
	public ProductoDTO mostrarProducto(EmpleadoDTO supervisor, ProductoDTO p) {
		return new ProductoDTO();
	}
}
