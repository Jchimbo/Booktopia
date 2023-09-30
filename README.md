# Booktopia
![Alt text](bookshelf.png)
A full-stack website for storing a booklist and searching for books using Springboot, Google Oauth, and the Open Book API. I first started developing this application as a way to keep track of books I was interested in, without the hassle of writing down title names or screenshoting covers and having them lost in my phone storage. This app would serve as a dedicated bookshelf for the books that I'd be interested in picking up at a later date. I plan to implement a recommendation system using book genres and rating scores.

## Getting Started
After cloning and going through the requirments simply run <code>mvn install</code> to run with test or <code>  mvn install -DskipTests</code> to run without 
test. The app should start on port 8080. If you have not setup a domain it should be localhost:8080.

## Requirements

### MySQL
Please use this guide to setup [MYSQL](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-22-04).

#### Manual Database Setup
This project requires a MySQL database to be up and running the database's
information can be customized in the application properties file located in /src/main/java/resources.
The database names can be altered to different names but they must be replace in the tomcatDBConfig and the openlibDatabaseConfig files. The username and password need to be replaced in the lines altered section below. 

Lines to be altered:
<ol>
  <li>spring.datasource.jdbcurl=jdbc:mysql://localhost:3306/tomcat</li>
  <li>spring.datasource.username=username</li>
  <li>spring.datasource.password=password</li>
  <li>book.url.customProperty=http://localhost/book/</li>
  <li>spring.openlib-datasource.url=jdbc:mysql://localhost:3306/openlibrary</li>
  <li>spring.openlib-datasource.username=username</li>
  <li>spring.openlib-datasource.password=password</li>
  <li>book.url.openlib-customProperty=http://localhost/book/</li>
</ol>


Create default database:
This will be called tomcat in folders and in text those spots need to be replaced.

<code>CREATE DATABASE '`<tomcat>`';</code>
<code>CREATE DATABASE '`<openlibrary>`';</code>

Create default user:

<code>CREATE USER username@localhost IDENTIFIED BY password';</code>

Grant Permissions on tomcat & openlibrary database:

<code>GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT ON tomcat.* TO username@localhost; </code>

<code> GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT ON openlibrary.* TO username@localhost;</code>


To create it login to mysql using the user information made above: <code>sudo mysql -u username -p  </code>

The database is structured so that there are two required tables users, and book_list. 

The users table is made up of two columns
user_name and user_email, where the primary key is user_name. 

<code>USE  '`<tomcat>`';</code>

To create this table: <code> CREATE TABLE users(user_name varchar(255) NOT NULL, user_email varchar(255) NOT NULL, PRIMARY KEY (user_email));</code>

The book_list table is made up of two columns, user_email and book_ibsn, where the primary key is a composition of both columns.

To create this table: <code> CREATE TABLE book_list(user_email varchar(255) NOT NULL, book_isbn varchar(255) NOT NULL,PRIMARY KEY (user_email,book_isbn));


<code>USE  '`<openlibrary>`';</code>
You will need the books from the books dump in the openlibrary website I will update this tutorial with those instructions at a later point. 
To create this table: <code> CREATE TABLE books(isbn varchar(18) NOT NULL, title varchar(1852), author varchar(1216), descript TEXT, PRIMARY KEY (isbn));</code>


### GOOGLE OAUTH
Please follow these steps to get a client_id, client_secret, and a token_uri, [GOOGLE OAUTH](https://developers.google.com/identity/protocols/oauth2).
