FROM adoptopenjdk/openjdk8
COPY ms-app-nisum.jar /home/ms-app-nisum.jar
COPY keystore.p12 /keystore.p12
CMD ["java", "-jar", "-Dspring.profiles.active=local", "/home/ms-app-nisum.jar"]
