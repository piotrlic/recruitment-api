#recruitment-api
#Problem to implement
We would like to ask you to create a backend app exposing a functionality via a
RESTful API (together with business logic and data layer) which manages two
entities: a user and job offers created by this user.

####Details:

User - consists of the following fields: login, password, name, account creation date.
We ask you for a typical CRUD (create-read-update-delete) for this entity.
Job offer - consists of the following fields: category (one of the following: IT, Food &
Drinks, Office, Courier, Shop assistant), start date, end date, name of employer (name
of User). We ask you for two methods providing the following functionalities:
- Saving - saves job offer entity
- Reading - get a list of current valid job offers, you can filter the list by employer’s
  name or category

#Tasks already done
I focused on basic functionality to prepare it for frontend team to be used. 
- basic CRUD for User.
- 2 methods for Offers controller to save and read with filtering.

#What more should be added
- Replace H2 with other DB implementation 
- Liquibase or other DB versioning system (currently DDL with example data is in data.sql file)
- Unit test and Integration tests to check functionalities on real DB
- Swagger to show API to the potential users
- create separate authentication service based on OAuth 

