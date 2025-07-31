package dev.matheus.CadastroDeNinjas.Ninjas.Controllers;


import dev.matheus.CadastroDeNinjas.Ninjas.Models.NinjaModel;
import dev.matheus.CadastroDeNinjas.Ninjas.Services.NinjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Bem-vindo ao Cadastro de Ninjas!";
    }

    // Lista todos os Ninjas
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Lista Ninja por ID
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.listarNinjasPorId(id);
    }

    // Adicionar Ninja
    @PostMapping("/criar")
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja) {
        return ninjaService.criarNinja(ninja);
    }


    // Alterar dados dos ninjas
    @PutMapping("/alterar/{id}")
    public String alterarNinjaPorId() {
        return "Dados alterados com sucesso!";
    }

    // Deletar Ninja por ID
    @DeleteMapping("/deletar/{id}")
    public String deletarNinjaPorId() {
        return "Ninja deletado com sucesso!";
    }
}
