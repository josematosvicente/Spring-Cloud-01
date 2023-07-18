
# Microservicios

El sistema a desarrollar está planteado en el contexto del negocio bancario que a medida que
se va avanzando en los proyectos, se irá ampliando en base a este mismo proyecto

## Modelo de arquitectura (Model C4)

### Nivel 1 - Diagrama de contexto del sistema

![Nivel 1 - Diagrama de contexto del sistema!](/img/modelc4_1.svg)

### Nivel 2 - Diagrama del contenedor

![Nivel 2 - Diagrama del contenedor!](/img/modelc4_2.svg)


### Nivel 3 - Diagrama de componentes

![Nivel 3 - Diagrama de componentes!](/img/modelc4_3.svg)


### Microservices - Client

Contiene los microservicios relacionados con el mantenimiento (CRUD) de clientes:

| Method | Path              | Description        |
|--------|-------------------|--------------------|
| GET    | /clients/{id}  | Listar Cliente     | 
| GET    | /clients | Obtener Cliente    | 
| POST   | /clients | Crear Cliente | 
| PUT    | /clients/{id}  | Actualizar Cliente | 
| DELETE | /clients/{id}  | Eliminar Cliente |

### Microservices - Administrative

Contiene los microservicios relacionados con las cuentas y productos asociados al cliente:


| Method    | Path              | Description                                      |
|-----------|-------------------|--------------------------------------------------|
| GET       | /administrative/account | Obtener Cuentas de todos los Clientes            |
| POST      | /administrative/account | Registro de Cuentas                              | 
| GET       | /administrative/account/{id} | Obtener Cuentas del Cliente                      |
| PUT       | /administrative/account/{id} | Actualización de Cuenta del Cliente              | 
| DELETE    | /administrative/account/{id} | Elimina una cuenta del Cliente                   |
| GET       | /administrative/credit | Obtener todos los Créditos de todos los Clientes | 
| POST      | /administrative/credit | Registro de Créditos                             | 
| GET       | /administrative/credit/{id} | Obtener Créditos del Cliente                     |
| PUT       | /administrative/credit/{id} | Actualización de Créditos del Cliente           |
| DELETE    | /administrative/credit/{id} | Elimina el Créditos del Cliente                  |


### Microservices - Financial

Contiene los microservicios relacionados con los movimientos financieros que realiza el cliente en sus diferentes productos bancarios:

| Method | Path              | Description                          |
|--------|-------------------|--------------------------------------|
| POST    | /finantial/account/{id}/movement| Abono / Retiro                       | 
| GET    | /finantial/account/{id}/balance | Saldo de cuenta                      | 
| POST   | /finantial/credit/{id}/movement | Consumo / Pago                       | 
| GET    | /finantial/credit/{id}/balance  | Saldo de cuenta                      |
| GET   | /finantial/account/{id}/movements | Listar movimiento bancarios cuentas  | 
| GET    | /finantial/credit/{id}/movements | Listar movimiento bancarios créditos |

### Microservices - Product

Contiene API relaciona con el mantenimiento (CRUD) de productos:

| Method | Path           | Description         |
|--------|----------------|---------------------|
| GET    | /products/{id} | Listar Producto      | 
| GET    | /products       | Obtener Producto     | 
| POST   | /products       | Crear Producto       | 
| PUT    | /products/{id}  | Actualizar Producto | 
| DELETE | /products/{id}  | Eliminar Producto    |


