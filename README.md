# 📕 Don't Panic

dontpanic è una "libreria" java creata per avere del codice da riusare il giorno dell'esame di OOP al Politecnico di Torino.

|:heavy_exclamation_mark: **IMPORTANTE** :heavy_exclamation_mark:|
|------------------------------------------------------------------|
|dontpanic potrebbe contenere degli errori al suo interno, controlla sempre il codice prima di usarlo. Dato che il codice è open source e controllabile da tutti il sottoscritto non si assume alcuna responsabilità dell'uso che l'utente farà di dontpanic, ovviamente il sottoscritto non si assume alcuna responsabilità nemmeno nel caso in cui un bug presente in dontpanic causi un errore nei test dell'esame a colui che lo utilizza.|

dontpanic fornisce:

1. Una classe per gestire le date (considerando anche anni bisestili e il fatto che nei vari mesi il numero di giorni cambia), in un formato a scelta dell'utente o in formato standard (formato ISO: `YYYY-MM-DD hh:mm:ss`)
2. La stessa classe per gestire le date, ma in versione light (dove tutti i mesi hanno 31 giorni e gran parte dei metodi precedenti sono stati rimossi se sostituibili da altri)
3. Una classe contentente una serie di metodi utili per modificare vari dati

> ***Warning*** attent* a usare i metodi increaseSecond/.../Days/Month/Year() e decreaseSecond/.../Days/Month/Year(), le probabilità che ci sia un bug in quei metodi è **più alta** rispetto a gli altri, specialmente per valori numerici molto alti!

In ogni caso, se trovi dei bug in dontpanic segnalamelo in privato su Telegram o WhatasApp o proponendo un modifica qui su GitHub!

> ***Note*** dontpanic è stato creato con l'utilizzo di [ChatGPT](https://chat.openai.com/chat) di OpenAI, e con alcuni ritocchi apportati da altri contributori della libreria
