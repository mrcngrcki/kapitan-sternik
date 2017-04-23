package pl.sternik.mg.web.controlers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sternik.mg.entities.Status;
import pl.sternik.mg.entities.Znaczek;
import pl.sternik.mg.services.KlaserService;
import pl.sternik.mg.services.NotificationService;

@Controller
public class ZnaczkiController {
    @Autowired
    // @Qualifier("spring")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @GetMapping(value = "/znaczki/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Znaczek> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Znaczek znaczek = result.get();
            model.addAttribute("znaczek", znaczek);
            return "znaczek";
        } else {
            notifyService.addErrorMessage("Cannot find znaczek #" + id);
            model.clear();
            return "redirect:/znaczki";
        }
    }

    @RequestMapping(value = "/znaczki/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Znaczek> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Znaczek> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Znaczek znaczek = result.get();
            return new ResponseEntity<Znaczek>(znaczek, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find znaczek #" + id);
            model.clear();
            return new ResponseEntity<Znaczek>(HttpStatus.NOT_FOUND);
        }
    }

  
}
