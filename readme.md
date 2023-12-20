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
/perms groups - zeigt eine Auflistung aller Gruppen an
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


- Statistiken zur Programmierung (evtl. Interessant?)
  ![Stats](https://i.imgur.com/JXmiGhI.png)


- Ich bitte davon, bei den Unit-Tests ein oder auch beide Augen zuzudrücken, da ich mit diesen noch nie gearbeitet habe aber probiert habe diese hier einzubauen. Um diese zu testen muss man noch eine am besten zweite Datenbank in der `DataSourceTest` Klasse angeben und die jeweiligen Login-Daten zur Datenbank abändern. Falls Fehler auftreten oder es irgendwelche Best-Practices gibt, bitte ich mir diese mitzuteilen. Ich habe mich bei folgenden Ressourcen informiert, falls das bei der Bewertung hilft :)
   - https://www.jetbrains.com/help/idea/tests-in-ide.html
   - https://www.youtube.com/watch?v=vZm0lHciFsQ


- Von den zusätzlichen Aufgaben habe ich probiert alle abzudecken, damit mehr zu sehen ist, da mir so dass ganze Projekt sonst relativ blank vorkam. Ich hoffe, ich kann die Erwartungen zufriedenstellend erfüllen. :)
