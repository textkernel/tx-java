# sovren-java
The official Java SDK for the Sovren v10 API for resume/CV and job parsing, searching, and matching. Supports Java 1.8+.

## Installation

### Requirements
- Java 1.8 or later

### Gradle Users
Add this dependency to your project's build file:
```
implementation "com.sovren:sovren-java:0.1.1
```

### Maven Users
Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>com.sovren</groupId>
  <artifactId>sovren-java</artifactId>
  <version>0.1.1</version>
</dependency>
```

### Others
You'll need to manually install the following JARs:
- The Sovren JAR from https://repo1.maven.org/maven2/com/sovren/sovren-java/0.1.1/sovren-java-0.1.1.jar
- [Google Gson][gson_url] from https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.6/gson-2.8.6.jar.
- [Square OkHttp][okhttp_url] from https://repo1.maven.org/maven2/com/squareup/okhttp3/okhttp/4.9.0/okhttp-4.9.0.jar


## Documentation
You can also refer to the [online Javadoc][javadoc_url]


[javadoc_url]: https://sovren.github.io/sovren-java/
[gson_url]: https://github.com/google/gson
[okhttp_url]: https://github.com/square/okhttp