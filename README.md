# Control-2-TBD
Control 2 TBD Grupo 1

Intrucciones de ejecucion:

Base de datos:
Mediante psql generar la base de datos con el archivo dbCreate.sql, para poblar la
base de datos, utilizar directamente el frontend de la aplicacion, pues pueden producirse
errores al crear nuevas instancias desde frontend si se pobla primero con el archivo loadData.sql,
especificamente con los id autoincrementales.

En caso de querer realizarse pruebas mediante postman tambien se puede poblar con
el archivo loadData.sql.

Backend:
Mediante la IDE Intellij IDEA ejecutar el backend (carpeta demo).
Para lo anterior asegurarse de cambiar el puerto de la base de datos de ser necesario.
El puerto por defecto es el 5433.

Frontend:
Abrir la carpeta front-grupo1-control2 con Visual Studio Code u otra IDE.
En la linea de comandos realizar ejecutar "npm install" para instalar dependencias.
Ejecutar el Frontend con el comando "npm run dev".

Consideraciones de trabajo:
Si bien para backend se trabajo de manera seccionada el codigo en commits de diferentes integrantes,
para Frontend se trabajo en su mayoria de manera cooperativa con la extension "Live Share" de Visual Studio Code,
que nos permite trabajar como equipo en una sola maquina.