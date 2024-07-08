package Controller;
import Entities.Livre;
import Services.LivreService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/livre")
public class LivreController {
    @Autowired
    private LivreService livreService;

    public LivreController(LivreService livreService) {
        super();
        this.livreService = livreService;
    }

    @GetMapping("") // Renvoie la liste des livres
    public String showLivre(Model model) {
        model.addAttribute("ListeLivre", livreService.getAll());
        return "Livre/list";
    }

    @GetMapping("/create")
    public String showNewFormLivre(Model model) {
        Livre livre = new Livre();
        model.addAttribute("Form", livre);
        return "Livre/add";
    }

    @PostMapping("/save")
    public String create(@Valid @ModelAttribute("Form") Livre livre, BindingResult result, Model model, RedirectAttributes res) throws NotFoundException {
        try {
            if (result.hasErrors()) {
                return "Livre/add";
            }
            livreService.save(livre);
            return "redirect:/livre";
        } catch (NotFoundException e) {
            res.addFlashAttribute("message", e.getMessage());
            return "redirect:/livre";
        }
    }

    @GetMapping("/edit/{livreCode}")
    public String edit(@PathVariable("livreCode") Long livreCode, Model model) throws NotFoundException {
        Livre livre = livreService.getById(livreCode);
        model.addAttribute("Form", livre);
        return "Livre/add";
    }

    @GetMapping("/delete/{livreCode}")
    public String delete(@PathVariable("livreCode") Long livreCode, RedirectAttributes res) {
        try {
            livreService.deleteById(livreCode);
            res.addFlashAttribute("message", "Suppression effectuée avec succès");
        } catch (Exception e) {
            res.addFlashAttribute("message", "Suppression impossible, cet enregistrement est en cours d'utilisation");
        }
        return "redirect:/livre";
    }
}

