# Evaluación Java - Nisum

# Explicación de proyecto

	El ejercicio consta de crear un Api Rest full para la creación de usuarios
	Se esta exponiendo en modo de prueba los servicios de creación, obtener, listar y eliminar usuarios.

# Pre-Requisitos

* Tener instalada jdk 1.8 - Validar con:

	java --version
 
* Tener configurado Maven en tu variable de entorno - validar con:

	mvn --version

# Consideraciones

*	No necesita un script de base de datos, ya que se usando flyway de sprint para crear e insertar datos para validar el usuario y contraseña al momento de generar el token, esta configuración la pueden encontrar en la carpeta de recursos en la ruta: 

	/resources/db/migration
	
*	Como parte del entragble se esta entregando la configuración del postman para las pruebas de lo desarrollado 

*	Finalmente como el proyecto cuenta con JWT, para invocar cualquier servicio primero debe generar el token, se encuentra en la configuración postman entregada, para probar este api de generación de token en formal local seria con el siguiente comando curl:

	curl --location --request POST 'localhost:9090/api/token' \
	--header 'Content-Type: application/json' \
	--header 'Cookie: JSESSIONID=7BA1C85DCFB7EA5BB08DD22F650C2A10' \
	--data-raw '{
    "username": "prueba",
    "password": "java"
	}'

	
# Descarga, Compilación y Ejecución del microservicio 

*	Descargar las fuentes del GitHub de la ruta:

	https://github.com/avillanueva198x/ms-app-nisum.git

*	En la raiz del proyecto (../ms-app-nisum/) ejecutar: 
	
	mvn clean install

*	Ingresar a la carpeta "../ms-app-nisum/target" y dentro ejecutar: 
	
	ava -jar -Dspring.profiles.active=local ms-app-nisum.jar
	
*	Ingresar a un navegador y validar que el servicio esta activo en la ruta: 
	
	http://localhost:9090/swagger-ui.html#/
	
	