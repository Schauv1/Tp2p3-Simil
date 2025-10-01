package system;

import java.util.HashSet;
import java.util.LinkedList;

public class Graph {
	private LinkedList<HashSet<User>> adjacency;
	private LinkedList<User> users;
	
	public Graph(int size) {
		adjacency = new LinkedList<HashSet<User>>();
		for (int i = 0;i < size; i++) {
			adjacency.add(new HashSet<User>());
		}
	}
	
	public int size() {
		return adjacency.size();
	}
	
	public HashSet<User> neighborsOf(int target) {
		return adjacency.get(target);
	}
	
	public User getUser(int id) {
		return users.get(id);
	}
	
	public int getPeso(int idOrigen, int idDestino) {
		if (idOrigen >= adjacency.size() || idDestino >= adjacency.size())
			throw new IllegalArgumentException("Fuera de rango");
		if (adjacency.get(idOrigen).contains(users.get(idDestino))) {
			User u1 = users.get(idOrigen);
			User u2 = users.get(idDestino);
			int sim_t = u1.get_interesTango() - u2.get_interesTango();
			int sim_f = u1.get_interesFolclore() - u2.get_interesFolclore();
			int sim_r = u1.get_interesRock() - u2.get_interesRock();
			int sim_u = u1.get_interesUrbano() - u2.get_interesUrbano();
			return (sim_t + sim_f + sim_r + sim_u);
		}
		else
			throw new IllegalArgumentException("Usuarios no relacionados");
	}
}
