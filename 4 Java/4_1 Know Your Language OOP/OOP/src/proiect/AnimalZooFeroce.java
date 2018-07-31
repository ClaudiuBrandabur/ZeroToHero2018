package proiect;

public class AnimalZooFeroce extends Animal {
    @Override
    void mananca(Object obj) throws AnimalManancaOmException, AnimalManancaAnimal {
        if(obj instanceof AngajatZoo){
            throw new AnimalManancaOmException("ajutor!");
        }if(obj instanceof AnimalZooRar || obj instanceof AnimalZooFeroce){
            throw new AnimalManancaAnimal("Nu va mancati intre voi!");
        }else{
            System.out.println("AnimalZooFeroce mananca");
        }
    }

    @Override
    void seJoaca() {
        System.out.println("AnimalZooFeroce se joaca");
    }

    @Override
    void faceBaie() {
        System.out.println("AnimalZooFeroce face baie");
    }

    @Override
    void doarme() {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }
}
