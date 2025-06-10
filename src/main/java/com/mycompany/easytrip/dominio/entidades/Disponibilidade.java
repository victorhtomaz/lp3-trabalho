package com.mycompany.easytrip.dominio.entidades;
    
import com.mycompany.easytrip.dominio.enums.StatusDisponibilidade;
import com.mycompany.easytrip.dominio.excecoes.DisponibilidadeException;
import com.mycompany.easytrip.dominio.interfaces.Validacao;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "Disponibilidade")
public class Disponibilidade implements Validacao{  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @Column(name = "Hospedagem_Id")
    private int hospedagemId;
    
    @Column(name = "Data")
    private LocalDate data;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusDisponibilidade status;
    
    private static final DateTimeFormatter DATA_FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("pt-BR"));
    
    protected Disponibilidade(){}
    
    public Disponibilidade(int hospedagemId, LocalDate data, StatusDisponibilidade status) throws DisponibilidadeException{
        this.hospedagemId = hospedagemId;
        this.data = data;
        this.status = status;
        
        validar();
    }
    
    @Override
    public void validar() throws DisponibilidadeException{
        if (hospedagemId < 0)
            throw new DisponibilidadeException("O id da hospedagem é inválido");
        
        if(data.isBefore(LocalDate.now()))
            throw new DisponibilidadeException("A data de disponibilidade precisa ser futura");
    }
    
    public LocalDate getData(){
        return data;
    }
    
    public StatusDisponibilidade getStatus(){
        return status;
    }
    
    @Override
    public String toString() {
        return "Data: " + data.format(DATA_FORMATADOR) + ", Status: " + status.name();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        Disponibilidade that = (Disponibilidade) obj;
        
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
