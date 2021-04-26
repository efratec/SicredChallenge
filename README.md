# SicrediChallenge
Cenário de Negócio:
Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e organiza as informações de 
contas para enviar ao Banco Central. Todas agencias e cooperativas enviam arquivos Excel à Retaguarda. Hoje o Sicredi 
já possiu mais de 4 milhões de contas ativas.
Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal, 
antes as 10:00 da manhã na abertura das agências.

Requisito:
Usar o "serviço da receita" (fake) para processamento automático do arquivo.

Funcionalidade:
0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma 
nova coluna.


Formato CSV:
agencia;conta;saldo;status
0101;12225-6;100,00;A
0101;12226-8;3200,50;A
3202;40011-1;-35,12;I
3202;54001-2;0,00;P
3202;00321-2;34500,00;B
...

#Requisitos
=> Java 11

#Projeto SicrediChallenge
O projeto conforme versionado nesse repositório possui um módulo chamado "api" no qual está toda lógica desenvolvida a partir dos critérios solicitados acima. 
Foi desenvolvido com SpringBoot + maven + java11 + rabbitmq.

Realizei uma pequena refatoraçãoem algumas classes no qual foram passadas pelo e-mail;

Para criar o jar referente a api basta da um maven install ou mvn install.

No ḿodulo principal foi criado um serviço utilizando o rabbitmq na intenção para se obter filas referente ao serviço jar de sincronização criado.

O RabbitMQ está dockerizado, para rodá-lo basta somente o comando docker-compose up no path onde está localizado.

#Acesso RabbitMq
http://localhost:1572
User: guest
Password: guest

Ou então rodar o projeto jar gerado pelo prompt de comando.

