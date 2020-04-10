# Hopur3F-Flight

## Uppsetning
Til að keyra verkefnið þá þarf að ná í þetta repository og setja á tölvuna sína. Síðan fara inn í möppuna $Hopur3F-Flight$ og þýða skrárnar með eftirfarandi skipun:

```
javac src/*.java
javac src/*/*.java
```

Síðan til að byrja þá er eftirfarandi skipun keyrð:

Fyrir Windows:

```
java -cp .;src/database/sqlite-jdbc-3.18.0.jar
```

Fyrir Unix:

```
java -cp .:src/database/sqlite-jdbc-3.18.0.jar
```
