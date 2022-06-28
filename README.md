Projeto de criado para estudo para desenvolvimento de REST API e testes de integração.

Foram utilizados Java e RestAssured para programar os testes de API.

# Teste de Carga e Performance

Nesse projeto também foi adicionado um exemplo de teste de carga e performance utilizando Artillery

Para instalar, basta executar os seguintes comandos:

```
npm install -g artillery@latest
```

E para executar os testes, dentro da pasta com o arquivo de configuração, execute o comando abaixo:
```
artillery run config.yml
```
Para gerar um report em HTML dos testes, execute:
```
artillery run config.yml --output test.json
```
 E depois 
 ```
 artillery report --output report.html test.json
```
