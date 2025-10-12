package system;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClusterDividerTest {

	    private ClusterDivider divider;

	    @BeforeEach
	    void setUp() {
	        divider = new ClusterDivider();
	    }

	    @Test
	    void testDividirEnDosGrupos() {
	        
	        List<Arista> mst = new ArrayList<>();
	        mst.add(new Arista(0, 1, 10));
	        mst.add(new Arista(1, 2, 100));  
	        mst.add(new Arista(2, 3, 10));

	        int cantidadUsuarios = 4;

	        
	        List<Set<Integer>> grupos = divider.dividirEnDosGrupos(mst, cantidadUsuarios);

	         
	        assertEquals(2, grupos.size(), "Deberían haberse creado exactamente dos grupos.");

	        Set<Integer> grupoEsperado1 = Set.of(0, 1);
	        Set<Integer> grupoEsperado2 = Set.of(2, 3);

	        assertTrue(grupos.contains(grupoEsperado1), "Debe contener el grupo {0, 1}.");
	        assertTrue(grupos.contains(grupoEsperado2), "Debe contener el grupo {2, 3}.");
	    }

	    @Test
	    void testDividirEnTresGrupos() {
	        
	        List<Arista> mst = new ArrayList<>();
	        mst.add(new Arista(0, 1, 10));
	        mst.add(new Arista(1, 2, 5));
	        mst.add(new Arista(1, 3, 100));
	        mst.add(new Arista(3, 4, 200));

	        int cantidadUsuarios = 5;
	        int cantidadGrupos = 3;

	       
	        List<Set<Integer>> grupos = divider.dividirEnXGrupos(mst, cantidadUsuarios, cantidadGrupos);

	       
	        assertEquals(3, grupos.size(), "Deberían haberse creado tres grupos.");

	        Set<Integer> grupoEsperado1 = Set.of(0, 1, 2);
	        Set<Integer> grupoEsperado2 = Set.of(3);
	        Set<Integer> grupoEsperado3 = Set.of(4);

	        assertTrue(grupos.contains(grupoEsperado1), "Debe contener el grupo {0, 1, 2}.");
	        assertTrue(grupos.contains(grupoEsperado2), "Debe contener el grupo {3}.");
	        assertTrue(grupos.contains(grupoEsperado3), "Debe contener el grupo {4}.");
	    }

	    @Test
	    void testDividirConMstVacioLanzaExcepcion() {
	        // Arrange
	        List<Arista> mstVacio = new ArrayList<>();
	        int cantidadUsuarios = 0;

	        // Assert
	        assertThrows(IllegalArgumentException.class, () -> {
	            divider.dividirEnDosGrupos(mstVacio, cantidadUsuarios);
	        });
	    }

	    @Test
	    void testDividirConCantidadDeGruposInvalidaLanzaExcepcion() {
	        // Arrange
	        List<Arista> mst = new ArrayList<>();
	        mst.add(new Arista(0, 1, 10));
	        mst.add(new Arista(1, 2, 20));

	        // Act & Assert
	        assertThrows(IllegalArgumentException.class, () -> {
	            divider.dividirEnXGrupos(mst, 3, 0); // grupos inválidos
	        });

	        assertThrows(IllegalArgumentException.class, () -> {
	            divider.dividirEnXGrupos(mst, 3, 5); // más grupos que usuarios
	        });
	    }

	    @Test
	    void testDividirEnUnSoloGrupoNoEliminaAristas() {
	        // Arrange
	        List<Arista> mst = new ArrayList<>();
	        mst.add(new Arista(0, 1, 10));
	        mst.add(new Arista(1, 2, 20));

	        int cantidadUsuarios = 3;
	        int cantidadGrupos = 1;

	        // Act
	        List<Set<Integer>> grupos = divider.dividirEnXGrupos(mst, cantidadUsuarios, cantidadGrupos);

	        // Assert
	        assertEquals(1, grupos.size(), "Solo debería formarse un grupo.");
	        assertEquals(Set.of(0, 1, 2), grupos.get(0), "Todos los usuarios deben estar en el mismo grupo.");
	    }
}
