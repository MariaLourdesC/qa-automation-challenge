# QA Automation Challenge

Este repositorio contiene la solución de una prueba técnica de automatización QA. El proyecto está dividido en dos ejercicios independientes: una prueba end-to-end de compra en Demoblaze y una prueba de servicios REST utilizando Swagger Petstore.

Para cada ejercicio utilicé un framework diferente, de acuerdo con el tipo de prueba y las herramientas sugeridas en el enunciado.

## Tecnologías utilizadas

- Java 17
- Maven
- Serenity BDD y Screenplay
- Selenium WebDriver
- Karate DSL
- JUnit
- Google Chrome

## Estructura del proyecto

```text
qa-automation-challenge/
├── web-tests/
├── api-tests/
├── .gitignore
├── README.md
└── conclusiones.txt
```

Ambos ejercicios tienen su propio archivo `pom.xml`, por lo que se pueden compilar y ejecutar de manera independiente.

## 1. Automatización web: Demoblaze

**Aplicación:** https://www.demoblaze.com/

Para el ejercicio web utilicé Serenity BDD con el patrón Screenplay. Organicé la automatización separando las interacciones, los elementos de la interfaz y las validaciones, con el objetivo de mantener el código legible y facilitar su mantenimiento.

La prueba automatiza el siguiente flujo de compra:

1. Ingresar a Demoblaze.
2. Seleccionar y agregar dos productos al carrito.
3. Verificar que ambos productos estén registrados.
4. Abrir el formulario de compra y completar los datos solicitados.
5. Confirmar la compra y validar el mensaje de confirmación.

### Ejecución

Desde la raíz del repositorio:

```bash
cd web-tests
mvn clean verify -Dtest=AddProductsToCartTest
```

La prueba requiere tener Google Chrome instalado.

### Reporte

Al finalizar la ejecución, Serenity genera un reporte HTML en:

```text
web-tests/target/site/serenity/index.html
```

## 2. Automatización API: Swagger Petstore

**API:** https://petstore.swagger.io/

Para el ejercicio de API utilicé Karate DSL. La prueba cubre el ciclo de creación, consulta y actualización de una mascota.

El escenario automatizado realiza las siguientes operaciones:

1. Registrar una mascota mediante una solicitud POST.
2. Consultarla por su identificador mediante GET.
3. Actualizar su nombre y cambiar su estado a `sold` mediante PUT.
4. Consultar las mascotas por estado y verificar la información actualizada.

Las validaciones comprueban los códigos de respuesta y los datos relevantes de cada operación.

### Ejecución

Desde la raíz del repositorio:

```bash
cd api-tests
mvn clean test -Dtest=PetRunner
```

### Reporte

Karate genera un reporte HTML en:

```text
api-tests/target/karate-reports/karate-summary.html
```

## Requisitos previos

Antes de ejecutar las pruebas, es necesario contar con:

- JDK 17
- Apache Maven
- Google Chrome, para el ejercicio web
- Conexión a Internet para acceder a Demoblaze y Swagger Petstore

Se pueden verificar las versiones de Java y Maven con:

```bash
java -version
mvn -version
```

## Consideraciones

Los ejercicios se ejecutan sobre aplicaciones públicas de demostración. Por ese motivo, su disponibilidad y comportamiento pueden variar por factores externos al proyecto.

Los reportes HTML se generan durante la ejecución y no se incluyen en el control de versiones. Para obtenerlos, se deben ejecutar las pruebas siguiendo las instrucciones anteriores.