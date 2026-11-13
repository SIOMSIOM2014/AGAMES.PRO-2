# AGAMES PRO

Android project for AGAMES PRO.

## Included in this starter version
- Arabic Android launcher interface.
- Pick a game file from device storage.
- Hand off the selected file to PPSSPP if it is installed, otherwise show compatible apps.
- GitHub Actions workflow that builds a debug APK on pushes to `main` and on manual runs.

## Get the APK
Open the repository's **Actions** tab, select **Android APK**, wait for a successful run, then download the **AGAMES-PRO-debug-APK** artifact.

## Important current limitation
The PSP emulation engine is **not bundled** in this version, and no game files are included. PSP playback currently depends on a compatible emulator installed separately. This is not yet a standalone PSP emulator or a verified final release.

## Build requirements
- Android 6.0+ (API 23+)
- JDK 17
- Android SDK Platform 35
