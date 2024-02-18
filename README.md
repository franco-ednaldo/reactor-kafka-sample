# reactor-kafka-sample
Este projeto é uma aplicação Spring Boot 3.0 que demonstra a integração reativa entre o Spring Boot e o Apache Kafka usando o Project Reactor. O objetivo principal é explorar as capacidades reativas do Kafka para processamento de streams de dados em tempo real, proporcionando um sistema altamente responsivo e escalável. Utilizamos Java 17, uma escolha moderna que oferece as últimas melhorias de linguagem e performance, juntamente com o MongoDB, um banco de dados NoSQL flexível, para armazenamento de dados. O Lombok é empregado para minimizar o boilerplate code, permitindo-nos concentrar na lógica de negócios. Este projeto serve como um exemplo prático para desenvolvedores que buscam implementar sistemas reativos com Kafka no ecossistema Spring.



## Tecnologias Utilizadas

- **Java 17**: Versão do Java utilizada no projeto.
- **Spring Boot 3.0**: Framework utilizado para a criação de aplicações Spring com configuração mínima.
- **MongoDB**: Banco de dados NoSQL utilizado para armazenar dados de forma flexível.
- **Kafka + Project Reactor**: Utilizado para processamento de streams de dados reativos com Kafka.
- **Lombok**: Biblioteca Java que ajuda a reduzir o boilerplate code.
- **Kafka Reativo**: Integração reativa do Kafka para processamento de mensagens de forma não bloqueante.

## Configuração do Ambiente

Instruções sobre como configurar o ambiente de desenvolvimento, incluindo a instalação de Java, MongoDB, Kafka e quaisquer outras ferramentas necessárias.

### Pré-requisitos

- Java 17 instalado
- MongoDB instalado e em execução
- Kafka instalado e configurado
- Maven ou Gradle para gestão de dependências e build do projeto

### Instalação

Passo a passo para clonar e configurar o projeto para desenvolvimento.

```bash
git clone <url-do-repositorio>
cd <nome-do-projeto>
# Ou usando Gradle
gradle build
