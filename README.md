# Book Library And LookUp Service
A simple backend for a storing a booklist and searching for books using springboot and Google Oauth.

You will need to add a client_id and client_secret as well as a token_uri for the Oauth to work.
You will also need a google api key to access the google book api. This is to serve as the api that will handle request from the frontend of the application.

This project requires a MySQL database to be up and running the database's
information can be customized in the application properties file.

The database is structured so that there are three required tables users, book_list and authorities. 

The users table is made up of two columns
user_name and user_email, where the primary key is user_name. 

The book_list table is made up of two columns, user_name and book_ibsn, where the primary key is a composition of both columns.

The authorities columns is unused, currently but will be used in further iterations of this project to differentate between admins and regular users and what access they have to the api. It will be made up of the columns' user_name and role.