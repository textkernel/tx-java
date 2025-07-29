# tx-java
![GitHub](https://img.shields.io/github/license/textkernel/tx-java?color=0575aa)
![Maven Central](https://img.shields.io/maven-central/v/com.textkernel/tx-java?color=0575aa)
[![build](https://github.com/textkernel/tx-java/actions/workflows/build.yml/badge.svg)](https://github.com/textkernel/tx-java/actions/workflows/build.yml)

The official Java SDK for the Textkernel Tx v10 API for resume/CV and job parsing, searching, and matching. Supports Java 1.8+.

## Installation

### Requirements
- Java 1.8 or later

### Gradle Users
Add this dependency to your project's build file:
```
implementation "com.textkernel:tx-java:3.0.2"
```

### Maven Users
Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>com.textkernel</groupId>
  <artifactId>tx-java</artifactId>
  <version>3.0.2</version>
</dependency>
```

### Others
You'll need to manually install the following JARs:
- The Textkernel Tx JAR from https://repo1.maven.org/maven2/com/textkernel/tx-java/3.0.2/tx-java-3.0.2.jar
- [Google Gson][gson_url] from https://repo1.maven.org/maven2/com/google/code/gson/gson/2.9.0/gson-2.9.0.jar
- [Square OkHttp][okhttp_url] from https://repo1.maven.org/maven2/com/squareup/okhttp3/okhttp/4.12.0/okhttp-4.12.0.jar


## Documentation
For the full API documentation, information about best practices, FAQs, etc. check out our [docs site][api-docs].

You can also refer to the [online Javadoc][javadoc_url]

## Examples
For full code examples, see [here][examples].

## Usage

### Creating a `TxClient`
This is the object that you will use to perform API calls. You create it with your account credentials and the `TxClient` makes the raw API calls for you. These credentials can be found in the [Tx Console][portal]. Be sure to select the correct `DataCenter` for your account.
```java
TxClientSettings settings = new TxClientSettings();
settings.AccountId = "12345678";
settings.ServiceKey = "abcdefghijklmnopqrstuvwxyz";
settings.DataCenter = DataCenter.US;
TxClient client = new TxClient(settings);
```

For self-hosted customers, you can create a `DataCenter` object with your custom URL using the constructor provided on that class.

### Using the various `TxClient` services
The `TxClient` has the following services available:
- `parser()`
- `geocoder()`
- `formatter()`
- `skillsIntelligence()`
- `searchMatchV1()`
- `searchMatchV2()`

Each service exposes certain API functionality via its methods. For example, to parse a resume you would do something like:
```java
TxClient client;//created or injected however
ParseResumeResponse parseResponse = client.parser().parseResume(...);
```

For the complete list of methods on each service and their method signatures, check out our [online Javadoc][javadoc_url].

### Handling errors and the `TxException`
Every call to any of the methods in the `TxClient` should be wrapped in a `try/catch` block. Any 4xx/5xx level errors will cause a `TxException` to be thrown. Sometimes these are a normal and expected part of the Tx API. For example, if you have a website where users upload resumes, sometimes a user will upload a scanned image as their resume. Textkernel does not process these, and will return a `422 Unprocessable Entity` response which will throw a `TxException`. You should handle any `TxException` in a way that makes sense in your application.

Additionally, there are `TxUsableResumeException` and `TxUsableJobException` which are thrown when some error/issue occurs in the API, but the response still contains a usable resume/job. For example, if you are geocoding while parsing and there is a geocoding error (which happens after parsing is done), the `ParsedResume` might still be usable in your application.

[javadoc_url]: https://textkernel.github.io/tx-java/
[gson_url]: https://github.com/google/gson
[okhttp_url]: https://github.com/square/okhttp
[examples]: https://github.com/textkernel/tx-java/tree/master/examples
[portal]: https://cloud.textkernel.com/tx/console
[api-docs]: https://developer.textkernel.com/tx-platform/v10/overview/