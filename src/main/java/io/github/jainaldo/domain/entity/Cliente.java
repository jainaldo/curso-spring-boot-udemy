package io.github.jainaldo.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

//    Não é obrigatório colocar o @column,
//    pois quando um classe tem o @Entity o spring entendi que suas propriedades são colunas.
//    Caso queira espeficicar ou o nome da coluna no banco é diferente então pode colocar o @column
    @Column(name = "nome", length = 100)
    private String nome;

    public Cliente() {
    };

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
