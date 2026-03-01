# PD PROJETOS

## Descrição do Projeto
O projeto PD PROJETOS é uma iniciativa para entender os padrões de projetos e seus paradigmas.


## Strategy Pattern
Entendo perfeitamente. O Strategy Pattern é um dos padrões comportamentais mais úteis porque ele nos ajuda a seguir
o princípio do **"Open/Closed"** (aberto para extensão, fechado para modificação). Em vez de encher seu código de *if/else*
ou switch intermináveis, você encapsula cada algoritmo em uma classe separada.

### O que é o Strategy Pattern?
Imagine que você tem diferentes formas de calcular um frete: Sedex, PAC e Loggi. Sem o Strategy, você teria um método gigante com várias condicionais. Com o Strategy, cada tipo de frete vira uma "estratégia" independente.

### Quando utilizar?

* Quando você tem múltiplas variações de um mesmo algoritmo.

* Quando você quer evitar condicionais complexas que verificam o tipo de objeto para decidir o que fazer.

* Quando você precisa mudar o comportamento de um objeto em tempo de execução.

---
## Implementação com Spring Boot
No ecossistema Spring, o Strategy fica ainda mais poderoso porque podemos usar a Injeção de Dependência para mapear todas as estratégias automaticamente.

1. A Interface (A Estratégia)
   Primeiro, definimos o contrato que todas as estratégias devem seguir.

```java
public interface PagamentoStrategy {
    void processar(double valor);
    String getTipoPagamento(); // Identificador da estratégia
}
```

2. As Implementações (As Estratégias Concretas)

* Agora, criamos as classes que implementam essa interface.

* Cada classe cuidará de uma regra específica.

```java
@Component
public class PagamentoCartao implements PagamentoStrategy {
    @Override
    public void processar(double valor) {
        System.out.println("Pagando R$ " + valor + " via Cartão de Crédito.");
    }

    @Override
    public String getTipoPagamento() {
        return "CARTAO";
    }
}

@Component
public class PagamentoPix implements PagamentoStrategy {
    @Override
    public void processar(double valor) {
        System.out.println("Pagando R$ " + valor + " via PIX.");
    }

    @Override
    public String getTipoPagamento() {
        return "PIX";
    }
}
```

3. O Contexto (Onde a mágica acontece)
    
    O Spring consegue injetar todas as implementações de PagamentoStrategy em uma lista ou mapa automaticamente.

```java
@Service
public class PagamentoService {

    private final Map<String, PagamentoStrategy> estrategias;

    // O Spring injeta todas as implementações no Map usando o nome como chave
    public PagamentoService(List<PagamentoStrategy> strategyList) {
        this.estrategias = strategyList.stream()
            .collect(Collectors.toMap(PagamentoStrategy::getTipoPagamento, s -> s));
    }

    public void realizarPagamento(String tipo, double valor) {
        PagamentoStrategy strategy = estrategias.get(tipo.toUpperCase());
        
        if (strategy == null) {
            throw new IllegalArgumentException("Método de pagamento inválido!");
        }
        
        strategy.processar(valor);
    }
}
```

4. O Controller (Exemplo de API)

   Agora, basta expor isso em um endpoint.

```java
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/{tipo}")
    public ResponseEntity<String> pagar(@PathVariable String tipo, @RequestBody double valor) {
        pagamentoService.realizarPagamento(tipo, valor);
        return ResponseEntity.ok("Pagamento processado com sucesso!");
    }
}

```

---
## Por que isso é melhor?

1. Manutenibilidade: Se amanhã surgir o "Pagamento via Cripto", você apenas cria uma nova classe que implementa a interface. Você não mexe no código que já existe.

2. Testabilidade: Você pode testar cada estratégia isoladamente com facilidade.

3. Legibilidade: O PagamentoService fica limpo, sem um switch de 50 linhas.


#  Strategy Patthern dentro Arquitetura Hexagonal (ou Ports and Adapters)

Implementar o Strategy Pattern dentro da Arquitetura Hexagonal (ou Ports and Adapters) é uma excelente prática, pois ajuda a manter o "Core" (núcleo) da sua aplicação isolado de tecnologias externas, focando apenas na lógica de negócio.

## Na Arquitetura Hexagonal, dividimos o sistema em:

1. Domain (Core): Onde as regras de negócio residem.

1. Application (Adapters de Entrada): Como o mundo externo interage com o core (ex: REST API).

1. Infrastructure (Adapters de Saída): Como o core interage com o mundo externo (ex: Banco de Dados, Gateways de Pagamento).

## Veja como estruturar o exemplo de pagamentos:

Implementar o Strategy Pattern dentro da Arquitetura Hexagonal (ou Ports and Adapters) é uma excelente prática, pois ajuda a manter o "Core" (núcleo) da sua aplicação isolado de tecnologias externas, focando apenas na lógica de negócio.

Na Arquitetura Hexagonal, dividimos o sistema em:

Domain (Core): Onde as regras de negócio residem.

Application (Adapters de Entrada): Como o mundo externo interage com o core (ex: REST API).

Infrastructure (Adapters de Saída): Como o core interage com o mundo externo (ex: Banco de Dados, Gateways de Pagamento).

Veja como estruturar o exemplo de pagamentos:

### 1. O Domínio (O Coração)
No centro, definimos nossa Porta (Port). É uma interface que o domínio conhece e usa, mas não sabe como é implementada.

`src/main/java/com/exemplo/core/ports/PaymentStrategy.java`

```java
public interface PaymentStrategy {
    void execute(Double amount);
    boolean isApplicable(String paymentMethod);
}
```
### 2. O Caso de Uso (Application Service)
O serviço de domínio coordena a escolha da estratégia. Note que ele não depende de nada do Spring aqui.

`src/main/java/com/exemplo/core/services/ProcessPaymentService.java`

```java
public class ProcessPaymentService {

    private final List<PaymentStrategy> strategies;

    public ProcessPaymentService(List<PaymentStrategy> strategies) {
        this.strategies = strategies;
    }

    public void process(String method, Double amount) {
        PaymentStrategy strategy = strategies.stream()
            .filter(s -> s.isApplicable(method))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Método não suportado"));
        
        strategy.execute(amount);
    }
}
```

###  3. Adaptadores de Saída (Infrastructure)
Aqui é onde as tecnologias específicas entram. Por exemplo, uma estratégia que chama a API do PayPal e outra que chama a do Stripe.

`src/main/java/com/exemplo/infrastructure/adapters/PayPalAdapter.java`

```java
@Component
public class PayPalAdapter implements PaymentStrategy {
    @Override
    public void execute(Double amount) {
        // Lógica específica para chamar a API do PayPal
        System.out.println("Processando R$ " + amount + " via PayPal SDK");
    }

    @Override
    public boolean isApplicable(String paymentMethod) {
        return "PAYPAL".equalsIgnoreCase(paymentMethod);
    }
}
```

### 4. Configuração (O "Ajudante" do Spring)
Como o nosso ProcessPaymentService está no "Core" e não deve ter anotações de framework (como @Service), precisamos de uma classe de configuração para dizer ao Spring como criá-lo.

`src/main/java/com/exemplo/infrastructure/config/BeanConfiguration.java`

```java
@Configuration
public class BeanConfiguration {

    @Bean
    public ProcessPaymentService processPaymentService(List<PaymentStrategy> strategies) {
        return new ProcessPaymentService(strategies);
    }
}
```
### 5. 5. Adaptador de Entrada (Controller)
A API que recebe a requisição.

`src/main/java/com/exemplo/application/web/PaymentController.java`

```java
@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    private final ProcessPaymentService paymentService;

    public PaymentController(ProcessPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{method}")
    public void pay(@PathVariable String method, @RequestBody Double amount) {
        paymentService.process(method, amount);
    }
}
```
---
## Benefícios dessa abordagem:
1. **Testabilidade:** Você pode testar o ProcessPaymentService com Mocks unitários puros, sem precisar subir o contexto do Spring.

1. **Independência de Framework:** Se você decidir trocar o Spring pelo Quarkus ou Micronaut, toda a sua lógica de escolha de estratégia (ProcessPaymentService) e as interfaces permanecem intactas.

1. **Flexibilidade:** Se o método de pagamento "PIX" precisar salvar algo no banco e o "Cartão" precisar chamar um serviço externo, cada Adapter cuidará da sua própria dependência de infraestrutura sem sujar o Core.