# Gestión Financiera de Clientes - ISW-FINANCIAL-APP

Aplicación web para el registro y gestión de información financiera de clientes y sus tarjetas de crédito.

## Herramientas utilizadas

### Lenguajes
- Java 17

### IDEs
- IntelliJ IDEA 2024.3.3

### Frameworks y tecnologías
- Spring Boot 3.5.15 (Backend)
- Jakarta EE 10 / JSF con Primefaces 14 (Frontend)
- JPA / Hibernate (Persistencia)
- Maven (Gestión de dependencias)

### Base de datos
- MySQL 8.8

### Herramientas de calidad y CI/CD
- SonarCloud (Análisis de calidad de código)
- GitHub Actions (Integración continua)

### Control de versiones
- Git / GitHub

## Estructura del proyecto

isw-financial-app/
├── backend/          # Proyecto Spring Boot (API REST)
├── frontend/         # Proyecto Jakarta EE con JSF y Primefaces
└── database/         # Scripts de base de datos

## Script de base de datos

```sql
CREATE DATABASE IF NOT EXISTS gestion_financiera;
USE gestion_financiera;

CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    numero_identificacion VARCHAR(20) NOT NULL UNIQUE,
    correo_electronico VARCHAR(100) NOT NULL,
    nombre_completo VARCHAR(100) NOT NULL
);

CREATE TABLE tarjeta_credito (
    id_tarjeta INT AUTO_INCREMENT PRIMARY KEY,
    numero_tarjeta VARCHAR(16) NOT NULL UNIQUE,
    fecha_vencimiento VARCHAR(7) NOT NULL,
    franquicia VARCHAR(20) NOT NULL,
    estado VARCHAR(10) NOT NULL DEFAULT 'ACTIVO',
    cupo_total DECIMAL(15,2) NOT NULL,
    cupo_disponible DECIMAL(15,2) NOT NULL,
    cupo_utilizado DECIMAL(15,2) NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);
```

## Instrucciones de ejecución

### Backend
1. Configurar MySQL con la base de datos `gestion_financiera`
2. Actualizar `application.properties` con las credenciales de MySQL
3. Ejecutar con `./mvnw spring-boot:run` dentro de la carpeta `backend/`
4. El backend corre en `http://localhost:8081`

### Frontend
1. Tener WildFly 35 instalado y configurado
2. Compilar con `./mvnw clean package` dentro de `frontend/gestion-financiera-front/`
3. Desplegar el `.war` en WildFly
4. El frontend corre en `http://localhost:8080/gestion-financiera-front-1.0-SNAPSHOT`