# Conversor de Monedas

Este proyecto es una aplicación de consola en Java que permite convertir cantidades entre diferentes monedas utilizando tasas de cambio obtenidas de un servicio externo.

## Características

- Selección de moneda de origen y destino.
- Conversión de cantidades entre las monedas seleccionadas.
- Interfaz de usuario en consola.
- Manejo de excepciones para entradas inválidas.

## Requisitos

- Java 8 o superior
- Maven (opcional, para gestión de dependencias)

## Instalación

1. Clona el repositorio:
    ```bash
    git clone https://github.com/saturnette/Alura-Conversor-Monedas.git
    cd conversor-de-monedas
    ```

2. Compila el proyecto:
    ```bash
    javac -d bin src/main/java/com/App/App.java
    ```

## Ejecución

1. Ejecuta la aplicación:
    ```bash
    java -cp bin com.App.App
    ```

## Uso

1. Al iniciar la aplicación, se mostrará un menú con las opciones disponibles.
2. Selecciona la opción `1` para realizar una conversión.
3. Selecciona la moneda de origen y la moneda de destino.
4. Ingresa la cantidad a convertir.
5. La aplicación mostrará el resultado de la conversión.
6. Puedes realizar otra conversión o salir de la aplicación seleccionando la opción correspondiente.