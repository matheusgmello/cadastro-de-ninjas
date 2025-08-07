package dev.matheus.CadastroDeNinjas.Missoes.Services;

import dev.matheus.CadastroDeNinjas.Missoes.DTOs.MissoesDTO;
import dev.matheus.CadastroDeNinjas.Missoes.Mappers.MissoesMapper;
import dev.matheus.CadastroDeNinjas.Missoes.Models.MissoesModel;
import dev.matheus.CadastroDeNinjas.Missoes.Repositories.MissoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    // Lista todas as missões
    public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .toList();
    }

    // Lista todas as missões por ID
    public MissoesDTO listarMissoesPorId(Long id){
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.map(missoesMapper::map).orElse(null);
    }

    // Criar uma nova missao
    public MissoesDTO criarMissao(MissoesDTO missaoDTO) {
        MissoesModel missao = missoesMapper.map(missaoDTO);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);

    }

    // Deleta uma missão por ID
    public void deletarMissaoPorId(Long id) {
        missoesRepository.deleteById(id);
    }

    // Atualiza uma missão por ID
    public MissoesDTO atualizarMissao(Long id, MissoesDTO missoesDTO) {
    Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);
    if (missaoExistente.isPresent()) {
        MissoesModel misssaoAtualizada = missoesMapper.map(missoesDTO);
        misssaoAtualizada.setId(id);
        MissoesModel missaoSalva = missoesRepository.save(misssaoAtualizada);
        return missoesMapper.map(missaoSalva);
    }
        return null;
    }

}
