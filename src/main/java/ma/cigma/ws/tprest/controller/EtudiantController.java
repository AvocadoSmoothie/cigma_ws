package ma.cigma.ws.tprest.controller;

import ma.cigma.ws.tprest.model.Etudiant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EtudiantController {

    List<Etudiant> db = Arrays.asList(
            new Etudiant(1L, "NAME_1", "PRENOM_1", 20),
            new Etudiant(2L, "NAME_2", "PRENOM_2", 20),
            new Etudiant(3L, "NAME_3", "PRENOM_3", 20),
            new Etudiant( 4L, "NAME_4", "PRENOM_4", 20)
    );

    @GetMapping(value = "/etudiant", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Etudiant> hello() {
        return db;
    }

    @PostMapping(value = "/etudiant", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Etudiant save(@RequestBody Etudiant etudiant) {
        db.add(etudiant);
        return etudiant;
    }
    @DeleteMapping(value = "/etudiant/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void remove(@PathVariable("id") Long id) {
        db = db.stream().filter(etudiant -> !etudiant.getId().equals(id)).collect(Collectors.toList());

    }
}
