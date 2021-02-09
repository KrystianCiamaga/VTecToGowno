package testrepo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import testrepo.demo.model.Konta;
import testrepo.demo.model.Osoby;

@Repository
public interface KontaRepository extends JpaRepository<Konta, Long> {

    @Query("select k from Konta k where ?1=k.daneLogowania")
    Konta findByCredentials(String daneLogowania);

    @Query("select k from Konta k where ?1=k.osoba")
    Konta findByUser(Osoby osoby);

    @Query("select k.osoba from Konta k where ?1=k.daneLogowania")
    Osoby findUserByCredentials(String poswiadczeniaUzytkownika);

}
