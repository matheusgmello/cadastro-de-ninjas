package dev.matheus.CadastroDeNinjas.Missoes.Services;

import dev.matheus.CadastroDeNinjas.Missoes.Models.MissoesModel;
import dev.matheus.CadastroDeNinjas.Missoes.Repositories.MissoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // Lista todas as missões
    public List<MissoesModel> listarMissoes() {
        return missoesRepository.findAll();
    }

}
