#!/usr/bin/env python3
"""Apply minimal, reproducible AGAMES PRO black-and-orange branding to the pinned PPSSPP source tree."""
from pathlib import Path
import sys

root = Path(sys.argv[1]).resolve()
gradle = root / "android" / "build.gradle.kts"
strings = root / "android" / "res" / "values" / "strings.xml"
manifest = root / "android" / "AndroidManifest.xml"

for path in (gradle, strings, manifest):
    if not path.is_file():
        raise SystemExit(f"Required upstream file not found: {path}")

g = gradle.read_text(encoding="utf-8")
old = 'applicationId = "org.ppsspp.ppsspp"'
if old not in g:
    raise SystemExit("Could not locate expected PPSSPP applicationId; review upstream changes.")
g = g.replace(old, 'applicationId = "com.agames.pro"')
gradle.write_text(g, encoding="utf-8")

s = strings.read_text(encoding="utf-8")
if '<string name="app_name">PPSSPP</string>' not in s:
    raise SystemExit("Could not locate expected PPSSPP app label; review upstream changes.")
s = s.replace('<string name="app_name">PPSSPP</string>', '<string name="app_name">AGAMES PRO</string>')
strings.write_text(s, encoding="utf-8")

m = manifest.read_text(encoding="utf-8")
if 'android:icon="@mipmap/ic_launcher"' not in m:
    raise SystemExit("Could not locate expected launcher icon reference.")
m = m.replace('android:icon="@mipmap/ic_launcher"', 'android:icon="@drawable/agames_pro_launcher"', 1)
manifest.write_text(m, encoding="utf-8")

icon = root / "android" / "res" / "drawable" / "agames_pro_launcher.xml"
icon.parent.mkdir(parents=True, exist_ok=True)
icon.write_text('''<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <path android:fillColor="#0C1120" android:pathData="M0,0h108v108h-108z"/>
    <path android:fillColor="#FF7A18" android:pathData="M16,24h76a8,8 0,0 1,8 8v44a8,8 0,0 1,-8 8h-76a8,8 0,0 1,-8 -8v-44a8,8 0,0 1,8 -8z"/>
    <path android:fillColor="#FFFFFF" android:pathData="M40,38l34,16 -34,16z"/>
    <path android:fillColor="#FFB36B" android:pathData="M22,18h64v4h-64z"/>
</vector>
''', encoding="utf-8")
print("AGAMES PRO branding applied to the PPSSPP Android app.")
