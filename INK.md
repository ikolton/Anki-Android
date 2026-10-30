# AnkiDroid Ink

Prywatna wersja AnkiDroid z wyglądem „ink & saffron” z Anki na komputerze. Funkcje są te same co w AnkiDroid. Aplikacja instaluje się obok oficjalnego AnkiDroid jako osobna aplikacja „AnkiDroid Ink”.

## Instalacja na telefonie (raz)

1. Zainstaluj [Obtainium](https://github.com/ImranR98/Obtainium/releases) (plik `app-arm64-v8a-release.apk` z najnowszego wydania).
2. W Obtainium: **Add App** → wklej `https://github.com/ikolton/Anki-Android` → w **Filter APKs by regular expression** wpisz `arm64-v8a` → **Add**.
3. **Install**. Android zapyta o zgodę na instalację z Obtainium. Zezwól.
4. Otwórz **AnkiDroid Ink** → **Synchronizuj z AnkiWeb** → zaloguj się i pobierz kolekcję.

Oficjalny AnkiDroid może zostać jako zapas albo możesz go odinstalować, kiedy Ink się sprawdzi. Obie aplikacje synchronizują się z tym samym kontem AnkiWeb. Przed przejściem z jednej na drugą zsynchronizuj tę, której używałeś.

## Aktualizacje

Co poniedziałek GitHub sprawdza, czy wyszła nowa stabilna wersja AnkiDroid. Jeśli tak, nakłada na nią zmiany wyglądu i publikuje nowe wydanie. Obtainium pokaże aktualizację. Wydanie można też uruchomić ręcznie: zakładka **Actions** → **Ink release** → **Run workflow**.

Jeśli zmiany wyglądu nie pasują do nowej wersji AnkiDroid, w repozytorium pojawi się zgłoszenie „Ink: konflikt przy przejściu na …”. Wtedy poproś Claude'a o przeniesienie gałęzi `ink`. Do tego czasu działa poprzednia wersja.

## Klucz podpisu

Aplikacja jest podpisana kluczem z `~/Documents/MyProjects/anki-backups/ink-signing/`. Klucz jest też w sekretach repozytorium. Bez niego telefon nie przyjmie aktualizacji, więc trzymaj kopię w bezpiecznym miejscu, np. w menedżerze haseł.

## Dla agentów

Zmiany leżą na gałęzi `ink` nad tagiem stabilnego wydania. Projekt: `docs/superpowers/specs/2026-10-30-ink-theme-design.md`. Plan: `docs/superpowers/plans/2026-10-30-ink-theme.md`. Paleta jest nakładką `ThemeOverlay.Ink.*` w `res/values/ink_theme.xml`, nakładaną w `Themes.setTheme`. Nie edytuj plików motywów AnkiDroid ani plików tłumaczeń.
