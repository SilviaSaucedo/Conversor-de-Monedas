package com.conversor;

import com.google.gson.Gson;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            APIconsulta consulta = new APIconsulta();
            String json = consulta.obtenerDatos();

            Gson gson = new Gson();
            APIrespuesta datos = gson.fromJson(json, APIrespuesta.class);

            Map<String, Double> tasas = datos.getConversion_rates();

            Conversor conversor = new Conversor();

            int opcion = 0;

            while (opcion != 11) {

                System.out.println("""
                        ***********************
                        Conversor de Monedas
                        
                        1.- Dolar(USD) -> Pesos(MXN)
                        2.- Pesos(MXN) -> Dolar(USD)
                        3.- Dolar(USD) -> Euros(EUR)
                        4.- Euros(EUR) -> Dolar(USD)
                        5.- Dolar(USD) -> Pesos(ARS)
                        6.- Pesos(ARS) -> Dolar(USD)
                        7.- Pesos(MXN) -> Euros(EUR)
                        8.- Euros(EUR) -> Pesos(MXN)
                        9.- Pesos(MXN) -> Wones(KRW)
                        10.-Wones(KRW) -> Pesos(MXN)
                        11 Salir
                        
                        ***********************
                        """);

                opcion = scanner.nextInt();

                if (opcion == 11) break;

                System.out.println("Ingrese el monto:");
                double monto = scanner.nextDouble();

                double resultado = 0;

                switch (opcion) {

                    case 1 -> resultado = conversor.convertir(monto, tasas.get("MXN"));
                    case 2 -> resultado = monto / tasas.get("MXN");

                    case 3 -> resultado = conversor.convertir(monto, tasas.get("EUR"));
                    case 4 -> resultado = monto / tasas.get("EUR");

                    case 5 -> resultado = conversor.convertir(monto, tasas.get("ARS"));
                    case 6 -> resultado = monto / tasas.get("ARS");

                    case 7 -> resultado = (monto / tasas.get("MXN")) * tasas.get("EUR");
                    case 8 -> resultado = (monto / tasas.get("EUR")) * tasas.get("MXN");

                    case 9 -> resultado = (monto / tasas.get("MXN")) * tasas.get("KRW");
                    case 10 -> resultado = (monto / tasas.get("KRW")) * tasas.get("MXN");

                    default -> System.out.println("Opción inválida");
                }

                System.out.println("Resultado: " + resultado);
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
