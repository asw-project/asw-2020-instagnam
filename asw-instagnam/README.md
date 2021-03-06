# INSTAGNAM 

Progetto del corso di Analisi e progettazione del software per l'anno accademico 2019-2020. 


## Descrizione di questo progetto 

Questo progetto contiene il il codice di *Instagnam*, un semplice social-network per la condivisione di ricette di cucina. 
Gli utenti del sistema possono pubblicare delle ricette. 
Possono poi seguire altri utenti del sistema. 
Quando un utente accede la pagina delle ricette che segue, gli vengono mostrate le ricette degli altri utenti che segue. 

L'applicazione *Instagnam* è composta dai seguenti microservizi: 

* Ricette
* Connessioni
* Ricette-seguite
* Api-Gateway


## Operazioni servizio Ricette

Il servizio *ricette* gestisce le ricette di cucina dei suoi utenti. 
  Ogni ricetta ha un autore, un titolo e una preparazione. 
  Operazioni: 
  * `POST /ricette` aggiunge una nuova ricetta (dato autore, titolo e preparazione)
  * `GET /ricette/{id}` trova una ricetta dato l'id 
  * `GET /ricette` trova tutte le ricette (in formato breve, solo id, autore e titolo)
  * `GET /ricette?autore={autore}` trova tutte le ricette di autore (in formato breve, solo id, autore e titolo)
  
  Per poter testare queste funzionalità è possibile utilizzare il servizio curl.
  
  `curl -X GET http://localhost:8080/ricette/ricette` è un comando da eseguire in Powershell o terminale per testare una chiamata GET
  
  
## Operazioni servizio Connessioni
  
Il servizio *connessioni* gestisce le connessioni tra gli utenti. 
  Ogni connessione è una coppia follower-followed, in cui follower e followed sono due utenti del sistema. 
  Operazioni: 
  * `POST /connessioni` aggiunge una nuova connessione (dato follower e followed)
  * `GET /connessioni/{id}` trova una connessione dato l’id 
  * `GET /connessioni` trova tutte le connessioni
  * `GET /connessioni?follower={utente}` trova tutte le connessioni di utente (quelle in cui l’utente è follower)

Per poter testare queste funzionalità è possibile utilizzare il servizio curl.
  
  `curl -X GET http://localhost:8080/connessioni/connessioni` è un comando da eseguire in Powershell o terminale per testare una chiamata GET


## Operazioni servizio Ricette-seguite

Il servizio *ricette-seguite* consente a un utente di trovare le ricette di tutti gli utenti che segue. 
  Operazioni: 
  * `GET /ricetteseguite/{utente}` trova tutte le ricette seguite da utente, ovvero le ricette di utenti di cui l’utente è follower (ricette in formato breve, trova solo id, autore e titolo)
  
  Per poter testare queste funzionalità è possibile utilizzare il servizio curl.
  
  `curl -X GET http://localhost:8080/ricetteseguite/{utente}` è un comando da eseguire in Powershell o terminale per testare una chiamata GET
  
  
  In questo progetto, l'implementazione dell'operazione `GET /ricetteseguite/U` del servizio *ricette-seguite*, 
per trovare le ricette seguite dall'utente U, è basata su invocazioni remote REST ai servizi *connessioni* e *ricette*: 
* prima viene invocata `GET /connessioni?follower=U` di *connessioni* 
  per trovare l'insieme AA di tutti gli utenti seguiti dall'utente U 
* poi, ripetutamente, per ciascun utente A nell'insieme AA, viene invocata `GET /ricette?autore=A` di *ricette*, 
  in modo da trovare, complessivamente, le ricette degli autori nell'insieme degli utenti AA seguiti da U 

  
## Operazioni servizio api-gateway
  
Il servizio *api-gateway* (esposto sulla porta *8080*) è l'API gateway dell'applicazione che: 
  * espone il servizio *ricette* sul path `/ricette` - ad esempio, `GET /ricette/ricette`
  * espone il servizio *connessioni* sul path `/connessioni` - ad esempio, `GET /connessioni/connessioni`
  * espone il servizio *ricette-seguite* sul path `/ricette-seguite` - ad esempio, `GET /ricette-seguite/ricetteseguite/{utente}`


## Esecuzione 

Per eseguire questo progetto: 

* Aprire una finestra powershell nella cartella del progetto ed eseguire `gradle build`

* Avviare *Docker* sul proprio dispositivo`

* Avviare la build dei contenitori trammite `instagnam-build.sh` 

* Avviare l'applicazione *Instagnam* avviando lo script `instagnam-start.sh` 

* L'applicazione può essere arrestata usando lo script `instagnam-stop` o direttamente dalla dashboard Docker. 

## Esecuzione con scalabilità

* Aprire una finestra powershell nella cartella del progetto ed eseguire `gradle build`

* Avviare *Docker* sul proprio dispositivo`

* Avviare la build dei contenitori trammite `instagnam-build.sh` 

* Avviare l'applicazione *Instagnam* avviando lo script `instagnam-scaled-start.sh` 

* L'applicazione può essere arrestata usando lo script `instagnam-stop` oppure direttamente dalla dashboard di Docker. 
