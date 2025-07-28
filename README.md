```markdown
# Controle Financeiro - Gerenciamento de Credores

Este projeto é uma aplicação Spring Boot para gerenciar credores, permitindo listar, inserir, editar e deletar credores. Abaixo está a explicação detalhada sobre os principais componentes relacionados ao gerenciamento de credores.

---

## **Model: Credor**

A classe `Credor` representa a entidade de um credor no sistema. Ela é mapeada como uma entidade JPA para persistência no banco de dados.

### **Atributos**
- `id`: Identificador único do credor (gerado automaticamente).
- `nome`: Nome do credor.
- `diaDeVencimento`: Dia do vencimento associado ao credor (valores entre 1 e 31).

### **Validações**
- O atributo `diaDeVencimento` possui validação para garantir que o valor esteja entre 1 e 31.

### **Código**
```java
@Entity
public class Credor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer diaDeVencimento;

    // Getters e Setters
}
```

---

## **Interface: CredorRepository**

A interface `CredorRepository` é responsável por interagir com o banco de dados. Ela estende `JpaRepository`, fornecendo métodos padrão para operações CRUD.

### **Métodos Personalizados**
- `existsByNome(String nome)`: Verifica se já existe um credor com o nome fornecido.

### **Código**
```java
public interface CredorRepository extends JpaRepository<Credor, Long> {
    boolean existsByNome(String nome);
}
```

---

## **Controller: CredorController**

O controlador `CredorController` expõe endpoints REST para gerenciar credores. Ele utiliza o serviço `CredorService` para realizar as operações.

### **Endpoints**
- `GET /api/credor`: Lista todos os credores.
- `POST /api/credor`: Insere um novo credor.
- `PUT /api/credor/{id}`: Edita um credor existente.
- `DELETE /api/credor/{id}`: Deleta um credor pelo ID.

### **Código**
```java
@RestController
@RequestMapping("/api/credor")
public class CredorController {
    @GetMapping
    public List<CredorDTO> listarTodos();

    @PostMapping
    public CredorDTO inserirCredor(@Valid @RequestBody CredorDTO credorDTO);

    @PutMapping("/{id}")
    public CredorDTO editarCredor(@PathVariable Long id, @Valid @RequestBody CredorDTO credorDTO);

    @DeleteMapping("/{id}")
    public void deletarCredor(@PathVariable Long id);
}
```

---

## **Service: CredorService**

A classe `CredorService` contém a lógica de negócios para gerenciar credores. Ela utiliza o `CredorRepository` para interagir com o banco de dados.

### **Métodos**
- `listarTodos()`: Retorna todos os credores.
- `inserirCredor(Credor credor)`: Insere um novo credor, validando se o nome já existe.
- `editarCredor(Long id, Credor credorAtualizado)`: Edita um credor existente, validando se o novo nome já existe.
- `deletarCredor(Long id)`: Deleta um credor pelo ID, verificando se ele existe.

### **Validações**
- Não permite inserir ou editar credores com nomes duplicados.
- Não permite deletar credores inexistentes.

### **Código**
```java
@Service
public class CredorService {
    public List<Credor> listarTodos();
    public Credor inserirCredor(Credor credor);
    public Credor editarCredor(Long id, Credor credorAtualizado);
    public void deletarCredor(Long id);
}
```

---

## **Como Executar**

1. Certifique-se de que as dependências do projeto estão instaladas (`mvn install`).
2. Configure o banco de dados no arquivo `application.properties` ou `application.yml`.
3. Execute o projeto com o comando:
   ```bash
   mvn spring-boot:run
   ```
4. Acesse os endpoints REST na URL base: `http://localhost:8080/api/credor`.

---

## **Dependências**

- **Spring Boot**: Framework principal para desenvolvimento.
- **Spring Data JPA**: Para persistência de dados.
- **H2 Database**: Banco de dados em memória para testes.
- **SLF4J**: Para logging.

---

## **Licença**

Este projeto está sendo desenvolvido por Rafael Cunha
```