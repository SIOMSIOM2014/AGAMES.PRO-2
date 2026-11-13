# Third-party notices

## PPSSPP

AGAMES PRO's integrated PSP emulation build is based on PPSSPP:
- Upstream project: https://github.com/hrydgard/ppsspp
- Upstream revision used by the build workflow: 1aebad2c651ff9d0684ee335e43aceff8fc7e3ec
- License: GNU General Public License version 2 or, at your option, any later version (GPL-2.0-or-later).

The build workflow checks out the upstream source and applies the reproducible branding changes in `scripts/prepare-ppsspp.py`. The corresponding source archive is uploaded as a separate GitHub Actions artifact alongside the APK.

PPSSPP is a third-party open-source project. AGAMES PRO is not the official PPSSPP product. All upstream copyright notices and license files are retained in the source checkout.
