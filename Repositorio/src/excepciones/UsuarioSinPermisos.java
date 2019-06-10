package excepciones;

public class UsuarioSinPermisos extends Exception {

	private static final long serialVersionUID = -5318578609271525570L;

	public UsuarioSinPermisos(String mensaje) {
		super(mensaje);
	}

}
