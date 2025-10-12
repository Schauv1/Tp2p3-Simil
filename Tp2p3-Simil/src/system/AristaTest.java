package system;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class AristaTest {
	 @Test
	    void testConstructorFuncionaCorrectamente() {
	        int origen = 0, destino = 1, peso = 100;
	        Arista arista = new Arista(origen, destino, peso);

	        assertNotNull(arista);
	        assertEquals(origen, arista.getOrigen());
	        assertEquals(destino, arista.getDestino());
	        assertEquals(peso, arista.getPeso());
	    }

	    @Test
	    void testCompareToOrdenaCorrectamentePorPeso() {
	        Arista baja = new Arista(2, 3, 10);
	        Arista media = new Arista(0, 1, 20);
	        Arista alta = new Arista(1, 2, 30);

	        List<Arista> lista = new ArrayList<>(List.of(media, alta, baja));
	        Collections.sort(lista);

	        assertEquals(baja, lista.get(0));
	        assertEquals(media, lista.get(1));
	        assertEquals(alta, lista.get(2));

	        // Comparaciones directas
	        assertTrue(baja.compareTo(alta) < 0);
	        assertTrue(alta.compareTo(baja) > 0);
	        assertEquals(0, media.compareTo(new Arista(0, 1, 20)));
	    }

	    @Test
	    void testEqualsYHashCode() {
	        Arista a1 = new Arista(0, 1, 50);
	        Arista a2 = new Arista(0, 1, 50);
	        Arista a3 = new Arista(1, 0, 50);
	        Arista a4 = new Arista(0, 1, 99);

	        assertEquals(a1, a2);
	        assertEquals(a1.hashCode(), a2.hashCode());
	        assertNotEquals(a1, a3);
	        assertNotEquals(a1, a4);
	        assertNotEquals(a1, null);
	        assertNotEquals(a1, "no es arista");
	    }
	    
	
}
