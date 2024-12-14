# Proyecto Alimentos

Un proyecto creado con la finalidad de gestionar de forma eficiente el almacen de alimentos.

Un posible diseño sería:


* Diseño de la Base de Datos

** Tablas Principales

1. Alimentos
- id: INT, PRIMARY KEY, autoincremento
- nombre: VARCHAR, nombre del alimento
- tipo: VARCHAR, perecedero o no perecedero
- estado: VARCHAR, abierto o cerrado
- fecha_caducidad: DATE, fecha en la que el alimento caduca

2. Ubicaciones
- id: INT, PRIMARY KEY, autoincremento
- descripcion: VARCHAR, descripción detallada de la ubicación (ej., "balda superior en la alacena")
- tipo_ubicacion: VARCHAR, tipo general de la ubicación (alacena, nevera, congelador)
- capacidad: INT, capacidad máxima de almacenamiento en términos de cantidad de productos

3. Existencias
- id: INT, PRIMARY KEY, autoincremento
- alimento_id: INT, FOREIGN KEY referenciada a Alimentos.id
- ubicacion_id: INT, FOREIGN KEY referenciada a Ubicaciones.id
- cantidad: INT, cantidad actual del alimento en la ubicación especificada
- fecha_entrada: DATE, fecha en que el alimento fue colocado en esa ubicación

** Relaciones

-Alimentos a Existencias: Relación uno a muchos desde `Alimentos` a `Existencias` (un alimento puede estar en múltiples ubicaciones en diferentes cantidades).
-Ubicaciones a Existencias: Relación uno a muchos desde `Ubicaciones` a `Existencias` (una ubicación puede contener varios alimentos).

** Consideraciones Adicionales

- Manejo de la Fecha de Caducidad: La tabla `Alimentos` incluye una fecha de caducidad que debe ser considerada en las operaciones del sistema para alertar a los usuarios sobre productos que necesitan ser consumidos o trasladados (por ejemplo, al congelador para prolongar su vida útil).
- Rotación de Productos: La tabla `Existencias` debe incluir `fecha_entrada` para implementar políticas de rotación como FIFO ("primero en entrar, primero en salir") y gestionar eficazmente los alimentos en cada ubicación.

*** Diagrama de la Base de Datos

Un diagrama de entidad-relación (ER) podría ser útil para visualizar las relaciones y estructuras de esta base de datos, ayudando a los desarrolladores a comprender cómo interactúan las entidades y cómo deberían implementarse las funcionalidades en el backend.

Este diseño proporciona una base sólida para tu sistema de gestión de inventarios de alimentos, asegurando que todas las características necesarias estén cubiertas y que la base de datos pueda soportar eficientemente la lógica de negocio requerida.