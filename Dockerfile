FROM maven:latest
RUN mkdir /wada
WORKDIR /wada
COPY . .
EXPOSE 9090
CMD ["mvn", "spring-boot:run"]