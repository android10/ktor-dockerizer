# Ktor Example with Docker Plugin

 - Please refer to the [Ktor Documentation](https://ktor.io/) to understand how this framework works. 
 - I have also created a more [complete sample specific to Ktor](https://fernandocejas.com) if you are interested in it.

## Gradle Tasks

 - `./gradlew clean`
 - `./gradlew build`
 - `./gradlew run`

## Run and setup this Project
 
 - Refer to the main `README.md` file of this repository to see how the Docker Plugin works.
 - Application configuration can be found in the `application.conf` file. 
 - You can run by executing `./gradlew run` inside this directory.

The app will run on `http://0.0.0.0:4000/` and you will see a similar output:

```bash 
2021-03-14 14:04:34.098 [main] TRACE Application - {
    # application.conf @ file:/home/fernando/dev/src/gradle-dockerizer/sample-project/ktor/build/resources/main/application.conf: 5
    "application" : {
        ...
    },
    # application.conf @ file:/home/fernando/dev/src/gradle-dockerizer/sample-project/ktor/build/resources/main/application.conf: 2
    "deployment" : {
        # application.conf @ file:/home/fernando/dev/src/gradle-dockerizer/sample-project/ktor/build/resources/main/application.conf: 3
        "port" : 4000
    },
    # Content hidden
    "security" : "***"
}

2021-03-14 14:04:34.124 [main] INFO  Application - Autoreload is disabled because the development mode is off.
2021-03-14 14:04:34.287 [main] INFO  Application - Responding at http://0.0.0.0:4000
```

## License

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


![http://www.fernandocejas.com](https://github.com/android10/Sample-Data/blob/master/android10/android10_logo_big.png)

<a href="https://www.buymeacoffee.com/android10" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>
