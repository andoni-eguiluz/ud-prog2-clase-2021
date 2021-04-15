package tema56.javaCollections;

public class Equipo {
	protected String nombre;

	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	@Override
	public int hashCode() {
		return nombre.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Equipo) {
			return nombre.equals( ((Equipo)obj).nombre );
		} else {
			return false;
		}
	}
	
}
