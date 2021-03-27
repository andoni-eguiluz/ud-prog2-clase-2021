package tema4.interfacesAvanzados;

public interface A extends InterfazConDefault {

	@Override
	default int comportamiento2() {
		return -2;
	}
	
}
