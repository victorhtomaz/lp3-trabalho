package com.mycompany.easytrip.dominio.entidades;

import com.mycompany.easytrip.dominio.enums.StatusReserva;
import com.mycompany.easytrip.dominio.excecoes.DominioException;
import com.mycompany.easytrip.dominio.excecoes.ReservaException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import com.mycompany.easytrip.dominio.objetosDeValor.Avaliacao;
import com.mycompany.easytrip.dominio.objetosDeValor.PrecoDiaria;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "Reserva")
public class Reserva implements Validacao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "Usuario_Id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "Hospedagem_Id")
    private Hospedagem hospedagem;
    
    @Column(name = "DataEntrada")
    private LocalDate dataEntrada;
    
    @Column(name = "DataSaida")
    private LocalDate dataSaida;
    
    @Column(name = "QuantidadeDeHospedes")
    private int quantidadeDeHospedes;
    
    @Column(name = "QuantidadeDeDias")
    private int quantidadeDeDias;
    
    @Embedded
    private PrecoDiaria precoDiaria;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusReserva status;
    
    @Embedded
    private Avaliacao avaliacao;
    
    public static final DateTimeFormatter DATA_FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("pt-BR"));
    
    protected Reserva(){}
    
    public Reserva(Usuario usuario, Hospedagem hospedagem,LocalDate dataEntrada, LocalDate dataSaida, int quantidadeHospedes, BigDecimal precoDiaria) throws DominioException{
        this.usuario = usuario;
        this.hospedagem = hospedagem;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.quantidadeDeHospedes = quantidadeHospedes;
        this.precoDiaria = new PrecoDiaria(precoDiaria);
        this.quantidadeDeDias = (int) (dataSaida.toEpochDay() - dataEntrada.toEpochDay());
        this.status = StatusReserva.PENDENTE;
        
        validar();
    }
    
    public int getId(){
        return id;
    }
    
    public String getDataEntradaFormatada(){
        return dataEntrada.format(DATA_FORMATADOR);
    }
    
    public LocalDate getDataEntrada(){
        return dataEntrada;
    }
    
    public String getDataSaidaFormatada(){
        return dataSaida.format(DATA_FORMATADOR);
    }
    
    public LocalDate getDataSaida(){
        return dataSaida;
    }
    
    public int getQuantidadeDeHospedes(){
        return quantidadeDeHospedes;
    }
    
    public PrecoDiaria getPrecoDiaria(){
        return precoDiaria;
    }
    
    public int getQuantidadeDeDias(){
        return quantidadeDeDias;
    }
    
    public StatusReserva getStatus(){
        return status;
    }
    
    public String getTituloHospedagem(){
        return hospedagem.getTitulo();
    }
    
    public String getEnderecoHospedagem(){
        return hospedagem.getEndereco().toString();
    }
    
    public Avaliacao getAvaliacao(){
        return avaliacao;
    }
    
    public void setAvaliacao(float nota) throws DominioException{
        avaliacao = new Avaliacao(nota);
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public Hospedagem getHospedagem(){
        return hospedagem;
    }
    
    private void setStatus(StatusReserva status){
        this.status = status;
    }
    
    public void setStatusParaCancelada(){
        setStatus(StatusReserva.CANCELADA);
    }
    
    public void setStatusParaConfirmada(){
        setStatus(StatusReserva.CONFIRMADA);
    }
    
    @Override
    public void validar() throws DominioException {
        if (usuario == null)
            throw new ReservaException("O usuario deve ser valido");
        
        if (hospedagem == null)
            throw new ReservaException("A hospedagem deve ser valido");
        
        if (dataEntrada.isEqual(dataSaida) || dataEntrada.isAfter(dataSaida))
            throw new ReservaException("A data de entrada deve ser anterior a data de saida");
        
        if (quantidadeDeDias < 0)
            throw new ReservaException("A quantidade de dias não pode ser menor que zero");
        
        if (quantidadeDeHospedes < 0)
            throw new ReservaException("Deve ter pelo menos um hospede na reserva");
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        Reserva that = (Reserva) obj;
        
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
