# Microservicios

El sistema a desarrollar está planteado en el contexto del negocio bancario que a medida que
se va avanzando en los proyectos, se irá ampliando en base a este mismo proyecto

## Modelo de arquitectura (Model C4)

### Nivel 3 - Diagrama de componentes

![Nivel 3 - Diagrama de componentes!](../img/modelc4_3_3.svg)


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
