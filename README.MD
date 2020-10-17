- Beginner Kotlin Microservice to talk to spotify and get the trackinfo
- At the moment there is no exception handeling available
- At the moment there is no unit test to test the service at build time

- run application and then hit URI http://server:port/track/trackId [please update application.properties if you are running application as gradle bootrun]


Build/run-
- build as gradle task without updating application.properties
- java -jar -Dspotify.clientid=yourclientid -Dspotify.clientsecret=yourclientsecret microservice-{{version}}-snapshot.jar



-Container -

- docker build . -t spotifytrackservice
- docker run -p 80:8080/tcp --env SPOTIFY_CLIENTID={spotify_client_id} --env SPOTIFY_CLIENTSECRET={spotify_client_secret} spotifytrackservice 


-Container publish to docker hub -

- docker tag spotifytrackservice:latest {your_repo_name}/spotifytrackservice:latest
- docker push {your_repo_name}/spotifytrackservice:latest (make sure you are loggedin to docker hub using docker login)


-Pull from docker hub-
- docker pull ashishawasthi10/spotifytrackservice:latest
- docker run -p 80:8080/tcp --env SPOTIFY_CLIENTID={spotify_client_id} --env SPOTIFY_CLIENTSECRET=${spotify_client_secret} ashishawasthi10/spotifytrackservice
- hit url http://localhost/track/{{trackid}} to get the expected response



TODO:
- Exception handeling
- Unit test
- add docker process as part of gradle (though springboot has builtin support for this but somehow it was getting stuck at 83%)