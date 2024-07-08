package Controller;
import Entities.Reservation;
import Services.ReservationService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        super();
        this.reservationService = reservationService;
    }

    @GetMapping("") // Renvoie la liste des réservations
    public String showReservation(Model model) {
        model.addAttribute("ListeReservation", reservationService.getAll());
        return "Reservation/list";
    }

    @GetMapping("/create")
    public String showNewFormReservation(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("Form", reservation);
        return "Reservation/add";
    }

    @PostMapping("/save")
    public String create(@Valid @ModelAttribute("Form") Reservation reservation, BindingResult result, Model model, RedirectAttributes res) throws NotFoundException {
        try {
            if (result.hasErrors()) {
                return "Reservation/add";
            }
            reservationService.save(reservation);
            return "redirect:/reservation";
        } catch (NotFoundException e) {
            res.addFlashAttribute("message", e.getMessage());
            return "redirect:/reservation";
        }
    }

    @GetMapping("/edit/{reservationId}")
    public String edit(@PathVariable("reservationId") Long reservationId, Model model) throws NotFoundException {
        Reservation reservation = reservationService.getById(reservationId);
        model.addAttribute("Form", reservation);
        return "Reservation/add";
    }

}