# AGAMES PRO

AGAMES PRO is an Android PSP emulator app branded from the official open-source PPSSPP project. The Android app is built from the pinned PPSSPP source revision; the emulator engine and its game-browser interface run inside AGAMES PRO. It does not hand games off to a separately installed PPSSPP app.

## Included app experience

The upstream PPSSPP Android front end provides:
- A game library/browser for PSP titles stored on the device.
- A separate **Homebrew & Demos** section and the built-in Homebrew Store for freely distributed homebrew and demos.
- Game artwork where available, library browsing, and game launching.
- On-screen touch controls and support for compatible external controllers.
- Emulator settings for graphics, audio, controls, performance, and save states.

The exact controls and performance depend on the Android device and the game. AGAMES PRO currently targets **PSP emulation only**; it is not a universal emulator for every console.

## Games and licensing

- The APK does not bundle commercial game images, copyrighted game downloads, or proprietary firmware.
- Use the built-in Homebrew & Demos area for freely distributed homebrew, or add game files that you are legally entitled to use.
- No third-party commercial game collection is included or promised.

## Build

Open **Actions** in this repository and run **AGAMES PRO - Integrated PSP Emulator**. The workflow:
1. Checks out a pinned PPSSPP source revision, including its submodules.
2. Applies AGAMES PRO application branding and package ID.
3. Builds the Android `normalDebug` variant with the Android NDK and CMake.
4. Uploads an installable APK and a matching source archive.

Download the artifact named **AGAMES-PRO-PSP-APK** after the workflow completes successfully.

## Important

- The emulator engine is licensed under GPL-2.0-or-later. See `LICENSES/PPSSPP.md` and the accompanying source artifact.
- The APK is a debug-signed test build, not a Play Store release. A separate private signing key is required for a signed release; never commit signing passwords or private keys.
- Compatibility and performance vary by device, GPU, Android version, and game. No claim of universal compatibility is made.
