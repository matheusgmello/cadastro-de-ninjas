package dev.matheus.CadastroDeNinjas.Missoes.Services;

import dev.matheus.CadastroDeNinjas.Missoes.DTOs.MissoesDTO;
import dev.matheus.CadastroDeNinjas.Missoes.Mappers.MissoesMapper;
import dev.matheus.CadastroDeNinjas.Missoes.Models.MissoesModel;
import dev.matheus.CadastroDeNinjas.Missoes.Repositories.MissoesRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return missoesRepository.findById(id)
                .map(missao -> {
                    if (missoesDTO.getNome() != null) {
                        missao.setNome(missoesDTO.getNome());
                    }
                    if (missoesDTO.getDificuldade() != null) {
                        missao.setDificuldade(missoesDTO.getDificuldade());
                    }
                    if (missoesDTO.getNinjas() != null) {
                        missao.setNinjas(missoesDTO.getNinjas());
                    }

                    MissoesModel missaoSalva = missoesRepository.save(missao);
                    return missoesMapper.map(missaoSalva);
                })
                .orElseThrow(() -> new EntityNotFoundException("Missão não encontrada com id " + id));
    }


}
