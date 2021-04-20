package tema56.javaCollections;

public class Equipo implements Comparable<Equipo> {
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
		return nombre.toUpperCase().hashCode();  // Si quisiéramos comparar con mayúsculas y minúsculas iguales
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Equipo) {
			return nombre.toUpperCase().equals( ((Equipo)obj).nombre.toUpperCase() );
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Equipo o) {
		return -nombre.compareTo( o.nombre );
	}
	
}
