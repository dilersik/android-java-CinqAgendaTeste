The premise of this test is to develop an application where it is possible to perform a user registration, informing E-mail and Password, and from this register, it is possible to enter the application, performing an Authentication process.

The minimum / expected requirements for challenge delivery are listed below:

1 - Develop an application using the following standards:
MVP Architecture 
SQLite
Storage Preferences
HTTP connection - REST

2 - Develop a screen where it is possible to perform the Login, containing the following fields:
Email
password
Login button
Link to the Registration Screen

3 - Develop a screen where it is possible to register the user, persisting in the App's local database (SQLite), containing the following fields:
Email
Name
password
Save button

4 - Develop a home screen, after Login, where you can view the following information:
Username Password
List of Registered Users in the Application
Ability to filter the above list
Button to Add New Users, redirecting to the screen of Step 2
Edit Users button, redirecting to the screen in Step 2
Button to Delete a User, giving a confirmation before performing the action

5 - In the initial screen a menu must be presented with the following options:
Home
Album List

6 - Develop a screen for the Album List containing the following fields:
Use an adapter in the list displaying only the title

Business rules

1 - Data Types:
E-mail String (50) Not Null
Name String (50) Not Null
Password String (50) Not Null

2 - On Login screen give feedback of required fields and inform user about invalid credentials
3 - In the Sign Up screen give feedback of required fields and message of success when there is such
4 - In the initial screen, the filter field must be of type text with maximum size of 50 characters; in real-time typing, the list must be updated;
5 - When editing a user it should not be possible to change the E-mail, only Name and Password
6 - When deleting a user, if the record to be deleted is the same as the logged in user, there should be feedback preventing the action
7 - On the Album List screen, the entities to be displayed should come from the following address: https://jsonplaceholder.typicode.com/albums, using HTTP protocol
