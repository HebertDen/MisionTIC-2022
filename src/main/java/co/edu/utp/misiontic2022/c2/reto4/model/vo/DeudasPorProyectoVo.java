package co.edu.utp.misiontic2022.c2.reto4.model.vo;

public class DeudasPorProyectoVo {
    
    private Integer id;
    private Double valor;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public Double getValor(){
        return valor;
    }
    public void setValor(Double valor){
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return "DeudasPorProyectoVo [id=" + id + ", valor=" + valor + "]";
    }
}