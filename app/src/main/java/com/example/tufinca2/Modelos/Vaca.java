package com.example.tufinca2.Modelos;

public class Vaca {
    private String Id;
    private String Nombre;
    private String NumChip;
    private String Genero;
    private String Desarrollo;
    private String Produce;
    private String Peso;
    private String FechaBirth;
    private String Image;

    public Vaca(String id, String nombre, String numChip, String genero, String desarrollo, String produce, String peso, String fechaBirth,
                String image) {
        Id = id;
        Nombre = nombre;
        NumChip = numChip;
        Genero = genero;
        Desarrollo = desarrollo;
        Produce = produce;
        Peso = peso;
        FechaBirth = fechaBirth;
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

    public String getNumChip() {
        return NumChip;
    }

    public void setNumChip(String numChip) {
        NumChip = numChip;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getDesarrollo() {
        return Desarrollo;
    }

    public void setDesarrollo(String desarrollo) {
        Desarrollo = desarrollo;
    }

    public String getProduce() {
        return Produce;
    }

    public void setProduce(String produce) {
        Produce = produce;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getFechaBirth() {
        return FechaBirth;
    }

    public void setFechaBirth(String fechaBirth) {
        FechaBirth = fechaBirth;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
