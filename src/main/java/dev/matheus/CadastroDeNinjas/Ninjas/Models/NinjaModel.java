package dev.matheus.CadastroDeNinjas.Ninjas.Models;


import dev.matheus.CadastroDeNinjas.Missoes.Models.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity ele transforma uma classe em uma Entidade do Banco de Dados

@Entity
@Table(name = "tb_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_url")
    private String img_url;

    @Column(name = "idade")
    private int idade;

    // @ManyToOne Um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missoes_id") // Define a chave estrangeira na tabela de ninjas
    private MissoesModel missoes;


}
