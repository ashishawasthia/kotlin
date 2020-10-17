FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD ./build/libs/microservice-0.0.1-SNAPSHOT.jar spotifytrackservice.jar
ENTRYPOINT ["java", "-Dspotify.clientid=${SPOTIFY_CLIENTID}", "-Dspotify.clientsecret=${SPOTIFY_CLIENTSECRET}", "-jar", "spotifytrackservice.jar"]