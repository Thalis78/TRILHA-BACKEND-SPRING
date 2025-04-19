# Revisão Spring

### JDBC(Java Database Connectivity)

O JDBC é uma API de baixo nível fornecida pelo Java para interagir com bancos de dados relacionais. Ela permite que você envie queries SQL diretamente para o banco e receba os resultados.

- Acesso direto ao banco: Você escreve SQL puro para interagir com o banco.
- Maior controle sobre consultas:Como você escreve SQL manualmente, tem mais controle sobre otimizações.
- Código mais verboso e propenso a erros: É necessário abrir conexão, criar statement, tratar exceções e fechar tudo manualmente.

### Statement vs PreparedStatement no JDBC

No JDBC, para executar consultas no banco de dados, usamos Statement ou PreparedStatement. Ambos permitem executar SQL, mas há diferenças importantes entre eles.

- O Statement é usado para executar consultas SQL simples, sem parâmetros dinâmicos. Ele envia a consulta como uma string diretamente para o banco de dados, sem qualquer tipo de otimização.
- O PreparedStatement é uma versão mais segura e otimizada do Statement. Ele permite definir consultas SQL com parâmetros, que são preenchidos de forma segura antes da execução.

#### Características do Statement
- Simples de usar para consultas estáticas (sem parâmetros).
- Mais lento, pois o SQL é compilado toda vez que é executado.
- Vulnerável a SQL Injection, pois permite concatenar strings no SQL manualmente.

```
Statement stmt = conexao.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE nome = 'Carlos'");

while (rs.next()) {
    System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome"));
}

rs.close();
stmt.close();
conexao.close();
```

#### Caracteristicas do PreparedStatement

- Mais rápido: A consulta é compilada apenas uma vez e pode ser reutilizada com diferentes valores.
- Mais seguro: Evita SQL Injection, pois os parâmetros são tratados separadamente.
- Melhor manutenção: O código fica mais organizado e fácil de entender.

```
String sql = "SELECT * FROM usuarios WHERE nome = ?";
PreparedStatement pstmt = conexao.prepareStatement(sql);
pstmt.setString(1, "Carlos");

ResultSet rs = pstmt.executeQuery();

while (rs.next()) {
    System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome"));
}

rs.close();
pstmt.close();
conexao.close();
```

### JPA(Java Persistence API

JPA é uma camada de abstração diante do JDBC, ele conta com ORM que significa mapeamento de objetos relacionais que é feita por uma ferramenta chamada hibernate, e nela você faz consultas mais simples e diretas no BD.

- Menos código repetitivo: Você não precisa abrir conexões, criar statements e tratar exceções manualmente.
- Consultas mais simples: Pode usar JPQL (Java Persistence Query Language), que se parece mais com SQL, mas funciona de forma mais orientada a objetos.
- Gerenciamento automático do estado das entidades: O Hibernate cuida do ciclo de vida dos objetos e sincroniza as mudanças com o banco.
- Independência de banco de dados: Você pode trocar de banco (MySQL, PostgreSQL, Oracle, etc.) sem mudar muito o código.

obs: No JDBC, usamos SQL puro, o mesmo que rodaríamos diretamente no banco de dados. O código Java apenas envia esse SQL e recebe os resultados.No JPA, usamos JPQL (Java Persistence Query Language), que não trabalha diretamente com tabelas e colunas, mas sim com entidades e atributos do modelo Java.

Diferenças:
```
JDBC:

String sql = "SELECT * FROM usuarios WHERE nome = 'Carlos'";
Statement stmt = conexao.createStatement();
ResultSet rs = stmt.executeQuery(sql);

```
```
JPA:

TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nome", Usuario.class);
query.setParameter("nome", "Carlos");
Usuario usuario = query.getSingleResult();
```

### Hibernate:

O Hibernate é um ORM (Object-Relational Mapping), ou seja, uma ferramenta que facilita a conversão entre objetos Java e tabelas do banco de dados relacional. Ele é amplamente utilizado com a JPA (Java Persistence API), que é a especificação do Java para persistência de dados.

- Facilita a persistência de dados – Converte objetos Java em registros do banco e vice-versa.
- Evita SQL "hardcoded" – Utiliza HQL (Hibernate Query Language) e Criteria API para consultas.
- Gerenciamento automático – Cuida do estado das entidades e do ciclo de vida dos objetos persistentes.
- Cache e otimização – Possui estratégias de cache para melhorar a performance.
- Suporte a transações – Gerencia transações de forma integrada ao banco de dados.

### Spring

O Spring é um framework poderoso que surgiu para facilitar o desenvolvimento em Java, especialmente em aplicações web e empresariais. Ele abstrai diversas complexidades, tornando a criação e manipulação de bancos de dados mais simples e eficiente.O Spring oferece várias ferramentas para simplificar o uso do banco de dados, evitando que o programador tenha que lidar com detalhes de baixo nível do JDBC. As principais formas são:

### Anotações de Controle (Spring MVC)

- @Controller -> Define um controlador MVC que retorna páginas HTML.
- @RestController -> Define um controlador REST que retorna JSON/XML.
- RequestMapping -> Mapeia URls para métodos de um controlador.
- @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping → Mapeiam requisições HTTP para métodos específicos.
- @RequestParam -> Captura parâmetros da URL.
- @PathVariable -> Captura valores da URL como variáveis.
- @ResponseBody -> Converte o retorno de um método para o formato JSON/XML.

### Anotações de Injeção de Dependência

A injeção de dependência é uma técnica usada na programação para evitar que um objeto crie suas próprias dependências. Em vez disso, essas dependências são passadas(injetadas) para ele de fora.

Usadas para gerenciar e injetar beans dentro do contexto do Spring.
- @Component -> Marca uma classe como um bean gerenciado pelo Spring.
- @Service -> Específica um bean que representa uma lógica de serviço.
- @Repository -> Indica um bean que representa um repositório(geralmente usado com BD).
- @Autowired -> Injeta automaticamente uma dependência em um campo, construtor ou método.
- @Qualifier -> Especifica qual bean deve ser injetado quando há múltiplos disponíveis.


### Anotações de Configuração 

Usadas para definir configurações do Spring.

- @Configuration -> Define uma classe como fonte de configurações para a aplicação.
- @Bean -> Declara um bean manualmente dentro de uma classe de configuração.
- @ComponentScan -> Define pacotes que o Spring deve escanear em busca de componentes.
- @PropertySource -> Importa propriedades de arquivos .properties.

### Anotações para Banco de Dados (Spring Data JPA)

Usadas para interação com bancos de dados.

- @Entity -> Define uma classe como uma entidade do banco de dados.
- @Table(name = "nome_tabela") -> Define o nome da tabela correspondente a uma entidade.
- @Id -> Define a chave primária de uma entidade.
- @GeneratedValue(strategy = GenerationType.IDENTITY) -> Define a geração automática do ID.
- @Column(name = "nome_coluna") -> Mapeia um campo para uma coluna no banco.
- @OneToOne, @OneToMany, @ManyToOne, @ManyToMany → Definem relações entre entidades.
- @Transactional -> Garante que um método execute dentro de uma transação.

### Anotações de Segurança(Spring Security)
Usadas para configurar autenticação e autorização.

- @EnableWebSecurity -> Habilita a configuração de segurança da aplicação.
- @PreAuthorize -> Define permissões em métodos com base em regras de acesso.
- @Secured -> Restringe o acesso a determinados métodos com base em roles.
- @RolesAllowed -> Define quais roles podem acessar um método.

### Anotações do Spring Boot
Usadas para configurar automaticamente a aplicação.

- @SpringBootApplication → Combina @Configuration, @EnableAutoConfiguration e @ComponentScan.

- @EnableAutoConfiguration → Permite que o Spring Boot configure automaticamente os beans necessários.

- @ConditionalOnProperty, @ConditionalOnClass, @ConditionalOnMissingBean → Definem condições para ativação de configurações.

### Escopo Singleton VS Prototype

- Singleton: Uma instância para toda a aplicação, criada uma vez e compartilhada.

- Prototype: Uma nova instância a cada solicitação, com ciclo de vida independente.

### Migrations
Em frameworks como o Spring Boot, o Hibernate (com JPA) pode ser configurado para gerar migrations automáticas com base nas alterações do modelo de dados (entidades), mas a maneira recomendada é gerar e aplicar migrations manualmente, utilizando ferramentas como Flyway ou Liquibase, que fornecem controle de versão para as mudanças no banco.

- Flyway: A ferramenta aplica migrations de forma sequencial e gera um histórico de migrações aplicadas ao banco de dados. Você cria scripts SQL com as mudanças e o Flyway os executa.
- Liquibase: Similar ao Flyway, o Liquibase também permite o controle de versões do banco de dados, mas oferece mais flexibilidade na definição de mudanças, incluindo XML, YAML, JSON ou SQL como formatos para as migrations.
- Em um BD é uma prática essencial para manter o esquema do BD(tabelas, índices,relacionamentos, etc.) em sicronia com as mudanças que foram feitas no código da aplicação. As migrations permitem que você acompanhe e gerencie de forma segura as alterações no BD ao longo do tempo, especialmente em ambientes de desenvolvimento, testes e produção.
- Manter o histórico de alterações: As migrations fornecem um histórico de todas as mudanças feitas no banco de dados.
- Facilidade de rastreamento: Com as migrations, você pode facilmente ver quais mudanças foram feitas em determinado ponto no tempo, o que facilita a resolução de problemas ou o entendimento de como o banco evoluiu.
- Sicronizar ambientes de desenvolvimento e produção: Migrations garantem que todos os desenvolvedores, bem como os ambientes de produção, possuam o mesmo esquema de banco de dados.


### Thymeleaf:

Thymeleaf ele é usado como template para criações de páginas dinâmicas, muito útil quando estamos construído um sistema monólito, pois suas repostas interativas deixa mais fácil o desenvolvimento.

- Integração nativa com Spring Boot – funciona bem com Spring MVC.
- Uso de sintaxe intuitiva – utiliza atributos como th:text, th:each, th:if, etc.
- Capacidade de trabalhar no modo offline – pode ser testado diretamente como um template HTML puro.
- Facilidade na construção de formulários – integração direta com objetos do modelo (th:object, th:field).
- Melhor para monólitos – especialmente quando queremos evitar o uso pesado de JavaScript ou frameworks front-end