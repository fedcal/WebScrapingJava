# WebScrapingJava

## Il progetto

Il progetto nasce con l'idea di prendere alcuni dati da siti che elaborano statistiche live su partite calcistiche, filtrarli 
e salvarli su un foglio excel. Di seguito sono stati elencati i siti, tutti i filtri delle partite e le librerie utilizzate.

## Total Corner

Il progetto consiste nel recuperare giornalmente dal sito [TotalCorner](https://www.totalcorner.com/match/today)
una lista di partice che verranno giocate in quella specifica giornata. All'interno del sito i dati sono memorizzati
in una tabella, che verrà recuperata in toto all'avvio del programma per poi essere rielaborata.

Le informazioni a cui dobbiamo prestare attenzione sono:

- Il campo **_League_**, il quale indica la lega calcistica di riferimento della partita
- Il campo **_time_**, indicante l'ora di inizio della partita, al quale bisogna aggiungere un'ora per via del fuso orario
- Il campo **_Home_**, indicante la squadra che gioca in casa
- Il campo **_Away_**, indicante la squadra avversaria
- Il campo **_Handicap_**, di cui attualmente ignoriamo il signigicato
- Il campo **_Goal Line_**, di cui attualmente ignoriamo il significato

Vi sono dei vincoli in base ai quali filtrare la lista delle partite:

1. Il campo _Handicap_ deve essere avvalorato con valori maggiori o uguali a 1.75 oppure minori o uguali a -1.75;
2. Il campo _Goal Line_ deve contenere un valore maggiore o uguale a 3.25

Il sistema deve elaborare ogni mattina la lista e inviarla a uno specifico orario, indicativamente verso le 07:00.
Il formato in cui deve essere inviato il messaggio bisogna capirlo in base alla sua migliore visualizzazione.
Un requsito opzionale consiste in un reminder 1 ora prima dell'inizio dalla partita.


Caso in cui il campo handicap è popolato come segue: ```3.5 (3.78)``` o è prensete una scritta rossa, comportarsi come
segue:

- La parte rossa è definita come **spred** e bisogna prendere tutti i valori superiori a 1.25


## AsianBet


## Librerie

Come librerie ho utilizzato Selenium per poter prendere i dati dai siti web, mentre per elaborarli all'interno dei file excel 
ho utilizzato la libreria Apache Poi.

Il linguaggio base è Java, creando un progetto basato su tecnologia Maven.

## Building jar

```<packaging>jar</packaging>```

```
<dependency>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-assembly-plugin</artifactId>
<version>2.3</version>
<type>maven-plugin</type>
</dependency>
```
```
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                        <configuration>

                            <archive>
                                <manifest>
                                    <mainClass>
                                        federicocalo.botwebscrapingJ.Launcher
                                    </mainClass>
                                </manifest>
                            </archive>
                            <descriptorRefs>
                                <descriptorRef>jar-with-dependencies</descriptorRef>
                            </descriptorRefs>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.10.1</version>
                <configuration>
                    <source>18</source>
                    <target>18</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
```