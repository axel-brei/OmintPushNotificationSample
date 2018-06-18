package com.example.macbook.pushnotificationsample;

import android.support.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;

import java.io.Serializable;

public abstract class OmintNotification implements Serializable{
    private String token;
    private Integer notifID;
    private String notifType;
    private Integer idTurno;
    private String centroMedico;
    private String fechaTurno;
    private String horaTurno;
    private String titulo;

    public OmintNotification() {
    }

    public static OmintNotification getInstance(@NonNull RemoteMessage message){
        OmintNotification omintNotification = new OmintNotification() {};
        omintNotification.token = message.getTo();
        omintNotification.notifID = Integer.parseInt(message.getData().get("IdNotificacion"));
        omintNotification.notifType = message.getData().get("TipoNotificacion");
        omintNotification.idTurno = Integer.parseInt(message.getData().get("IdTurno"));
        omintNotification.centroMedico = message.getData().get("CentroMedico");
        omintNotification.fechaTurno = message.getData().get("FechaTraslado");
        omintNotification.horaTurno = message.getData().get("HoraTraslado");
        omintNotification.titulo = message.getData().get("title");
        return omintNotification;
    }

    public String getToken() {
        return token;
    }

    public Integer getNotifID() {
        return notifID;
    }

    public String getNotifType() {
        return notifType;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public String getCentroMedico() {
        return centroMedico;
    }

    public String getFechaTurno() {
        return fechaTurno;
    }

    public String getHoraTurno() {
        return horaTurno;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return centroMedico + " / " + fechaTurno + " - " + horaTurno;
    }
}
