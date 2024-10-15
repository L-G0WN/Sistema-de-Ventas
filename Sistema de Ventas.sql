CREATE DATABASE IF NOT EXISTS Sistema_de_Ventas;

USE Sistema_de_Ventas;

CREATE TABLE IF NOT EXISTS Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    question VARCHAR(255) NOT NULL,
    answer VARCHAR(255) NOT NULL,
    account_type INT NOT NULL,  
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Users (username, password, firstname, lastname, question, answer, account_type) 
VALUES ('Monagas', '12345', 'Héctor', 'Monagas', '¿CUÁL ES TU PELÍCULA FAVORITA?', 'Harry Potter', 1);