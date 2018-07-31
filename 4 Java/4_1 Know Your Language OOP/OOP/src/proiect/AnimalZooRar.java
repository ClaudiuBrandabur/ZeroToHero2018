package proiect;

public class AnimalZooRar extends Animal {

    private String nume;
    private String numeTaraDeOrigine;


    AnimalZooRar(){
        System.out.println("Yeeey! Animal zoo rar!");
    }

    AnimalZooRar(String nume, int bonus){
        System.out.println("Yeeey! Animal zoo rar:" + nume);
        setBonus(bonus);
    }

    AnimalZooRar(String nume, String numeTaraDeOrigine, int bonus){
        this.nume = nume;
        this.numeTaraDeOrigine = numeTaraDeOrigine;
        setBonus(bonus);
    }

    @Override
    void mananca(Object obj) throws AnimalManancaOmException, AnimalManancaAnimal {
        if(obj instanceof AngajatZoo){
            throw new AnimalManancaOmException("ajutor!rar");
        }if(obj instanceof AnimalZooRar || obj instanceof AnimalZooFeroce){
            throw new AnimalManancaAnimal("Nu va mancati intre voi!");
        }else{
            System.out.println("AnimalZooRar mananca");
        }
    }

    @Override
    void seJoaca() {
        System.out.println("AnimalulZooRar se joaca");
        super.doarme();
    }

    @Override
    void faceBaie() {
        System.out.println("AnimalulZooRar face baie");
    }
}
