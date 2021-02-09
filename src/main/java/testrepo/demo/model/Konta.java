package testrepo.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Konta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_osoby", referencedColumnName = "id")
    private Osoby osoba;

    @Column(name = "dane_logowania")
    private String daneLogowania;

    @Column(name = "rola")
    private String rola;

    public Konta(Osoby osoba, String daneLogowania, String rola) {
        this.osoba = osoba;
        this.daneLogowania = daneLogowania;
        this.rola = rola;
    }

}
