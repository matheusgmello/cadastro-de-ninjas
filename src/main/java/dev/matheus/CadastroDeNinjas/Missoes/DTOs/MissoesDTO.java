package dev.matheus.CadastroDeNinjas.Missoes.DTOs;

import dev.matheus.CadastroDeNinjas.Ninjas.Models.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissoesDTO {


    private Long id;
    private String nome;
    private String dificuldade;
    private List<NinjaModel> ninjas;

}
