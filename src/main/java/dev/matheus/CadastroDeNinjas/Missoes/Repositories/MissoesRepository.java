package dev.matheus.CadastroDeNinjas.Missoes.Repositories;

import dev.matheus.CadastroDeNinjas.Missoes.Models.MissoesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissoesRepository extends JpaRepository<MissoesModel, Long> {


}
