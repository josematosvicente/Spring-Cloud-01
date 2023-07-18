# Microservicios

El sistema a desarrollar está planteado en el contexto del negocio bancario que a medida que
se va avanzando en los proyectos, se irá ampliando en base a este mismo proyecto

## Modelo de arquitectura (Model C4)

### Nivel 3 - Diagrama de componentes

![Nivel 3 - Diagrama de componentes!](../img/modelc4_3_2.svg)


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

