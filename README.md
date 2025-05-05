# Processo inicial de setup para rodar o projeto (ligando o backend)
```bash
# Para rodar o projeto primeiro entre na pasta chatfuria
cd chatfuria

# Crie um artifact do backend usando
./mvnw clean package
# ou
mvn clean package

# Volte para o raiz da pasta
cd ..

# Rode o docker compose para criar o container do backend e do ollama
docker compose up -d --build

```

## Após os containers serem criados verifique se a o Ollama já possui algum modelo instalado

```bash
# rode no seu terminal
docker exec ollama ollama list

# caso não mostre nenhum modelo do ollama instalado, rode
docker exec ollama ollama pull llama2

# assim o modelo llama2 será baixado no container docker

```


### Após esse processo você poderá fazer requisições pelo [frontend](https://github.com/Jose-Alberto-Rodrigues-Neto/Furia-Challange1-Frontend1) 

### Ou fazer requisições por meio do `localhost:8080/chat/ask` ou `localhost:8080/chat/fallen` e passar a mensagem como formato de texto no body da requisição `POST`