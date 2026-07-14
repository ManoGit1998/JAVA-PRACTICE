# Backend Implementation Starter Guide
### E-Commerce Microservices — Where to Start & What to Build (Step by Step)

This guide turns the high-level architecture doc into an actual build plan. It tells you
**exactly what order to build services in, and for each one: the entities, DTOs,
repository, service, controller, config, and dependencies.**

---

## 0. The Build Order (and why)

Do not build all 9 services at once. Build in dependency order so each service can be
tested standalone before the next one needs it.

```
Phase 1 → Auth Service        (everything needs identity/JWT)
Phase 2 → User Service        (profile data, depends on Auth's userId)
Phase 3 → Catalog Service     (products — independent, no dependencies)
Phase 4 → Inventory Service   (stock — depends on Catalog's productId)
Phase 5 → Cart Service        (depends on Catalog for price/availability)
Phase 6 → Order Service       (depends on Cart + Inventory)
Phase 7 → Payment Service     (depends on Order)
Phase 8 → Notification Service(listens to Order/Payment events)
Phase 9 → API Gateway         (wire everything together LAST)
```

**Why Gateway last:** it's easiest to develop and test each service directly on its own
port (8081, 8082, ...) with Postman first. Add the Gateway once you have 2-3 working
services, so you're not debugging routing and business logic at the same time.

**Why Auth first:** every other service needs a `userId` and a way to check "is this
person logged in / what role do they have." Build it once, reuse everywhere.

---

## 1. One-Time Setup (do this before Phase 1)

### 1.1 Prerequisites
- Java 21+
- Maven or Gradle
- Docker (for Postgres, Redis, Kafka — you don't need all of these on day 1)
- IntelliJ IDEA (Community is fine) or VS Code with Java extensions
- Postman or Insomnia for testing endpoints

### 1.2 Root project layout
Create a parent folder (not necessarily a Maven multi-module project — flatter is
easier to start with):

```
ecommerce-platform/
├── auth-service/
├── user-service/
├── catalog-service/
├── inventory-service/
├── cart-service/
├── order-service/
├── payment-service/
├── notification-service/
├── api-gateway/
└── docker-compose.yml
```

### 1.3 Starter `docker-compose.yml` (Postgres only for now)
Add Redis/Kafka later when you actually reach the services that need them —
don't set up infra you're not using yet.

```yaml
version: '3.8'
services:
  auth-db:
    image: postgres:16
    environment:
      POSTGRES_DB: auth_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5433:5432"

  catalog-db:
    image: postgres:16
    environment:
      POSTGRES_DB: catalog_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5434:5432"
```
(Add one block per service's DB as you reach that phase.)

### 1.4 Generate each service
For every service, start from [start.spring.io] with:
- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Validation
- Spring Boot Actuator
- Lombok
- Spring Security (only for auth-service and gateway initially)

---

## 2. PHASE 1 — Auth Service (build this first)

### 2.1 Responsibilities
Register, login, issue JWT access + refresh tokens, validate tokens.

### 2.2 Dependencies (`pom.xml` additions beyond the base Spring Web/JPA)
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### 2.3 Package structure
```
com.ecommerce.auth
├── AuthApplication.java
├── entity/
│   ├── User.java
│   └── Role.java              (enum: CUSTOMER, ADMIN, SUPPORT)
├── dto/
│   ├── RegisterRequest.java
│   ├── LoginRequest.java
│   ├── AuthResponse.java
│   └── RefreshRequest.java
├── repository/
│   └── UserRepository.java
├── service/
│   └── AuthService.java
├── controller/
│   └── AuthController.java
├── security/
│   ├── JwtUtil.java
│   ├── JwtAuthFilter.java
│   └── SecurityConfig.java
└── exception/
    ├── GlobalExceptionHandler.java
    └── UserAlreadyExistsException.java
```

### 2.4 Entity — `User`
```java
@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;

    private boolean enabled = true;

    private Instant createdAt = Instant.now();
}
```

`Role.java`
```java
public enum Role { CUSTOMER, ADMIN, SUPPORT }
```

### 2.5 DTOs
```java
public record RegisterRequest(
    @Email @NotBlank String email,
    @Size(min = 8) String password) {}

public record LoginRequest(
    @Email @NotBlank String email,
    @NotBlank String password) {}

public record AuthResponse(
    String accessToken,
    String refreshToken,
    String userId,
    String role) {}

public record RefreshRequest(@NotBlank String refreshToken) {}
```

### 2.6 Repository
```java
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
```

### 2.7 Service — method signatures + logic outline
```java
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        // 1. check existsByEmail -> throw UserAlreadyExistsException if true
        // 2. hash password with passwordEncoder.encode()
        // 3. save User entity with Role.CUSTOMER
        // 4. generate access + refresh token via jwtUtil
        // 5. return AuthResponse
    }

    public AuthResponse login(LoginRequest request) {
        // 1. find user by email -> throw if not found (401, not 404 — don't leak existence)
        // 2. passwordEncoder.matches(raw, stored) -> throw if false
        // 3. generate tokens, return AuthResponse
    }

    public AuthResponse refresh(RefreshRequest request) {
        // 1. validate refresh token signature + expiry via jwtUtil
        // 2. extract userId, look up user (check still enabled)
        // 3. issue a NEW access token (and rotate refresh token — invalidate old one)
    }
}
```

### 2.8 Controller — endpoints
```java
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody RefreshRequest req) {
        return ResponseEntity.ok(authService.refresh(req));
    }
}
```

| Method | Path            | Body              | Response       | Auth required |
|--------|-----------------|--------------------|----------------|----------------|
| POST   | `/auth/register`| RegisterRequest    | AuthResponse   | No             |
| POST   | `/auth/login`   | LoginRequest       | AuthResponse   | No             |
| POST   | `/auth/refresh` | RefreshRequest     | AuthResponse   | No (uses refresh token) |

### 2.9 JwtUtil — what it needs to do
- `generateAccessToken(User user)` → short-lived (15 min), claims: `sub=userId`, `role`, `email`
- `generateRefreshToken(User user)` → long-lived (7 days), claims: `sub=userId` only
- `validateToken(String token)` → returns claims or throws
- `extractUserId(String token)`

Use a signing secret from `application.yml` (never hardcode):
```yaml
jwt:
  secret: ${JWT_SECRET:change-this-in-prod-use-256-bit-key}
  access-token-expiry-ms: 900000      # 15 min
  refresh-token-expiry-ms: 604800000  # 7 days
```

### 2.10 SecurityConfig — key points
- `/auth/**` → `permitAll()`
- Everything else → `authenticated()` (relevant once you add profile endpoints later)
- Stateless session (`SessionCreationPolicy.STATELESS`)
- Add `JwtAuthFilter` before `UsernamePasswordAuthenticationFilter`
- Use `BCryptPasswordEncoder` bean

### 2.11 `application.yml`
```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/auth_db
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: ${JWT_SECRET:dev-only-secret-change-me-please-32-bytes-min}
```

### 2.12 How you'll know Phase 1 is done
- [ ] `POST /auth/register` creates a user, returns tokens
- [ ] `POST /auth/login` returns tokens for correct credentials, 401 for wrong ones
- [ ] `POST /auth/refresh` issues a new access token from a valid refresh token
- [ ] Duplicate email registration returns 409, not a stack trace
- [ ] Passwords are never returned in any response body
- [ ] A unit test exists for `AuthService.register()` and `login()` using Mockito

---

## 3. PHASE 2 — User Service

### 3.1 Responsibilities
Store profile data (name, addresses, preferences) keyed by the `userId` issued by Auth.
This service does **not** re-implement login — it trusts a `userId` passed in
(from the gateway/JWT once you wire that up; for now, pass it as a path/header param).

### 3.2 Entity
```java
@Entity
@Table(name = "user_profiles")
@Getter @Setter
public class UserProfile {
    @Id
    private String userId;          // same UUID as Auth's User.id — no FK across services!

    private String firstName;
    private String lastName;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();
}

@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private boolean isDefault;
}
```

### 3.3 Endpoints
| Method | Path                          | Purpose                  |
|--------|-------------------------------|---------------------------|
| GET    | `/users/{userId}`              | get profile                |
| PUT    | `/users/{userId}`               | update profile             |
| POST   | `/users/{userId}/addresses`     | add address                |
| DELETE | `/users/{userId}/addresses/{id}`| remove address             |

Repository/Service/Controller follow the same 3-layer pattern as Auth (Repository →
Service → Controller). Use a `UserProfileRepository extends JpaRepository<UserProfile, String>`.

---

## 4. PHASE 3 — Catalog Service

### 4.1 Entity
```java
@Entity
@Table(name = "products")
@Getter @Setter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private String imageUrl;
    private boolean active = true;
    private Instant createdAt = Instant.now();
}
```

### 4.2 DTOs
```java
public record ProductRequest(@NotBlank String name, String description,
                              String category, @Positive BigDecimal price, String imageUrl) {}

public record ProductResponse(String id, String name, String description,
                               String category, BigDecimal price, String imageUrl) {}
```

### 4.3 Endpoints
| Method | Path                    | Purpose                        | Auth       |
|--------|-------------------------|----------------------------------|------------|
| GET    | `/products`              | list/search (paginated, filterable by category/price) | Public |
| GET    | `/products/{id}`         | get one product                  | Public     |
| POST   | `/products`              | create (admin)                   | ADMIN      |
| PUT    | `/products/{id}`         | update (admin)                   | ADMIN      |
| DELETE | `/products/{id}`         | soft-delete → `active=false`     | ADMIN      |

### 4.4 Service — notes
- `list()` should accept `Pageable` + optional filters (category, min/max price, search text)
- Use `Specification<Product>` or Query methods like:
  `findByCategoryAndPriceBetween(String category, BigDecimal min, BigDecimal max, Pageable pageable)`
- Add `@Cacheable("products")` on `getById()` once you introduce Redis (not needed day 1)

---

## 5. PHASE 4 — Inventory Service

### 5.1 Entity
```java
@Entity
@Table(name = "stock_items")
@Getter @Setter
public class StockItem {
    @Id
    private String productId;      // matches Catalog's Product.id

    private int availableQuantity;
    private int reservedQuantity;

    @Version                       // optimistic locking — prevents overselling
    private Long version;
}
```

### 5.2 DTOs
```java
public record ReserveStockRequest(String productId, int quantity, String orderRef) {}
public record ReserveStockResponse(boolean success, String message) {}
```

### 5.3 Endpoints
| Method | Path                          | Purpose                              |
|--------|-------------------------------|----------------------------------------|
| GET    | `/inventory/{productId}`       | check available quantity                |
| POST   | `/inventory/reserve`           | reserve stock (called during checkout)  |
| POST   | `/inventory/release`           | release reservation (payment failed)    |
| POST   | `/inventory/commit`            | finalize reservation (order confirmed)  |

### 5.4 Service — key logic
```java
@Transactional
public ReserveStockResponse reserve(ReserveStockRequest req) {
    // 1. fetch StockItem by productId (throws if not found)
    // 2. if availableQuantity - reservedQuantity < req.quantity -> return failure
    // 3. reservedQuantity += req.quantity  (the @Version field prevents lost updates
    //    under concurrent requests — catch OptimisticLockException and retry/fail)
    // 4. save, return success
}
```
This is the one place in the whole system where correctness matters most — don't skip
the `@Version` field, it's what prevents overselling under concurrent checkouts.

---

## 6. PHASE 5 — Cart Service

### 6.1 Entity
```java
@Entity
@Table(name = "carts")
@Getter @Setter
public class Cart {
    @Id
    private String userId;         // one cart per user, guest carts use a session id instead

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    private String couponCode;
}

@Entity
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private String productId;
    private String productName;    // denormalized snapshot so cart doesn't break if Catalog is down
    private BigDecimal unitPrice;   // snapshot at time of adding
    private int quantity;
}
```

### 6.2 Endpoints
| Method | Path                          | Purpose            |
|--------|-------------------------------|----------------------|
| GET    | `/cart/{userId}`               | view cart             |
| POST   | `/cart/{userId}/items`         | add item              |
| PUT    | `/cart/{userId}/items/{itemId}`| update quantity       |
| DELETE | `/cart/{userId}/items/{itemId}`| remove item           |
| POST   | `/cart/{userId}/coupon`        | apply coupon          |

### 6.3 Service note
When adding an item, `CartService` calls Catalog Service (via `RestTemplate`/`WebClient`
or a Feign client) to fetch current price/name — don't trust the client to send the price.

---

## 7. PHASE 6 — Order Service (the most important one)

### 7.1 Entity
```java
@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    private String idempotencyKey;   // unique constraint — prevents duplicate orders on retry
    private Instant createdAt = Instant.now();
}

public enum OrderStatus { CREATED, PAID, PACKED, SHIPPED, DELIVERED, CANCELLED }

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne @JoinColumn(name = "order_id")
    private Order order;

    private String productId;
    private String productName;
    private BigDecimal unitPrice;
    private int quantity;
}
```

Add a **unique constraint** on `idempotencyKey` in the DB — this is your real defense
against duplicate orders, not just application logic.

### 7.2 DTOs
```java
public record CreateOrderRequest(String userId, String idempotencyKey,
                                  List<OrderItemRequest> items) {}
public record OrderItemRequest(String productId, int quantity) {}
public record OrderResponse(String orderId, OrderStatus status, BigDecimal totalAmount) {}
```

### 7.3 Endpoints
| Method | Path              | Purpose                     |
|--------|-------------------|-------------------------------|
| POST   | `/orders`          | create order (checkout)       |
| GET    | `/orders/{id}`     | get order detail              |
| GET    | `/orders?userId=`  | order history for a user      |
| PATCH  | `/orders/{id}/status` | update status (admin/internal) |

### 7.4 Service — orchestration outline (this is the Saga in practice)
```java
@Transactional
public OrderResponse createOrder(CreateOrderRequest req) {
    // 0. check idempotencyKey — if an order already exists with it, return that order
    //    (don't create a second one)
    // 1. call Inventory.reserve() for each item
    //    -> if any reservation fails, release the ones already reserved, throw
    // 2. call Payment.charge() with total amount
    //    -> if payment fails: call Inventory.release() for all items, throw
    // 3. save Order + OrderItems with status = PAID
    // 4. publish "OrderCreated" event (Kafka — add this once you're past a basic REST version)
    // 5. return OrderResponse
}
```
For your first working version, steps 1–3 can be plain synchronous REST calls between
services (`RestTemplate`/`WebClient`). Don't reach for Kafka/Saga orchestration frameworks
until the synchronous version works end-to-end — you can evolve into the event-driven
version afterward.

---

## 8. PHASE 7 — Payment Service

### 8.1 Entity
```java
@Entity
@Table(name = "payments")
@Getter @Setter
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String orderId;
    private BigDecimal amount;
    private String method;              // CARD, PAYPAL, WALLET

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String idempotencyKey;      // unique constraint
    private Instant createdAt = Instant.now();
}

public enum PaymentStatus { PENDING, SUCCESS, FAILED, REFUNDED }
```

### 8.2 Endpoints
| Method | Path                     | Purpose            |
|--------|--------------------------|-----------------------|
| POST   | `/payments/charge`        | process a payment     |
| POST   | `/payments/{id}/refund`   | refund a payment       |
| GET    | `/payments/{id}`          | get payment status     |

### 8.3 Strategy pattern (this is where it earns its place)
```java
public interface PaymentStrategy {
    PaymentResult pay(BigDecimal amount, PaymentDetails details);
}

@Component("CARD")
public class CardPaymentStrategy implements PaymentStrategy { /* calls gateway adapter */ }

@Component("PAYPAL")
public class PayPalPaymentStrategy implements PaymentStrategy { /* ... */ }
```
`PaymentService` picks the strategy from a `Map<String, PaymentStrategy>` (Spring
auto-wires all `@Component`-annotated beans of that type keyed by bean name) based on
`method`. Start with just `CardPaymentStrategy` — mock the actual gateway call for now
(return success unless amount is e.g. exactly 0, to test failure paths).

---

## 9. PHASE 8 — Notification Service

### 9.1 Entity
```java
@Entity
public class NotificationLog {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;
    private String channel;    // EMAIL, SMS, PUSH
    private String type;       // ORDER_CONFIRMATION, PASSWORD_RESET, etc.
    private String status;     // SENT, FAILED
    private Instant sentAt;
}
```

### 9.2 Endpoints
| Method | Path                | Purpose                         |
|--------|---------------------|------------------------------------|
| POST   | `/notifications/send`| send a notification directly (early version, before Kafka) |

### 9.3 Factory pattern
```java
public interface NotificationSender {
    void send(String to, String subject, String body);
}

@Component("EMAIL")
public class EmailSender implements NotificationSender { /* JavaMailSender or a stub */ }

@Component("SMS")
public class SmsSender implements NotificationSender { /* stub for now */ }
```
For your first version, call this service directly via REST from `OrderService` after
a successful order. Swap to Kafka event consumption later without changing this
service's internals.

---

## 10. PHASE 9 — API Gateway (build this last)

Only do this once several services work individually.

- Use **Spring Cloud Gateway**
- Routes: `/auth/**` → auth-service, `/users/**` → user-service, `/products/**` →
  catalog-service, etc. (path-based routing)
- Add a `JwtAuthenticationFilter` (a `GlobalFilter`) that:
  1. Skips `/auth/**` (public)
  2. For everything else, extracts `Authorization: Bearer <token>`, validates it using
     the same `JwtUtil` logic as Auth Service (share via a small shared library, or
     duplicate for now — don't over-engineer on day 1)
  3. On success, forwards request with `X-User-Id` and `X-User-Role` headers so
     downstream services don't need to re-validate JWTs themselves
- Add rate limiting (`RequestRateLimiter` filter + Redis) once basic routing works

---

## 11. Suggested Weekly Milestones

| Week | Goal |
|------|------|
| 1 | Auth Service fully working + tested (register/login/refresh) |
| 2 | User Service + Catalog Service (CRUD, pagination, filters) |
| 3 | Inventory Service (with optimistic locking) + Cart Service |
| 4 | Order Service calling Cart/Inventory/Payment synchronously end-to-end |
| 5 | Payment Service (strategy pattern) + Notification Service |
| 6 | API Gateway wiring everything together + JWT propagation |
| 7 | Add Kafka events (OrderCreated etc.), swap sync calls for async where it matters |
| 8 | Observability (Actuator + Prometheus + Grafana), Testcontainers integration tests |

---

## 12. What NOT to do on day one
- Don't set up Kafka before you have Order Service calling things synchronously and
  working. Async makes debugging much harder — earn the complexity.
- Don't build the API Gateway first. You'll spend all your time debugging routing
  instead of business logic.
- Don't try to make Inventory/Payment "perfectly" idempotent and Saga-compliant before
  the happy path works. Get it correct, then get it resilient.
- Don't share one database across services, even though it would be faster to wire up.
  It'll bite you later when you actually need independent scaling/deployment.

---

## 13. Quick Reference — Layering Every Service Follows

```
Controller   → handles HTTP, validation (@Valid), maps to/from DTOs
Service      → business logic, transactions (@Transactional), orchestration
Repository   → Spring Data JPA interface, no logic
Entity/Model → JPA-mapped table
DTO          → request/response records, never expose entities directly
Exception    → @ControllerAdvice + custom exceptions → clean error JSON
Config       → SecurityConfig, CacheConfig, WebClientConfig, etc.
```

Standard error response shape to use in every service's `GlobalExceptionHandler`:
```java
public record ErrorResponse(String error, String message, Instant timestamp) {}
```

---

Start with Phase 1 (Auth Service). Get `/auth/register` and `/auth/login` fully
working and tested before touching anything else — everything downstream depends on it.