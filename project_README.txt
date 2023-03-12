# Progetto Smart Vending Machine (SVM)

Configurazione : 

	Requisiti : 
		
		- Java SDK 

		- Eclipse IDE

		- Apache Tomcat

		- MySQL

	Testing :

		1. Importare il progetto "project.war" in Eclipse IDE
		
		2. Importare il database "db.sql" in mySQL

		3. Modificare il file "context.xml" inserendo in Context nella Resource 
		   con name = "jdbc/myDataSource" username e password del proprio server mySQL

		4. Database : 
		   - 6 records user (1 admin, 2 tecnici e 3 clienti) con credito differente
		   - 2 records machine
		   - 10 records product	 
		   - 10 records stock (il distributore #1 è rifornito, il #2 è vuoto)

		5. Credenziali di accesso : 

		   	Email utenti : 

		   		- Admin (ID 1) -> mario.rossi@gmail.com
		 	  
		   		- Tecnico (ID 2) -> antonio.bianchi@gmail.com

				- Tecnico (ID 3) -> francesco.verdi@gmail.com

				- Cliente (ID 4) -> giuseppe.rossi@gmail.com

				- Cliente (ID 5) -> sofia.bianchi@gmail.com

				- Cliente (ID 6) -> aurora.verdi@gmail.com
			
			ID distributori : 
	
				- Distributore #1 -> 1

				- Distributore #2 -> 2

				N.B. I distributori vengono registrati con ID incrementale automatico.
			
		   	
			Password (per tutti gli utenti e distributori) -> Pass1234  	
