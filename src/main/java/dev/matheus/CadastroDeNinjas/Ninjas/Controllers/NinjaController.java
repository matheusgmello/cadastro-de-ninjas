package dev.matheus.CadastroDeNinjas.Ninjas.Controllers;


import dev.matheus.CadastroDeNinjas.Ninjas.DTOs.NinjaDTO;
import dev.matheus.CadastroDeNinjas.Ninjas.Services.NinjaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Bem-vindo ao Cadastro de Ninjas!";
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<String> listarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            return ResponseEntity.ok("Ninja encontrado: " + ninja.getNome());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O Ninja com ID " + id + " não foi encontrado.");
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> atualizarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
       NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O Ninja com ID " + id + " não foi encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {
        if (ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Ninja com ID " + id + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O Ninja com ID " + id + " não foi encontrado.");
        }
    }
}
