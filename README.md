# CloudExify-Project-4
# E-Commerce | NOVA

A modern **Java-based E-Commerce application** built using **Java Swing**.  
The project provides a simple and interactive shopping experience with a dark-themed graphical user interface.

---

## Project Overview

**E-Commerce | NOVA** is a desktop based e-commerce application developed in Java. It allows customers to browse products, search and filter products by category, add items to a shopping cart, and place orders through a checkout process.

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
The bin folder contains compiled .class files and is excluded from GitHub using .gitignore.
```
## Technology Used

| Technology | Purpose |
|---|---|
| **Java** | Main programming language |
| **Java Swing** | Building the graphical user interface |
| **Object-Oriented Programming (OOP)** | Structuring the application using classes, inheritance, encapsulation, polymorphism, and abstraction |
| **Java Collections** | Managing products, cart items, and orders |
| **Exception Handling** | Handling errors such as invalid payments and insufficient stock |
| **VS Code** | Development and coding environment |
| **Git & GitHub** | Version control and project management |

## ▶️ How to Run
### Prerequisites

Make sure you have:

- Java JDK installed
- VS Code or any Java-compatible IDE
- Git (optional, if cloning from GitHub)

### Run in VS Code

1. Clone or download the project from GitHub.
2. Open the `ECommerceDark` folder in VS Code.
3. Open the `src` folder.
4. Open `ECommerceApp.java`.
5. Click the **Run ▶** button in VS Code.

### Run Using Terminal

Open the terminal in the project folder and run:

```bash
cd src
javac *.java
java ECommerceApp
```
## Exception Handling

The project uses Java exception handling to manage errors and prevent the application from crashing during invalid operations.

### Custom Exceptions

#### `OutOfStockException`

This exception is used when a customer tries to add or purchase a quantity greater than the available product stock.

#### `InvalidPaymentException`

This exception is used when an invalid payment operation occurs during checkout.

### Exception Handling Approach

The application uses:

- `try-catch` blocks
- Custom exception classes
- Input validation
- Error messages using `JOptionPane`

These mechanisms help provide a better and safer user experience.

## Future Improvements

The project can be further improved by adding:

- User registration and login system
- MySQL database integration
- Admin dashboard
- Advanced inventory management
- Product images
- Advanced product search and filtering
- Product reviews and ratings
- Discount and coupon system
- Order tracking
- Real online payment gateway integration
- PDF invoice generation
- Customer profile management
- Sales and order reports
- Cloud-based data storage
