package dev.matheus.CadastroDeNinjas.Missoes.Services;

import dev.matheus.CadastroDeNinjas.Missoes.Models.MissoesModel;
import dev.matheus.CadastroDeNinjas.Missoes.Repositories.MissoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // Lista todas as missões por ID
    public MissoesModel listarMissoesPorId(Long id){
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.orElse(null);
    }

}
