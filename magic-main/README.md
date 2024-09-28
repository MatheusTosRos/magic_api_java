# Magic API

Esta API permite gerenciar usuários, decks e cartas do jogo Magic: The Gathering. Abaixo estão as instruções para configurar e testar a API usando o Postman.

## Pré-requisitos

- Java 11+
- Postman instalado
- JWT Token para acessar as rotas protegidas

## Endpoints da API

### Autenticação

1. **Registrar Usuário**

    - **Endpoint**: `POST /auth/register`
    - **Descrição**: Cria um novo usuário na aplicação.
    - **Body** (JSON):
      ```json
      {
        "login": "seuLogin",
        "password": "suaSenha",
        "role": "ADMIN" // ou "USER"
      }
      ```

    - **Exemplo de resposta**:
      ```json
      {
        "message": "Usuário criado com sucesso!"
      }
      ```

2. **Login**

    - **Endpoint**: `POST /auth/login`
    - **Descrição**: Autentica um usuário e gera um token JWT.
    - **Body** (JSON):
      ```json
      {
        "login": "seuLogin",
        "password": "suaSenha"
      }
      ```
    - **Exemplo de resposta**:
      ```json
      {
        "token": "seuTokenJWT"
      }
      ```
    - **Notas**: Salve o token JWT gerado, pois ele será necessário para acessar as rotas protegidas.

---

### Gerenciamento de Decks

1. **Listar todos os Decks (Admin)**

    - **Endpoint**: `GET /deck/allDecks`
    - **Descrição**: Retorna todos os decks cadastrados. Apenas usuários com o papel `ADMIN` têm acesso.
    - **Autenticação**: Adicione o token JWT no cabeçalho da requisição.
        - **Authorization**: `Bearer {seuTokenJWT}`
    - **Exemplo de resposta**:
      ```json
      [
        {
          "id": 1,
          "commander": "Commander Name",
          "cards": [...]
        },
        ...
      ]
      ```

2. **Listar Decks do Usuário Logado**

    - **Endpoint**: `GET /deck/login/userDecks`
    - **Descrição**: Retorna os decks do usuário autenticado.
    - **Autenticação**: Adicione o token JWT no cabeçalho da requisição.
        - **Authorization**: `Bearer {seuTokenJWT}`

3. **Enviar um Deck**

    - **Endpoint**: `POST /deck/send/deck`
    - **Descrição**: Envia e cadastra um novo deck.
    - **Autenticação**: Não requer autenticação.
    - **Exemplo de resposta**:
      ```json
      {
        "message": "Deck enviado com sucesso!"
      }
      ```

---

### Gerenciamento de Cartas

1. **Adicionar um Comandante ao Deck**

    - **Endpoint**: `POST /card/commander`
    - **Descrição**: Adiciona um comandante a um deck existente.
    - **Body** (JSON):
      ```json
      {
        "name": "NomeDoCommander",
        "quantidadeCartas": 99
      }
      ```

    - **Exemplo de resposta**:
      ```json
      {
        "name": "CommanderName",
        "cardType": "COMMANDER",
        "colors": ["blue", "white"],
        "response": "Carta adicionada com sucesso!"
      }
      ```

---

## Testando no Postman

1. **Criando a Coleção no Postman**:
    - Crie uma nova coleção no Postman para organizar seus testes da API.

2. **Adicionando as Requisições**:
    - Para cada endpoint descrito acima, crie uma requisição correspondente no Postman.
    - Nos endpoints protegidos (como `/deck/allDecks`), adicione o token JWT gerado no login ao cabeçalho das requisições:
        - No Postman, vá para a aba "Authorization" e selecione o tipo **Bearer Token**, inserindo o token recebido.

3. **Execução**:
    - Execute as requisições no Postman, verificando as respostas da API e fazendo ajustes conforme necessário.

---

## Erros Comuns

- **403 Forbidden**: Verifique se o token JWT foi adicionado corretamente no cabeçalho da requisição.
- **400 Bad Request**: Verifique se os dados enviados no body da requisição estão corretos e completos.

## Contribuições

Sinta-se à vontade para abrir issues ou enviar pull requests para melhorar a API.

---

## Licença

Este projeto está sob a licença XYZ. Consulte o arquivo [LICENSE](LICENSE) para obter mais informações.
