# project_final_PI

Pontos de Melhorias:
- As requisições da api não estão passando por validação de dados;
- Os endpoints estão liberados para serem batidos sem veririficar a parte de segurança;
- O Login é algo fake que só valida a senha e se existe o user no banco e gera um token;
- A senha no banco não coloquei criptografia;
- Criar um swagger para entender melhor os endpoints;
Se quiserem compartilhar mais coisas só ir adicionando aqui, não sei se vai dar tempo de tocarmos essas melhorias.

Falta ainda:
- A parte da marcação de horários pelo usuário e um endpoint para mostrar os horários marcados pro profissional.

Pontos Importantes:
- Para quem não tiver o postgre instalado em sua máquina na pasta raiz do projeto deixei um docker-compose que gera um container de postgre.
- Para testarem a api tb ja deixei uma pasta com toda a collection do Postman, só importar no Postman e sair usando.
- O nome do database ficou como mydb;
- Fiz o mais simples possível para termos uma base, melhorias são bem vindas.
