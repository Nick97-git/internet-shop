# Internet-shop project
# Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Author](#author)

# <a name="purpose"></a>Project purpose
Creation of internet-shop with basic operations required for it.
<hr>
Everyone can see all available products in the store. Users can register and then log-in for
getting possibilities to add products to their personal shopping cart and make orders. Also, user can 
log out. 
There are specific urls for admins only: editing list of available products in the internet-shop and viewing 
a list of all registered users and delete them.
<hr>

# <a name="structure"></a>Project Structure
* Java 11
* Maven 3.6.0
* maven-checkstyle-plugin 3.1.1
* maven-war-plugin 3.2.3
* javax.servlet-api 3.1.0
* jstl 1.2
* mysql-connector-java 8.0.17
* log4j 1.2.17
<hr>

# <a name="developer-start"></a>For developer

1. Open the project in your IDE.

2. Choose sdk 11.0.2 in Project Structure.

3. Configure Tomcat:
add the artifact internet-shop:war exploded;
add as URL http://localhost:8080/

4. Start MySQLWorkbench.

5. At internet-shop.src.main.java.dev.internet.shop.util.ConnectionUtil class use your username 
and password for your MySQLWorkbench to create a Connection.

6. Run internet-shop.src.main.resources.init_db.sql to create all the tables required by this app.

7. Change a path to log file in internet-shop.src.main.resources.log4j.properties.

8. Run the project.

There are test data that you can use.
There’s one user already registered with ADMIN role (login = "admin"", password = 123) and
one user with USER role (login = "alice"", password = 123).
Also, there are three items in the store: bread, milk, coca-cola.

# <a name="author"></a>Author

Mykyta Arkhanhelskyi: https://github.com/Nick97-git


