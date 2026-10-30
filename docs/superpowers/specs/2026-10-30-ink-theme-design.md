# AnkiDroid "Ink": personal restyle fork

## Intent

The owner (single user, Polish UI) studies on the phone and creates cards on the Mac.
On the Mac, Anki runs a personal theme add-on ("ink & saffron", source:
`~/Documents/MyProjects/anki-theme`, tokens in `tokens.css`, interactive reference in
`design/prototype.html`). AnkiDroid looks old-fashioned and bland next to it. The goal is a
visual upgrade of AnkiDroid that feels like the desktop app, **with no functional changes**,
distributed as an APK from the owner's GitHub and easy to keep current with upstream.

Card content already matches: card HTML/CSS lives in the note types and syncs via AnkiWeb
(it has a `max-width: 600px` phone layout). This project is about the app chrome.

## Goals

1. **Deck list (start screen):** keep AnkiDroid's layout (list of decks, three counters
   new/learn/review, the + button, toolbar). Light lift only: ink & saffron palette, serif
   title, calmer spacing, hairline separators between rows, quieter subdeck rows.
2. **Study screen:** like the desktop reviewer, as approved in the mockup
   (`.superpowers/brainstorm/*/content/polaczenie.html`, right phone):
   - no regular toolbar; a slim top row with ✕ (close), a thin saffron progress bar,
     "zostało N", and ⋮ (overflow menu keeping undo, edit, flag, etc.);
   - answer buttons as a 2×2 grid of outlined rounded buttons, each with label and next
     interval, "Dobrze" highlighted (saffron outline + soft saffron fill);
   - labels match desktop: Jeszcze raz / Trudne / Dobrze / Łatwe;
   - the area around the WebView matches the card background so the card fills the screen.
3. **Everything else** (browser, settings, stats, dialogs): recolour only via the theme,
   no layout changes.
4. **Dark by default**, with a light counterpart following the system setting, same as
   desktop. Tokens below.
5. **Installable next to official AnkiDroid** (different package id and app name,
   e.g. `com.ichi2.anki.ink`, "AnkiDroid Ink"), own signing key.
6. **Updates:** a GitHub Actions workflow in the fork rebuilds on new upstream stable
   releases and publishes a signed APK as a GitHub Release; the phone updates via Obtainium.

## Non-goals

- New features, settings, behaviour changes, or a different home screen (the big
  "today" count + Zacznij button from desktop was explicitly rejected as clutter).
- Changing card templates/CSS (they come from the collection).
- Upstream pull requests. AnkiDroid's `AI_POLICY.md` forbids AI-made contributions from
  new contributors; this fork is personal use only.
- Play Store / F-Droid distribution, translations beyond what Polish UI needs.

## Anti-goals (failure even if it works)

- A diff so large or invasive that rebasing onto a new upstream release regularly
  conflicts. Prefer resource/theme overrides and small, isolated layout edits over
  rewriting Kotlin; keep the patch set reviewable in one sitting.
- Breaking sync, scheduling, or the collection; losing access to reviewer actions
  (undo/edit/flag) by hiding the toolbar.
- Losing the signing key (the phone would refuse updates).

## Constraints

- Public GitHub fork of `ankidroid/Anki-Android` under `ikolton` (owner's choice; GPL-3).
- Base on the **latest stable tag** (currently `v2.25.3`, 2026-10-17), not `main`
  (the local clone at `~/Documents/MyProjects/Anki-Android` is on `main`, 2.26 alpha).
- All changes on one branch (e.g. `ink`) on top of the tag, made the fork's default
  branch so scheduled workflows run from it.
- Toolchain on the Mac: JDK 17, Android SDK at `~/Library/Android/sdk`, emulator available,
  `gh` logged in as `ikolton`.
- Release build supports `-PcustomSuffix=… -PcustomName=…` already (see
  `AnkiDroid/build.gradle`, release buildType).
- Never grade real cards when testing; use an emulator with a test deck.
- The first install and AnkiWeb login on the phone are done by the owner.

## Visual tokens (from `anki-theme/tokens.css`)

| Token | Dark | Light |
|---|---|---|
| canvas (screen bg) | `#12161f` | `#f6f7f9` |
| surface (bars, sheets, cards) | `#1c2230` | `#ffffff` |
| line / border | `#262d3d` | `#e1e4ea` |
| fg | `#e9ebf1` | `#1a2030` |
| muted | `#8a93a7` | `#667085` |
| accent (saffron) | `#e9b35f` | `#a35f00` |
| accent soft fill | `rgba(233,179,95,.12)` | `rgba(163,95,0,.10)` |
| on-accent text | `#1a1406` | `#ffffff` |
| new / learn / review counters | `#8fb0f2` / `#ee9a8b` / `#93cfa5` | `#3b6fd8` / `#c4513f` / `#2f8a52` |

Type: system sans for UI; serif (Android `serif`, i.e. Noto Serif) for screen titles.
Shapes: rounded (~12dp buttons, pill for primary actions), 1dp hairlines, no heavy shadows.

## Decided by me (owner may override)

- Study screen target: the **new study screen** (`ui/windows/reviewer/ReviewerFragment`).
  Owner confirmed (2026-10-30) they only reveal and grade on the phone, and currently use
  the legacy screen; they accepted switching. It is where upstream is heading, so a patch
  there survives longer. The fork enables it by default (the setting stays, so the legacy
  screen remains reachable). Verify cloze, basic/reversed, MathJax and images render; if a
  blocker shows up, stop and report rather than silently falling back.
- Light/dark: follow system, with dark as the dark choice (not "black"/"plain").
- Polish answer labels via a `values-pl` string override of the four answer-button strings.
- Deck list: the text in Goal 1 wins over the mockup's left phone. Keep AnkiDroid's toolbar,
  drawer and search; style them (serif title, palette). No new sync-status text.
- "zostało N" and the progress bar use the desktop add-on's formula
  (`thProgress` in `anki-theme/__init__.py`: done / (done + left)).
- Web-based screens (stats, deck options, card info) are out of scope for v1; they keep
  Anki's own dark/light styling.
- Release cadence: weekly scheduled check + manual trigger; only rebuild when the upstream
  stable tag or the `ink` branch changed. On rebase conflict, fail and open an issue.
- Signing key generated locally (`keytool`), stored as GitHub Actions secrets, with a copy in
  `~/Documents/MyProjects/anki-backups/` (owner told to also keep it somewhere safe).

## Left to the builder

- Exact mechanism for the palette (editing existing `theme_dark.xml` / `theme_light.xml`
  and colors vs. adding a theme), as long as the diff stays small.
- Product flavor (`play` vs `full`), ABI split vs universal APK, and the versionCode scheme
  that lets a rebuilt `ink` patch on the same upstream version upgrade over the previous one.
- How the workflow tracks "last built" (tags like `v2.25.3-ink.N`, release metadata, etc.).
- Small typographic/spacing details not covered by the mockup.

## Verification

Screenshots from an emulator, dark and light: deck list, study screen front, study screen
back with answer buttons, one recoloured secondary screen (browser or settings). Compare
against the mockup and the desktop prototype. A release workflow run producing an
installable signed APK. The owner installs it and confirms on the phone.
