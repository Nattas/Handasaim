package nadav.tasher.handasaim.activities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import nadav.tasher.handasaim.R;
import nadav.tasher.handasaim.architecture.app.Center;
import nadav.tasher.handasaim.architecture.app.Download;
import nadav.tasher.handasaim.architecture.app.LinkFetcher;
import nadav.tasher.handasaim.architecture.app.PreferenceManager;
import nadav.tasher.handasaim.architecture.appcore.AppCore;
import nadav.tasher.handasaim.architecture.appcore.components.Schedule;
import nadav.tasher.lightool.graphics.ColorFadeAnimation;
import nadav.tasher.lightool.info.Device;

import static nadav.tasher.handasaim.architecture.app.Center.hasLink;

public class SplashActivity extends Activity {

    private PreferenceManager pm;
    private LinkFetcher linkFetcher = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
        go();
    }

    @Override
    public Context getApplicationContext() {
        return this;
    }

    private void initVars() {
        pm = new PreferenceManager(getApplicationContext());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void go() {
        getWindow().setStatusBarColor(Center.getColorTop(getApplicationContext()));
        getWindow().setNavigationBarColor(Center.getColorBottom(getApplicationContext()));
        final LinearLayout ll = new LinearLayout(getApplicationContext());
        ll.setGravity(Gravity.CENTER);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundColor(Center.getColorTop(getApplicationContext()));
        final ImageView icon = new ImageView(getApplicationContext());
        icon.setScaleType(ImageView.ScaleType.FIT_XY);
        icon.setImageDrawable(getApplicationContext().getDrawable(R.drawable.ic_icon));
        int is = (int) (Device.screenX(getApplicationContext()) * 0.8);
        icon.setLayoutParams(new LinearLayout.LayoutParams(is, is));
        ll.addView(icon);
        setContentView(ll);
        ColorFadeAnimation cfa = new ColorFadeAnimation(Center.getColorBottom(getApplicationContext()), Center.getColorTop(getApplicationContext()), new ColorFadeAnimation.ColorState() {
            @Override
            public void onColor(final int color) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ll.setBackground(generateGradient(color, Center.getColorBottom(getApplicationContext())));
                        getWindow().setNavigationBarColor(Center.getColorBottom(getApplicationContext()));
                        getWindow().setStatusBarColor(color);
                    }
                });
            }
        });
        cfa.start(3000);
        initStageC();
    }

    private Drawable generateGradient(int colorA, int colorB) {
        return new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
                colorA,
                colorB
        });
    }

    private void initStageC() {
        //        Log.i("Stage", "C");
        if (Device.isOnline(getApplicationContext())) {
            initStageD();
        } else {
            Toast.makeText(getApplicationContext(), R.string.interface_no_connection, Toast.LENGTH_SHORT).show();
            initStageE(false);
        }
    }

    private void initStageD() {
//        Log.i("Window Focus", String.valueOf(hasWindowFocus()));
        if (linkFetcher == null && hasWindowFocus()) {
//            Log.i("StageD", "Here");
            linkFetcher = new LinkFetcher(getString(R.string.provider_internal_schedule_page), getResources().getString(R.string.provider_internal_schedule_page_fallback), getResources().getString(R.string.provider_internal_github_fallback), new LinkFetcher.OnFinish() {
                @Override
                public void onLinkFetch(final String link) {
                    if (link != null) {
                        StringBuilder fileName = new StringBuilder();
                        fileName.append(getResources().getString(R.string.schedule_file_name));
                        fileName.append(".");
                        fileName.append(link.split("\\.")[link.split("\\.").length - 1]);
                        if (!hasLink(getApplicationContext(), link)) {
                            new Download(link, new File(getApplicationContext().getCacheDir(), fileName.toString()), new Download.Callback() {
                                @Override
                                public void onSuccess(File file) {
                                    Schedule schedule = AppCore.getSchedule(file, link);
                                    boolean hasOrigin = hasLink(getApplicationContext(), schedule.getOrigin());
                                    if (!hasOrigin) {
                                        pm.getCoreManager().addSchedule(schedule);
                                    } else {
                                        pm.getCoreManager().renewSchedule(schedule);
                                    }
                                    initStageE(true);
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    initStageC();
                                }
                            }).execute();
                        } else {
                            initStageE(false);
                        }
                    } else {
                        initStageE(false);
                    }
                }

                @Override
                public void onFail() {
                    initStageC();
                }
            });
            linkFetcher.execute();
        }
    }

    private void initStageE(boolean newSchedule) {
        if (!pm.getUserManager().get(R.string.preferences_user_launch_first, true)) {
            if (newSchedule) {
                if (pm.getKeyManager().isKeyLoaded(R.string.preferences_keys_type_news)) {
                    Center.enter(this, HomeActivity.class);
                } else {
                    Center.enter(this, NewsActivity.class);
                }
            } else {
                Center.enter(this, HomeActivity.class);
            }
        } else {
            Center.enter(this, TutorialActivity.class);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) initStageC();
    }
}
