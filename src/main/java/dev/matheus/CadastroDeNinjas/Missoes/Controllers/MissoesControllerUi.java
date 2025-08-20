package dev.matheus.CadastroDeNinjas.Missoes.Controllers;

import dev.matheus.CadastroDeNinjas.Missoes.DTOs.MissoesDTO;
import dev.matheus.CadastroDeNinjas.Missoes.Services.MissoesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;

    public MissoesControllerUi(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissaoPorId(@PathVariable Long id) {
        missoesService.deletarMissaoPorId(id);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarMissaoPorId(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        if (missao != null) {
            model.addAttribute("missao", missao);
            return "detalhesMissao";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada.");
            return "listarMissoes";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissao(Model model) {
        model.addAttribute("missao", new MissoesDTO());
        return "adicionarMissao";
    }

    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoesService.criarMissao(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão cadastrada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

}