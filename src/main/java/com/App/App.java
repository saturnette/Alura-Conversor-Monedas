package com.App;

import java.util.Scanner;

import com.conversordemoneda.ExchangeRateService;
import com.conversordemoneda.Moneda;
import java.util.InputMismatchException;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            try {
                mostrarMenu();
                int opcion = obtenerOpcionUsuario();

                if (opcion == 0) {
                    continuar = false;
                    System.out.println("¡Gracias por usar el conversor de monedas!");
                    continue;
                }

                // Moneda origen
                System.out.println("\nSELECCIONE MONEDA ORIGEN");
                Moneda monedaOrigen = seleccionarMoneda();

                // Moneda destino
                System.out.println("\nSELECCIONE MONEDA DESTINO");
                Moneda monedaDestino = seleccionarMoneda();

                // Cantidad a convertir
                System.out.println("\nINGRESE CANTIDAD A CONVERTIR");
                System.out.print("$ Ingrese el monto: ");
                double cantidad = scanner.nextDouble();

                // Realizar conversión
                System.out.println("\n>>> Calculando...");
                String[] tasas = ExchangeRateService.obtenerTasasDeCambio(monedaOrigen, monedaDestino);
                double tasaOrigen = Double.parseDouble(tasas[0]);
                double tasaDestino = Double.parseDouble(tasas[1]);

                // Calcular conversión a través de USD como moneda puente
                double cantidadEnUSD = cantidad / tasaOrigen;
                double resultado = cantidadEnUSD * tasaDestino;

                // Resultado
                System.out.println("\nRESULTADO");
                System.out.printf("$ %,.2f %s \n-> %,.2f %s%n",
                        cantidad,
                        monedaOrigen.getDescripcion(),
                        resultado,
                        monedaDestino.getDescripcion());

                // Preguntar si desea continuar
                System.out.println("\n¿OTRA CONVERSIÓN?");
                System.out.print(">> ¿Desea realizar otra conversión? (s/n): ");
                continuar = scanner.next().toLowerCase().startsWith("s");

            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nCONVERSOR DE MONEDAS");
        System.out.println("1. Realizar conversión");
        System.out.println("0. Salir");
        System.out.print("-> Seleccione una opción: ");
    }

    private static int obtenerOpcionUsuario() {
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    private static Moneda seleccionarMoneda() {
        while (true) {
            try {
                Moneda[] monedas = Moneda.values();
                for (int i = 0; i < monedas.length; i++) {
                    System.out.printf("%d. %s (%s)%n", i + 1, monedas[i].getDescripcion(), monedas[i].name());
                }
                System.out.print("Seleccione una opción: ");
                int seleccion = scanner.nextInt();
                scanner.nextLine();

                if (seleccion < 1 || seleccion > monedas.length) {
                    System.out.println("\nError: Opción inválida. Por favor seleccione un número entre 1 y " + monedas.length);
                    continue;
                }

                return monedas[seleccion - 1];
            } catch (InputMismatchException e) {
                System.out.println("\nError: Por favor ingrese un número válido");
                scanner.nextLine();
            }
        }
    }
}