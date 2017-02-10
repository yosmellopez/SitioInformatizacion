package utiles;

public class DireccionServidor {

    private String direccion;

    private String direccionArchivos;

    public DireccionServidor() {
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccionArchivos(String direccionArchivos) {
        this.direccionArchivos = System.getProperty("catalina.base") + "/webapps/Postgrado/";
    }

    public String getDireccionArchivos() {
        direccionArchivos = System.getProperty("catalina.base");
        return direccionArchivos + "/webapps/Postgrado/";
    }

//    public void setDireccionArchivos(String direccionArchivos) {
//        this.direccionArchivos = direccionArchivos;
//    }
//
//    public String getDireccionArchivos() {
//        return direccionArchivos;
//    }
}
