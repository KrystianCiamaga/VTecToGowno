package testrepo.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
//@Table(name = "pojazdy")
@Data
@NoArgsConstructor
public class Pojazdy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Pojazdy musi posiadać markę")
    @Column(name = "marka")
    private String marka;

    @NotBlank(message = "Podaj model pojazdu")
    @Column(name = "model")
    private String model;

    @Column(name = "moc")
    private String moc;

    @NotBlank(message = "Podaj Numer Rejestracyjny")
    @Column(name = "srednie_spalanie")
    private String srednieSpalanie;

    @NotBlank(message = "Podaj Cenę za dobę")
    @Column(name = "cena_za_dobe")
    private String cenaZaDobe;

    @Column(name = "miniaturka")
    private String miniaturka;


    public Pojazdy(@NotBlank(message = "Pojazdy musi posiadać markę") String marka, @NotBlank(message = "Podaj model pojazdu") String model, String moc, @NotBlank(message = "Podaj Numer Rejestracyjny") String srednieSpalanie, @NotBlank(message = "Podaj Cenę za dobę") String cenaZaDobe, String miniaturka) {
        this.marka = marka;
        this.model = model;
        this.moc = moc;
        this.srednieSpalanie = srednieSpalanie;
        this.cenaZaDobe = cenaZaDobe;
        this.miniaturka = miniaturka;
    }


}
