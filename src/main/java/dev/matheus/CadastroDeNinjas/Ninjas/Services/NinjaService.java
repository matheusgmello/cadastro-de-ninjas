package dev.matheus.CadastroDeNinjas.Ninjas.Services;

import dev.matheus.CadastroDeNinjas.Ninjas.Models.NinjaModel;
import dev.matheus.CadastroDeNinjas.Ninjas.Repositories.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    // Lista todos os ninjas
    public List<NinjaModel> listarNinjas() {
        return ninjaRepository.findAll();
    }

}
