package Domain;

import java.util.Objects;

public class Medicament {
    private int id;
    private double price;
    private String name, producer;
    private boolean needsRecipe;

    public Medicament(int id, double price, String name, String producer, boolean needsRecipe) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.producer = producer;
        this.needsRecipe = needsRecipe;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", needsRecipe=" + needsRecipe +
                '}';
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament medicine = (Medicament) o;
        return Objects.equals(id, medicine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public boolean isNeedsRecipe() {
        return needsRecipe;
    }

    public void setNeedsRecipe(boolean needsRecipe) {
        this.needsRecipe = needsRecipe;
    }
}
