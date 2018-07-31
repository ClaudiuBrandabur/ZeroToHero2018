package proiect;

public class VeterinarZoo implements AngajatZoo {

    private int bonusTotal = 0;


    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Veterinarul are grija de animal");
        if(animal instanceof AnimalZooFeroce) {
            animal.faceBaie();
        }
        this.calculeazaBonusSalarial(animal);
        System.out.println(getBonusTotal());

    }

    @Override
    public void calculeazaBonusSalarial(Animal animal) {
        bonusTotal += 2 * animal.getBonus();
    }

    int getBonusTotal(){
        return bonusTotal;
    }
}
