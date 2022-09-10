package edu.udea.main.model;

public class ObjetoRespuesta {
    private String mensajeRespuesta;
    private Object object;

    public ObjetoRespuesta(String mensajeRespuesta, Object object) {
        this.mensajeRespuesta = mensajeRespuesta;
        this.object = object;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
