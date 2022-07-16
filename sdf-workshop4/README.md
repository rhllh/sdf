# sdf-workshop4

A server-client application that serves a random cookie from a file containing a list of different cookies. Created with Maven on VSCode.

On server, run 
```
mvn compile exec:java -Dexec.mainClass="vttp2022.day4.workshop.Server" -Dexec.args="12345 cookie_file.text"
```

On client, run
```
mvn compile exec:java -Dexec.mainClass="vttp2022.day4.workshop.Client" -Dexec.args="localhost:12345"
```
# Workshop 4 - Cookies
