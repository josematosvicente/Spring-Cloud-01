# Microservicios

El sistema a desarrollar está planteado en el contexto del negocio bancario que a medida que
se va avanzando en los proyectos, se irá ampliando en base a este mismo proyecto

## Modelo de arquitectura (Model C4)

### Nivel 3 - Diagrama de componentes

![Nivel 3 - Diagrama de componentes!](../img/modelc4_3_4.svg)


### Microservices - Product

Contiene los microservicios relacionados con el mantenimiento (CRUD) de Productos:

| Method | Path              | Description        |
|--------|-------------------|--------------------|
| GET    | /products/{id}  | Listar Producto     | 
| GET    | /products | Obtener Producto    | 
| POST   | /products | Crear Producto | 
| PUT    | /products/{id}  | Actualizar Producto | 
| DELETE | /products/{id}  | Eliminar Producto |
