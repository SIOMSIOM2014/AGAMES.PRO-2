# AGAMES PRO

AGAMES PRO is built on the open-source PPSSPP PSP emulator engine.

## Build

Open **Actions** in this repository and run **AGAMES PRO - Integrated PSP Emulator**. The workflow:
1. Checks out a pinned PPSSPP source revision, including its submodules.
2. Applies AGAMES PRO application branding and package ID.
3. Builds the Android `normalDebug` variant with the Android NDK and CMake.
4. Uploads an installable APK and a matching source archive.

Download the artifact named **AGAMES-PRO-PSP-APK** after the workflow completes successfully.

## Important

- This is a PSP emulator build. It does not include commercial game files, firmware, or copyrighted game downloads.
- Users must provide game files they are legally entitled to use.
- Compatibility and performance vary by device, GPU, Android version, and game. No claim of universal compatibility is made.
- The emulator engine is licensed under GPL-2.0-or-later. See `LICENSES/PPSSPP.md` and the accompanying source artifact.
- The APK is a debug-signed test build, not a Play Store release. A separate private signing key is required for a signed release; never commit signing passwords or private keys.
