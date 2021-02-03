#!/bin/bash -x

# Cambiar en maquina virtual
ruta_apache="/home/vagrant/Escritorio/apache-tomcat-9.0.39"

ruta_apache+="/webapps"

rm -r compilados
mkdir compilados

echo "Compilado de servidor central"
# echo $ruta_apache --> imprime variable

cd servidor-central && mvn package -DskipTests dependency:copy-dependencies
mv target/servidor-central-1.0.jar ../compilados
mv target/dependency ../compilados
cd ../compilados && mkdir image-storage && cd ..
echo "Prendida de servidor central"

chmod u+x start_central.sh
./start_central.sh &
# sleep 10

echo "Compilado de swing"

echo "WSimport"
# cd estacion-trabajo/src/main/java && rm -r webservices
# wsimport -keep http://localhost:8090/publicador?wsdl
# cd ../../..

cd estacion-trabajo

mvn package -DskipTests dependency:copy-dependencies
mv target/estacion-trabajo-1.0.jar ../compilados
mv -v target/dependency/* ../compilados/dependency

echo "Prendida de estacion de trabajo"
cd ..

#chmod u+x start_estacion.sh
#./start_estacion.sh &

echo "Compilado de servidor web"

echo "WSimport"
# cd servidor-web/src && rm -r webservices
# wsimport -keep http://localhost:8090/publicador?wsdl
cd servidor-web && mvn package

/bin/cp target/servidor-web.war $ruta_apache

cd $ruta_apache 
rm -r servidor-web
cd ../bin
./shutdown.sh
sleep 10
./startup.sh 
#/bin/cp

