# -*- mode: Python -*-
load('ext://helm_resource', 'helm_resource', 'helm_repo')

docker_build(
          'localhost:5000/tilt-quarkus-demo',
            '.',
              dockerfile='./src/main/docker/Dockerfile.tilt',
                live_update=[sync('./src/main/kotlin', '/app/src/main/kotlin'), sync('./src/test/kotlin', '/app/src/test/kotlin')  ],
                  entrypoint = './gradlew quarkusDev --no-daemon')

yaml = helm('src/main/helm/tilt-quarkus-demo')
k8s_yaml(yaml)
k8s_resource('tilt-quarkus-demo')

