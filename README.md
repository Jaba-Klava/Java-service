# Разработка и развертывание ИИ-приложения в Kubernetes
Контейнеризированное Java-приложение для анализа тональности текста, развёрнутое в локальном Kubernetes кластере с автоматическим масштабированием и мониторингом.

## Быстрый старт
### Предварительные требования
- Docker
- Minikube
- kubectl
- Helm
- Java 21
- Maven

### Установка и запуск
1. Клонировать репозиторий
```bash
git clone https://github.com/Jaba-Klava/Java-service.git
cd Java-service
```
2. Собрать приложение
```bash
mvn clean package -DskipTests
```
3. Запустить Minikube кластер
```bash
minikube start --cpus=2 --memory=3072mb --driver=docker
```
4. Собрать Docker-образ
```bash
eval $(minikube -p minikube docker-env)
docker build -t sentiment-service:latest .
```
5. Развернуть в Kubernetes
```bash
kubectl apply -f k8s/
```
6. Проверить статус
```bash
kubectl get all
```
## Структура проекта

```html
Java-service/
├── pom.xml                      # Maven конфигурация
├── Dockerfile                   # Многоступенчатая сборка Docker
├── src/main/java/sentiment/     # Исходный код приложения
├── src/main/resources/          # Конфигурационные файлы
└── k8s/                         # Kubernetes манифесты
    ├── java-sentiment-deployment.yaml  # Deployment (3 реплики)
    ├── java-sentiment-hpa.yaml         # Horizontal Pod Autoscaler
    ├── sentiment-ingress.yaml          # Ingress для маршрутизации
    └── sentiment-service.yaml          # Service (LoadBalancer)
```
## API
### Анализ тональности текста
```bash
GET /api/sentiment?text=<текст>
```
## Пример ответа:
```json
{
  "sentiment": "positive"
}
```
Возможные значения: `positive`, `negative`, `neutral`
### Метрики и мониторинг
* `/actuator/health` - состояние приложения
* `/actuator/prometheus` - метрики в формате Prometheus

## Мониторинг
Для мониторинга развёрнуты:
* Prometheus
```bash
kubectl port-forward -n monitoring svc/prometheus-kube-prometheus-prometheus 9090:9090
```
Доступен по адресу: http://localhost:9090
* Grafana
```bash 
kubectl port-forward -n monitoring svc/prometheus-grafana 3000:80
```
Доступен по адресу: http://localhost:3000

##  Технологический стек
* Приложение: Java 21, Spring Boot 3.3.5
* Контейнеризация: Docker, многоступенчатая сборка
* Оркестрация: Kubernetes (Minikube)
* Мониторинг: Prometheus, Grafana, Spring Boot Actuator
* Инфраструктура: Helm, YAML, kubectl

