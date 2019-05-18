# TP-BigData

Este proyecto se divide en 2 partes:
 
 * Data Fetching: Con un script de python y la API de twitter se obtienen las ultimas menciones del hashtag #Tesla y se guardan en un CSV.
 * Data Analysis: Del CSV generado en la primera parte con el analisis de sentimiento de google en la nube y hadoop se obtiene el sentimiendo de cada tweet, luego se puede obtener el promedio de todos los sentimientos.
Nota: Se debio realizar el promedio en otro proyecto de Hadoop ya que por la arquitectura de esta no se pueden realizar de un solo Map y Reduce el resultado esperado: que consiste en un CSV con todos los sentimientos y un promedio de todos los sentimientos, ya que para el promedio todos los tweets deben tener una sola key, lo cual imposibilita guardar cada tweet por separado, o al menos, personalmente, escapa de los conocimientos adquiridos en clase.


## Para Data Fetching
  
### Requisitos

* Python 3 con Pipenv pandas, csv y tweepy.

### Instrucciones

* Ejecutar el scrapper.py dentro de la carpeta Twitter

```
pipenv run python scrapper.py
```

## Para Data Analysis

### Requisitos

* Java SDK y maven con las librerias Google Cloud, Hadoop y OpenCSV
* Hadoop
* IntelliJ

### Instrucciones para instalar Hadoop en Windows
https://exitcondition.com/install-hadoop-windows/

### Instrucciones para utilizar Google Cloud

Crear una cuenta Gratis en Google Cloud y seguir los pasos para configurar en Java
https://cloud.google.com/natural-language/docs/quickstart-client-libraries#client-libraries-install-java

### Instrucciones para ejecutar el proyecto de Sentiment Analysis

* Copiar el output generado con el Data Fetching a la carpeta TwitterSentiment/input.
* Configurar IntelliJ para ejecutar con Hadoop: https://mrchief2015.wordpress.com/2015/02/09/compiling-and-debugging-hadoop-applications-with-intellij-idea-for-windows/
* Ejecutar

Nota: Debido a la cantidad de lineas y las llamadas constantes a la API de Google el proceso tarda mas de una hora. Se recomienda testear con 5 lineas, de todas formas el resutado de todas las partes estan subidadas en este proyecto.

### Instrucciones para ejecutar el proyecto de Promedio de Sentimientos

* Copiar el output generado del paso anterior y pegarlo como tesla.csv a la carpeta TwitterAverage/input
* Configurar este proyecto como en el paso anterior para correr con Hadoop
* Ejecutar

## Licencia

Este proyecto esta bajo la licencia GNU General Public License v3.0 - lee el archivo [LICENSE.md](LICENSE.md) para mas detalles
