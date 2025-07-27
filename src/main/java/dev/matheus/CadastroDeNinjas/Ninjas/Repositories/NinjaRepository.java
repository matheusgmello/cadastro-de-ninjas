package dev.matheus.CadastroDeNinjas.Ninjas.Repositories;

import dev.matheus.CadastroDeNinjas.Ninjas.Models.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
}
