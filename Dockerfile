FROM java:8
ADD build/libs/ansatt-adapter-bluegarden-*.jar /data/app.jar
CMD ["java", "-jar", "/data/app.jar"]