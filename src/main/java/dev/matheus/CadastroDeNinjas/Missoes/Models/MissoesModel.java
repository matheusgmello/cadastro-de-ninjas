package dev.matheus.CadastroDeNinjas.Missoes.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.matheus.CadastroDeNinjas.Ninjas.Models.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissoesModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_missao")
    private String nome;

    @Column(name = "dificuldade_missao")
    private String dificuldade;

    // @OneToMany Uma missao pode ter muitos ninjas
    @OneToMany(mappedBy = "missoes")
    @JsonIgnore
    private List<NinjaModel> ninjas;

}
