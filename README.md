# Detector De ADN Mutante

##  Descripción
Programa para detección de mutantes a partir de cadenas de ADN que se consultan a través de las Apis REST que expone el mismo. El programa cuenta con una BD en memoria, H2, donde se van registrando todas las consultas y se utiliza para generar las estadísticas.

##  Instalación
Para la instalación se tiene que importar como un proyecto Maven

Paso1:

![ScreenShot](https://github.com/juanChacon1983/imagenes/blob/master/paso1.png)

Paso2:

![ScreenShot](https://github.com/juanChacon1983/imagenes/blob/master/paso2.png)

##  Apis REST:

El programa expone tres servicios:

https://detector-de-mutantes.appspot.com/mutant/

Servicio POST que recibe una cadena de ADN con el siguiente formato donde las únicas letras posibles que la pueden formar son G,C,T,C
{"dna":["GACCCG", "CAGGGC", "TTATGT", "AGAGGT", "TACTGT", "ACCCTG" ]}
Devuelve 200-Ok en caso de que se trate de un mutante y 403-Forbidden si se trata de un humano.

https://detector-de-mutantes.appspot.com/stats/

Servicio GET que devuelve las estadísticas de las consultas realizadas: cantidad de ADN humano, cantidad de ADN mutante y la proporción del mismo. Devuelve 200-Ok

https://detector-de-mutantes.appspot.com/clear/

Servicio DELETE que hace limpieza de los registros guardados en la BD. Devuelve 200-Ok

##   Test  

El proyecto cuenta con una carpeta que se llama “informeDeCoberturaDeTes”. En la misma abriendo el archivo index.html se puede ver in informe de la cobertura de los test.

También  cuenta con una carpeta que se llama “JMetrTest” donde se cuenta el proyecto con el que se realizaron las pruebas de estrés de los servicios.

Para ejecutar los test de estrés hay que bajarse en el Jmeter desde

https://jmeter.apache.org/download_jmeter.cgi

abrir el archivo DetectorDeMutantes.jmx que se encuentra dentro de la carpeta

![ScreenShot](https://github.com/juanChacon1983/imagenes/blob/master/paso1JM.PNG)

y en luego cargar el archivo de con para realizar las pruebas que también se encuentra dentro de la carpeta, prueba.csv.

![ScreenShot](https://github.com/juanChacon1983/imagenes/blob/master/paso2JM.PNG)


 
