package fr.univtln.jguillon725.projet.model.entities;

/**
 * Created by julien on 20/10/15.
 */
public class CRoom {
    private String numBatiment;
    private int numRoom;
    private String typeOfRoom;
    private int capacity;

    public CRoom(String numBatiment, int numRoom, String typeOfRoom, int capacity) {
        this.numBatiment = numBatiment;
        this.numRoom = numRoom;
        this.typeOfRoom = typeOfRoom;
        this.capacity = capacity;
    }

    public String getNumBatiment() {
        return numBatiment;
    }

    public void setNumBatiment(String numBatiment) {
        this.numBatiment = numBatiment;
    }

    public int getNumRoom() {
        return numRoom;
    }

    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CRoom cRoom = (CRoom) o;

        if (numRoom != cRoom.numRoom) return false;
        if (capacity != cRoom.capacity) return false;
        if (numBatiment != null ? !numBatiment.equals(cRoom.numBatiment) : cRoom.numBatiment != null) return false;
        return !(typeOfRoom != null ? !typeOfRoom.equals(cRoom.typeOfRoom) : cRoom.typeOfRoom != null);

    }

    @Override
    public int hashCode() {
        int result = numBatiment != null ? numBatiment.hashCode() : 0;
        result = 31 * result + numRoom;
        result = 31 * result + (typeOfRoom != null ? typeOfRoom.hashCode() : 0);
        result = 31 * result + capacity;
        return result;
    }
}
