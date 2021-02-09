package testrepo.demo.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testrepo.demo.AppUtils;
import testrepo.demo.dto.TransferData;
import testrepo.demo.dto.UserContext;
import testrepo.demo.dto.UserData;
import testrepo.demo.model.Konta;
import testrepo.demo.model.Osoby;
import testrepo.demo.model.Pojazdy;
import testrepo.demo.service.AccountService;
import testrepo.demo.service.CarService;
import testrepo.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserContext userContext;
    private final AccountService accountService;
    private final CarService carService;
    private final TransferData transferData;




    @RequestMapping(value = "zaloguj", produces = "application/json",
            method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    public String zaloguj(
            @Valid UserData userData,
            Model model) {


        String encodedUserDetails = AppUtils.encodeToBase64(userData);

        Konta konto = userService.getAccountByCredentials(encodedUserDetails);
        if (konto==null) {
            return "zaloguj";
        } else {
            userContext.setPoswiadczeniaUzytkownika(konto.getDaneLogowania());
            userContext.setUserId(konto.getOsoba().getId());
        }

        Pojazdy one = carService.getOnePojazd(transferData.getIdPojazdu());
        model.addAttribute("pojazdDoWynajecia", one);
        transferData.setIdPojazdu(one.getId());
        model.addAttribute("transferData", transferData);
        return "redirect:/";
    }


    @RequestMapping(value = "zarejestruj", produces = "application/json",
            method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    public String zarejestruj(
            @Valid UserData userData,
            Model model) {
        if (userData.getLogin() != null) {
            userService.saveUser(new Osoby(userData.getImie(), userData.getNazwisko(), userData.getNumerDowodu(), userData.getNumerTelefonu()));
            Osoby byNumerDowodu = userService.getUserByNumerDowodu(userData.getNumerDowodu());
            userService.saveAccount(new Konta(byNumerDowodu, AppUtils.encodeToBase64(userData), "CLIENT"));
            return "redirect:/";
        }
        model.addAttribute("userData", new UserData());

        return "zarejestruj";
    }

    @GetMapping(value = "/wyloguj")
    public String wyloguj() {
        userContext.setUserId(null);
        userContext.setPoswiadczeniaUzytkownika("");
        return "redirect:/";

    }


    @GetMapping(value = "/boss")
    public String panelSzefa(Model model) {
        if (userContext.getPoswiadczeniaUzytkownika().isEmpty()) {
            return "redirect:/";
        }
        if (userContext.getUserId() != null) {
            Osoby osoba = userService.getOneUser(userContext.getUserId());
            String role = accountService.getAccountByUser(osoba).getRola();
            if (!role.equalsIgnoreCase("boss")){
                return "redirect:/";
            }
        }


        model.addAttribute("pojazdy", carService.getAllCars());
        return "carDetails";
    }

    @GetMapping(value = "/")
    public String start(Model model) {
        model.addAttribute("userContext", userContext);
        String role = "";
        if (userContext.getUserId() != null) {
            Osoby osoba = userService.getOneUser(userContext.getUserId());
            role = accountService.getAccountByUser(osoba).getRola();

        }
        model.addAttribute("rola", role);


        return "index";
    }



    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }






}
