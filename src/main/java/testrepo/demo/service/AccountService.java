package testrepo.demo.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import testrepo.demo.model.Konta;
import testrepo.demo.model.Osoby;
import testrepo.demo.repository.KontaRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final KontaRepository kontaRepository;


    public Konta getAccountByCredentials(String poswiadczeniaUzytkownika) {
        return kontaRepository.findByCredentials(poswiadczeniaUzytkownika);
    }

    public Konta getAccountByUser(Osoby osoba) {
        return kontaRepository.findByUser(osoba);
    }
}
