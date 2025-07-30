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

    // Adicionar Ninja
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja cadastrado com sucesso!";
    }

    // Lista todos os Ninjas
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Procurar Ninja por ID
    @GetMapping("/todos/{id}")
    public String mostrarNinjaPorId() {
        return "Lista de todos os ninjas!";
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
