package proiect;

public class IngrijitorZoo implements AngajatZoo {

    private int bonusTotal = 0;

    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Ingrijitorul intra in cusca animalului" + animal);
    }

    @Override
    public void calculeazaBonusSalarial(Animal animal) {
        bonusTotal += 3 * animal.getBonus();
    }


    public void lucreaza(Animal animal,Object mancare) throws AnimalPeCaleDeDisparitieException, AnimalManancaOmException {
        this.lucreaza(animal);
        animal.seJoaca();
        animal.faceBaie();
        animal.doarme();
        if(animal instanceof AnimalZooRar && mancare == null){
            throw new AnimalPeCaleDeDisparitieException("dispar!angajat");
        }else{
            animal.mananca(mancare);
            System.out.println(getBonusTotal());
        }

    }

    int getBonusTotal(){
        return bonusTotal;
    }
}
