# CloudExify-Project-4
# E-Commerce Dark

A modern **Java-based E-Commerce application** built using **Java Swing**.  
The project provides a simple and interactive shopping experience with a dark-themed graphical user interface.

---

## Project Overview

**E-Commerce Dark** is a desktop-based e-commerce application developed in Java. It allows customers to browse products, search and filter products by category, add items to a shopping cart, and place orders through a checkout process.

The application focuses on combining **Object-Oriented Programming concepts** with a modern and user-friendly GUI.

---

## Features

### Home
- Modern dashboard-style home page
- Featured products
- Quick access to the shopping section
- Store statistics

### Shop
- View all available products
- Browse products by category
- Categories include:
  - Electronics
  - Books
  - Clothing
- Search products by name

### Product Search
- Search products quickly
- Display matching products
- Automatically restore the full product list when the search is cleared

### Shopping Cart
- Add products to cart
- Select desired quantity
- View cart items
- Calculate item subtotals
- Calculate total cart amount
- Remove items from cart
- Clear cart after successful order placement

### Orders
- Place customer orders
- Generate unique Order IDs
- View previously placed orders
- Display order status
- Display order details and total amount

### Checkout
- Select payment method
- Enter delivery information
- Confirm order before placement
- Validate required customer information
- Generate an order confirmation
- Generate a receipt

### User Interface
- Modern dark theme
- Purple accent colors
- Sidebar navigation
- Category navigation buttons
- Product cards
- Interactive buttons
- Clean and organized layout

---

## Object-Oriented Programming Concepts

This project demonstrates several important Java OOP concepts:

- **Classes and Objects**
- **Inheritance**
- **Encapsulation**
- **Polymorphism**
- **Abstraction**
- **Interfaces**
- **Exception Handling**
- **Collections**
- **File Handling**

---

## Project Structure

All Java files are kept directly inside the `src` folder to keep the project simple and easy to run.

```text
ECommerceDark/
│
├── src/
│   │
│   ├── Product.java
│   ├── Electronics.java
│   ├── Book.java
│   ├── Clothing.java
│   │
│   ├── CartItem.java
│   ├── ShoppingCart.java
│   ├── Customer.java
│   ├── Order.java
│   │
│   ├── Payment.java
│   ├── Purchasable.java
│   ├── Receipt.java
│   ├── StoreService.java
│   │
│   ├── OutOfStockException.java
│   ├── InvalidPaymentException.java
│   │
│   └── ECommerceApp.java
│
├── README.md
├── .gitignore
└── bin/
               
