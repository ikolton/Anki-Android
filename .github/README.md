# AnkiDroid Ink

**An unofficial, personal restyle of [AnkiDroid](https://github.com/ankidroid/Anki-Android). Not affiliated with or endorsed by the AnkiDroid team.**

If you are looking for AnkiDroid, use the official app: [Google Play](https://play.google.com/store/apps/details?id=com.ichi2.anki), [F-Droid](https://f-droid.org/packages/com.ichi2.anki/) or [GitHub](https://github.com/ankidroid/Anki-Android). Report AnkiDroid bugs there, not here.

## What this is

The latest stable AnkiDroid with a different look, matching the owner's "ink & saffron" desktop Anki theme. Nothing else is changed: same features, same scheduling, same AnkiWeb sync.

- Calm dark and light palette (navy canvas, saffron accent), serif titles
- Deck list with hairline rows and evenly spaced counters
- Study screen: thin progress bar with cards left, a 2×2 grid of outlined answer buttons, a large show-answer button
- The new study screen is on by default; dark (not black) is the default night theme

It installs as a separate app (`com.ichi2.anki.ink`, "AnkiDroid Ink") next to the official one and is signed with its own key.

## How it is maintained

All changes live on the [`ink`](../../tree/ink) branch on top of an upstream stable tag. A weekly [workflow](workflows/ink-release.yml) merges `ink` into the newest stable AnkiDroid release, builds signed APKs and publishes them under [Releases](../../releases) (tags `vX.Y.Z-ink.N`). Owner notes (Polish): [INK.md](../INK.md).

## License

GPL-3.0-or-later, like AnkiDroid. See [COPYING](../COPYING). The original AnkiDroid README is [here](../README.md).
