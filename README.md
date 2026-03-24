Nome do Projeto
Sistema IoT para Monitoramento de Temperatura e Controle de Acesso por RFID

Descrição
Este projeto tem como objetivo monitorar a temperatura de um ambiente onde alimentos estão armazenados, garantindo que estejam dentro da faixa ideal. O sistema também utiliza RFID para identificar usuários e registrar acessos.

Objetivo
Garantir a conservação dos alimentos através do monitoramento contínuo da temperatura e permitir a identificação de usuários de forma simples utilizando RFID.

Tecnologias utilizadas
ESP8266
Sensor DHT22
Módulo RFID
MQTT
Node-RED
MySQL
HTML CSS e JavaScript

Funcionamento do sistema
O ESP8266 realiza a leitura da temperatura e umidade utilizando o sensor DHT22
Os dados são enviados via MQTT
O sistema recebe os dados no Node-RED
Os dados são processados e armazenados no banco de dados
A aplicação web exibe as informações em tempo real

O RFID é utilizado para identificação de usuários
O cartão é lido pelo ESP8266
O UID é enviado para o sistema
O sistema valida no banco de dados
O acesso é liberado ou negado e registrado

Funcionalidades
Monitoramento de temperatura em tempo real
Alerta de temperatura fora do padrão
Histórico de medições
Identificação de usuários por RFID
Registro de acessos
CRUD de usuários
CRUD de produtos
Dashboard com visualização dos dados

Estrutura do sistema
Dispositivo coleta os dados
Dados são enviados via MQTT
Node-RED processa e conecta com o banco
API disponibiliza os dados
Frontend exibe as informações

Como executar o projeto
Conectar o ESP8266 na rede Wi-Fi
Configurar o broker MQTT
Executar o Node-RED
Configurar a conexão com o banco de dados
Executar a aplicação web
Garantir que todos os componentes estejam na mesma rede

Banco de dados
O sistema utiliza tabelas para usuários produtos sensores e acessos
Os dados são armazenados com data e hora
Os produtos estão vinculados aos usuários
Os acessos registram tentativas com RFID

Observações
O sistema foi desenvolvido de forma simples para apresentação
Não possui autenticação avançada
O foco é demonstrar a integração entre hardware software e banco de dados
Todos os componentes devem estar funcionando em conjunto para a demonstração

Autor
Caio Gomes
