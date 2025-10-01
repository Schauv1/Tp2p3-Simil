package system;

import java.util.Random;

public class User {
	private int _interesTango, _interesFolclore, _interesRock, _interesUrbano, _id;
	private String _nombre;
	
	public User(int id, String nombre) {
		Random rand = new Random();
		_interesTango = rand.nextInt(6);
		_interesFolclore = rand.nextInt(6);
		_interesRock = rand.nextInt(6);
		_interesUrbano = rand.nextInt(6);
		_id = id;
		_nombre = nombre;
	}

	public int get_interesTango() {
		return _interesTango;
	}

	public int get_interesFolclore() {
		return _interesFolclore;
	}

	public int get_interesRock() {
		return _interesRock;
	}

	public int get_interesUrbano() {
		return _interesUrbano;
	}

	public int get_id() {
		return _id;
	}

	public String get_nombre() {
		return _nombre;
	}
	
	@Override
	public int hashCode() {
		return _id + 32768;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != Arista.class) 
			return false;
		if (obj.hashCode() == this.hashCode())
			return true;	
		User obj1 = (User)obj;
		if (obj1._id == _id)
			return true;
		return false;
	}
}
