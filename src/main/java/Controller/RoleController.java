package Controller;

import Entities.Role;
import Services.RoleService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public String showRoles(Model model) {
        model.addAttribute("roles", roleService.getAll());
        return "Role/list";
    }

    @GetMapping("/create")
    public String showNewRoleForm(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "Role/add";
    }

    @PostMapping("/save")
    public String createOrUpdateRole(@Valid @ModelAttribute("role") Role role, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws NotFoundException {
        if (result.hasErrors()) {
            return "Role/add";
        }

        try {
            roleService.save(role);
            return "redirect:/role";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/role";
        }
    }

    @GetMapping("/edit/{roleId}")
    public String editRole(@PathVariable("roleId") Long roleId, Model model) throws NotFoundException {
        Role role = roleService.getById(roleId);
        model.addAttribute("role", role);
        return "Role/add";
    }

    @GetMapping("/delete/{roleId}")
    public String deleteRole(@PathVariable("roleId") Long roleId) {
        roleService.deleteById(roleId);
        return "redirect:/role";
    }
}
