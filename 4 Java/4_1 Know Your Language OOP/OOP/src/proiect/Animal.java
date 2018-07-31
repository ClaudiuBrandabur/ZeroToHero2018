package proiect;

public abstract class Animal {

    private int bonus;

    Animal(){
        System.out.println("Animal nou");
    }

    abstract void mananca(Object obj) throws AnimalManancaOmException, AnimalManancaAnimal;
    abstract void seJoaca();
    abstract void faceBaie();

    void doarme(){
        System.out.println("Animalul doarme");
    }

    @Override
    public String toString() {
        return "Animal{}";
    }

    void setBonus(int bonus){
        this.bonus = bonus;
    }

    int getBonus(){
        return bonus;
    }
}
