package system;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	 @Test
	    void constructorFuncionaCorrectamente() {
	        int id = 1;
	        String nombre = "Juan Perez";
	        int tango = 5, folclore = 4, rock = 3, urbano = 2;

	        User usuario = new User(id, nombre, tango, folclore, rock, urbano);

	        assertNotNull(usuario);
	        assertEquals(id, usuario.getId());
	        assertEquals(nombre, usuario.getNombre());
	        assertEquals(tango, usuario.getInteresTango());
	        assertEquals(folclore, usuario.getInteresFolclore());
	        assertEquals(rock, usuario.getInteresRock());
	        assertEquals(urbano, usuario.getInteresUrbano());
	    }

	    @Test
	    void usuarioConInteresInvalidoMenorLanzaExcepcion() {
	        assertThrows(IllegalArgumentException.class, 
	            () -> new User(1, "Usuario Invalido", 0, 3, 3, 3),
	            "Debe lanzar excepción si algún interés es menor a 1");
	    }

	    @Test
	    void usuarioConInteresInvalidoMayorLanzaExcepcion() {
	        assertThrows(IllegalArgumentException.class, 
	            () -> new User(2, "Otro Usuario Invalido", 5, 4, 6, 1),
	            "Debe lanzar excepción si algún interés es mayor a 5");
	    }

	    @Test
	    void testEqualsYHashCodeSeBasanSoloEnElId() {
	        User usuario1 = new User(10, "Juan", 5, 5, 5, 5);
	        User usuario2 = new User(10, "Maria", 1, 1, 1, 1);

	        assertEquals(usuario1, usuario2);
	        assertEquals(usuario1.hashCode(), usuario2.hashCode());
	    }

	    @Test
	    void testEqualsUsuariosDiferentes() {
	        User usuario1 = new User(10, "Juan", 5, 5, 5, 5);
	        User usuario2 = new User(20, "Juan", 5, 5, 5, 5);

	        assertNotEquals(usuario1, usuario2, "Usuarios con distinto ID no deben ser iguales.");
	    }

	    @Test
	    void testSetIdActualizaCorrectamenteElId() {
	        User usuario = new User(1, "Usuario Original");
	        int nuevoId = 22;
	        usuario.setId(nuevoId);

	        assertEquals(nuevoId, usuario.getId());
	    }
}
