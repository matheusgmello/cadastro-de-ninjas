package dev.matheus.CadastroDeNinjas.Missoes.Controllers;

import dev.matheus.CadastroDeNinjas.Missoes.DTOs.MissoesDTO;
import dev.matheus.CadastroDeNinjas.Missoes.Models.MissoesModel;
import dev.matheus.CadastroDeNinjas.Missoes.Services.MissoesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissao() {
        List<MissoesDTO> missoes =  missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissoesPorId(@PathVariable Long id) {
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);
        if (missoes != null) {
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A Missao com ID " + id + " não foi encontrada.");
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missao) {
        MissoesDTO missoes =  missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " + missoes.getNome() + " (ID): " + missoes.getId());
    }


    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> atualizarMissaoPorId(@PathVariable Long id, @RequestBody MissoesDTO missaoAtualizada) {
        MissoesDTO missoes =  missoesService.atualizarMissao(id, missaoAtualizada);
        if (missoes != null) {
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A Missao com ID " + id + " não foi encontrada.");
        }
    }


    // Delete -- Mandar uma requisao para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id) {
        if(missoesService.listarMissoesPorId(id) != null) {
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("A Missao com ID " + id + " foi deletada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A Missao com ID " + id + " não foi encontrada.");
        }
    }
}
