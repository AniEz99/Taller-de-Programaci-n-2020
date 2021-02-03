# Leeme

En el presente texto se describe cómo navegar el sitio construido durante la primera parte de la tarea 2 de Taller de Programación. 

El documento se divide en dos secciones, una que detalla la parte estática del proyecto, y otra que detalla la parte dinámica.

## Vistas estáticas

Para acceder a la **página inicial** del proyecto web (estático) se entra a ```WebContent/html/inicio.html```.

Las barra lateral y superior se encuentran en todas las páginas.

Desde la **barra superior** se puede realizar una búsqueda general de paquetes, usuarios, espectáculos, etc presionando el botón Buscar. También se puede acceder desde el menú desplegable de usuario a varios casos de uso como alta de espectáculos, paquete y función, como también cerrar sesión y acceder a la info del usuario. Estas operaciones varían según el tipo de usuario, pero ahora, a efectos de mostrar las distintas posibilidades, siempre se puede acceder a todas ellas.

En la **barra lateral** se puede acceder rápidamente a una búsqueda de espectáculos discriminando por una categoría o plataforma determinada. También se puede acceder a la lista de paquetes.

En la **página de inicio** se muestra una bienvenida y algunos espectáculos y paquetes relevantes.

Para **realizar un registro** a una función se debe seleccionar un espectáculo, ahí se despliega la información del mismo y sus funciones. Luego se selecciona una función y con el botón de registro en la página de la funciones se puede acceder a cualquiera de las 3 modalidades de registro.

Para **comprar un paquete** basta con solo seleccionarlo a través de una búsqueda o listar paquetes y presionar comprar.

Para **modificar los datos de usuario**, el usuario puede acceder a su información personal utilizando el menú desplegable ubicado en la esquina superior derecha de la pantalla. Luego ahí basta con presionar el lápiz al lado del nombre del usuario. A efecto de mostrar cómo sería la vista para distintos tipos de usuario (artista o espectador) se agrega un botón switch en la barra lateral izquierda que permite cambiar el usuario que se muestra. 

Notar que para acceder al caso de uso **Alta de usuario**, se debe utilizar la versión dinámica del sitio, ya que la pantalla de login es la que contiene el link hacia la de registro.


## Casos de uso relacionados a la sesión

Para acceder al **login** y **logout** (hay que configurar el servidor de tomcat en eclipse y acceder a ```localhost:8080/servidor-web/Home```. Ahí se accede a una página de inicio para un visitante no logueado. 

Por ahora existe muy poca navegabilidad desde esta página en jsp con las estáticas (html), por eso es mejor utilizarla solo para probar estos casos de uso.

Para **ingresar como usuario** se aprieta el botón Ingresar que redirige al visitante a una página de login. Desde ahí el usuario podrá loguearse o registrarse. 

Notar que todavía **el registro no se comunica con la lógica del servidor central**. Si se loguea con éxito, el sitio lo redireccionará a la misma página de inicio en la cual se comenzó, pero ahora en la esquina superior derecha verá la información del usuario que inició sesión. 

Para **cerrar la sesión**, se debe hacer click sobre el nombre del usuario, lo que desplegará un menú. En este menú se clickea la opción Cerrar sesión.

Para **registrar un usuario**, desde la pantalla de login se hace click en Registrate aqui, debajo del botón Ingresa.


