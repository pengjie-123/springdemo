FROM tomcat:9

ARG inputDirectory=$CATALINA_HOME/webapps/springdemo

COPY target/springdemo.war $CATALINA_HOME/webapps/springdemo.war

RUN mkdir $CATALINA_HOME/webapps/springdemo/; cd $CATALINA_HOME/webapps/springdemo; unzip ../springdemo.war; rm ../springdemo.war

EXPOSE 8080
EXPOSE 8009

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]