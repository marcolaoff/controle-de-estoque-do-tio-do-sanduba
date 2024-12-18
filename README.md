# Controle de Estoque

## Integrantes
- [Marco Antonio Lourenci Silva](https://github.com/marcolaoff)
- [Matheus Ferreira Machado](https://github.com/speeky00)
- [Adriano Ferreira](https://github.com/AdrianoJr07)
- [Luis Henrique](https://github.com/lui0908)
- [Luis Gustavo](https://github.com/LuisGlima)
- [Ester Carlos](https://github.com/esterssc24)

## Tecnologias Utilizadas
- **Frontend**: HTML, CSS, JavaScript
- **Banco de Dados**: MySQL
- **Back-end**: Java (Spring Boot)
- **Containerização**: Docker e Docker Compose
- **Pipeline de CI/CD**: GitHub Actions
- **Ferramentas de Controle de Versão**: Git e GitHub

## Descrição Geral do Sistema
O sistema de controle de estoque foi desenvolvido para atender às necessidades de pequenos negócios, permitindo o gerenciamento de produtos no estoque de maneira prática e eficiente. As principais funcionalidades incluem:
- Gerenciar produtos no estoque: adicionar, editar e visualizar produtos.
- Registrar entradas e saídas de produtos no estoque.
- Gerar relatórios detalhados, tanto em formato JSON quanto PDF, sobre o histórico de movimentações e o inventário atual.

## Funcionalidades Desenvolvidas
- **Adicionar Produtos**: Permite ao administrador cadastrar novos produtos no sistema.
- **Listar Produtos**: Exibe uma tabela com todos os produtos cadastrados, incluindo ID, nome, quantidade, preço e descrição.
- **Registrar Entradas e Saídas**: Atualiza o estoque com as movimentações de entrada e saída de produtos, garantindo controle preciso.
- **Editar Produtos**: Permite corrigir ou atualizar informações de produtos existentes.
- **Gerar Relatórios**:
  - Relatório JSON detalhado sobre as movimentações (entradas e saídas) e quantidade final de cada produto.
  - Relatório em PDF para download, consolidando o inventário com dados claros e bem formatados.

## Histórias de Usuários

### Marco Antonio Lourenci Silva
**Como administrador, quero poder adicionar novos produtos ao sistema para manter o estoque atualizado.**
- Implementado um endpoint `POST /produtos` para adicionar produtos. O administrador pode usar um formulário na interface para cadastrar novos itens.

### Matheus Ferreira Machado
**Como administrador, quero visualizar uma lista de produtos com suas respectivas quantidades para monitorar o estoque.**
- Desenvolvido um endpoint `GET /produtos` que retorna todos os produtos do banco de dados. Os produtos são exibidos em uma tabela na interface web.

### Adriano Ferreira
**Como administrador, quero registrar entradas de produtos para que o estoque reflita as reposições realizadas.**
- Criado o endpoint `POST /produtos/{id}/entrada`, permitindo registrar entradas de produtos no estoque e atualizar as quantidades.

### Luis Henrique
**Como administrador, quero registrar saídas de produtos para manter o controle sobre as vendas realizadas.**
- Desenvolvido o endpoint `POST /produtos/{id}/saida`, que subtrai as quantidades de produtos vendidas do estoque.

### Luis Gustavo
**Como administrador, quero gerar relatórios do inventário para analisar o histórico do estoque.**
- Dois relatórios foram criados:
  - Relatório JSON, acessado pelo endpoint `GET /produtos/relatorio/movimentacao`.
  - Relatório em PDF, acessado pelo endpoint `GET /produtos/relatorio/pdf`, com layout formatado para impressão e análise.

### Ester Carlos
**Como administrador, quero editar informações de produtos existentes para corrigir eventuais erros de cadastro.**
- Implementado o endpoint `PUT /produtos/{id}` para atualizar informações de produtos. A funcionalidade é acessível via formulário na interface web.

## Configuração de CI/CD com GitHub Actions
O pipeline de CI/CD foi configurado para automatizar o processo de validação, construção e teste da aplicação. As etapas do pipeline incluem:

1. **Instalação e Configuração do Ambiente**:
   - Configuração do MySQL como serviço para integração com o backend.
   - Setup do Java 17 com Maven para gerenciamento e construção do projeto.
2. **Build e Teste Automatizado**:
   - Uso do Maven para compilar o projeto e rodar testes automatizados.
   - Teste de endpoints usando o `curl` para validar a integridade da aplicação.
3. **Containerização com Docker Compose**:
   - Simulação do ambiente de produção localmente. O backend e o banco de dados MySQL são levantados em containers Docker para garantir consistência entre ambientes.
4. **Artefatos do Build**:
   - O arquivo JAR gerado pelo Maven é salvo como artefato no GitHub Actions, garantindo que possa ser baixado e implantado facilmente em outros ambientes.

## Testes com Docker e Integração com GitHub Actions
1. **Docker Compose para Simulação Local**:
   - Configuramos um `docker-compose.yml` que levanta os containers do backend e do banco de dados. Isso permitiu rodar a aplicação localmente em um ambiente próximo ao de produção.
2. **GitHub Actions**:
   - Garantimos a integração contínua (CI) ao configurar um pipeline que executa os testes e validações automaticamente em cada commit ou pull request.
   - A entrega contínua (CD) foi simulada ao salvar o JAR como artefato, permitindo sua reutilização em diferentes etapas ou ambientes.

## Conclusão
Este projeto nos permitiu aprender e aplicar conceitos fundamentais de desenvolvimento de software, incluindo backend com Spring Boot, containerização com Docker, e automação com GitHub Actions. A aplicação atende aos requisitos de todas as histórias de usuários, oferecendo uma solução prática e funcional para controle de estoque. O processo de integração e entrega contínua fortaleceu a confiabilidade do sistema e garantiu sua qualidade.
