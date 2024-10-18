# PARKLINE

>Documentacion tecnica del aplicativo web

## Descripción

PARKLINE es una solución inteligente para la gestión de estacionamientos que permite a 
los usuarios reservar espacios de manera eficiente. Facilita la administración del 
aparcamiento mediante la automatización de la asignación de plazas, agilizando el 
proceso de búsqueda de espacios disponibles. Además, incluye herramientas de monitoreo 
en tiempo real para mejorar la seguridad y el control del flujo vehicular.

## Características

- Sistema de reservas de estacionamientos en tiempo real
- Gestión automática de espacios disponibles
- Monitoreo en tiempo real de vehículos
- Panel de control administrativo para la gestión de usuarios y espacios
- Notificaciones de reserva y estado de espacios

### Requisitos 📋

Lista de software y hardware, incluyendo versiones, que se recomienda para instalar y 
ejecutar este proyecto:

| Recurso                    | Requerimiento                |
|----------------------------|------------------------------|
| **PC**                     | Al menos 8 GB de RAM         |
| **Sistema Operativo**      | Windows 10 o superior        |
| **Framework**              | Spring Boot                  |
| **Base de Datos**          | SQL Server                   |
| **IDE**                    | NetBeans                     |
| **JDK**                    | Java Development Kit 17      |
| **Gestor de Dependencias** | Maven                        |

### Instalación 🔧

Guía paso a paso sobre cómo configurar el entorno de desarrollo e instalar todas las 
dependencias.

```bash
paso 1: Clona el repositorio
git clone https://github.com/PrincipeCarrascoAugusto/parkline.git

 paso 2: Entra en el directorio del proyecto
 cd parkline

 paso 3: Compila el proyecto usando Maven
 mvn clean install

 paso 4: Configura el archivo application.properties para conectar con tu base de datos       SQL Server

paso 5: Ejecuta la aplicación Spring Boot
mvn spring-boot:run
```

## Ejecutando las Pruebas ⚙️

Para ejecutar las pruebas del sistema, usa el siguiente comando en el directorio del 
proyecto. Las pruebas verificarán la correcta integración y funcionalidad del sistema 
de reservas y la asignación de espacios.

```
Ejecuta las pruebas unitarias:

mvn test
```

## Construido Con 🛠️

Tecnologías usadas para construir el proyecto:

- Java - Lenguaje principal
- Spring Boot - Framework para backend
- Thymeleaf - Motor de plantillas para el frontend
- SQL Server - Base de datos relacional
- Maven - Gestión de dependencias
- Bootstrap - Framework CSS para el diseño del frontend

## Conclusiones

PARKLINE es una solución que optimiza la gestión de aparcamientos, brindando una experiencia 
de usuario eficiente al reservar y administrar espacios. La integración de tecnologías modernas 
como Spring Boot, SQL Server y Bootstrap asegura un sistema robusto, escalable y fácil de mantener.
