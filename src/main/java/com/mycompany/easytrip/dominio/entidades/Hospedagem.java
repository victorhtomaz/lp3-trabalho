package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.enums.TipoDeHospedagem;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.excecoes.HospedagemException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import com.mycompany.easytrip.dominio.objetosDeValor.Avaliacao;
import com.mycompany.easytrip.dominio.objetosDeValor.PrecoDiaria;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Hospedagem")
public class Hospedagem implements Validacao{
    private static final int DIFERENCA_HORAS_MINIMA = 12;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @Column(name = "Usuario_Id")
    private int usuarioId;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Endereco_Id")
    private Endereco endereco;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Hospedagem_Id", nullable = false)
    @OrderBy("data ASC")
    private final List<Disponibilidade> disponibilidades = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Hospedagem_Id", nullable = false)
    private final List<Imagem> imagens = new ArrayList<>();
    
    @Column(name = "Titulo")
    private String titulo;
    
    @Column(name = "Descricao")
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo")
    private TipoDeHospedagem tipo;
    
    @Column(name = "CapacidadeMaxima")
    private int capacidadeMaxima;
    
    @Embedded
    private PrecoDiaria precoDiaria;
    
    @Column(name = "CheckIn")
    private LocalTime checkIn;
    
    @Column(name = "CheckOut")
    private LocalTime checkOut;
    
    @Embedded
    @AttributeOverride(name = "nota", column = @Column(name = "AvaliacaoMedia"))
    private Avaliacao avaliacaoMedia;
    
    protected Hospedagem() {}
    
    public Hospedagem(int usuarioId, String titulo, String descricao, 
            TipoDeHospedagem tipo, int capacidadeMaxima, BigDecimal precoDiaria,
            LocalTime checkIn, LocalTime checkOut, Endereco endereco) throws DominioException{
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.capacidadeMaxima = capacidadeMaxima;
        this.precoDiaria = new PrecoDiaria(precoDiaria);
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.endereco = endereco;
        this.avaliacaoMedia = new Avaliacao(5);
        
        validar();
    }
    
    @Override
    public void validar() throws DominioException {
        if(usuarioId < 0)
            throw new HospedagemException("O id do usuário é inválida");
        if (titulo.isBlank())
            throw new HospedagemException("O titulo não pode ser vazio");
        
        if(descricao.isBlank())
            throw new HospedagemException("A descrição não pode ser vazia");
        
        if(capacidadeMaxima < 0)
            throw new HospedagemException("A capacidade máxima não pode ser negativa");
        
        long duracao = Duration.between(checkIn, checkOut).toHours();
        if(duracao < 0)
            duracao += 24;
        
        if(duracao < DIFERENCA_HORAS_MINIMA)
            throw new HospedagemException("É necessário de uma diferença de: " + DIFERENCA_HORAS_MINIMA + "no checkin e checkout");
    }
    
    public int getId(){
        return id;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public TipoDeHospedagem getTipo(){
        return tipo;
    }
    
    public int getCapacidadeMaxima(){
        return capacidadeMaxima;
    }
    
    public void setCapacidadeMaxima(int capacidadeMaxima){
        this.capacidadeMaxima = capacidadeMaxima;
    }
    
    public PrecoDiaria getPrecoDiaria(){
        return precoDiaria;
    }
    
    public void setPrecoDiaria(BigDecimal precoDiaria) throws DominioException{
        this.precoDiaria = new PrecoDiaria(precoDiaria);
    }
    
    public LocalTime getCheckIn(){
        return checkIn;
    }
    
    public void setCheckIn(LocalTime checkIn){
        this.checkIn = checkIn;
    }
    
    public LocalTime getCheckOut(){
        return checkOut;
    }
    
    public void setCheckOut(LocalTime checkOut){
        this.checkOut = checkOut;
    }
    
    public Endereco getEndereco(){
        return endereco;
    }
    
    public float getNotaAvaliacao(){
        return avaliacaoMedia.getNota();
    }
    
    public List<Imagem> getImagens(){
        return imagens;
    }
    
    public Imagem getPrimeiraImagem(){
        return imagens.getFirst();
    }
    
    public Imagem getImagem(int imagemId) throws HospedagemException{
        Imagem imagem = null;
        for (Imagem img : imagens) {
            if (img.getId() == imagemId)
                imagem = img;
        }
        
        if (imagem == null)
            throw new HospedagemException("A imagem não foi encontrada");
        
        return imagem;
    }
    
    public void adicionarImagem(Imagem imagem) throws HospedagemException{
        if (imagens.contains(imagem))
            throw new HospedagemException("A imagem já foi adicionada");
        
        imagens.add(imagem);
    }
    
    public void removerImagem(Imagem imagem){
        imagens.remove(imagem);
    }
    
    public List<Disponibilidade> getDisponibilidades(){
        return disponibilidades;
    }
    
    public void adicionarDisponibilidade(Disponibilidade disponibilidade) throws HospedagemException{
        if (disponibilidades.contains(disponibilidade))
            throw new HospedagemException("A Disponibilidade já foi adicionada");
        
        disponibilidades.add(disponibilidade);
    }
    
    public void removerDisponibilidade(Disponibilidade disponibilidade){
        disponibilidades.remove(disponibilidade);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        Hospedagem that = (Hospedagem) obj;
        
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
