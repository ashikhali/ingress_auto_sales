FROM alpine:3.18.3
RUN apk add --no-cache openjdk17
RUN apk add --no-cache tzdata
COPY build/libs/ingress_auto_sales-1.0.fdfe5f1.jar /app/
COPY gradle.properties /app/gradle.properties
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/ingress_auto_sales-1.0.fdfe5f1.jar"]
