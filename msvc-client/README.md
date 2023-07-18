# Microservicios

El sistema a desarrollar está planteado en el contexto del negocio bancario que a medida que
se va avanzando en los proyectos, se irá ampliando en base a este mismo proyecto

## Modelo de arquitectura (Model C4)

### Nivel 3 - Diagrama de componentes

![Nivel 3 - Diagrama de componentes!](../img/modelc4_3_1.svg)


### Microservices - Client

Contiene los microservicios relacionados con el mantenimiento (CRUD) de clientes:

| Method | Path              | Description        |
|--------|-------------------|--------------------|
| GET    | /clients/{id}  | Listar Cliente     | 
| GET    | /clients | Obtener Cliente    | 
| POST   | /clients | Crear Cliente | 
| PUT    | /clients/{id}  | Actualizar Cliente | 
| DELETE | /clients/{id}  | Eliminar Cliente |
