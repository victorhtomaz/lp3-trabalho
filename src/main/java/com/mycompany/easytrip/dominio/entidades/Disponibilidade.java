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
    
    @Column(name = "Data")
    private LocalDate data;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusDisponibilidade status;
    
    public static final DateTimeFormatter DATA_FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("pt-BR"));
    
    protected Disponibilidade(){}
    
    public Disponibilidade(LocalDate data) throws DisponibilidadeException{
        this.data = data;
        this.status = StatusDisponibilidade.DISPONIVEL;
        
        validar();
    }
    
    @Override
    public void validar() throws DisponibilidadeException{
        if(data.isBefore(LocalDate.now()))
            throw new DisponibilidadeException("A data de disponibilidade precisa ser futura");
    }
    
    public int getId(){
        return id;
    }
    
    public LocalDate getData(){
        return data;
    }
    
    public String getDataFormatada(){
        return data.format(DATA_FORMATADOR);
    }
    
    public StatusDisponibilidade getStatus(){
        return status;
    }
    
    private void setStatus(StatusDisponibilidade status){
        this.status = status;
    }
    
    public void setStatusParaReservado(){
        setStatus(StatusDisponibilidade.RESERVADO);
    }
    
    public void setStatusParaDisponivel(){
        setStatus(StatusDisponibilidade.DISPONIVEL);
    }
    
    @Override
    public String toString() {
        return data.format(DATA_FORMATADOR);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        
        Disponibilidade that = (Disponibilidade) obj;
        
        return this.id == that.id || this.data.equals(that.data);
    }
    
    public boolean equals(LocalDate data){
        return this.data != null && this.data.equals(data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
