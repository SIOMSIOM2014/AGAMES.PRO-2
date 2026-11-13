package com.agames.pro;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int PICK_GAME = 41;
    private Uri selectedGame;

    private int dp(float value) { return (int)(value * getResources().getDisplayMetrics().density); }

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(12, 17, 32));
        getWindow().setNavigationBarColor(Color.rgb(12, 17, 32));
        getWindow().getDecorView().setSystemUiVisibility(0);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(dp(22), dp(34), dp(22), dp(24));
        root.setBackgroundColor(Color.rgb(12, 17, 32));

        TextView logo = new TextView(this);
        logo.setText("🎮  AGAMES PRO");
        logo.setTextColor(Color.WHITE);
        logo.setTextSize(29);
        logo.setGravity(Gravity.CENTER);
        logo.setTypeface(null, 1);
        root.addView(logo, matchWrap());

        TextView subtitle = new TextView(this);
        subtitle.setText("مكتبتك للألعاب في مكان واحد");
        subtitle.setTextColor(Color.rgb(174, 190, 218));
        subtitle.setTextSize(16);
        subtitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams subParams = matchWrap();
        subParams.topMargin = dp(10);
        root.addView(subtitle, subParams);

        TextView status = new TextView(this);
        status.setText("مرحباً بك في AGAMES PRO\n\nأضف ملفات الألعاب التي تملكها، ثم افتحها باستخدام مشغل متوافق مثبت على جهازك.");
        status.setTextColor(Color.WHITE);
        status.setTextSize(16);
        status.setGravity(Gravity.CENTER);
        status.setPadding(dp(18), dp(26), dp(18), dp(26));
        status.setBackgroundColor(Color.rgb(27, 38, 61));
        LinearLayout.LayoutParams card = matchWrap();
        card.topMargin = dp(34);
        root.addView(status, card);

        Button choose = makeButton("＋  اختيار ملف لعبة");
        LinearLayout.LayoutParams p1 = matchWrap(); p1.topMargin = dp(26);
        root.addView(choose, p1);
        choose.setOnClickListener(v -> chooseGame());

        Button launch = makeButton("▶  تشغيل اللعبة المختارة");
        LinearLayout.LayoutParams p2 = matchWrap(); p2.topMargin = dp(12);
        root.addView(launch, p2);
        launch.setOnClickListener(v -> launchGame());

        TextView note = new TextView(this);
        note.setText("ملاحظة: هذا الإصدار هو واجهة ومكتبة ألعاب أولية. محرك PSP غير مدمج بعد؛ يلزم تثبيت محاكي متوافق بشكل منفصل. لا يتضمن التطبيق ألعاباً محمية بحقوق النشر.");
        note.setTextColor(Color.rgb(165, 178, 201));
        note.setTextSize(12);
        note.setGravity(Gravity.CENTER);
        note.setPadding(dp(8), dp(28), dp(8), dp(8));
        root.addView(note, matchWrap());

        setContentView(root);
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private Button makeButton(String label) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setTextColor(Color.WHITE);
        b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(38, 105, 235)));
        b.setPadding(dp(12), dp(10), dp(12), dp(10));
        return b;
    }

    private void chooseGame() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(i, PICK_GAME);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_GAME && resultCode == RESULT_OK && data != null) {
            selectedGame = data.getData();
            if (selectedGame != null) {
                try { getContentResolver().takePersistableUriPermission(selectedGame, Intent.FLAG_GRANT_READ_URI_PERMISSION); }
                catch (Exception ignored) {}
                Toast.makeText(this, "تم اختيار ملف اللعبة", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void launchGame() {
        if (selectedGame == null) {
            Toast.makeText(this, "اختر ملف لعبة أولاً", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent view = new Intent(Intent.ACTION_VIEW);
        view.setDataAndType(selectedGame, "*/*");
        view.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            view.setPackage("org.ppsspp.ppsspp");
            startActivity(view);
        } catch (ActivityNotFoundException missing) {
            try {
                view.setPackage("org.ppsspp.ppssppgold");
                startActivity(view);
            } catch (ActivityNotFoundException alsoMissing) {
                view.setPackage(null);
                try { startActivity(Intent.createChooser(view, "فتح ملف اللعبة")); }
                catch (Exception e) { Toast.makeText(this, "لا يوجد تطبيق متوافق لفتح هذا الملف", Toast.LENGTH_LONG).show(); }
            }
        }
    }
}
