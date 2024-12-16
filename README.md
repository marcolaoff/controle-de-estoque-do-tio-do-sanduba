# Controle de Estoque

## Integrantes
- [Marco Antonio Lourenci Silva](https://github.com/marcolaoff)
- [Matheus Ferreira Machado](https://github.com/speeky00)
- [Adriano Ferreira](https://github.com/AdrianoJr07)
- [Luis Henrique](https://github.com/lui0908)
- [Luis Gustavo](https://github.com/LuisGlima)
- [Ester Carlos](https://github.com/esterssc24)

## Tecnologias Utilizadas
- **Frontend**: HTML, CSS
- **Banco de Dados**: SQL
- **Back-end**: Java (Spring Boot)
- **Ferramentas de Controle de Versão**: Git e GitHub

## Descrição Geral do Sistema
O sistema de controle de estoque será uma aplicação simples que permite:
- Gerenciar produtos no estoque.
- Visualizar a quantidade de itens disponíveis.
- Registrar a entrada e saída de produtos.
- Gerar relatórios básicos do inventário.

O objetivo é desenvolver uma solução funcional e prática, que possa ser utilizada por pequenos negócios para organizar seus estoques de maneira eficiente.

## Plano de Desenvolvimento por Etapas

### Segunda-feira
- Configurar o repositório no GitHub.
- Estruturar os arquivos iniciais do projeto (HTML e CSS).
- Criar o esquema inicial do banco de dados SQL.
- Configurar um pipeline básico de CI/CD no GitHub Actions.
- Configurar o Docker Desktop para containerização do projeto.
- Configurar o ambiente inicial do back-end em Java (Spring Boot).

### Terça-feira
- Desenvolver a interface principal para exibição de produtos e estoque.
- Implementar a funcionalidade de adicionar novos produtos no front-end.
- Desenvolver o back-end para gerenciar os produtos no banco de dados.
- Testar e ajustar a integração entre o front-end e o back-end.

### Quarta-feira
- Implementar as funcionalidades de entrada e saída de produtos no sistema.
- Desenvolver a geração de relatórios no back-end e exibir no front-end.
- Finalizar e documentar o projeto no README.

## Histórias de Usuários

### Marco Antonio Lourenci Silva
**Como administrador, quero poder adicionar novos produtos ao sistema para manter o estoque atualizado.**

### Matheus Ferreira Machado
**Como administrador, quero visualizar uma lista de produtos com suas respectivas quantidades para monitorar o estoque.**

### Adriano Ferreira
**Como administrador, quero registrar entradas de produtos para que o estoque reflita as reposições realizadas.**

### Luis Henrique
**Como administrador, quero registrar saídas de produtos para manter o controle sobre as vendas realizadas.**

### Luis Gustavo
**Como administrador, quero gerar relatórios do inventário para analisar o histórico do estoque.**

### Ester Carlos
**Como administrador, quero editar informações de produtos existentes para corrigir eventuais erros de cadastro.**

## Configuração de CI/CD com GitHub Actions
Para garantir um fluxo de desenvolvimento automatizado, será configurado um pipeline básico de CI/CD usando GitHub Actions. Este pipeline realizará as seguintes tarefas:

1. **Validação de Código**: Verificar a sintaxe de arquivos HTML, CSS e SQL.
2. **Testes Automatizados**: Executar testes básicos para garantir a integridade do sistema.
3. **Deploy Automatizado**: Publicar a versão mais recente em um ambiente configurado (opcional).