--Drop table BookBorrow;
--Drop table BookAuthor;
--Drop table Book;
--Drop table Location;
--Drop table Category;
--Drop table UserRole;
--Drop table Role;
--Drop table LibraryUser;
--Drop table Writer;


create table Author (
id BIGINT NOT NULL AUTO_INCREMENT,
firstName VARCHAR(255),
lastName VARCHAR(255),
PRIMARY KEY(id));

create table User (
id BIGINT NOT NULL AUTO_INCREMENT,
username VARCHAR(255),
password VARCHAR(255),
firstName VARCHAR(255),
lastName VARCHAR(255),
phoneNumber VARCHAR(255),
address VARCHAR(255),
removed BOOL,
PRIMARY KEY(id));

create table UserRole (
id BIGINT NOT NULL AUTO_INCREMENT,
userId BIGINT,
roleName VARCHAR(255),
PRIMARY KEY(id),
FOREIGN KEY(userId) REFERENCES User(id));

create table Category (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
description VARCHAR(255),
PRIMARY KEY(id));

create table Location (
id BIGINT NOT NULL AUTO_INCREMENT,
rackSymbol VARCHAR(255),
shelfSymbol VARCHAR(255),
position BIGINT,
PRIMARY KEY(id));

create table Book (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
isbn VARCHAR(255),
format VARCHAR(255),
category BIGINT,
location BIGINT,
removed BOOL,
PRIMARY KEY(id),
FOREIGN KEY(category) REFERENCES Category(id),
FOREIGN KEY(location) REFERENCES Location(id));

create table BookAuthor (
id BIGINT NOT NULL AUTO_INCREMENT,
bookId BIGINT,
authorId BIGINT,
PRIMARY KEY(id),
FOREIGN KEY(bookId) REFERENCES Book(id),
FOREIGN KEY(authorId) REFERENCES Author(id));

create table BookBorrow (
id BIGINT NOT NULL AUTO_INCREMENT,
userId BIGINT,
bookId BIGINT,
borrowDate DATETIME,
returnDate DATETIME,
PRIMARY KEY(id),
FOREIGN KEY(userId) REFERENCES User(id),
FOREIGN KEY(bookId) REFERENCES Book(id));





INSERT INTO User (username, password, firstName, lastName, phoneNumber, address, removed) 
	VALUES ('Doe', 'Password', 'John', 'Doe', '9011223344', 'Nowhere St. 2, Nothingtown', false);
INSERT INTO User (username, password, firstName, lastName, phoneNumber, address, removed) 
	VALUES ('Lovelace', 'Ockham', 'Ada', 'Lovelace', '', 'Londyn', false);
INSERT INTO User (username, password, firstName, lastName, phoneNumber, address, removed) 
	VALUES ('Babbage', 'ITfather', 'Charles', 'Babbage', '', 'Walworth, Londyn', true);

INSERT INTO UserRole(userId, roleName) VALUES (1, 'ADMIN');
INSERT INTO UserRole(userId, roleName) VALUES (1, 'READER');
INSERT INTO UserRole(userId, roleName) VALUES (2, 'READER');
INSERT INTO UserRole(userId, roleName) VALUES (3, 'ADMIN');


INSERT INTO Category(name, description) VALUES ('Novel', 'Book category for novels');
INSERT INTO Category(name, description) VALUES ('Technical', 'Book category for technical related books');
INSERT INTO Category(name, description) VALUES ('Fantasty', 'Book category for fantasty books');


INSERT INTO Author(firstName, lastName) VALUES ('Robert Cecil', 'Martin');
INSERT INTO Author(firstName, lastName) VALUES ('Eric', 'Evans');
INSERT INTO Author(firstName, lastName) VALUES ('Joshua', 'Bloch');
INSERT INTO Author(firstName, lastName) VALUES ('Kent', 'Beck');
INSERT INTO Author(firstName, lastName) VALUES ('Martin', 'Fowler');


INSERT INTO Location(rackSymbol, shelfSymbol, position) VALUES ('C', 'III', 12);
INSERT INTO Location(rackSymbol, shelfSymbol, position) VALUES ('C', 'III', 13);
INSERT INTO Location(rackSymbol, shelfSymbol, position) VALUES ('C', 'III', 14);
INSERT INTO Location(rackSymbol, shelfSymbol, position) VALUES ('D', 'V', 2);
INSERT INTO Location(rackSymbol, shelfSymbol, position) VALUES ('G', 'XIX', 231);
INSERT INTO Location(rackSymbol, shelfSymbol, position) VALUES ('G', 'XIX', 233);
INSERT INTO Location(rackSymbol, shelfSymbol, position) VALUES ('G', 'XIX', 238);
INSERT INTO Location(rackSymbol, shelfSymbol, position) VALUES ('G', 'XIX', 239);


INSERT INTO Book(name, isbn, format, category, location, removed) 
	VALUES ('Clean code', '978-83-283-0234-1', 'B5', 2, 1, 0);
INSERT INTO Book(name, isbn, format, category, location, removed) 
	VALUES ('The Clean Coder', '978-83-246-7534-0', 'B5', 2, 2, 0);
INSERT INTO Book(name, isbn, format, category, location, removed) 
	VALUES ('Domain-Driven Design', '978-83-246-7534-0', 'B5', 2, 3, 0);
INSERT INTO Book(name, isbn, format, category, location, removed) 
	VALUES ('Effective Java', '978-83-283-3311-6', 'B5', 2, 4, 0);
INSERT INTO Book(name, isbn, format, category, location, removed) 
	VALUES ('Test Driven Development: by example', '978-83-246-8500-4', 'B5', 2, 5, 0);
INSERT INTO Book(name, isbn, format, category, location, removed) 
	VALUES ('Refactoring: Improving the Design of Existing Code', '978-83-283-3455-7', 'B5', 2, 6, 0);
INSERT INTO Book(name, isbn, format, category, location, removed) 
	VALUES ('No SQL Destilled', '978-83-246-9905-6', 'B5', 2, 7, 0);
INSERT INTO Book(name, isbn, format, category, location, removed) 
	VALUES ('Patterns of Enterprise Application Architecture', '83-7361-715-9', 'B5', 2, 8, 0);


INSERT INTO BookAuthor(bookId, authorId) VALUES (1, 1);
INSERT INTO BookAuthor(bookId, authorId) VALUES (2, 1);
INSERT INTO BookAuthor(bookId, authorId) VALUES (3, 2);
INSERT INTO BookAuthor(bookId, authorId) VALUES (4, 3);
INSERT INTO BookAuthor(bookId, authorId) VALUES (5, 4);
INSERT INTO BookAuthor(bookId, authorId) VALUES (6, 4);
INSERT INTO BookAuthor(bookId, authorId) VALUES (6, 5);
INSERT INTO BookAuthor(bookId, authorId) VALUES (7, 5);
INSERT INTO BookAuthor(bookId, authorId) VALUES (8, 5);


INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (1, 2, '2017-07-11 13:04:55' , '2017-07-13 17:23:11');
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (1, 3, '2017-07-13 17:25:36' , null);
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (1, 8, '2017-07-13 17:25:36' , null);
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (2, 2, '2017-08-25 07:24:32' , null);
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (2, 7, '2017-08-30 11:21:35' , null);
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (3, 1, '2016-07-28 21:10:00' , '2016-08-05 9:48:30');
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (3, 2, '2016-07-28 21:10:00' , '2016-08-05 9:48:30');
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (3, 3, '2016-08-05 9:52:00' , '2016-08-14 16:52:30');
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (3, 1, '2016-08-18 16:16:00' , '2016-08-26 17:12:30');
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (3, 4, '2016-08-18 16:16:00' , '2016-08-26 17:12:30');
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (3, 5, '2016-08-18 16:16:00' , '2016-08-26 17:12:30');
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (3, 6, '2016-08-18 16:16:00' , '2016-08-26 17:12:30');
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (3, 3, '2016-09-01 06:08:00' , '2016-09-09 14:28:30');
INSERT INTO BookBorrow(userId, bookId, borrowDate, returnDate) 
	VALUES (3, 7, '2016-09-01 06:08:00' , '2016-09-09 14:28:30');

