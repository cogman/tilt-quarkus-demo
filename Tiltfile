# -*- mode: Python -*-
load('ext://helm_resource', 'helm_resource', 'helm_repo')
load('ext://helm_remote', 'helm_remote')

dbPassword = 'burnt-toast-1234'

helm_remote(
  'postgresql',
  repo_url='https://charts.bitnami.com/bitnami',
  set=["auth.database=tilt","auth.postgresPassword=" + dbPassword]
)

docker_build(
          'localhost:5000/tilt-quarkus-demo',
            '.',
              dockerfile='./src/main/docker/Dockerfile.tilt',
                live_update=[sync('./src/main/kotlin', '/app/src/main/kotlin'), sync('./src/test/kotlin', '/app/src/test/kotlin')  ],
                  entrypoint = './gradlew quarkusDev --no-daemon')

yaml = helm('src/main/helm/tilt-quarkus-demo')
k8s_yaml(yaml)
k8s_resource('tilt-quarkus-demo', 
  port_forwards=['5005:5005', '8080:8080'], 
  links=[
    link('http://localhost:8080/q/dev-ui', 'Dev UI'),
    link('http://localhost:8080/q/swagger-ui', 'Swagger'),
  ],
  labels=['demo'],
  resource_deps=['postgresql']
)

docker_build(
          'localhost:5000/tilt-quarkus-test',
            '.',
              dockerfile='./src/main/docker/Dockerfile.tilt',
              entrypoint = './gradlew --no-daemon -Dquarkus.http.test-host=tilt-quarkus-demo-srv -Dquarkus.http.test-port=80 quarkusIntTest')

k8s_yaml('src/main/kubernetes/testJob.yaml')
k8s_resource('integration-test',
  resource_deps=['tilt-quarkus-demo'],
  trigger_mode=TRIGGER_MODE_MANUAL,
  labels=['test'])