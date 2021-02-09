package testrepo.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testrepo.demo.model.Pojazdy;

@Repository
public interface PojazdRepository extends JpaRepository<Pojazdy, Long> {

    @Transactional
    @Modifying
    @Query("update Pojazdy p set p.marka=?2, p.model=?3, p.moc=?4, p.srednieSpalanie=?5, p.cenaZaDobe=?6, p.miniaturka=?7 where p.id=?1")
    void updateCar(long id, String marka, String model, String moc, String srednieSpalanie, String cenaZaDobe, String miniaturka);
}
