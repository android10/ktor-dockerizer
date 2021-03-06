# Gradle Ktor Dockerizer

Gradle Plugin that facilitates docker integration with [Ktor](https://ktor.io/).

# Usage

 - TODO
 - Make sure your current user can run docker without sudo.

# Local Development

## Run Unit Tests

 - `./gradlew runUnitTests`            --> Runs all the Plugin Unit Tests.

## Sample Execution

From the root folder you can execute:

 - `./gradlew clean build`             --> Builds the project.
 - `./gradlew clean test`              --> Runs all tests.
 - `./gradlew runKtor`                 --> Runs Ktor.
 - `./gradlew generateKtorJar`         --> Generates a Jar from the Ktor Project.
 - `./gradlew buildKtorDockerImage`    --> Builds Ktor Sample Docker Image.
 - `./gradlew runKtorDocker`           --> Runs Ktor in a Docker Container.
 - `./gradlew runKtorDockerDetached`   --> Runs Ktor in a Docker Container Detached.
 - `./gradlew stopKtorDockerContainer` --> Stops Docker Container running Ktor.

## Test Manually Ktor Sample

From the root folder you can execute:

 - `./gradlew buildKtorDockerImage`
 - `./gradlew runKtorDockerDetached`
 - `curl -i -X GET http://localhost:8080`

You should see:

```
HTTP/1.1 200 OK
Content-Length: 12
Content-Type: text/plain; charset=UTF-8

Hello World!
```

# TODOs

- [ ] Documentation.
- [ ] Support Docker Compose.
- [ ] Automate Test Execution.
- [ ] Automate Publishing.

# License

    Copyright 2021 Fernando Cejas

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


![https://fernandocejas.com](https://github.com/android10/Sample-Data/blob/master/android10/android10_logo_big.png)

<a href="https://www.buymeacoffee.com/android10" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>
