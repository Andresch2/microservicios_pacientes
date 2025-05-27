# Microservicio Pacientes

## Equipo de Desarrollo
**Grupo:** Pacientes

| Integrante | Rol |
|------------|-----|
| Nelson Andrés Chaves | Desarrollador |
| Diego Rosero | Desarrollador |
| Juan David Sánchez | Desarrollador |
| Jhon Fredy Morán | Desarrollador |

---

## Descripción General

El microservicio **Pacientes** es el núcleo del sistema de gestión hospitalaria, encargado de administrar toda la información relacionada con los pacientes. Proporciona funcionalidades completas de CRUD y características avanzadas para el análisis de actividad de pacientes.

### Funcionalidades Principales
- Gestión completa de pacientes (CRUD)
- Identificación de pacientes activos con citas próximas
- Análisis de pacientes frecuentes
- Consulta de actividad reciente (citas y consultas)
- Integración con microservicios de Citas y Consultas

---

## Tecnologías Utilizadas

| Componente | Tecnología |
|------------|------------|
| **Lenguaje** | Java 21 |
| **Framework** | Spring Boot |
| **Base de Datos** | MySQL |
| **Build** | Maven |
| **Comunicación** | FeignClient |
| **Despliegue** | Railway |

---

## API Endpoints

### Endpoints Básicos (CRUD)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/pacientes` | Obtener todos los pacientes |
| `GET` | `/pacientes/{id}` | Obtener paciente por ID |
| `POST` | `/pacientes` | Crear nuevo paciente |
| `PUT` | `/pacientes/{id}` | Actualizar paciente existente |
| `DELETE` | `/pacientes/{id}` | Eliminar paciente |

### Endpoints Avanzados

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/pacientes/activos` | Listar pacientes con citas próximas |
| `GET` | `/pacientes/frecuentes?min=N` | Pacientes con ≥ N citas/consultas |
| `GET` | `/pacientes/{id}/actividad` | Actividad reciente del paciente |

---

## Instalación y Configuración

### 1. Clonar el Repositorio
```bash
git clone https://github.com/Andresch2/microservicios_pacientes.git
cd microservicios_pacientes
```

### 2. Compilación

**Con Maven:**
```bash
mvn clean install
```

**Con Gradle:**
```bash
./gradlew build
```

### 3. Ejecución Local

```bash
# Con Maven
mvn spring-boot:run

# Con Gradle
./gradlew bootRun
```

La aplicación estará disponible en: `http://localhost:8080`

---

## Documentación de la API

### Entornos Disponibles

| Entorno | URL Base |
|---------|----------|
| **Local** | `http://localhost:8080` |
| **Producción** | `https://microserviciospacientes-production.up.railway.app` |

---

## Ejemplos de Uso

### Entorno Local

#### Obtener todos los pacientes
```bash
GET http://localhost:8080/pacientes
```

#### Obtener paciente específico
```bash
GET http://localhost:8080/pacientes/1
```

#### Crear nuevo paciente
```bash
POST http://localhost:8080/pacientes
Content-Type: application/json

{
  "nombre": "John",
  "apellido": "Moran",
  "cedula": 123456789,
  "email": "john@gmail.com",
  "telefono": "1234567890",
  "fecha_nacimiento": "1990-01-01",
  "activo": true
}
```

#### Actualizar paciente
```bash
PUT http://localhost:8080/pacientes/1
Content-Type: application/json

{
  "nombre": "John",
  "apellido": "Updated",
  "cedula": 123456789,
  "email": "john.updated@gmail.com",
  "telefono": "0987654321",
  "fecha_nacimiento": "1990-01-01",
  "activo": false
}
```

#### Consultas avanzadas
```bash
# Pacientes activos
GET http://localhost:8080/pacientes/activos

# Pacientes frecuentes (mínimo 5 cita)
GET http://localhost:8080/pacientes/frecuentes?min=5

# Actividad de un paciente
GET http://localhost:8080/pacientes/1/actividad
```

### Entorno Producción (Railway)

#### Operaciones básicas
```bash
# Listar pacientes
GET https://microserviciospacientes-production.up.railway.app/pacientes

# Obtener paciente por ID
GET https://microserviciospacientes-production.up.railway.app/pacientes/1

# Crear paciente
POST https://microserviciospacientes-production.up.railway.app/pacientes
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Sanchez",
  "cedula": 123456234,
  "email": "juan@gmail.com",
  "telefono": "123489303",
  "fecha_nacimiento": "2000-05-17",
  "activo": true
}
```

---

## Arquitectura del Sistema

### Estructura por Capas

```
┌─────────────────────────┐
│     Controllers         │  ← API REST Endpoints
│     (Presentation)      │
├─────────────────────────┤
│     Services            │  ← Lógica de Negocio
│     (Business Logic)    │
├─────────────────────────┤
│     Repositories        │  ← Acceso a Datos
│     (Data Access)       │
├─────────────────────────┤
│     External Clients    │  ← Integración con otros servicios
│     (Integration)       │
└─────────────────────────┘
```

### Integración con Otros Servicios

El microservicio se comunica con otros servicios mediante **FeignClient**:

```java
@FeignClient(name = "citas-service", url = "${citas.service.url}")
public interface CitasClient {
    @GetMapping("/citas/paciente/{id}/proximas")
    List<CitaDto> getProximasCitas(@PathVariable("id") Long pacienteId);
}
```

### Patrones Implementados

- **DTO/Mapper Pattern**: Separación entre entidades de dominio y objetos de transferencia
- **Repository Pattern**: Abstracción del acceso a datos
- **Service Layer Pattern**: Encapsulación de la lógica de negocio
- **Exception Handling**: Gestión centralizada de errores

---

## Despliegue en Railway

### Configuración Automática

1. **Importar repositorio** desde GitHub en Railway
2. **Configurar variables de entorno**:
   ```
   SPRING_DATASOURCE_URL=jdbc:mysql://host:port/database
   SPRING_DATASOURCE_USERNAME=usuario
   SPRING_DATASOURCE_PASSWORD=contraseña
   CITAS_SERVICE_URL=https://citas-service-url
   CONSULTAS_SERVICE_URL=https://consultas-service-url
   ```
3. **Añadir MySQL plugin** y enlazarlo al proyecto
4. **Deploy automático** con cada push a `main`

### URL de Producción
**Base URL:** `https://microserviciospacientes-production.up.railway.app/`

---

## Recursos Adicionales

- **Repositorio:** [GitHub](https://github.com/Andresch2/microservicios_pacientes.git)
- **Deploy:** [Railway Dashboard](https://railway.app)
- **Colección de Postman:** [Descargar colección Pacientes](https://raw.githubusercontent.com/Andresch2/coleccion_pacientes/53632be98cdc65f319351954e9caa91b18d6c3a3/Pacientes.postman_collection.json)


---

## Notas de Desarrollo

- **Java Version:** 21 LTS
- **Spring Boot Version:** Última versión estable
- **Base de Datos:** MySQL con charset UTF-8
- **Comunicación:** REST API con FeignClient