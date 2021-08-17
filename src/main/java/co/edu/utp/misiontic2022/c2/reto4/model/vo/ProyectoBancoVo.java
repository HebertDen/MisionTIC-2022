package co.edu.utp.misiontic2022.c2.reto4.model.vo;

public class ProyectoBancoVo {
    private Integer id;
    private String constructora;
    private String ciudad;
    private String clasificacion;
    private Integer estrato;
    private String lider;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getConstructora() {
        return constructora;
    }
    public void setConstructora(String constructora) {
        this.constructora = constructora;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getClasificacion() {
        return clasificacion;
    }
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    public Integer getEstrato() {
        return estrato;
    }
    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }
    public String getLider() {
        return lider;
    }
    public void setLider(String lider) {
        this.lider = lider;
    }

    @Override
    public String toString() {
        return "ProyectoBancoVo [ciudad=" + ciudad + ", clasificacion=" + clasificacion + ", constructora="
                + constructora + ", estrato=" + estrato + ", id=" + id + ", lider=" + lider + "]";
    }
}