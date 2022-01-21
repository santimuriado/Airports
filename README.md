# Airports

Airports is a back-end focused project with minimal front-end simulating a system that manages multiple airports, planes and users. Users can register themselves
log-in and even purchase tickets. As the front-end is extremely minimal most of the functionality is only accessible by an API platform like Postman.
Airports uses a postgreSQL database and form login based security. 
(Testing is currently WIP)

# Requirements

Airports uses java 11 and a web interface for viewing and testing emails during development called maildev. For instructions on installing and using maildev
go to https://github.com/maildev/maildev.
It also requires access to a database using PostgreSQL called "airports", however this can be changed in the "application.yml" file. POSTUSERNAME and
POSTPASSWORD are env variables that must be set for it to access the database properly.

# Run the Program

+ Clone the repo:

      git clone https://github.com/santimuriado/Airports.git
    
+ Run maildev:

      maildev
      
+ Start the program as you would run any java program

# API:

You can either log-in with username:admin@gmail.com password:password or username:user@gmail.com password:password. With user@gmail.com most of the functionality
is blocked for not having the required authorities. However you can access every function with the admin@gmail.com user so it's recommended that the admin is used
to access everything.
The username attribute for an appuser is mostly useless as the email is used to login.

# Endpoints:

Every endpoint starts with /api/v1 except the login.

GET /airport - Get all airports

GET /ticket - Get all tickets

GET /plane - Get all planes

GET /appusers - Get all users

GET /airport/:id/planes - Get a given airport planes

POST /airport/save - Register a new airport

POST /plane/save - Register a new plane

POST /registration - Register a new user

POST /login - Login

POST /appusers/purchaseticket - Purchase a ticket for a user

DELETE /airport/:id - Delete a given airport

DELETE /ticket/:id - Delete a given ticket

There are more endpoints but to keep the readme a little cleaner I decided to not include all of them and only the main ones.
