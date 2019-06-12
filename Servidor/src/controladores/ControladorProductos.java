package controladores;

import java.util.ArrayList;

import daos.ProductoDAO;
import daos.VentaDAO;
import dto.EmpleadoDTO;
import dto.ProductoDTO;
import dto.VentaDTO;
import enumeraciones.Puesto;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import negocio.Producto;
import negocio.Venta;

public class ControladorProductos {
	
	public void cargarProdtuco(EmpleadoDTO supervisor, ProductoDTO p) {
		
	}

	public void bajaProducto(EmpleadoDTO supervisor, ProductoDTO p) {
		
	}
	
	public void actualizarStock(EmpleadoDTO supervisor, ProductoDTO p) {
		
	}
	
	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO cajero, ProductoDTO p) {
		//TODO ProductoDTO puede ser null, se devuelve todos los productos. 
		//Puede tener nombre, se devuelven todos los productos que coincidan con el nombre. 
		//O puede tener un codigo, te devuelve un array con el producto de ese codigo.
		//y que tenga stock
		
		if (ControladorEmpleados.getInstance().estaLogueado(cajero)) {
			if (cajero.getPuesto().getId() >= Puesto.CAJERO.getId()) {
				ArrayList<Producto> prods;
				if (p == null) {
					prods = ProductoDAO.getinstance().getProductos();
				} if (!empty(p.getNombre) {
					
					
				}
				
				
				if (ventas != null) {
					ArrayList<VentaDTO> vtas = new ArrayList<VentaDTO> ();
					for (Venta v: ventas) {
						vtas.add(v.getDTO());
					}
					return vtas;
				}
				else throw new ExcepcionProceso("No existen ventas con esos criterios.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acción");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");
		
		
		return new ArrayList<ProductoDTO>();
	}
	
	public ProductoDTO mostrarProducto(EmpleadoDTO supervisor, ProductoDTO p) {
		return new ProductoDTO();
	}
}
