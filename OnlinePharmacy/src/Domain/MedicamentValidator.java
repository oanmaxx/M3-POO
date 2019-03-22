package Domain;

public class MedicamentValidator {
    public void validate(Medicament medicament) {
        if (medicament.getPrice() <= 0) {
            throw new RuntimeException("price must be > 0!");
        }
    }
}
