## Wichtigsten Befehle:

### Nutzerbefehle:

```
/perms user <name> add <permission> - fügt dem Spieler eine Berechtigung hinzu
/perms user <name> remove <permission> - entfernt dem Spieler eine Berechtigung
/perms user <name> info - zeigt die Berechtigungen des Spielers an
/perms user <name> group add <group> [duration] - fügt dem Spieler eine Gruppe hinzu
/perms user <name> group remove <group> - entfernt dem Spieler eine Gruppe
```

### Schilderbefehle:
```
/perms sign set - setzt ein Permissions-Schild (man muss auf ein leeres Schild schauen)
/perms sign remove - entfernt ein Permissions-Schild
```

### Gruppenbefehle:

```
/perms group <name> create - erstellt eine Gruppe 
/perms group <name> delete - löscht eine Gruppe
/perms group <name> add <permission> - fügt der Gruppe eine Berechtigung hinzu
/perms group <name> remove <permission> - entfernt der Gruppe eine Berechtigung
/perms group <name> info - zeigt Informationen über die Gruppe an
/perms group <name> default <true/false> - setzt die Gruppe als Standardgruppe
/perms group <name> prefix <prefix> - setzt den Prefix der Gruppe
/perms group <name> suffix <suffix> - setzt den Suffix der Gruppe
/perms group <name> weight <weight> - setzt das Gewicht der Gruppe - je höher, desto höher die Priorität
```


### Allgemeine Informationen:
- Ich weiß nicht genau, ob die Tablist richtig sortiert ist, da ich keinen Zweitaccount zum Testen besitze. Normalerweise sollte es folgendermaßen zu testen gehen: 
    - Admin: (weight) 2000
    - Moderator: (weight) 1000
    - Spieler: (weight) 5


- Die Translations sind aktuell global geregelt. Man könnte noch kurz den Spielern seine Sprache speichern lassen, da dass aber nicht explizit in der Aufgabe genannt war, habe ich das einfach nur in der translations.yml einstellbar gemacht, sodass die Language global gilt.


- Von den zusätzlichen Aufgaben habe ich probiert alle abzudecken, damit mehr zu sehen ist, da mir so dass ganze Projekt sonst relativ blank vorkam. Ich hoffe, ich kann die Erwartungen zufriedenstellend erfüllen. :)