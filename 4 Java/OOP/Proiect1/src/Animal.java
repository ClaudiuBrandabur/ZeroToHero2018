public abstract class Animal extends AnimalZooRar {


    public Animal() {
        System.out.println("Animal nou");
    }

    public abstract void mananca(int mancare);

    public abstract void seJoaca();

    public abstract void faceBaie();

    public void doarme(){
        System.out.println("Animalul doarme.");
    }
}
