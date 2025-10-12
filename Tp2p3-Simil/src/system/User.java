package system;

import java.util.Random;

public class User {
	private static final int MIN_INTERES = 1;
    private static final int MAX_INTERES = 5;
    private int interesTango, interesFolclore, interesRock, interesUrbano, id;
    private String nombre;
    
 // Constructor aleatorio
    public User(int id) {
        Random rand = new Random();
        String[] nombres = new String[] {"Alexa","Siri","Danna","Ana","Beto","Carla","Damián"
        		,"Ramona","Tomas","Wilbur","Scott","Willard","Javier"};
        String[] apellidos = new String[] {"Flowers","Pilgrim","Vergara","Gomez","Mendez","Ramirez"
        		,"Fernandez","Rodriguez","Fox","Wolff","Arenas","Smith","Sanz"};
        this.id = id;
        interesTango = rand.nextInt(5) + 1;     
        interesFolclore = rand.nextInt(5) + 1;
        interesRock = rand.nextInt(5) + 1;
        interesUrbano = rand.nextInt(5) + 1;
        nombre = nombres[rand.nextInt(nombres.length)] + " " + apellidos[rand.nextInt(apellidos.length)];
    }

  
 // Constructor con intereses fijos
    public User(int id, String nombre, int tango, int folclore, int rock, int urbano) {
        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("Se necesita un nombre");
        this.id = id;
        this.nombre = nombre;
        this.interesTango = validarInteres(tango);
        this.interesFolclore = validarInteres(folclore);
        this.interesRock = validarInteres(rock);
        this.interesUrbano = validarInteres(urbano);
    }
    public User(int id, String nombre) {
        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("Se necesita un nombre");

        Random rand = new Random();
        this.id = id;
        this.nombre = nombre;
        
 
        this.interesTango = rand.nextInt(5) + 1;
        this.interesFolclore = rand.nextInt(5) + 1;
        this.interesRock = rand.nextInt(5) + 1;
        this.interesUrbano = rand.nextInt(5) + 1;
    }

    private int validarInteres(int interes) {
        if (interes < MIN_INTERES || interes > MAX_INTERES)
            throw new IllegalArgumentException("Interés fuera de rango. Debe ser entre " + MIN_INTERES + " y " + MAX_INTERES);
        return interes;
    }
    
    

    // Getters
    public int getInteresTango() { return interesTango; }
    public int getInteresFolclore() { return interesFolclore; }
    public int getInteresRock() { return interesRock; }
    public int getInteresUrbano() { return interesUrbano; }
    public int getId() { return id; }
    public int[] getAllInterests() { return new int[] {interesTango, interesFolclore, interesRock, interesUrbano};
    }
    public String getNombre() { return nombre; }
 // El seteo de Id para incorporar la logica de eliminar un usuario y acomodar las id en el grafo 
    public void setId(int id) {
        this.id = id;
    }
    // equals y hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return nombre;
    }
}

