package controladores;

import java.util.ArrayList;
import daos.ProductoDAO;
import dto.EmpleadoDTO;
import dto.ProductoDTO;
import enumeraciones.Puesto;
import excepciones.ExcepcionProceso;
import excepciones.UsuarioNoLogueado;
import excepciones.UsuarioSinPermisos;
import negocio.Producto;
import negocio.Stock;

public class ControladorProductos {
	
	private static ControladorProductos instancia;
	
	private ControladorProductos() {}
	
	public static ControladorProductos getInstancia() {
		if (instancia == null) {
			instancia = new ControladorProductos();
		}
		return instancia;
	}
	
	public void altaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(supervisor)) {
			if (supervisor.getPuesto().getId() >= Puesto.SUPERVISOR.getId()) {
				 ArrayList<Producto> prods = ProductoDAO.getinstance().getProductoByCodigo(p.getCodigo());
				//if (prods.size() == 0) {
					Stock s = new Stock(p.getStock().getCantidadMinimo(), p.getStock().getCantidadTotal(), p.getStock().getCantidadDisponible());
					Producto nuevo = new Producto(p.getCodigo(), p.getNombre(), p.getDescripcion(), p.getPresentacion(), p.getPrecio(), s);
					nuevo.guardar();
				/*} else {
					Producto act = prods.get(0);
					act.setNombre(p.getNombre());
					act.setDescripcion(p.getDescripcion());
					act.setPresentacion(p.getPresentacion());
					act.setPrecio(p.getPrecio());
					act.getStock().actualizarStock(p.getStock().getCantidadTotal(),p.getStock().getCantidadDisponible(),p.getStock().getCantidadMinimo());
					act.guardar();
					//throw new ExcepcionProceso("Ya existe un producto con ese c�digo.");
				}*/
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n.");
		}
	}

	public void bajaProducto(EmpleadoDTO supervisor, ProductoDTO p) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(supervisor)) {
			if (supervisor.getPuesto().getId() >= Puesto.SUPERVISOR.getId()) {
				 ArrayList<Producto> prods = ProductoDAO.getinstance().getProductoByCodigo(p.getCodigo());
				if (prods.size() == 0) {
					prods.get(0).bajaProducto();
					prods.get(0).guardarStock();
				} else
					throw new ExcepcionProceso("Error al dar de baja el producto.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n.");
		}
	}
	
	public void modifcacionProducto(EmpleadoDTO supervisor, ProductoDTO p) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(supervisor)) {
			if (supervisor.getPuesto().getId() >= Puesto.SUPERVISOR.getId()) {
				 ArrayList<Producto> prods = ProductoDAO.getinstance().getProductoByCodigo(p.getCodigo());
				if (prods.size() == 0) {
					Producto act = prods.get(0);
					act.setNombre(p.getNombre());
					act.setDescripcion(p.getDescripcion());
					act.setPresentacion(p.getPresentacion());
					act.setPrecio(p.getPrecio());
					act.getStock().actualizarStock(p.getStock().getCantidadTotal(),p.getStock().getCantidadDisponible(),p.getStock().getCantidadMinimo());
					act.guardar();
				} else
					throw new ExcepcionProceso("Error al modificar el producto.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n.");
		}
	}
	
	public void actualizarStock(EmpleadoDTO supervisor, ProductoDTO p) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(supervisor)) {
			if (supervisor.getPuesto().getId() >= Puesto.SUPERVISOR.getId()) {
				 ArrayList<Producto> prods = ProductoDAO.getinstance().getProductoByCodigo(p.getCodigo());
				if (prods.size() == 0) {
					prods.get(0).actualizarStock(p.getStock().getCantidadTotal(), p.getStock().getCantidadDisponible(), p.getStock().getCantidadMinimo());
					prods.get(0).guardarStock();
				} else
					throw new ExcepcionProceso("Error al actualizar el producto.");
			} else
				throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n.");
		}
	}
	
	public ArrayList<ProductoDTO> listarProductos(EmpleadoDTO cajero, ProductoDTO p) throws UsuarioNoLogueado, UsuarioSinPermisos {
		//TODO ProductoDTO puede ser null, se devuelve todos los productos. 
		//Puede tener nombre, se devuelven todos los productos que coincidan con el nombre. 
		//O puede tener un codigo, te devuelve un array con el producto de ese codigo.

		if (ControladorEmpleados.getInstance().estaLogueado(cajero)) {
			if (cajero.getPuesto().getId() >= Puesto.CAJERO.getId()) {
				ArrayList<Producto> prods = null;
				if (p == null) {
					prods = ProductoDAO.getinstance().getProductos();
				} 
				else {
					if (p.getNombre() != null) {
						prods = ProductoDAO.getinstance().getProductoByNombre(p.getNombre());
					}
					if (p.getCodigo() != null) {
						prods = ProductoDAO.getinstance().getProductoByCodigo(p.getCodigo());
					}
				}
				ArrayList<ProductoDTO> prodsDTO = new ArrayList<ProductoDTO> ();
				for (Producto r: prods)
					prodsDTO.add(r.getDTO());

				return prodsDTO;		
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		}		
		else throw new UsuarioNoLogueado("Usuario no logueado.");
	}
	
	public ProductoDTO mostrarProducto(EmpleadoDTO supervisor, ProductoDTO p) throws UsuarioNoLogueado, ExcepcionProceso, UsuarioSinPermisos {
		if (ControladorEmpleados.getInstance().estaLogueado(supervisor)) {
			if (supervisor.getPuesto().getId() >= Puesto.CAJERO.getId()) {
				ArrayList<Producto> prods = ProductoDAO.getinstance().getProductoByCodigo(p.getCodigo());
				if (prods.size() > 0) {
					return prods.get(0).getDTO();
				}
				else throw new ExcepcionProceso("No existen productos con esos criterios.");								
			} 		
			else throw new UsuarioSinPermisos("No tiene permisos para realizar esta acci�n");
		}
		else throw new UsuarioNoLogueado("Usuario no logueado.");
	}
	
}