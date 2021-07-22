package com.example.tufinca2.Sqlite;

import java.util.UUID;

public class Constants {

    interface ColumnasUsuario {
        String ID = "id";
        String NOMBRE = "nombre";
        String CORREO = "correo";
        String PASS = "pass";
    }

    interface ColumnasFinca {
        String Id = "id";
        String Nombre = "nombre";
        String Abreviatura = "abreviatura";
        String Proposito = "proposito";
        String Area1 = "area1";
        String Area2 = "area2";
        String Medida = "Medida";
        String MedidaL = "medidaL";
        String MedidaP = "medidaP";
        String Image = "Image";
    }

    interface ColumnasVacas {
        String Id = "id";
        String Nombre = "nombre";
        String NumChip = "numchip";
        String Genero = "genero";
        String Desarrollo = "desarrollo";
        String Produce = "produce";
        String Peso = "peso";
        String FechaBirth = "fechabirth";
        String Image = "Image";
    }


    public static class Usuario implements ColumnasUsuario{
        public static String generarIdNumero() {
            return "USER-1234";
        }
    }

    public static class Finca implements ColumnasFinca{
        public static String generarIdFinca() {
            return "FINCA-1234";
        }
    }

    public static class Vaca implements ColumnasVacas{
        public static String generarIdVaca() {
            return "Vac-" + UUID.randomUUID().toString();
        }
    }

    private Constants() {
    }
}