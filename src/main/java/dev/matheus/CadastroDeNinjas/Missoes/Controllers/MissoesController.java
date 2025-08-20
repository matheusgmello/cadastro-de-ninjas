package dev.matheus.CadastroDeNinjas.Missoes.Controllers;

import dev.matheus.CadastroDeNinjas.Missoes.DTOs.MissoesDTO;
import dev.matheus.CadastroDeNinjas.Missoes.Models.MissoesModel;
import dev.matheus.CadastroDeNinjas.Missoes.Services.MissoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todas as missões", description = "Esta rota retorna uma lista com todas as missões cadastradas no sistema.")
    public ResponseEntity<List<MissoesDTO>> listarMissao() {
        List<MissoesDTO> missoes =  missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista missão por ID", description = "Esta rota retorna uma missão específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
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
    @Operation(summary = "Cria uma nova missão", description = "Esta rota cria uma nova missão no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação da missão")
    })
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missao) {
        MissoesDTO missoes =  missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " + missoes.getNome() + " (ID): " + missoes.getId());
    }


    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Atualiza uma missão existente", description = "Esta rota atualiza uma missão existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<?> atualizarMissaoPorId(@PathVariable Long id, @RequestBody MissoesDTO missaoAtualizada) {
        MissoesDTO missoes =  missoesService.atualizarMissao(id, missaoAtualizada);
        if (missoes != null) {
            return ResponseEntity.ok(missoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A Missao com ID " + id + " não foi encontrada.");
        }
    }


    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta uma missão por ID", description = "Esta rota deleta uma missão específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
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
