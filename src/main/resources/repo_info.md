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