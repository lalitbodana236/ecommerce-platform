# 🛒 E-Commerce Backend System (Spring Boot Microservices)

## 📌 Overview

This project is a **production-grade backend system** for an e-commerce platform built using **Spring Boot and Microservices Architecture**.

The system is designed to handle:

* High traffic and scalability
* Fault tolerance and resilience
* Event-driven workflows
* Clear domain-driven service boundaries

---

## 🏗️ Architecture

```
Client → API Gateway → Microservices

Microservices:
- user-service
- product-service
- cart-service
- order-service
- payment-service
- notification-service

Infrastructure:
- Service Registry (Eureka)
- Kafka (Event Streaming)
- Redis (Caching)
- Database per service
```

---

## 📦 Microservices & APIs

---

## 👤 User Service (`user-service`)

**Base Path:** `/api/v1/users`
**Responsibility:** Authentication & User Management

### APIs

#### Authentication

```
POST /api/v1/users/register
POST /api/v1/users/login
```

#### Profile Management

```
GET    /api/v1/users/{userId}
PUT    /api/v1/users/{userId}
DELETE /api/v1/users/{userId}
```

#### Address Management

```
POST   /api/v1/users/{userId}/addresses
GET    /api/v1/users/{userId}/addresses
PUT    /api/v1/users/{userId}/addresses/{addressId}
DELETE /api/v1/users/{userId}/addresses/{addressId}
```

---

## 🛍️ Product Service (`product-service`)

**Base Path:** `/api/v1/products`
**Responsibility:** Product Catalog & Inventory

### APIs

#### Product CRUD

```
POST   /api/v1/products
GET    /api/v1/products/{productId}
PUT    /api/v1/products/{productId}
DELETE /api/v1/products/{productId}
```

#### Product Discovery

```
GET /api/v1/products
GET /api/v1/products?category=electronics
GET /api/v1/products/search?q=iphone
```

#### Inventory Management (Internal)

```
GET  /api/v1/products/{productId}/availability
POST /api/v1/products/{productId}/reserve
POST /api/v1/products/{productId}/release
```

---

## 🧺 Cart Service (`cart-service`)

**Base Path:** `/api/v1/carts`
**Responsibility:** User Cart (Redis-based)

### APIs

```
GET    /api/v1/carts/{userId}
POST   /api/v1/carts/{userId}/items
PUT    /api/v1/carts/{userId}/items/{productId}
DELETE /api/v1/carts/{userId}/items/{productId}
DELETE /api/v1/carts/{userId}
```

---

## 📦 Order Service (`order-service`)

**Base Path:** `/api/v1/orders`
**Responsibility:** Order Lifecycle & Orchestration

### APIs

#### Create Order

```
POST /api/v1/orders
```

**Request Example**

```json
{
  "userId": "u1",
  "items": [
    { "productId": "p1", "quantity": 2 }
  ],
  "addressId": "a1"
}
```

---

#### Get Order

```
GET /api/v1/orders/{orderId}
GET /api/v1/orders?userId=u1
```

---

#### Update Order Status (Internal)

```
PATCH /api/v1/orders/{orderId}/status
```

**Statuses**

* CREATED
* PENDING_PAYMENT
* PAID
* FAILED
* SHIPPED

---

#### Cancel Order

```
POST /api/v1/orders/{orderId}/cancel
```

---

## 💳 Payment Service (`payment-service`)

**Base Path:** `/api/v1/payments`
**Responsibility:** Payment Processing

### APIs

#### Initiate Payment

```
POST /api/v1/payments
```

**Request Example**

```json
{
  "orderId": "o123",
  "amount": 500
}
```

---

#### Payment Status

```
GET /api/v1/payments/{paymentId}
GET /api/v1/payments/order/{orderId}
```

---

#### Payment Callback (External Gateway)

```
POST /api/v1/payments/callback
```

---

## 🔔 Notification Service (`notification-service`)

**Base Path:** `/api/v1/notifications`
**Responsibility:** Async Notifications

### APIs

```
POST /api/v1/notifications/email
POST /api/v1/notifications/sms
GET  /api/v1/notifications/{userId}
```

> Note: In production, notifications are Kafka-driven.

---

## 🔄 Service Communication

### Synchronous (REST / Feign)

* Order → Product (inventory check)
* Order → Payment (process payment)

### Asynchronous (Kafka)

* Order Created → Notification Service
* Payment Success → Order Update

---

## 🔐 Internal vs External APIs

### External APIs (Client-facing)

```
/api/v1/users/**
/api/v1/products/**
/api/v1/carts/**
/api/v1/orders/**
/api/v1/payments/**
```

### Internal APIs (Service-to-service)

```
/internal/products/{id}/reserve
/internal/orders/{id}/update-status
```

---

## ⚡ Key Design Decisions

* **Database per service** → Loose coupling
* **Order Service as orchestrator**
* **Kafka for event-driven workflows**
* **Redis for caching and cart storage**
* **API Gateway for centralized routing**

---

## 🚀 Advanced Features

### Idempotency

```
POST /payments
Header: Idempotency-Key
```

### Pagination

```
GET /products?page=0&size=10
```

### Filtering & Sorting

```
GET /products?category=electronics&sort=price,asc
```

---

## 📊 Order Flow

```
1. Client → POST /orders
2. Order → Product Service (check stock)
3. Order → Payment Service
4. Payment → Kafka Event
5. Notification Service consumes event
```

---

## 🧠 Interview Summary

This system is designed using:

* Domain-driven microservices
* Clear service ownership
* Hybrid communication (sync + async)
* Event-driven architecture for scalability
* Resilience patterns (retry, circuit breaker, idempotency)

---

## 📌 Future Enhancements

* Distributed tracing (Zipkin)
* Centralized logging (ELK)
* Kubernetes deployment
* Saga pattern for distributed transactions

---

## 🏁 Conclusion

This architecture ensures:

* Scalability
* Fault tolerance
* Maintainability
* Clear separation of concerns

---
