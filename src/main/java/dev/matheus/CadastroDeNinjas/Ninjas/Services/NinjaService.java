package dev.matheus.CadastroDeNinjas.Ninjas.Services;

import dev.matheus.CadastroDeNinjas.Ninjas.DTOs.NinjaDTO;
import dev.matheus.CadastroDeNinjas.Ninjas.Mappers.NinjaMapper;
import dev.matheus.CadastroDeNinjas.Ninjas.Models.NinjaModel;
import dev.matheus.CadastroDeNinjas.Ninjas.Repositories.NinjaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Lista todos os ninjas
    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    // Lista todos os ninjas por ID
    public NinjaDTO listarNinjasPorId(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    // Deleta um ninja por ID
    public void deletarNinjaPorId(Long id) {
        ninjaRepository.deleteById(id);
    }

    // Atualiza parcialmente um ninja por ID (PATCH)
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {
        return ninjaRepository.findById(id)
                .map(ninja -> {
                    if (ninjaDTO.getNome() != null) {
                        ninja.setNome(ninjaDTO.getNome());
                    }
                    if (ninjaDTO.getIdade() > 0) {
                        ninja.setIdade(ninjaDTO.getIdade());
                    }
                    if (ninjaDTO.getEmail() != null) {
                        ninja.setEmail(ninjaDTO.getEmail());
                    }
                    if (ninjaDTO.getImg_url() != null) {
                        ninja.setImg_url(ninjaDTO.getImg_url());
                    }
                    if (ninjaDTO.getRank() != null) {
                        ninja.setRank(ninjaDTO.getRank());
                    }

                    if (ninjaDTO.getMissoes() != null) {
                        ninja.setMissoes(ninjaDTO.getMissoes());
                    }

                    NinjaModel ninjaSalvo = ninjaRepository.save(ninja);
                    return ninjaMapper.map(ninjaSalvo);
                })
                .orElseThrow(() -> new EntityNotFoundException("Ninja não encontrado com id " + id));
    }
}
