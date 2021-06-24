Do uruchomienia aplikacji potrzeba jest baza PostgreSQL 

Polecenia potrzebne do utworzenia bazy:

1. docker pull postgres
   
2. docker run -p 5432:5432 -d -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=postgres -v pgdata:/var/lib/postgresql/data postgres