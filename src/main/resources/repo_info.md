# 📂 Metodi Ereditati da JpaRepository

Estendendo `JpaRepository<User, Long>`, l'interfaccia eredita automaticamente i seguenti metodi CRUD, pronti all'uso senza scrivere query SQL:

### 1. 📖 Lettura dei dati (Read)
* **`findAll()`**: Recupera tutte le righe della tabella. Ritorna una `List<User>`.
* **`findById(Long id)`**: Cerca una riga specifica tramite la chiave primaria. Ritorna un `Optional<User>`.
* **`existsById(Long id)`**: Controlla se un ID esiste nel DB (restituisce `true`/`false`) con una query ottimizzata e velocissima.

### 2. ✍️ Scrittura e Modifica (Create / Update)
* **`save(User entity)`**: Svolge un doppio ruolo:
  * **Create**: Se l'ID dell'oggetto è `null`, esegue una `INSERT` SQL.
  * **Update**: Se l'ID è presente, esegue una `UPDATE` SQL sovrascrivendo i dati.
* **`saveAll(Iterable<User> entities)`**: Salva una lista di entità in un colpo solo.

### 3. ❌ Cancellazione (Delete)
* **`deleteById(Long id)`**: Rimuove il record associato a quell'ID (`DELETE FROM...`).
* **`delete(User entity)`**: Cancella il record passandogli direttamente l'oggetto Entity.
* **`deleteAll()`**: Svuota completamente la tabella.

### 4. 📊 Utility
* **`count()`**: Restituisce un numero `long` che indica il totale delle righe presenti nella tabella.

---

# 📂 Architettura dei Dati in Spring Boot: "Monoriga" vs "Manuale"

L'ecosistema di Spring si divide in due grandi filosofie quando si tratta di comunicare con i database: il mondo **Spring Data** (dove basta una riga di interfaccia) e il mondo **JDBC** (dove si scrive il codice a mano).

---

## 🟩 Fazione 1: I Database "Monoriga" (Spring Data)

In questa fazione non devi scrivere codice per le operazioni base. Crei un'interfaccia vuota, estendi il Repository specifico di Spring Data, e hai immediatamente pronti tutti i metodi CRUD (`save()`, `findAll()`, `findById()`, `delete()`).

### 1. Database Relazionali (SQL / Tabelle)
*Estendono tutti `JpaRepository<Entity, TipoID>`:*
* **PostgreSQL**
* **MySQL**
* **MariaDB**
* **Oracle Database**
* **Microsoft SQL Server**
* **IBM DB2**
* **H2 / HSQLDB / Apache Derby** (Database temporanei in memoria RAM per i test)

### 2. Database NoSQL a Documenti (Formato simile a JSON)
*Estendono i Repository specifici del mondo NoSQL:*
* **MongoDB** ──> `extends MongoRepository<Entity, TipoID>`
* **Couchbase** ──> `extends CouchbaseRepository<Entity, TipoID>`

### 3. Database NoSQL Chiave-Valore e Colonnari
*Estendono le interfacce dedicate all'alta scalabilità:*
* **Redis** ──> `extends CrudRepository<Entity, TipoID>` (Database ultra-veloce in RAM)
* **Hazelcast** ──> `extends KeyValueRepository<Entity, TipoID>`
* **Apache Cassandra** ──> `extends CassandraRepository<Entity, TipoID>` (Usato da Netflix/Facebook)
* **Amazon DynamoDB** ──> `extends CrudRepository<Entity, TipoID>` (Il NoSQL cloud di AWS)

### 4. Database Orientati ai Grafi (Nodi e Relazioni)
* **Neo4j** ──> `extends Neo4jRepository<Entity, TipoID>` (Usato per social network e sistemi anti-frode)

### 5. Motori di Ricerca testuale
* **Elasticsearch** ──> `extends ElasticsearchRepository<Entity, TipoID>` (Per barre di ricerca e-commerce)

---

## 🟥 Fazione 2: I Database "Manuali" (JDBC / Template)

In questa fazione **non esiste la magia della singola riga**. Non crei un'interfaccia vuota. Devi scrivere una classe concreta (`@Repository`), farti iniettare l'assistente di Spring e scrivere tu la query e la mappatura dei dati riga per riga.

* **JDBC Classico / Puro**: Controllo manuale al 100%. Devi aprire/chiudere le connessioni a mano, scrivere le query SQL, gestire i blocchi `try-catch` e mappare i record usando i cicli `while (rs.next())`.
* **Spring `JdbcTemplate`**: Rimuove il fastidio di gestire le connessioni e gli errori, ma l'SQL (`"SELECT * FROM utenti WHERE..."`) e la mappatura dei campi (`ResultSet`) rimangono un lavoro manuale da scrivere riga per riga tramite un `RowMapper`. Si usa sui DB relazionali per avere prestazioni estreme.
* **Spring `NamedParameterJdbcTemplate`**: Come il JdbcTemplate, ma ti permette di usare parametri con nome (es. `:idUtente`) al posto dei punti di domanda (`?`) nelle query SQL manuali.
* **Driver Nativi (Senza Spring Data)**: Quando colleghi un database usando solo le librerie ufficiali del produttore (es. i driver Java puri di Cassandra o ScyllaDB) senza usare i moduli preconfezionati di Spring Boot.

---

## 💡 Il Trucco della Libreria (Come capire chi usare)
* Se nel file `pom.xml` inserisci una dipendenza che inizia con **`spring-boot-starter-data-...`**, sei nella **Fazione 1 (Monoriga)**.
* Se nel file `pom.xml` inserisci la dipendenza **`spring-boot-starter-jdbc`**, sei nella **Fazione 2 (Manuale)**.