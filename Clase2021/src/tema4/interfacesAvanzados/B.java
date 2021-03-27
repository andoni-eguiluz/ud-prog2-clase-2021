package tema4.interfacesAvanzados;

public interface B extends InterfazConDefault {
	@Override
	default int comportamiento2() {
		return -3;
	}
}
