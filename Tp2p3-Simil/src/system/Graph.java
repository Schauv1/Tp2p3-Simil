package system;

import java.util.*;

public class Graph {
    private List<User> usuarios;

    public Graph() {
        usuarios = new ArrayList<>();
    }

    public void addUser(User u) {
        if (u == null) 
            throw new IllegalArgumentException("Usuario nulo");
        usuarios.add(u);
    }

    public int size() {
        return usuarios.size();
    }
  
    public void deleteUserAndReIndex(User userToDelete) {
        if (userToDelete == null || !usuarios.contains(userToDelete)) return;
        usuarios.remove(userToDelete);

        for (int i = userToDelete.getId(); i < usuarios.size(); i++) {
            usuarios.get(i).setId(i);
        }
    }

    public User getUser(int index) {
        if (index < 0 || index >= usuarios.size()) 
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        return usuarios.get(index);
    }

    
    public Set<User> neighborsOf(int id) {
        Set<User> vecinos = new HashSet<>();
        for (User u : usuarios) {
            if (u.getId() != id)
                vecinos.add(u);
        }
        return vecinos;
    }

    public List<User> getAllUsers() {
        return Collections.unmodifiableList(usuarios);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Grafo de usuarios:\n");
        for (User u : usuarios) {
            sb.append("  ").append(u.toString()).append("\n");
        }
        return sb.toString();
    }
}

