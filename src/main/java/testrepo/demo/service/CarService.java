package testrepo.demo.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import testrepo.demo.model.Pojazdy;
import testrepo.demo.repository.PojazdRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final PojazdRepository pojazdRepository;


    public Pojazdy getOnePojazd(long idPojazdu) {
       return pojazdRepository.getOne(idPojazdu);
    }

    public void saveCar(Pojazdy pojazdy) {
        pojazdRepository.save(pojazdy);
    }

    public Pojazdy getOneCar(long id) {
        return pojazdRepository.getOne(id);
    }

    public void deleteCarById(long id) {
        pojazdRepository.deleteById(id);
    }

    public Optional<Pojazdy> getCarById(long id) {
        return pojazdRepository.findById(id);
    }

    public List<Pojazdy> getAllCars() {
       return pojazdRepository.findAll();
    }

    public void updateCar(long id, String marka, String model, String moc, String srednieSpalanie, String cenaZaDobe, String miniaturka) {
        pojazdRepository.updateCar(id,marka,model,moc,srednieSpalanie,cenaZaDobe,miniaturka);
    }
}
