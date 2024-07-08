package Controller;

import Entities.Utilisateur;
import Services.UtilisateurService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        super();
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("") // Renvoie la liste des utilisateurs
    public String showUtilisateur(Model model) {
        model.addAttribute("ListeUtilisateur", utilisateurService.getAll());
        return "Utilisateur/list";
    }

    @GetMapping("/create")
    public String showNewFormUtilisateur(Model model) {
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("Form", utilisateur);
        return "Utilisateur/add";
    }

    @PostMapping("/save")
    public String create(@Valid @ModelAttribute("Form") Utilisateur utilisateur, BindingResult result, Model model, RedirectAttributes res) throws NotFoundException {
        try {
            if (result.hasErrors()) {
                return "Utilisateur/add";
            }
            utilisateurService.save(utilisateur);
            return "redirect:/utilisateur";
        } catch (NotFoundException e) {
            res.addFlashAttribute("message", e.getMessage());
            return "redirect:/utilisateur";
        }
    }

    @GetMapping("/edit/{utilCode}")
    public String edit(@PathVariable("utilCode") Long utilCode, Model model) throws NotFoundException {
        Utilisateur utilisateur = utilisateurService.getById(utilCode);
        model.addAttribute("Form", utilisateur);
        return "Utilisateur/add";
    }

    @GetMapping("/delete/{utilCode}")
    public String delete(@PathVariable("utilCode") Long utilCode, RedirectAttributes res) {
        try {
            utilisateurService.deleteById(utilCode);
            res.addFlashAttribute("message", "Suppression effectuée avec succès");
        } catch (Exception e) {
            res.addFlashAttribute("message", "Suppression impossible, cet enregistrement est en cours d'utilisation");
        }
        return "redirect:/utilisateur";
    }
}

