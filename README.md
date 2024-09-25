# API RESTFUL USER MANAGEMENET 

Desarrolle una aplicación que exponga una API RESTful de creación de usuarios. 
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de error. 
Todos los mensajes deben seguir el formato: 
 {"mensaje": "mensaje de error"} 

###  Registro 
* Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más un listado de objetos "teléfono", respetando el siguiente formato: 
     ```sh
     { 
        "name": "Juan Rodriguez", 
        "email": "juan@rodriguez.org" , 
        "password": "hunter2", 
        "phones": [ 
            { 
                "number": "1234567", 
                "citycode": "1",	 
                "contrycode": "57" 
            } 
        ] 
    }
     ```
* Responder el código de status HTTP adecuado 
* En caso de éxito, retorne el usuario y los siguientes campos: 
    - id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería más deseable un UUID) 
    - created: fecha de creación del usuario 
    - modified: fecha de la última actualización de usuario 
    - last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha de creación) 
    - token: token de acceso de la API (puede ser UUID o JWT) 
    - isactive: Indica si el usuario sigue habilitado dentro del sistema. 

* Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya registrado"
* El correo debe seguir una expresión regular para validar que formato sea el correcto. (aaaaaaa@dominio.cl) 
* La clave debe seguir una expresión regular para validar que formato sea el correcto. (Una Mayuscula, letras minúsculas, y dos numeros) 
* El token deberá ser persistido junto con el usuario 

### Requisitos 
* Plazo: 2 días, si tienes algún inconveniente con el tiempo comunicate con nosotros
* Banco de datos en memoria, como HSQLDB o H2. 
* Proceso de build vía Gradle o Maven.
* Persistencia con JPA. Ejemplo: EclipseLink, Hibernate u OpenJPA
* Framework Spring. 
* Java 8+ 
* Entrega en un repositorio público (github o bitbucket) con el código fuente y script de creación de BD. 
* Entrega diagrama de la solución. 

### Requisitos opcionales 
* JWT cómo token 
* Pruebas de unidad 
* Git / Git Bash
### Solución

#### 1 Ejecución del Proyecto

* Requisitos Previos

    - Java Development Kit 17
    - Apache Maven 3.8.8
    - Configuración de Variables de Entorno (JAVA_HOME , MAVEN_HOME)
	- Verificar en consola la version de java con la instrucción java -version y la versión de maven mvn -v
	
* Paso 1

    - Clonar el proyecto con git o descargarlo de repositorio: https://github.com/klintfox/api-user-managemenet.git
    - Utilizando Git
	
    ```sh
        git clone https://github.com/klintfox/api-user-managemenet.git
    ```
	
* Pasos 2 : Ejecución del proyecto con Maven por consola (windows)

    - Abrir el prompt de comandos (consola) y ubicarse en la raiz del proyecto clonado "..\api-user-managemenet"
	- Ejecutar la siguiente instrucción:
	
    ```sh
        mvn clean install -DskipTests -DskipSpringDoc
     ```
	 
	- clean: Este comando elimina todos los archivos generados por las construcciones anteriores
	- install: Este comando compila el proyecto, ejecuta las pruebas y, si todo es exitoso, empaqueta el código y lo instala en el repositorio local de Maven.
	- Si queremos ejecutar las pruebas unitarias desde consola ejecutamos el siguiente comando
	
	```sh
        mvn test
    ```	 
	
    - Sí el build es exitoso ejecutamos el siguiente comando para correr el proyecto
	
	```sh
        mvn spring-boot:run
    ```
* Pasos 2.1 : Ejecución del proyecto con Spring Tool Suite - STS

	- Importamos el proyecto clonado en STS
	- Hacemos click derecho al proyecto opción "Run" y luego seleccionamos "maven install"
	- Si el Build es exitoso del paso previo, hacemos click derecho en el proyecto opción "Run" y luego selecciomos "Spring boot app"

* Pruebas con Swagger - OpenAPI

	- Abrimos el navegador y escribimos http://localhost:8090/swagger-ui/index.html
	- En el navegador aparecera una ventana solicitanos escribir el usuario y la contraseña:
	
	```sh
		usuario : admin
		contraseña: admin
	```
	
	- Hacemos click en aceptar para entrar a la página de Swagger - OpenApi localmente
	- Hacemos click en el método Post de user-controller y se desplegará la ventana con el botón "Try it out"
	- Al hacer click en el botón "try it our" se habilita el BodyRequest para poder editar el cuerpo de la petición el cual es pre cargado con un ejemplo
	- Si queremos enviar la petición al API presionamos el botón "Execute" y verificamos la salida desplazandonos a la sección "Server Response"
	- Para ver el paso a paso en imágenes abrir los archivos ubicados en la carpeta "api-user-management\src\main\resources\swagger"

* Pruebas con Postman
    - En el proyecto en la ruta api-user-management\src\main\resources\postman se encuentra el archivo "SmartJob.postman_collection.json"
	- Abrimos Postman y en la parte superior izquieda en la sección "My Workspace"  hay un botón llamado "Import" hacemos click y seleccionamos el archivo "SmartJob.postman_collection.json"
	- Una vez importado desplegamos en postman la coleccion "SmartJob" y encontraremos el request "UserRegister"
	- Para probarlo primero iremos a la pestaña "Authorization" y seleccionamos en la sección "Auth Type" la opción "Basic Auth"
	- Al seleccionar esta opción en la parte derecha nos pedira ingresa el usuario y contraseña para realizar la petición
	
	```sh
		usuario : admin
		contraseña: admin
	```
	
	- Una vez ingresado el usuario y la contraseña hacemos click en la pestaña "Body" seleccionamos "raw" e ingresamos en formato json la petición
	
    ```sh
    { 
        "name": "Carlos Rodriguez", 
        "email": "Carlos@rodriguez.cl" , 
        "password": "Program26", 
        "phones": [ 
            { 
                "number": "987654321", 
                "citycode": "11",	 
                "countrycode": "57" 
            },
                { 
                "number": "1234567887", 
                "citycode": "11",	 
                "countrycode": "57" 
            }  
        ] 
    }	
     ```
	
	- Para enviar la petición al API presionamos el botón azul "Send"
	- Para ver el paso a paso en imágenes abrir los archivos ubicados en la carpeta "api-user-management\src\main\resources\postman"

#### 2 Esquema BD - H2 
   
	- En la carpeta del proyecto "api-user-management\src\main\resources\db" se deja el archivo "script.sql" con el esquema de las tablas.
	
	create table app_user ( 
		id  varchar(255)  primary key not null,
		name varchar(50) not null, 
		email varchar(255) not null, 
		password varchar(20)  not null, 
		created timestamp null, 
		modified timestamp null,
		last_login timestamp null,
		is_active null,
		token varchar(300) not null
	);

	create table user_phones ( 
		id INT primary key not null,
		number  int null, 
		city_code int null, 
		country_code int null, 
		fk_user varchar(300),
		foreign key (fk_user ) references user(id)
	);

#### 3 Diagrama de Arquitectura de API	

	![Diagrama Arquitectura API](https://github.com/klintfox/api-user-managemenet/tree/main/src/main/resources/diagrama/DiagramaApi.PNG?raw=true)


	- El archivo "Diagrama API.PNG" está ubicado en "api-user-management\src\main\resources\diagrama"

#### 4 Coverage de código con Jacoco
	- Abrir el prompt de comandos (consola) y nos en la raiz del proyecto clonado "\api-user-managemenet"
	- Ejecutar la siguiente instrucción:
	
	```sh
		mvn jacoco:report
	```	
	
	- Si el build es exitoso ubicarse en la siguiente ubicación en el proyecto "\api-user-management\target\site\jacoco"
	- Abrir con el navegador el archivo index.html para ver el coverage del código
	- En la carpeta del proyecto "api-user-management\src\main\resources\coverage" se deja una captura del coverage
	
#### 5 Dependencias utilizadas (pom.xml)
	1. spring-boot-starter-data-jpa
	Proporciona soporte para acceder a bases de datos utilizando Spring Data JPA. Incluye todas las configuraciones necesarias para trabajar con JPA (Java Persistence API) y Hibernate.
	
	2. spring-boot-starter-security
	Añade funcionalidades de seguridad a tu aplicación, como autenticación y autorización. Facilita la protección de tus endpoints y la gestión de usuarios.
	
	3. spring-boot-starter-web
	Proporciona las funcionalidades necesarias para crear aplicaciones web, incluyendo soporte para RESTful APIs, controladores y servidores embebidos (como Tomcat).
	
	4. spring-boot-starter-validation
	Añade soporte para la validación de datos utilizando la API de validación de Java (javax.validation). Facilita la validación de parámetros de entrada en tus controladores.
	
	5. spring-boot-devtools
	Incluye herramientas para mejorar el desarrollo, como la recarga automática de aplicaciones. Se activa en tiempo de desarrollo y es opcional.
	
	6. spring-cloud-starter
	Proporciona funcionalidades de Spring Cloud, que facilitan el desarrollo de aplicaciones distribuidas. Incluye herramientas para la configuración, descubrimiento de servicios, entre otros.
	
	7. h2
	Un motor de base de datos en memoria que es ligero y fácil de usar para el desarrollo y pruebas. Se configura para usarse en tiempo de ejecución.
	
	8. jjwt
	Proporciona una biblioteca para crear y validar JSON Web Tokens (JWT). Es útil para la implementación de autenticación basada en tokens.
	
	9. jaxb-api
	Proporciona las API necesarias para trabajar con Java Architecture for XML Binding (JAXB), permitiendo convertir entre objetos Java y representaciones XML.
	
	10. jaxb-runtime
	Proporciona la implementación de tiempo de ejecución de JAXB. Es necesario para la serialización y deserialización de objetos XML.
	
	11. lombok
	Una biblioteca que reduce el código boilerplate en Java. Permite generar automáticamente métodos como getters, setters, y constructores a través de anotaciones.
	
	12. therapi-runtime-javadoc
	Proporciona una biblioteca para generar documentación de API en tiempo de ejecución a partir de anotaciones en el código.
	
	13. springdoc-openapi-starter-webmvc-ui
	Facilita la integración de OpenAPI (Swagger) en aplicaciones Spring, generando documentación de API de manera automática y proporcionando una interfaz de usuario para interactuar con ella.
	
	14. spring-boot-starter-test
	Incluye dependencias para realizar pruebas en aplicaciones Spring, como JUnit, AssertJ y Spring Test, facilitando la escritura y ejecución de pruebas.
	
	15. mockito-core
	Una biblioteca para realizar pruebas unitarias en Java que permite crear objetos simulados (mocks) y verificar su comportamiento.
	
	16. spring-security-test
	Proporciona soporte para pruebas relacionadas con la seguridad en aplicaciones Spring, facilitando la simulación de la seguridad durante las pruebas.
	
	17. junit-jupiter
	Proporciona la plataforma de pruebas para JUnit 5, permitiendo la escritura de pruebas unitarias utilizando nuevas características como anotaciones y extensiones.
	
	18. org.jacoco.agent
	Una biblioteca para medir la cobertura de código durante las pruebas. Permite analizar qué partes del código fueron ejecutadas durante las pruebas.
	
#### 6 Versiones utilizadas

	- <java.version> 17 </java.version>
	- <spring.boot.version> 3.3.4 </spring.boot.version>
	- <spring-cloud.version> 2023.0.2 </spring-cloud.version>
