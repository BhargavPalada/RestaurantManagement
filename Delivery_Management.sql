CREATE database DELIVERY_MANAGEMENT;
USE DELIVERY_MANAGEMENT;
CREATE TABLE customers (
    cust_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(15),
    password VARCHAR(50)
);

CREATE TABLE menu (
    menu_id INT,
    item_name VARCHAR(255),
    item_price DECIMAL(10, 2),
    PRIMARY KEY (item_name, menu_id)
);

CREATE TABLE restaurants (
    restaurant_id INT PRIMARY KEY,
    menu_id INT,
    name VARCHAR(255)
);
CREATE TABLE payment (
    order_id INT,
    transaction_no VARCHAR(50),
    PRIMARY KEY (order_id)
);
CREATE TABLE suborder (
    item_name VARCHAR(255),
    order_id INT,
    item_price DECIMAL(10, 2),
    quantity INT,
    amount DECIMAL(10, 2),
    restaurant_id INT,
    order_date DATE,
    cust_id INT,
    PRIMARY KEY (item_name, order_id)
    
    
);

Select * from payment;
Select * from customers ;
Select * from menu ;
Select * from restaurants;
Select * from suborder;


Insert into menu values( 1, "Coffe", 10.00);
Insert into menu values( 2, "Tea", 10.00);
Insert into menu values( 3, "Dosa", 25.50);
Insert into menu values( 4, "Idly", 15.00);
Insert into menu values( 5, "Juice", 30.00);
Insert into menu values( 6, "Milkshake", 45.00);

Insert into restaurants values(1,1,"Coffe");
Insert into restaurants values(2,2,"Tea");
Insert into restaurants values(3,3,"Dosa");
Insert into restaurants values(4,4,"Idly");
Insert into restaurants values(5,5,"Juice");
Insert into restaurants values(6,6,"Milkshake");



