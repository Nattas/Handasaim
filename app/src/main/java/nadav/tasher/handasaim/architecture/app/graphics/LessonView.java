package nadav.tasher.handasaim.architecture.app.graphics;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import nadav.tasher.handasaim.R;
import nadav.tasher.handasaim.architecture.app.Center;
import nadav.tasher.handasaim.architecture.app.Theme;
import nadav.tasher.handasaim.architecture.appcore.AppCore;
import nadav.tasher.handasaim.architecture.appcore.components.Subject;
import nadav.tasher.lightool.graphics.views.ExpandingView;
import nadav.tasher.lightool.graphics.views.Utils;
import nadav.tasher.lightool.info.Device;

public class LessonView extends ExpandingView {
    private Activity activity;
    private RatioView topView, timeView;
    private ArrayList<RatioView> texts = new ArrayList<>();
    private Subject subject;
    private ArrayList<Subject> teacherSubjects;
    private int breakHour = -1;
    private Theme currentTheme;
    private BroadcastReceiver timeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
                refreshTopText();
            }
        }
    };

    public LessonView(Activity activity, Theme theme, Subject subject) {
        super(activity);
        this.activity = activity;
        this.subject = subject;
        this.currentTheme = theme;
        init();
    }

    public LessonView(Activity activity, Theme theme, int breakHour) {
        super(activity);
        this.activity = activity;
        this.currentTheme = theme;
        this.breakHour = breakHour;
        init();
    }

    private void refreshTopText() {
        String topText = subject.getHour() + ". " + subject.getName();
        if (currentTheme.showRemainingTime) {
            topText += AppCore.Utils.DIVIDER;
            topText += AppCore.getSchool().getEndingMinute(subject) - AppCore.getSchool().getStartingMinute(subject) - (AppCore.getSchool().getEndingMinute(subject) - Center.currentMinute());
            topText += getContext().getResources().getString(R.string.interface_minutes);
        }
        topView.setText(topText);
    }

    private Drawable initBackground() {
        int markID;
        boolean currentLesson = Center.inRange(Center.currentMinute(), AppCore.getSchool().getStartingMinute(subject), AppCore.getSchool().getEndingMinute(subject));
        if (currentTheme.markPrehours && subject.getHour() == 0) {
            if (currentLesson) {
                markID = R.color.coaster_special_dark;
            } else {
                markID = R.color.coaster_special_bright;
            }
        } else {
            if (currentLesson) {
                markID = R.color.coaster_dark;
            } else {
                markID = R.color.coaster_bright;
            }
        }
        return Utils.getCoaster(getContext().getResources().getColor(markID), 32, 5);
    }

    private void init() {
        LinearLayout bottomLayout = new LinearLayout(getContext());
        bottomLayout.setGravity(Gravity.CENTER);
        bottomLayout.setOrientation(LinearLayout.HORIZONTAL);
        bottomLayout.setLayoutDirection(LAYOUT_DIRECTION_RTL);
        setLayoutDirection(LAYOUT_DIRECTION_RTL);
        if (subject != null) {
            // Register receiver
            getContext().registerReceiver(timeReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
            // Setup text view
            topView = getText(1);
            timeView = getText(Center.generateTime(subject.getHour()), 0.8);
            // Bottom setup
            bottomLayout.addView(getTexts(subject.getTeacherNames()));
        } else {
            if (teacherSubjects != null) {
            } else {
                RatioView minutesView = getText(AppCore.getSchool().getBreakLength(breakHour - 1, breakHour) + " " + getContext().getResources().getString(R.string.interface_minutes), 0.8);
                texts.add(minutesView);
                topView = getText(getContext().getResources().getString(R.string.interface_break), 1);
                timeView = getText(Center.generateBreakTime(breakHour - 1, breakHour), 0.8);
                bottomLayout.addView(minutesView);
            }
        }
        topView.setPadding(20, 0, 20, 0);
        timeView.setTextDirection(TEXT_DIRECTION_LTR);
        topView.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        timeView.setGravity(Gravity.CENTER);
        topView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Device.screenY(getContext()) / 12));
        timeView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Device.screenY(getContext()) / 13, 1));
        texts.add(topView);
        texts.add(timeView);
        bottomLayout.addView(timeView);
        // Expanding View
        setDuration(200);
        setBackground(initBackground());
        setPadding(20, 25);
        setTop(topView);
        setBottom(bottomLayout);
//        ExpandingView ev = new ExpandingView(getContext());
//        ev.setDuration(200);
//        ev.setBackground(initBackground());
//        ev.setPadding(20, 25);
//        ev.setTop(topView);
//        ev.setBottom(bottomLayout);
//        addView(ev);
    }

    public void setTheme(Theme theme) {
        this.currentTheme = theme;
        for (RatioView text : texts) text.setTextColor(theme.textColor);
        for (RatioView text : texts) text.setTextSize(theme.textSize);
    }

    private LinearLayout getTexts(ArrayList<String> strings) {
        LinearLayout textsLayout = new LinearLayout(getContext());
        textsLayout.setGravity(Gravity.CENTER);
        textsLayout.setOrientation(LinearLayout.VERTICAL);
        textsLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        for (String teacher : strings) {
            RatioView text = getText(teacher, 0.8);
            text.setGravity(Gravity.CENTER);
            text.setEllipsize(TextUtils.TruncateAt.END);
            text.setTypeface(Center.getTypeface(getContext()), Typeface.ITALIC);
            text.setPadding(30, 0, 30, 0);
            texts.add(text);
            textsLayout.addView(text);
        }
        return textsLayout;
    }

    private RatioView getText(String text, final double textSizeRatio) {
        RatioView tv = new RatioView(getContext(), textSizeRatio);
        tv.setText(text);
        tv.setTextSize(Center.getFontSize(getContext()));
        tv.setTypeface(Center.getTypeface(getContext()));
        tv.setTextColor(Center.getTextColor(getContext()));
        tv.setTextDirection(TEXT_DIRECTION_RTL);
        tv.setSingleLine(true);
        return tv;
    }

    private RatioView getText(final double textSizeRatio) {
        RatioView tv = new RatioView(getContext(), textSizeRatio);
        tv.setTextSize(Center.getFontSize(getContext()));
        tv.setTypeface(Center.getTypeface(getContext()));
        tv.setTextColor(Center.getTextColor(getContext()));
        tv.setTextDirection(TEXT_DIRECTION_RTL);
        tv.setSingleLine(true);
        return tv;
    }
}
