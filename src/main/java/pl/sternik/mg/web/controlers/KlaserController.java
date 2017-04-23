package pl.sternik.mg.web.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.sternik.mg.entities.Znaczek;
import pl.sternik.mg.services.KlaserService;
import pl.sternik.mg.services.NotificationService;

@Controller
public class KlaserController {

    @Autowired
  //   @Qualifier("spring")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notificationService;

//    @ModelAttribute("statusyAll")
//    public List<Status> populateStatusy() {
//        return Arrays.asList(Status.ALL);
//    }

    @ModelAttribute("stampsAll")
    public List<Znaczek> populateStamps() {
        return this.klaserService.findAll();
    }

//    @ModelAttribute("coinsToSell")
//    public List<Moneta> populateCoinsToSell() {
//        return this.klaserService.findAllToSell();
//    }

//    @ModelAttribute("coinsLast3")
//    public List<Moneta> populateLast3Coins() {
//        return this.klaserService.findLatest3();
//    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/znaczki", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages",  notificationService.getNotificationMessages());
        return "klaser";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "tosell";
    }

}