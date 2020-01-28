# Installation/Setup

## Template Projekt
Am einfachsten ist es, wenn man sich das Template Projekte von https://github.com/emign/engineEmi_Template herunter lädt.
Dieses sorgt für die komplette Einrichtung der Engine.

## Einbinden des Gradle Plugins
In die Datei `build.gradle` eines beliebigen gradle-Projekts einfach die folgenden Zeilen übernehmen
```
buildscript {
    repositories {
        maven { url = uri("https://dl.bintray.com/emign/engineEmi") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("me.emig:engineEmiGradlePlugin:ENGINEEMIVERSION")
    }
}

apply plugin: me.emig.engineEmi.gradle.EngineEmiGradlePlugin
```
Der String ENGINEEMIVERSION ist mit der aktuellen Version zu ersetzen. Diese kann auf 
der https://bintray.com/beta/#/emign/engineEmi/engineEmi eingesehen werden.

Wichtig ist es, gradle ab Version 5.6.4 einzusetzen. Am besten mit einem Wrapper. Dies gelingt durch
den Befehl im Terminal (entweder in der IDE/IntelliJ oder im regulären Terminal, im Ordner
des engineEmi-Projektes): `gradle wrapper --gradle-version=5.6.4`.
Zudem muss in der `settings.gradle` die Zeile `enableFeaturePreview('GRADLE_METADATA')` ergänzt werden

## Direktes einbinden der Engine-Library
Es ist auch möglich, die Engine direkt als Library einzubinden: https://bintray.com/beta/#/emign/engineEmi/engineEmi

# Changelog`

## v0.29 2020-01-28
Engine ist nun eine library und verfügt über ein Gradle plugin

### Added
- Korge 1.5.6
- Fix für Tilemap Bug https://github.com/emign/engineEmi/issues/1

## v0.21 2020-01-15

### Added
- SpriteAnimationen sind nun möglich. Siehe: `AnimationController.kt`, `SpriteAnimation.kt` und `SpriteView.kt`

### Refined
- Kleinere Anpassungen an `CanvasElement` zur Vereinfachung der Erstellung

## v0.20 2020-01-13

### Added
- Korge 1.5.4.0 support
- Kamera hinzugefügt

## v0.19 2019-12-16

### Added
- Controller Klasse eingefügt inkl. Funktion registerController()`
- Engine: Veränderbare delay-Property der Engine für Wartezeit zwischen den Frames
- Keyboard hat jetzt `lastKeyDown` Property
- `ScreenElement.kt` enthält nun `Bodies` und `CanvasElemente (Strukturierung)

## 0.18 2019-12-14 

### Wichtig
Die `Main.kt` sieht nun deutlich verändert aus. Es gibt 3 Funktionen, in welchen programmiert werden kann/soll.
Die genauen Aufgaben der 3 Funktionen sind im Quelltext erläutert.

### Added
- Korge 1.5.0d support
- PhysikSample integriert (physikdemo)
- Scale als Parameter in main und run methode eingefügt
- Scale standard auf 100 inkl. Hotfix für Image Scaling Faktor 10

### Changed
- Gradle auf Groovy geändert (build.gradle anstatt build.gradle.kts), da es schneller ist
- Aufbau `Main.kt` geändert, als Vorbereitung für Physik-Projekte

### Removed
- openLocalJVM entfernt

## 0.17

### Added
- Korge 1.4.3c support. openLocalJNA target funktioniert nun korrekt (lädt deutlich schneller als openLocal und ist insgesamt deutlich performanter)

### Fixed
- Concurrency error bei schnellen Änderungen von Views

### Known bugs
- Einige Targets (darunter openLocalJNA) können bei manchen Systemen falsche Anzeige-Proportionen aufweisen (etwa Anzeige nur auf halbe Höhe und Breite skaliert)

### Deprecated
- openLocal target -> switch to openLocalJNA

## 0.16
- Audio Support in Klasse Audio (Noch clunky bis nächste Korge Version)
- Inputsample hinzugefügt und besseres Sample-Management

## 0.15
- engine.run() nimmt nun width und height als optionale Parameter um das GameWindow in der Größe anzupassen
- Keyboard und Mouse-Input nun möglich
- WorldView zentriert

## 0.14
### Added
- Korge 1.4.2 Support
- MacOSX64 native target

## Changed
- Engine ist jetzt Objekt und kein Singleton mehr. (geänderter engine-Start s. Docs)
- Dokka Generierung umbenannt und an task Management angepasst.

## 0.13c
### Added
- Support für github actions
- github badges für jvm und js build, doc und versionsnummer
- fun register() erweitert um Collections und Arrays im Gesamten anzumelden

### Fixed
- Breite und Höhe kann nun korrekt angezeigt werden `Engine.view.height`

## 0.12 alpha 2019-11-29
### Added
- Kotlin 1.3.60 support
- Korge 1.40 support
- Property skalierung bei Bild hinzuge`fügt. Nun kann ein Bild einfach auf die korrekte Größe skaliert werden (0.5 -> 50%, ...)
- `Image` Klasse bei den Bodies. Zeigt Bilder anstatt Geometrische Formen an.
- Doku überarbeitet
- `animate(animationRoutine) Funktion hinzugefügt, um Objekte auch ohne Erzeugung von Subklassen zu animieren.

### Fixed
- Skalierung der `CanvasElements`und `Bodies`nun automatisch. Keine ausgefragsten Ecken mehr bei Geraden

## 0.11 alpha 2019-11-25 
### Added
- Property skalierung bei Bild hinzugefügt. Nun kann ein Bild einfach auf die korrekte Größe skaliert werden (0.5 -> 50%, ...)
- `Image` Klasse bei den Bodies. Zeigt Bilder anstatt Geometrische Formen an.

### Issues
- Spawning multiple `Images` throws an exception (see [https://github.com/korlibs/korge/issues/45#issuecomment-558140737](https://github.com/korlibs/korge/issues/45#issuecomment-558140737) )

## 0.1 alpha 2019-11-22 
Testbranch umbenannt in dev. Die Implementierung von box2d und der korge engine wird nun priorisiert.
### Added
- Alle CanvasElements besitzen nun deutsche Namen und Propertiebezeichnungen, was die Abgrenzung zu den Bodies erleichtert
- Alle CanvasElements und Bodies können nun mit Zahlenwerten vom Typ `Number` initialisiert werden (kein Double, Int, Float mehr notwendig.) 