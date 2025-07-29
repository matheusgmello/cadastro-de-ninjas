package dev.matheus.CadastroDeNinjas.Ninjas.Controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Bem-vindo ao Cadastro de Ninjas!";
    }

    // Adicionar Ninja
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja cadastrado com sucesso!";
    }

    // Mostrar todos os Ninjas
    @GetMapping("/todos")
    public String mostrarNinja() {
        return "Lista de todos os ninjas!";
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
