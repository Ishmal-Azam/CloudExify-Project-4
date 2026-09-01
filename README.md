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

# Dashboard
<img width="1365" height="727" alt="dashboard" src="https://github.com/user-attachments/assets/0c47e744-7f03-4b89-8d6c-3791f2f585b8" />

# Shop
- <img width="1118" height="691" alt="shop 1" src="https://github.com/user-attachments/assets/586ac32f-3f17-4676-820b-c73cdffc6920" />
- <img width="1097" height="616" alt="shop 2" src="https://github.com/user-attachments/assets/691eddf0-4569-49cf-8cad-c6a3af4cb06d" />

# By Search
<img width="1099" height="670" alt="search" src="https://github.com/user-attachments/assets/3e74341d-1796-4047-b39f-dc28d2b22ab8" />

# Add to cart
<img width="425" height="330" alt="add to cart" src="https://github.com/user-attachments/assets/31ee5f7e-1058-4dfb-a214-9eb51e694332" />
<img width="414" height="292" alt="successfull" src="https://github.com/user-attachments/assets/1f39aac0-df95-4990-9212-d8354cfaeb9d" />

# Shopping Cart Dashboard
<img width="1362" height="727" alt="shopping cart" src="https://github.com/user-attachments/assets/80eda680-0b67-492f-964f-9d38fa6d742a" />

# Checkout details
<img width="650" height="706" alt="checkout" src="https://github.com/user-attachments/assets/396607cf-853b-4e51-881c-4ebc584c74ab" />
<img width="487" height="419" alt="confirm" src="https://github.com/user-attachments/assets/6da25c6a-a0cc-478e-b8f0-fe7f9e9ab77b" />
<img width="486" height="394" alt="confirm 1" src="https://github.com/user-attachments/assets/81a91b33-2c1e-4be2-905c-6792e26e127a" />

# Order Dashboard
<img width="1365" height="724" alt="your order" src="https://github.com/user-attachments/assets/248f0467-7589-4d85-9fcc-cdb94a8ff893" />

## After order home page updates
<img width="1096" height="408" alt="dashboard update" src="https://github.com/user-attachments/assets/6c7a49fd-4571-42d6-a83d-f8fca09c1a3c" />

