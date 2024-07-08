package Controller;

import Entities.Emprunt;
import Services.EmpruntService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/emprunt")
public class EmpruntController {
    @Autowired
    private EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        super();
        this.empruntService = empruntService;
    }

    @GetMapping("") // Renvoie la liste des emprunts
    public String showEmprunt(Model model) {
        model.addAttribute("ListeEmprunt", empruntService.getAll());
        return "Emprunt/list";
    }

    @GetMapping("/create")
    public String showNewFormEmprunt(Model model) {
        Emprunt emprunt = new Emprunt();
        model.addAttribute("Form", emprunt);
        return "Emprunt/add";
    }

    @PostMapping("/save")
    public String create(@Valid @ModelAttribute("Form") Emprunt emprunt, BindingResult result, Model model, RedirectAttributes res) throws NotFoundException {
        try {
            if (result.hasErrors()) {
                return "Emprunt/add";
            }
            empruntService.save(emprunt);
            return "redirect:/emprunt";
        } catch (NotFoundException e) {
            res.addFlashAttribute("message", e.getMessage());
            return "redirect:/emprunt";
        }
    }

    @GetMapping("/edit/{empruntCode}")
    public String edit(@PathVariable("empruntCode") Long empruntCode, Model model) throws NotFoundException {
        Emprunt emprunt = empruntService.getById(empruntCode);
        model.addAttribute("Form", emprunt);
        return "Emprunt/add";
    }

    @GetMapping("/delete/{empruntCode}")
    public String delete(@PathVariable("empruntCode") Long empruntCode, RedirectAttributes res) {
        try {
            empruntService.deleteById(empruntCode);
            res.addFlashAttribute("message", "Suppression effectuée avec succès");
        } catch (Exception e) {
            res.addFlashAttribute("message", "Suppression impossible, cet enregistrement est en cours d'utilisation");
        }
        return "redirect:/emprunt";
    }
}
