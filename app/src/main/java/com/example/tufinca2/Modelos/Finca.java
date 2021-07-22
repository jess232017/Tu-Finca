package com.example.tufinca2.Modelos;

public class Finca {
    private String Id;
    private String Nombre;
    private String Abreviatura;
    private String Proposito;
    private String Area1;
    private String Area2;
    private String Medida;
    private String MedidaL;
    private String MedidaP;
    private String Image;

    public Finca(String id, String nombre, String abreviatura, String proposito, String area1, String area2, String medida, String medidaL, String medidaP, String image) {
        Id = id;
        Nombre = nombre;
        Abreviatura = abreviatura;
        Proposito = proposito;
        Area1 = area1;
        Area2 = area2;
        Medida = medida;
        MedidaL = medidaL;
        MedidaP = medidaP;
        Image = image;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAbreviatura() {
        return Abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        Abreviatura = abreviatura;
    }

    public String getProposito() {
        return Proposito;
    }

    public void setProposito(String proposito) {
        Proposito = proposito;
    }

    public String getArea1() {
        return Area1;
    }

    public void setArea1(String area1) {
        Area1 = area1;
    }

    public String getArea2() {
        return Area2;
    }

    public void setArea2(String area2) {
        Area2 = area2;
    }

    public String getMedida() {
        return Medida;
    }

    public void setMedida(String medida) {
        Medida = medida;
    }

    public String getMedidaL() {
        return MedidaL;
    }

    public void setMedidaL(String medidaL) {
        MedidaL = medidaL;
    }

    public String getMedidaP() {
        return MedidaP;
    }

    public void setMedidaP(String medidaP) {
        MedidaP = medidaP;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
