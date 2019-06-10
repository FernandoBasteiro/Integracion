package excepciones;

public class UsuarioNoLogueado extends Exception {

	private static final long serialVersionUID = 5945854480128060676L;

	public UsuarioNoLogueado(String mensaje) {
		super(mensaje);
	}

}
