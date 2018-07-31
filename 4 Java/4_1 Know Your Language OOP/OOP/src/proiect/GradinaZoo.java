package proiect;

import java.util.Date;

public final class GradinaZoo {

    private final String denumireGradinaZoo;
    private final AnimalZooRar animalRar;
    private final IngrijitorZoo angajatulLunii;
    private final Date dataDeschideriiGradinii ;

    public GradinaZoo(String denumireGradinaZoo, AnimalZooRar animalRar, IngrijitorZoo angajatulLunii, Date dataDeschideriiGradinii){

        this.denumireGradinaZoo = denumireGradinaZoo;
        this.animalRar = animalRar;
        this.angajatulLunii = angajatulLunii;
        this.dataDeschideriiGradinii = dataDeschideriiGradinii;
    }

    public String getDenumireGradinaZoo() {
        return denumireGradinaZoo;
    }

    public AnimalZooRar getAnimalRar() {
        return animalRar;
    }

    public IngrijitorZoo getAngajatulLunii() {
        return angajatulLunii;
    }

    public Date getDataDeschideriiGradinii() {
        return dataDeschideriiGradinii;
    }
}
