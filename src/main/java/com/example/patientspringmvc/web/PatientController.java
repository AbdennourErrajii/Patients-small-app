package com.example.patientspringmvc.web;

import com.example.patientspringmvc.entities.Patient;
import com.example.patientspringmvc.repository.PatientRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class PatientController {

    @Autowired
    private PatientRepo patientRepo;

    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "5") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Patient> pagePatients=patientRepo.findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(keyword,keyword,PageRequest.of(page,size));
        model.addAttribute("patients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepo.findAll();
    }

    @GetMapping("/admin/deletePatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@RequestParam(name="id") Long idPatient, String keyword, int page){
        patientRepo.deleteById(idPatient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/formPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatient(Model model ){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @PostMapping("/admin/savePatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult,
                          @RequestParam(defaultValue = "")    String keyword,
                              @RequestParam(defaultValue = "0") int page){
        if (bindingResult.hasErrors()) return "formPatient";
        patientRepo.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(@RequestParam(name = "id") Long id, Model model, String keyword, int page){
        Patient patient=patientRepo.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient not found");
        model.addAttribute("patient",patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editPatient";
    }

    @GetMapping("/")
    public String main(){
        return "home";
    }
}
