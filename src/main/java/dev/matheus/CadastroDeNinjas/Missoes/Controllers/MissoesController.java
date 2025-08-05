package dev.matheus.CadastroDeNinjas.Missoes.Controllers;

import dev.matheus.CadastroDeNinjas.Missoes.DTOs.MissoesDTO;
import dev.matheus.CadastroDeNinjas.Missoes.Models.MissoesModel;
import dev.matheus.CadastroDeNinjas.Missoes.Services.MissoesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET -- Mandar uma requisao para mostrar as missoes
    @GetMapping("/listar")
    public List<MissoesDTO> listarMissao() {
        return missoesService.listarMissoes();
    }

    // GET -- Mandar uma requisao para mostrar as missoes por ID
    @GetMapping("/listar/{id}")
    public MissoesDTO listarMissoesPorId(@PathVariable Long id) {
        return missoesService.listarMissoesPorId(id);
    }


    // Post -- Mandar uma requisao para criar as missoes
    @PostMapping("/criar")
    public MissoesDTO criarMissao(@RequestBody MissoesDTO missao) {
        return missoesService.criarMissao(missao);
    }


    // PUT -- Mandar uma requisao para alterar as missoes
    @PutMapping("/alterar/{id}")
    public MissoesDTO atualizarMissaoPorId(@PathVariable Long id, @RequestBody MissoesDTO missaoAtualizada) {
        return missoesService.atualizarMissao(id, missaoAtualizada);
    }


    // Delete -- Mandar uma requisao para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public void deletarMissaoPorId(@PathVariable Long id) {
        missoesService.deletarMissaoPorId(id);
    }
}
