package system;

public class Arista {
	private int _origen, _destino, _peso;
	
	public Arista(int idOr, int idDes, int peso) {
		_origen = idOr;
		_destino = idDes;
		_peso = peso;
	}

	public int get_origen() {
		return _origen;
	}

	public int get_destino() {
		return _destino;
	}

	public int get_peso() {
		return _peso;
	}
}
