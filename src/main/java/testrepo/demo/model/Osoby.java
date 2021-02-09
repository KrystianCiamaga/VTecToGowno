package testrepo.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
//@Table(name = "REGISTRATION_USERS")
public class Osoby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imie;
    private String nazwisko;
    private String numerDowoduOsobistego;
    private String numerTelefonu;


    public Osoby(String imie, String nazwisko, String numerDowoduOsobistego, String numerTelefonu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerDowoduOsobistego = numerDowoduOsobistego;
        this.numerTelefonu = numerTelefonu;
    }


}
