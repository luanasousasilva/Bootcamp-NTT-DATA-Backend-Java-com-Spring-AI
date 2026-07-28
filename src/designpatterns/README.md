# Projeto Design Patterns

Projeto de console que simula o fluxo de um pedido em uma loja virtual.

- **Builder:** monta pedidos com itens e frete.
- **Strategy:** permite escolher Pix ou cartão como forma de pagamento.
- **Factory Method:** cria notificadores de e-mail ou SMS.
- **Observer:** avisa o cliente a cada mudança de status do pedido.
- **Singleton:** centraliza as configurações da loja.

## Execução

Na raiz do repositório (`Bootcamp-NTT-DATA-Backend-Java-com-Spring-AI`), execute:

```bash
javac -d out src/designpatterns/*.java
java -cp out designpatterns.Main
```

Caso esteja na pasta `src/javafundamentos`, volte primeiro para a raiz:

```bash
cd ../..
javac -d out src/designpatterns/*.java
java -cp out designpatterns.Main
```
