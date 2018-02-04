package nadav.tasher.handasaim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;

import nadav.tasher.lightool.Light;

import static nadav.tasher.handasaim.Push.getDay;

public class Main extends Activity {
    static int textColor = Color.WHITE;
    private int color = Values.defaultColor;
    private int secolor = color + 0x333333;
    private int countheme = 0;
    private int keyentering = 0;
    private String day;
    private Class currentClass;
    private ForTeachers.Teacher currentTeacher;
    private LinearLayout masterLayout;
    private LinearLayout masterNavigation;
    private Graphics.CurvedTextView ctv;
    private Theme[] themes = new Theme[]{new Theme("#000000"), new Theme("#2c7cb4"), new Theme("#562627"), new Theme("#1b5c96"), new Theme("#773272"), new Theme("#9b8c36"), new Theme("#425166"), new Theme("#112233"), new Theme("#325947"), new Theme("#893768"), new Theme("#746764"), new Theme("#553311"), new Theme(color)};
    private String[] ees = new String[]{"Love is like the wind, you can't see it but you can feel it.", "I'm not afraid of death; I just don't want to be there when it happens.", "All you need is love. But a little chocolate now and then doesn't hurt.", "When the power of love overcomes the love of power the world will know peace.", "For every minute you are angry you lose sixty seconds of happiness.", "Yesterday is history, tomorrow is a mystery, today is a gift of God, which is why we call it the present.", "The fool doth think he is wise, but the wise man knows himself to be a fool.", "In three words I can sum up everything I've learned about life: it goes on.", "You only live once, but if you do it right, once is enough.", "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.", "Life is pleasant. Death is peaceful. It's the transition that's troublesome.", "There are only two ways to live your life. One is as though nothing is a miracle. The other is as though everything is a miracle.", "We are not retreating - we are advancing in another Direction.", "The difference between fiction and reality? Fiction has to make sense.", "The right to swing my fist ends where the other man's nose begins.", "Denial ain't just a river in Egypt.", "Every day I get up and look through the Forbes list of the richest people in America. If I'm not there, I go to work.", "Advice is what we ask for when we already know the answer but wish we didn't", "The nice thing about egotists is that they don't talk about other people.", "Obstacles are those frightful things you see when you take your eyes off your goal.", "You can avoid reality, but you cannot avoid the consequences of avoiding reality.", "You may not be interested in war, but war is interested in you.", "Don't stay in bed, unless you can make money in bed.", "C makes it easy to shoot yourself in the foot; C++ makes it harder, but when you do, it blows away your whole leg.", "I have not failed. I've just found 10,000 ways that won't work.", "Black holes are where God divided by zero.", "The significant problems we face cannot be solved at the same level of thinking we were at when we created them.", "Knowledge speaks, but wisdom listens.", "Sleep is an excellent way of listening to an opera.", "Success usually comes to those who are too busy to be looking for it"};
    private String[] infact = new String[]{"Every year more than 2500 left-handed people are killed from using right-handed products.", "In 1895 Hampshire police handed out the first ever speeding ticket, fining a man for doing 6mph!", "Over 1000 birds a year die from smashing into windows.", "Squirrels forget where they hide about half of their nuts.", "The average person walks the equivalent of twice around the world in a lifetime.", "A company in Taiwan makes dinnerware out of wheat, so you can eat your plate!", "An apple, potato, and onion all taste the same if you eat them with your nose plugged.", "Dying is illegal in the Houses of Parliaments – This has been voted as the most ridiculous law by the British citizens.", "The first alarm clock could only ring at 4am.", "If you leave everything to the last minute… it will only take a minute.", "Every human spent about half an hour as a single cell.", "The Twitter bird actually has a name – Larry.", "Sea otters hold hands when they sleep so they don’t drift away from each other.", "The French language has seventeen different words for ‘surrender’.", "The Titanic was the first ship to use the SOS signal.", "A baby octopus is about the size of a flea when it is born.", "You cannot snore and dream at the same time.", "A toaster uses almost half as much energy as a full-sized oven.", "If you consistently fart for 6 years & 9 months, enough gas is produced to create the energy of an atomic bomb!", "An eagle can kill a young deer and fly away with it.", "Polar bears can eat as many as 86 penguins in a single sitting.", "If Pinokio says “My Nose Will Grow Now”, it would cause a paradox.", "Bananas are curved because they grow towards the sun.", "Human saliva has a boiling point three times that of regular water.", "Cherophobia is the fear of fun.", "When hippos are upset, their sweat turns red.", "Pteronophobia is the fear of being tickled by feathers!", "Banging your head against a wall burns 150 calories an hour."};
    private boolean opened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startApp();
    }

    private void loadTheme() {
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        if (sp.getBoolean(Values.fontColor, Values.fontColorDefault)) {
            textColor = Color.WHITE;
        } else {
            textColor = Color.BLACK;
        }
        if (!sp.getBoolean(Values.seasonalTheming, Values.seasonDefault) || !sp.getBoolean(Values.seasonPriority, false)) {
            color = sp.getInt(Values.color, color);
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            if (!hexColor.contains("D") && !hexColor.contains("E") && !hexColor.contains("F")) {
                secolor = color + 0x333333;
            } else {
                secolor = color;
            }
        } else if ((sp.getBoolean(Values.seasonalTheming, Values.seasonDefault) && sp.getBoolean(Values.seasonPriority, false))) {
            color = sp.getInt(Values.seasonMain, color);
            secolor = sp.getInt(Values.seasonSub, color + 0x333333);
        }
    }

    private void refreshTheme() {
        loadTheme();
        getWindow().setStatusBarColor(secolor);
        getWindow().setNavigationBarColor(color);
        masterLayout.setBackgroundColor(color);
        masterNavigation.setBackgroundColor(secolor);
        taskDesc();
    }

    private void taskDesc() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(getString(R.string.app_name), bm, secolor);
        setTaskDescription(taskDesc);
    }

    private void splash() {
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        loadTheme();
        final Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
        window.setNavigationBarColor(color);
        taskDesc();
        final LinearLayout ll = new LinearLayout(this);
        ll.setGravity(Gravity.CENTER);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundColor(color);
        final ImageView icon = new ImageView(this);
        icon.setScaleType(ImageView.ScaleType.FIT_XY);
        icon.setImageDrawable(getDrawable(R.drawable.ic_icon));
        int is = (int) (Light.Device.screenX(getApplicationContext()) * 0.8);
        icon.setLayoutParams(new LinearLayout.LayoutParams(is, is));
        ll.addView(icon);
        final ProgressBar pb = new ProgressBar(this);
        pb.setIndeterminate(true);
        pb.setVisibility(View.GONE);
        pb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) (Light.Device.screenY(getApplicationContext()) * 0.2)));
        String curved = getString(R.string.app_name);
        if (sp.getBoolean(Values.seasonalTheming, Values.seasonDefault) && sp.getBoolean(Values.seasonPriority, false)) {
            curved = sp.getString(Values.seasonName, curved);
        }
        ctv = new Graphics.CurvedTextView(this, curved, 50, Values.bakedIconColor, Light.Device.screenX(this), (int) (Light.Device.screenY(getApplicationContext()) * 0.3), (int) (Light.Device.screenY(getApplicationContext()) * 0.15) / 2);
        ctv.setVisibility(View.GONE);
        ctv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Light.Device.screenY(getApplicationContext()) * 0.3)));
        ll.addView(ctv);
        final TextView tv = new TextView(getApplicationContext());
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(21);
        tv.setTypeface(getTypeface());
        String versionin = "v" + Light.Device.getVersionName(getApplicationContext(), getPackageName());
        tv.setText(versionin);
        tv.setLayoutParams(new LinearLayout.LayoutParams(is, (int) (Light.Device.screenY(getApplicationContext()) * 0.3)));
        final ObjectAnimator slideRC = ObjectAnimator.ofFloat(tv, View.TRANSLATION_X, Light.Animations.getSlideRight(getApplicationContext()));
        slideRC.setDuration(500);
        slideRC.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                tv.setVisibility(View.GONE);
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                rotateAnimation.setDuration(2500);
                rotateAnimation.setRepeatCount(Animation.INFINITE);
                ctv.startAnimation(rotateAnimation);
                ctv.setVisibility(View.VISIBLE);
                ctv.setAlpha(0);
                ObjectAnimator oa = ObjectAnimator.ofFloat(ctv, View.ALPHA, Light.Animations.INVISIBLE_TO_VISIBLE);
                oa.setDuration(300);
                oa.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        ObjectAnimator slideR = ObjectAnimator.ofFloat(tv, View.TRANSLATION_X, -Light.Device.screenX(getApplicationContext()), 0);
        slideR.setDuration(500);
        slideR.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        slideRC.start();
                    }
                }, 500);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        slideR.start();
        ll.addView(tv);
        setContentView(ll);
        Graphics.ColorFadeAnimation cfa = new Graphics.ColorFadeAnimation(themes[new Random().nextInt(themes.length)].color, color, new Graphics.ColorFadeAnimation.ColorState() {
            @Override
            public void onColor(final int color) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ll.setBackgroundColor(color);
                        getWindow().setNavigationBarColor(color);
                        getWindow().setStatusBarColor(color);
                    }
                });
            }
        });
        cfa.start(2000);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("toast") != null) {
                Toast.makeText(this, getIntent().getExtras().getString("toast"), Toast.LENGTH_LONG).show();
            } else if (getIntent().getExtras().getString("popup") != null) {
                AlertDialog.Builder pop = new AlertDialog.Builder(this);
                pop.setCancelable(true);
                pop.setMessage(getIntent().getExtras().getString("popup"));
                pop.setPositiveButton("OK", null);
                pop.show();
            }
        }
    }

    private int getFontSize() {
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        return sp.getInt(Values.fontSizeNumber, Values.fontSizeBig);
    }

    private void newsSplash(final ArrayList<Class> classes) {
        getWindow().setStatusBarColor(color);
        getWindow().setNavigationBarColor(color);
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        if (sp.getBoolean(Values.messageBoardSkipEnabler, false)) {
            view(classes);
        } else {
            final LinearLayout full = new LinearLayout(getApplicationContext());
            full.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
            full.setOrientation(LinearLayout.VERTICAL);
            full.setPadding(10, 10, 10, 10);
            LinearLayout newsAll = new LinearLayout(getApplicationContext());
            newsAll.setGravity(Gravity.CENTER);
            final LinearLayout loadingTView = new LinearLayout(getApplicationContext());
            loadingTView.setGravity(Gravity.CENTER);
            loadingTView.setOrientation(LinearLayout.VERTICAL);
            //            loadingTView.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
            final TextView messBoardTitle = new TextView(getApplicationContext()), prinSays = new TextView(getApplicationContext()), loadingText = new TextView(getApplicationContext()), egg = new TextView(getApplicationContext());
            loadingText.setGravity(Gravity.CENTER);
            loadingText.setText(R.string.loadingtext);
            loadingText.setTextColor(Color.LTGRAY);
            loadingText.setTypeface(getTypeface());
            loadingText.setTextSize(getFontSize() + 4);
            loadingTView.addView(loadingText);
            egg.setGravity(Gravity.CENTER);
            egg.setText(getEasterEgg());
            egg.setTextColor(Color.LTGRAY);
            egg.setTypeface(getTypeface());
            egg.setTextSize(getFontSize() - 8);
            egg.setLayoutParams(new LinearLayout.LayoutParams((int) (Light.Device.screenX(getApplicationContext()) * 0.7), ViewGroup.LayoutParams.WRAP_CONTENT));
            loadingTView.addView(egg);
            loadingTView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Light.Device.screenY(getApplicationContext()) * 0.7)));
            newsAll.addView(loadingTView);
            messBoardTitle.setText(R.string.messageboard);
            prinSays.setText(R.string.psay);
            prinSays.setGravity(Gravity.CENTER);
            messBoardTitle.setGravity(Gravity.CENTER);
            prinSays.setTypeface(getTypeface());
            messBoardTitle.setTypeface(getTypeface());
            messBoardTitle.setTextSize(getFontSize() + 2);
            prinSays.setTextSize(getFontSize() + 2);
            prinSays.setTextColor(textColor);
            messBoardTitle.setTextColor(textColor);
            final LinearLayout news = new LinearLayout(getApplicationContext());
            news.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START);
            newsAll.setOrientation(LinearLayout.VERTICAL);
            news.setOrientation(LinearLayout.VERTICAL);
            final Button princibleSay = new Button(getApplicationContext());
            princibleSay.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
            princibleSay.setTypeface(getTypeface());
            princibleSay.setPadding(10, 10, 10, 10);
            princibleSay.setTextColor(textColor);
            princibleSay.setTextSize(getFontSize() - 15);
            princibleSay.setEllipsize(TextUtils.TruncateAt.END);
            princibleSay.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 4));
            //            newsAll.setPadding(0, 20, 0, 20);
            news.setPadding(40, 40, 40, 40);
            news.addView(messBoardTitle);
            news.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
            //        news.setAlpha(0.5f);
            newsAll.addView(news);
            final LinearLayout principals = new LinearLayout(getApplicationContext());
            principals.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START);
            principals.setOrientation(LinearLayout.VERTICAL);
            principals.addView(prinSays);
            principals.addView(princibleSay);
            newsAll.addView(principals);
            principals.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
            principals.setPadding(40, 20, 40, 40);
            prinSays.setPadding(10, 10, 10, 10);
            messBoardTitle.setPadding(10, 10, 10, 10);
            final ScrollView newsAllSV = new ScrollView(getApplicationContext());
            full.setBackgroundColor(color);
            newsAllSV.addView(newsAll);
            newsAllSV.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 15 * 12));
            full.addView(newsAllSV);
            LinearLayout nextLayout = new LinearLayout(getApplicationContext());
            nextLayout.setGravity(Gravity.CENTER);
            nextLayout.setOrientation(LinearLayout.HORIZONTAL);
            final Button nextButton = new Button(getApplicationContext());
            final TextView waiting = new TextView(getApplicationContext());
            TextView instructions = new TextView(getApplicationContext());
            LinearLayout.LayoutParams buttons = new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 4, Light.Device.screenY(getApplicationContext()) / 10);
            nextButton.setLayoutParams(buttons);
            waiting.setLayoutParams(buttons);
            instructions.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 3 * 2, Light.Device.screenY(getApplicationContext()) / 10));
            nextLayout.addView(instructions);
            nextLayout.addView(waiting);
            nextLayout.addView(nextButton);
            nextButton.setVisibility(View.GONE);
            instructions.setTextColor(textColor);
            waiting.setTextColor(textColor);
            instructions.setTextSize(getFontSize() - 10);
            waiting.setTextSize(getFontSize() - 10);
            instructions.setText(R.string.instructions);
            instructions.setTypeface(getTypeface());
            waiting.setTypeface(getTypeface());
            instructions.setGravity(Gravity.CENTER);
            instructions.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
            waiting.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
            waiting.setGravity(Gravity.CENTER);
            instructions.setPadding(20, 20, 20, 20);
            waiting.setPadding(20, 20, 20, 20);
            waiting.setText(R.string.secondsleft);
            //            nextLayout.setPadding(0,10,0,10);
            nextButton.setText(R.string.nxt);
            nextButton.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
            nextButton.setTypeface(getTypeface());
            nextButton.setTextSize(getFontSize() - 10);
            nextButton.setTextColor(textColor);
            nextLayout.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
            nextLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 15 * 2));
            full.addView(nextLayout);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view(classes);
                }
            });
            principals.setVisibility(View.GONE);
            news.setVisibility(View.GONE);
            setContentView(full);
            new GetMainSite(new GetMainSite.OnGet() {

                @Override
                public void onGet(final MainSite ms) {
                    if (ms != null) {
                        loadingTView.setVisibility(View.GONE);
                        if (ms.readMorePrics == null && ms.princSaying == null) {
                            view(classes);
                        }
                        princibleSay.setText(ms.princSaying);
                        principals.setVisibility(View.VISIBLE);
                        princibleSay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String url = ms.readMorePrics;
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            }
                        });
                        news.setVisibility(View.VISIBLE);
                        for (int n = 0; n < ms.news.size(); n++) {
                            final LinearLayout nt = new LinearLayout(getApplicationContext());
                            nt.setOrientation(LinearLayout.VERTICAL);
                            nt.setGravity(Gravity.CENTER);
                            Button newtopic = new Button(getApplicationContext());
                            nt.addView(newtopic);
                            nt.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
                            nt.setPadding(10, 10, 10, 10);
                            newtopic.setText(ms.news.get(n).name);
                            newtopic.setEllipsize(TextUtils.TruncateAt.END);
                            newtopic.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
                            newtopic.setTextColor(textColor);
                            newtopic.setTextSize(getFontSize() - 10);
                            newtopic.setPadding(20, 10, 20, 10);
                            newtopic.setEllipsize(TextUtils.TruncateAt.END);
                            newtopic.setLines(2);
                            newtopic.setTypeface(getTypeface());
                            if (!ms.news.get(n).imgurl.equals("") || ms.news.get(n).imgurl != null) {
                                final int finalN1 = n;
                                new PictureLoader(ms.news.get(n).imgurl, new PictureLoader.GotImage() {

                                    @Override
                                    public void onGet(Bitmap image) {
                                        ImageView imageView = new ImageView(getApplicationContext());
                                        imageView.setImageBitmap(image);
                                        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 3));
                                        imageView.setPadding(20, 20, 20, 40);
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String url = ms.news.get(finalN1).url;
                                                Intent i = new Intent(Intent.ACTION_VIEW);
                                                i.setData(Uri.parse(url));
                                                startActivity(i);
                                            }
                                        });
                                        if (image != null) nt.addView(imageView);
                                    }
                                }).execute();
                            }
                            newtopic.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 8));
                            final int finalN = n;
                            newtopic.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String url = ms.news.get(finalN).url;
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url));
                                    startActivity(i);
                                }
                            });
                            news.addView(nt);
                            newsAllSV.setScrollY(0);
                        }
                    } else {
                        view(classes);
                    }
                }
            }).execute();
            new CountDownTimer((Values.waitTime + 1) * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    String text = String.valueOf((millisUntilFinished / 1000) - 1) + "s";
                    waiting.setText(text);
                    if (millisUntilFinished <= 2000) {
                        waiting.setVisibility(View.GONE);
                        nextButton.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFinish() {
                    //                            waiting.setVisibility(View.GONE);
                    //                            nextButton.setVisibility(View.VISIBLE);
                }
            }.start();
        }
    }

    private void welcome(final ArrayList<Class> classes, final boolean renew) {
        getWindow().setStatusBarColor(color);
        getWindow().setNavigationBarColor(color);
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        LinearLayout part1 = new LinearLayout(this);
        final LinearLayout part2 = new LinearLayout(this);
        final LinearLayout part3 = new LinearLayout(this);
        part1.setGravity(Gravity.CENTER);
        part2.setGravity(Gravity.CENTER);
        part3.setGravity(Gravity.CENTER);
        part1.setOrientation(LinearLayout.VERTICAL);
        part2.setOrientation(LinearLayout.VERTICAL);
        part3.setOrientation(LinearLayout.VERTICAL);
        part1.setBackgroundColor(color);
        part2.setBackgroundColor(color);
        part3.setBackgroundColor(color);
        //part1
        ImageView icon = new ImageView(this);
        final Button setup = new Button(this);
        final TextView welcome = new TextView(this);
        setup.setTypeface(getTypeface());
        setup.setAllCaps(false);
        welcome.setTypeface(getTypeface());
        setup.setText(R.string.set);
        setup.setAlpha(0);
        setup.setBackgroundColor(Color.TRANSPARENT);
        setup.setTextSize((float) 30);
        welcome.setAlpha(0);
        welcome.setGravity(Gravity.CENTER);
        welcome.setTextSize((float) 29);
        welcome.setTextColor(Color.WHITE);
        welcome.setText(R.string.wlc);
        icon.setImageDrawable(getDrawable(R.drawable.ic_icon));
        int is = (int) (Light.Device.screenX(getApplicationContext()) * 0.8);
        icon.setLayoutParams(new LinearLayout.LayoutParams(is, (int) (Light.Device.screenY(getApplicationContext()) * 0.7)));
        ObjectAnimator iconSlide = ObjectAnimator.ofFloat(icon, View.TRANSLATION_X, -Light.Device.screenX(getApplicationContext()), 0);
        iconSlide.setDuration(1000);
        iconSlide.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ObjectAnimator buttonAn = ObjectAnimator.ofFloat(setup, View.ALPHA, Light.Animations.INVISIBLE_TO_VISIBLE);
                buttonAn.setDuration(500);
                buttonAn.start();
                ObjectAnimator welAn = ObjectAnimator.ofFloat(welcome, View.ALPHA, Light.Animations.INVISIBLE_TO_VISIBLE);
                welAn.setDuration(500);
                welAn.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        iconSlide.start();
        part1.addView(welcome);
        part1.addView(icon);
        part1.addView(setup);
        if (!renew) setContentView(part1);
        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(part2);
            }
        });
        //part2
        TextView selClas = new TextView(this);
        ScrollView clascroll = new ScrollView(this);
        final RadioGroup classs = new RadioGroup(this);
        clascroll.addView(classs);
        Button next = new Button(this);
        next.setTypeface(getTypeface());
        next.setAllCaps(false);
        selClas.setTypeface(getTypeface());
        next.setBackgroundColor(Color.TRANSPARENT);
        next.setTextSize((float) 30);
        selClas.setGravity(Gravity.CENTER);
        selClas.setTextSize((float) 29);
        selClas.setTextColor(Color.WHITE);
        selClas.setText(R.string.scla);
        next.setText(R.string.nxt);
        part2.addView(selClas);
        part2.addView(clascroll);
        part2.addView(next);
        classs.setGravity(Gravity.CENTER);
        clascroll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Light.Device.screenY(getApplicationContext()) * 0.7)));
        for (int c = 0; c < classes.size(); c++) {
            RadioButton rb = new RadioButton(this);
            rb.setText(classes.get(c).name);
            rb.setTextSize((float) 30);
            rb.setTypeface(getTypeface());
            rb.setGravity(Gravity.CENTER);
            rb.setId(c);
            rb.setLayoutParams(new RadioGroup.LayoutParams(Light.Device.screenX(getApplicationContext()) / 10 * 8, ViewGroup.LayoutParams.WRAP_CONTENT));
            classs.addView(rb);
        }
        classs.check(0);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(Values.favoriteClass, classes.get(classs.getCheckedRadioButtonId()).name).commit();
                setContentView(part3);
            }
        });
        //part3
        LinearLayout spcl = new LinearLayout(getApplicationContext());
        spcl.setOrientation(LinearLayout.VERTICAL);
        spcl.setGravity(Gravity.CENTER);
        TextView spclSet = new TextView(this);
        Button done = new Button(this);
        done.setTypeface(getTypeface());
        spclSet.setTypeface(getTypeface());
        done.setBackgroundColor(Color.TRANSPARENT);
        done.setTextSize((float) 30);
        done.setAllCaps(false);
        spclSet.setGravity(Gravity.CENTER);
        spclSet.setTextSize((float) 29);
        spclSet.setTextColor(Color.WHITE);
        spclSet.setText(R.string.spclstt);
        done.setText(R.string.dn);
        final Switch showTimes = new Switch(this);
        showTimes.setChecked(sp.getBoolean(Values.lessonTime, Values.lessonTimeDefault));
        showTimes.setText(R.string.sct);
        showTimes.setTextSize((float) 23);
        showTimes.setTypeface(getTypeface());
        showTimes.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 10 * 8, ViewGroup.LayoutParams.WRAP_CONTENT));
        final Switch textCo = new Switch(this);
        textCo.setChecked(sp.getBoolean(Values.fontColor, Values.fontColorDefault));
        textCo.setText(R.string.white);
        textCo.setTextSize((float) 23);
        textCo.setTypeface(getTypeface());
        textCo.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 10 * 8, ViewGroup.LayoutParams.WRAP_CONTENT));
        textCo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    textCo.setText(R.string.white);
                    textCo.setTextColor(Color.WHITE);
                } else {
                    textCo.setText(R.string.black);
                    textCo.setTextColor(Color.BLACK);
                }
            }
        });
        final Switch showBreaks = new Switch(this);
        showBreaks.setChecked(sp.getBoolean(Values.breakTime, Values.breakTimeDefault));
        showBreaks.setText(R.string.showbrk);
        showBreaks.setTextSize((float) 23);
        showBreaks.setTypeface(getTypeface());
        showBreaks.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 10 * 8, ViewGroup.LayoutParams.WRAP_CONTENT));
        final Switch push = new Switch(this);
        push.setChecked(sp.getBoolean(Values.pushService, Values.pushDefault));
        push.setText(R.string.push);
        push.setTextSize((float) 23);
        push.setTypeface(getTypeface());
        push.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 10 * 8, ViewGroup.LayoutParams.WRAP_CONTENT));
        final Switch seasonalTheming = new Switch(this);
        seasonalTheming.setChecked(sp.getBoolean(Values.seasonalTheming, Values.seasonDefault));
        seasonalTheming.setText(R.string.season);
        seasonalTheming.setTextSize((float) 23);
        seasonalTheming.setTypeface(getTypeface());
        seasonalTheming.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 10 * 8, ViewGroup.LayoutParams.WRAP_CONTENT));
        final Switch automute = new Switch(this);
        automute.setChecked(sp.getBoolean(Values.autoMute, Values.autoMuteDefault));
        automute.setText(R.string.dnd);
        automute.setTextSize((float) 23);
        automute.setTypeface(getTypeface());
        automute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                boolean granted = false;
                if (nm != null) {
                    granted = nm.isNotificationPolicyAccessGranted();
                }
                if (!granted && isChecked) {
                    AlertDialog.Builder pop = new AlertDialog.Builder(Main.this);
                    pop.setCancelable(true);
                    pop.setMessage("You need to enable 'Do Not Disturb' permissions for the app.");
                    pop.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (Build.VERSION.SDK_INT >= 23)
                                startActivityForResult(new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS), 0);
                        }
                    });
                    pop.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            automute.setChecked(false);
                        }
                    });
                    pop.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            automute.setChecked(false);
                        }
                    });
                    pop.show();
                }
            }
        });
        automute.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 10 * 8, ViewGroup.LayoutParams.WRAP_CONTENT));
        spcl.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 10 * 9, (int) (Light.Device.screenY(getApplicationContext()) * 0.7)));
        part3.addView(spclSet);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(Light.Device.screenX(getApplicationContext()) / 10 * 8, ViewGroup.LayoutParams.WRAP_CONTENT);
        spcl.addView(showTimes);
        spcl.addView(getTv("Show lesson's start time near lesson's name", 15, p));
        spcl.addView(showBreaks);
        spcl.addView(getTv("Show breaks in schedule, between lessons", 15, p));
        spcl.addView(textCo);
        spcl.addView(getTv("Text color (black/white)", 15, p));
        spcl.addView(push);
        spcl.addView(getTv("Live messages for important things", 15, p));
        spcl.addView(seasonalTheming);
        spcl.addView(getTv("Change the color based on upcoming events", 15, p));
        if (Build.VERSION.SDK_INT >= 23) {
            spcl.addView(automute);
            spcl.addView(getTv("Auto mute and unmute on lessons", 15, p));
        }
        spcl.addView(getTv("\nYou can long click on any tile in the app for an explanation of what it does.", 15, p));
        part3.addView(spcl);
        part3.addView(done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putBoolean(Values.lessonTime, showTimes.isChecked()).commit();
                sp.edit().putBoolean(Values.autoMute, automute.isChecked()).commit();
                sp.edit().putBoolean(Values.fontColor, textCo.isChecked()).commit();
                sp.edit().putBoolean(Values.breakTime, showBreaks.isChecked()).commit();
                sp.edit().putBoolean(Values.pushService, push.isChecked()).commit();
                sp.edit().putBoolean(Values.seasonalTheming, seasonalTheming.isChecked()).commit();
                if (!renew) {
                    sp.edit().putInt(Values.color, Values.defaultColor).commit();
                }
                sp.edit().putInt(Values.lastRecordedVersionCode, Light.Device.getVersionCode(getApplicationContext(), getPackageName())).commit();
                sp.edit().putBoolean(Values.firstLaunch, false).commit();
                if (push.isChecked()) {
                    startPush(getApplicationContext());
                }
                loadTheme();
                view(classes);
            }
        });
        if (renew) {
            spclSet.setText(R.string.rnew);
            setContentView(part3);
        }
    }

    private void checkInternet() {
        if (Light.Device.isOnline(getApplicationContext())) {
            new Light.Net.Pinger(5000, new Light.Net.Pinger.OnEnd() {
                @Override
                public void onPing(String s, boolean b) {
                    if (s.equals(Values.serviceProvider) && b) {
                        openApp();
                    } else if (s.equals(Values.serviceProvider) && !b) {
                        //                        popup("Server Error: No Response From Service Provider.");
                        checkInternet();
                    }
                }
            }).execute(Values.serviceProvider);
        } else {
            popup("No Internet Connection.");
        }
    }

    private void startApp() {
        splash();
        checkInternet();
    }

    private void popup(String text) {
        AlertDialog.Builder pop = new AlertDialog.Builder(this);
        pop.setCancelable(true);
        pop.setMessage(text);
        pop.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startApp();
            }
        });
        pop.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                startApp();
            }
        });
        pop.show();
    }

    private void loadKey(int type) {
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        switch (type) {
            case 1:
                sp.edit().putBoolean(Values.messageBoardSkipEnabler, true).commit();
                break;
            case 2:
                sp.edit().putBoolean(Values.teacherModeEnabler, true).commit();
                break;
            default:
                break;
        }
        Toast.makeText(getApplicationContext(), "Key loaded successfully.", Toast.LENGTH_SHORT).show();
    }

    private void checkAndLoadKey(final String key) {
        new Light.Net.Pinger(10000, new Light.Net.Pinger.OnEnd() {
            @Override
            public void onPing(String s, boolean b) {
                if (b) {
                    ArrayList<Light.Net.PHP.Post.PHPParameter> parms = new ArrayList<>();
                    parms.add(new Light.Net.PHP.Post.PHPParameter("deactivate", key));
                    new Light.Net.PHP.Post(Values.keyProvider, parms, new Light.Net.PHP.Post.OnPost() {
                        @Override
                        public void onPost(String s) {
                            Log.i("DEBUG", s);
                            try {
                                JSONObject o = new JSONObject(s);
                                if (o.getBoolean("success")) {
                                    if (o.getString("key").equals(key)) {
                                        loadKey(o.getInt("type"));
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Key comparison failed.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Key does not exist, or already used", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Key verification failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Key provider unreachable.", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(Values.puzProvider);
    }

    private void popupKeyEntering() {
        AlertDialog.Builder pop = new AlertDialog.Builder(this);
        pop.setCancelable(true);
        pop.setTitle("Enter Unlock Key");
        final EditText key = new EditText(this);
        key.setFilters(new InputFilter[]{Filters.codeFilter, new InputFilter.AllCaps()});
        pop.setView(key);
        key.setLayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pop.setPositiveButton("Unlock", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                checkAndLoadKey(key.getText().toString().toUpperCase());
            }
        });
        pop.setNegativeButton("Close", null);
        pop.show();
    }

    private void aboutPopup() {
        AlertDialog.Builder ab = new AlertDialog.Builder(Main.this);
        ab.setTitle(R.string.app_name);
        ab.setMessage("Made By NadavTasher\nVersion: " + Light.Device.getVersionName(getApplicationContext(), getPackageName()) + "\nBuild: " + Light.Device.getVersionCode(getApplicationContext(), getPackageName()));
        ab.setCancelable(true);
        ab.setPositiveButton("Close", null);
        keyentering++;
        if (keyentering == Values.maxKeyEntering) {
            keyentering = 0;
            ab.setNegativeButton("Enter Code", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    popupKeyEntering();
                }
            });
        }
        ab.show();
    }

    private void showEasterEgg() {
        Toast.makeText(getApplicationContext(), getEasterEgg(), Toast.LENGTH_LONG).show();
    }

    private void view(final ArrayList<Class> classes) {
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        final ImageView logo = new ImageView(this);
        final ImageView news = new ImageView(this);
        final ImageView gear = new ImageView(this);
        final int x = Light.Device.screenX(getApplicationContext());
        final int y = Light.Device.screenY(getApplicationContext());
        final int logoSize = (y / 8) - (y / 30);
        final int newsSize = (y / 9) - (y / 30);
        final int navY = y / 8;
        final LinearLayout.LayoutParams logoParms = new LinearLayout.LayoutParams(logoSize, logoSize);
        final LinearLayout.LayoutParams newsParms = new LinearLayout.LayoutParams(newsSize, newsSize);
        final LinearLayout.LayoutParams navigationParms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, navY);
        masterLayout = new LinearLayout(this);
        final HorizontalScrollView tileNavigationScroll = new HorizontalScrollView(this);
        final ScrollView contentScroll = new ScrollView(this);
        final FrameLayout content = new FrameLayout(this);
        masterNavigation = new LinearLayout(this);
        final LinearLayout permenantNavigation = new LinearLayout(this);
        final LinearLayout tileNavigation = new LinearLayout(this);
        refreshTheme();
        masterNavigation.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        masterNavigation.setLayoutParams(navigationParms);
        masterNavigation.setPadding(20, 10, 20, 10);
        content.setPadding(10, 10, 10, 10);
        masterLayout.setOrientation(LinearLayout.VERTICAL);
        masterNavigation.setOrientation(LinearLayout.HORIZONTAL);
        tileNavigation.setOrientation(LinearLayout.HORIZONTAL);
        permenantNavigation.setOrientation(LinearLayout.HORIZONTAL);
        masterLayout.setGravity(Gravity.START | Gravity.CENTER_HORIZONTAL);
        masterNavigation.setGravity(Gravity.CENTER);
        tileNavigation.setGravity(Gravity.CENTER);
        permenantNavigation.setGravity(Gravity.CENTER);
        masterLayout.setBackgroundColor(color);
        masterNavigation.setBackgroundColor(secolor);
        tileNavigationScroll.addView(tileNavigation);
        masterNavigation.addView(permenantNavigation);
        masterNavigation.addView(tileNavigationScroll);
        contentScroll.addView(content);
        masterLayout.addView(masterNavigation);
        masterLayout.addView(contentScroll);
        permenantNavigation.addView(logo);
        permenantNavigation.addView(news);
        permenantNavigation.addView(gear);
        //Icons
        news.setLayoutParams(newsParms);
        news.setImageDrawable(getDrawable(R.drawable.ic_news));
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupNews();
            }
        });
        logo.setLayoutParams(logoParms);
        logo.setImageDrawable(getDrawable(R.drawable.ic_icon));
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutPopup();
            }
        });
        logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showEasterEgg();
                return true;
            }
        });
        gear.setLayoutParams(newsParms);
        gear.setImageDrawable(getDrawable(R.drawable.ic_gear));
        //Home
        final int tileHome = (x);
        final int permHome = ((x / 2) - (logoSize + 2 * newsSize) / 2) - (masterNavigation.getPaddingLeft() + masterNavigation.getPaddingRight()) / 2;
        //Hide Things
        tileNavigationScroll.setX(tileHome);
        //Locate
        permenantNavigation.setX(permHome);
        gear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gear.setEnabled(false);
                if (opened) {
                    ObjectAnimator.ofFloat(permenantNavigation, View.TRANSLATION_X, 0, permHome).setDuration(500).start();
                    ObjectAnimator.ofFloat(tileNavigationScroll, View.TRANSLATION_X, 0, tileHome).setDuration(500).start();
                    gear.setEnabled(true);
                } else {
                    ObjectAnimator.ofFloat(permenantNavigation, View.TRANSLATION_X, permHome, 0).setDuration(500).start();
                    ObjectAnimator.ofFloat(tileNavigationScroll, View.TRANSLATION_X, tileHome, 0).setDuration(500).start();
                    gear.setEnabled(true);
                }
                opened = !opened;
            }
        });
        Graphics.Tile teacherModeTile = new Graphics.Tile(getApplicationContext(), Values.teacherMode, false, R.drawable.ic_teacher_mode, false);
        final Graphics.Tile lessonNameTile = new Graphics.Tile(getApplicationContext(), Values.lessonName, false, R.drawable.ic_lessonname, false);
        lessonNameTile.setLongClick("Toggle Lesson Name", Gravity.CENTER);
        teacherModeTile.setLongClick("Toggle Teacher Mode", Gravity.CENTER);
        if (sp.getBoolean(Values.teacherModeEnabler, false)) {
            tileNavigation.addView(teacherModeTile);
            tileNavigation.addView(lessonNameTile);
        }
        Graphics.Tile lessonTimeTile = new Graphics.Tile(getApplicationContext(), Values.lessonTime, Values.lessonTimeDefault, R.drawable.ic_clock, false);
        lessonTimeTile.setLongClick("Toggle Lesson Time", Gravity.CENTER);
        tileNavigation.addView(lessonTimeTile);
        Graphics.Tile breakTile = new Graphics.Tile(getApplicationContext(), Values.breakTime, Values.breakTimeDefault, R.drawable.ic_break_time, false);
        breakTile.setLongClick("Toggle Breaks In Schedule", Gravity.CENTER);
        tileNavigation.addView(breakTile);
        final Graphics.Tile autoMuteTile = new Graphics.Tile(getApplicationContext(), Values.autoMute, Values.autoMuteDefault, R.drawable.ic_auto_mute, false);
        autoMuteTile.setOnValueChanged(new Graphics.Tile.OnValueChanged() {
            @Override
            public void on() {
                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                boolean granted = false;
                if (nm != null) {
                    granted = nm.isNotificationPolicyAccessGranted();
                }
                if (!granted) {
                    AlertDialog.Builder pop = new AlertDialog.Builder(Main.this);
                    pop.setCancelable(true);
                    pop.setMessage("You need to enable 'Do Not Disturb' permissions for the app.");
                    pop.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (Build.VERSION.SDK_INT >= 23) {
                                startActivityForResult(new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS), 0);
                            }
                        }
                    });
                    pop.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            autoMuteTile.manualOff();
                        }
                    });
                    pop.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            autoMuteTile.manualOff();
                        }
                    });
                    pop.show();
                }
            }

            @Override
            public void off() {
            }
        });
        autoMuteTile.setLongClick("Toggle Auto-Mute", Gravity.CENTER);
        tileNavigation.addView(autoMuteTile);
        Graphics.Tile textColorTile = new Graphics.Tile(getApplicationContext(), Values.fontColor, Values.fontColorDefault, R.drawable.ic_white, true);
        textColorTile.setSecondIcon(R.drawable.ic_black);
        textColorTile.setOnValueChanged(new Graphics.Tile.OnValueChanged() {
            @Override
            public void on() {
                textColor = Color.WHITE;
            }

            @Override
            public void off() {
                textColor = Color.BLACK;
            }
        });
        textColorTile.setLongClick("Switch Text Color", Gravity.CENTER);
        tileNavigation.addView(textColorTile);
        Graphics.Tile seasonalTile = new Graphics.Tile(getApplicationContext(), Values.seasonalTheming, Values.seasonDefault, R.drawable.ic_season, false);
        seasonalTile.setOnValueChanged(new Graphics.Tile.OnValueChanged() {
            @Override
            public void on() {
                checkSeasonalTheming(new After() {
                    @Override
                    public void after() {
                        refreshTheme();
                    }
                });
            }

            @Override
            public void off() {
                sp.edit().putBoolean(Values.seasonPriority, false).apply();
                loadTheme();
            }
        });
        seasonalTile.setLongClick("Toggle Seasonal Theming", Gravity.CENTER);
        tileNavigation.addView(seasonalTile);
        Graphics.Tile pushTile = new Graphics.Tile(getApplicationContext(), Values.pushService, Values.pushDefault, R.drawable.ic_push, false);
        pushTile.setLongClick("Toggle Live Messages", Gravity.CENTER);
        tileNavigation.addView(pushTile);
        Graphics.Tile fontSizeTile = new Graphics.Tile(getApplicationContext(), Values.fontSize, Values.fontSizeDefault, R.drawable.ic_font_big, false);
        fontSizeTile.setSecondIcon(R.drawable.ic_font_small);
        fontSizeTile.setOnValueChanged(new Graphics.Tile.OnValueChanged() {
            @Override
            public void on() {
                sp.edit().putInt(Values.fontSizeNumber, Values.fontSizeSmall).apply();
            }

            @Override
            public void off() {
                sp.edit().putInt(Values.fontSizeNumber, Values.fontSizeBig).apply();
            }
        });
        fontSizeTile.setLongClick("Switch Font Size", Gravity.CENTER);
        tileNavigation.addView(fontSizeTile);
        Graphics.Tile.EndlessTile themeTile = new Graphics.Tile.EndlessTile(getApplicationContext(), R.drawable.ic_paint, new Graphics.Tile.EndlessTile.OnAction() {
            @Override
            public void click() {
                if (!sp.getBoolean(Values.seasonPriority, false)) {
                    sp.edit().putInt(Values.color, themes[countheme].color).commit();
                    color = themes[countheme].color;
                    refreshTheme();
                    if (countheme + 1 < themes.length) {
                        countheme++;
                    } else {
                        countheme = 0;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Seasonal Theming Is Blocking You From Changing The Theme. You Can Turn It Off By Clicking On Its Tile.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void hold() {
                final int fontSize = getFontSize();
                final LinearLayout newsll = new LinearLayout(getApplicationContext());
                final LinearLayout bts = new LinearLayout(getApplicationContext());
                final LinearLayout layerer = new LinearLayout(getApplicationContext());
                newsll.setGravity(Gravity.CENTER);
                newsll.setOrientation(LinearLayout.HORIZONTAL);
                bts.setGravity(Gravity.CENTER);
                bts.setOrientation(LinearLayout.HORIZONTAL);
                layerer.setGravity(Gravity.CENTER);
                layerer.setOrientation(LinearLayout.VERTICAL);
                final EditText colorEditor = new EditText(getApplicationContext());
                colorEditor.setAllCaps(true);
                colorEditor.setFilters(new InputFilter[]{Filters.colorFilter});
                TextView hashv = new TextView(getApplicationContext());
                hashv.setText("#");
                hashv.setTextSize((float) fontSize);
                hashv.setTypeface(getTypeface());
                colorEditor.setTypeface(getTypeface());
                colorEditor.setTextSize((float) fontSize);
                colorEditor.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.length() == 6) {
                            layerer.setBackgroundColor(Color.parseColor("#" + charSequence));
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
                hashv.setTextColor(textColor);
                colorEditor.setTextColor(textColor);
                colorEditor.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                newsll.addView(hashv);
                newsll.addView(colorEditor);
                layerer.addView(newsll);
                Button save, close;
                save = new Button(getApplicationContext());
                close = new Button(getApplicationContext());
                save.setText(R.string.sv);
                close.setText(R.string.close);
                save.setBackground(getDrawable(R.drawable.button));
                close.setBackground(getDrawable(R.drawable.button));
                final Dialog dialog = new Dialog(Main.this);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (colorEditor.getText().length() == 6) {
                            sp.edit().putInt("color", Color.parseColor("#" + colorEditor.getText().toString())).commit();
                            refreshTheme();
                            dialog.dismiss();
                            showHS(currentClass, content, classes, sp.getBoolean(Values.lessonTime, false), getFontSize(), sp.getBoolean(Values.breakTime, true), sp.getBoolean(Values.teacherMode, false));
                        } else {
                            Toast.makeText(getApplicationContext(), "Color Has To Include 6 Characters.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                bts.addView(close);
                bts.addView(save);
                layerer.addView(bts);
                dialog.setCancelable(true);
                dialog.setContentView(layerer);
                layerer.setPadding(20, 20, 20, 20);
                layerer.setBackgroundColor(secolor);
                dialog.show();
            }
        });
        tileNavigation.addView(themeTile);
        Graphics.Tile.EndlessTile shareTile = new Graphics.Tile.EndlessTile(getApplicationContext(), R.drawable.ic_share, new Graphics.Tile.EndlessTile.OnAction() {
            @Override
            public void click() {
                if (!sp.getBoolean(Values.teacherMode, false)) {
                    share(currentClass.name + "\n" + hourSystemForClassString(currentClass, sp.getBoolean(Values.lessonTime, Values.lessonTimeDefault)));
                } else {
                    share(currentTeacher.mainName + "\n" + ForTeachers.hourSystemForTeacherString(currentTeacher, sp.getBoolean(Values.lessonTime, Values.lessonTimeDefault)));
                }
            }

            @Override
            public void hold() {
            }
        });
        tileNavigation.addView(shareTile);
        teacherModeTile.setOnValueChanged(new Graphics.Tile.OnValueChanged() {
            @Override
            public void on() {
                lessonNameTile.setVisibility(View.VISIBLE);
            }

            @Override
            public void off() {
                lessonNameTile.setVisibility(View.GONE);
            }
        });
        if (sp.getBoolean(Values.teacherMode, false)) {
            lessonNameTile.setVisibility(View.VISIBLE);
        } else {
            lessonNameTile.setVisibility(View.GONE);
        }
        Graphics.Tile.OnAction onAction = new Graphics.Tile.OnAction() {
            @Override
            public void execute() {
                refreshTheme();
                showHS(currentClass, content, classes, sp.getBoolean(Values.lessonTime, Values.lessonTimeDefault), getFontSize(), sp.getBoolean(Values.breakTime, Values.breakTimeDefault), sp.getBoolean(Values.teacherMode, false));
            }
        };
        teacherModeTile.setAfter(onAction);
        lessonNameTile.setAfter(onAction);
        themeTile.setAfter(onAction);
        seasonalTile.setAfter(onAction);
        lessonTimeTile.setAfter(onAction);
        breakTile.setAfter(onAction);
        textColorTile.setAfter(onAction);
        fontSizeTile.setAfter(onAction);
        int selectedClass = 0;
        if (sp.getString(Values.favoriteClass, null) != null) {
            if (classes != null) {
                for (int fc = 0; fc < classes.size(); fc++) {
                    if (sp.getString(Values.favoriteClass, "").equals(classes.get(fc).name)) {
                        selectedClass = fc;
                        break;
                    }
                }
            } else {
                startApp();
            }
        }
        if (classes != null)
            showHS(classes.get(selectedClass), content, classes, sp.getBoolean(Values.lessonTime, false), getFontSize(), sp.getBoolean(Values.breakTime, true), sp.getBoolean(Values.teacherMode, false));
        setContentView(masterLayout);
    }

    private void openApp() {
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        new GetLink(Values.scheduleProvider, new GetLink.GotLink() {

            @Override
            public void onLinkGet(String link) {
                if (link != null) {
                    String fileName = "hs.xls";
                    if (link.endsWith(".xlsx")) {
                        fileName = "hs.xlsx";
                    }
                    final String finalFileName = fileName;
                    String date = link.split("/")[link.split("/").length - 1].split("\\.")[0];
                    if (!sp.getString(Values.latestFileDate, "").equals(date)) {
                        sp.edit().putString(Values.latestFileName, fileName).commit();
                        sp.edit().putString(Values.latestFileDate, date).commit();
                        new FileDownloader(link, new File(getApplicationContext().getFilesDir(), fileName), new FileDownloader.OnDownload() {
                            @Override
                            public void onFinish(final File file, final boolean be) {
                                if (sp.getBoolean(Values.seasonalTheming, Values.seasonDefault) && !sp.getBoolean(Values.firstLaunch, true)) {
                                    checkSeasonalTheming(new After() {
                                        @Override
                                        public void after() {
                                            parseAndLoad(new File(getApplicationContext().getFilesDir(), finalFileName), true, finalFileName);
                                        }
                                    });
                                } else {
                                    parseAndLoad(new File(getApplicationContext().getFilesDir(), finalFileName), true, finalFileName);
                                }
                            }

                            @Override
                            public void onProgressChanged(File file, int i) {
                            }
                        }).execute();
                    } else {
                        parseAndLoad(new File(getApplicationContext().getFilesDir(), fileName), true, finalFileName);
                    }
                } else {
                    //                    popup("Could Not Fetch Link, Please Try Disconnecting From Wi-Fi");
                    openApp();
                }
            }

            @Override
            public void onFail(String e) {
                //                popup("Failed");
                openApp();
            }
        }).execute();
    }

    private void parseAndLoad(File f, boolean b, String filename) {
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        if (b) {
            ArrayList<Class> classes;
            if (filename.endsWith(".xlsx")) {
                classes = readExcelFileXLSX(f);
                day = readExcelDayXLSX(f);
            } else {
                classes = readExcelFile(f);
                day = readExcelDay(f);
            }
            /////
            //                                file = new File(getApplicationContext().getFilesDir(), "hs.xlsx");
            //                                classes = readExcelFileXLSX(file);
            //                                day = readExcelDayXLSX(file);
            /////
            if (classes != null) {
                if (!sp.getBoolean(Values.firstLaunch, true)) {
                    if (sp.getInt(Values.lastRecordedVersionCode, 0) != Light.Device.getVersionCode(getApplicationContext(), getPackageName())) {
                        welcome(classes, true);
                    } else {
                        newsSplash(classes);
                        //                                            welcome(classes, true);
                        beginDND(getApplicationContext());
                    }
                } else {
                    welcome(classes, false);
                }
            }
        } else {
            //                                popup("Failed To Download Excel File");
            openApp();
        }
    }

    private void checkSeasonalTheming(final After after) {
        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
        new Light.Net.Pinger(5000, new Light.Net.Pinger.OnEnd() {
            @Override
            public void onPing(String s, boolean b) {
                if (b) {
                    new FileReader(Values.themeProvider, new FileReader.OnRead() {
                        @Override
                        public void done(String s) {
                            try {
                                ArrayList<Theme.SchudledTheme> tm = new ArrayList<>();
                                JSONObject reader = new JSONObject(s);
                                Iterator<String> types = reader.keys();
                                while (types.hasNext()) {
                                    String name = types.next();
                                    try {
                                        Theme.SchudledTheme i = new Theme.SchudledTheme();
                                        JSONObject uo = reader.getJSONObject(name);
                                        i.id = Integer.parseInt(name);
                                        i.name = uo.getString("name");
                                        i.main = Color.parseColor(uo.getString("main"));
                                        i.sub = Color.parseColor(uo.getString("sub"));
                                        i.sd = uo.getInt("sd");
                                        i.sm = uo.getInt("sm");
                                        i.sy = uo.getInt("sy");
                                        i.ed = uo.getInt("ed");
                                        i.em = uo.getInt("em");
                                        i.ey = uo.getInt("ey");
                                        tm.add(i);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                Calendar c = Calendar.getInstance();
                                for (int n = 0; n < tm.size(); n++) {
                                    Theme.SchudledTheme it = tm.get(n);
                                    int cdate = getDay(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
                                    int adate = getDay(it.sd, it.sm, it.sy);
                                    int bdate = getDay(it.ed, it.em, it.ey);
                                    boolean dated = (adate <= cdate) && (cdate <= bdate);
                                    if (dated) {
                                        sp.edit().putBoolean(Values.seasonID + it.id, true).apply();
                                        sp.edit().putBoolean(Values.seasonPriority, true).apply();
                                        sp.edit().putInt(Values.seasonEndDay, it.ed).apply();
                                        sp.edit().putInt(Values.seasonEndMonth, it.em).apply();
                                        sp.edit().putInt(Values.seasonEndYear, it.ey).apply();
                                        sp.edit().putInt(Values.seasonMain, it.main).apply();
                                        sp.edit().putInt(Values.seasonSub, it.sub).apply();
                                        sp.edit().putString(Values.seasonName, it.name).apply();
                                        loadTheme();
                                        break;
                                    } else if (sp.getBoolean(Values.seasonID + it.id, false) && !dated) {
                                        sp.edit().putBoolean(Values.seasonPriority, false).apply();
                                        loadTheme();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (after != null) after.after();
                        }
                    }).execute();
                } else {
                    if (after != null) after.after();
                }
            }
        }).execute(Values.puzProvider);
    }

    private void popupNews() {
        final int fontSize = getFontSize();
        final Dialog dialog = new Dialog(Main.this);
        dialog.setCancelable(true);
        LinearLayout fullPage = new LinearLayout(getApplicationContext());
        final LinearLayout news = new LinearLayout(getApplicationContext()), push = new LinearLayout(getApplicationContext());
        ScrollView npscroll = new ScrollView(getApplicationContext());
        LinearLayout newsAndPush = new LinearLayout(getApplicationContext());
        fullPage.setOrientation(LinearLayout.VERTICAL);
        news.setOrientation(LinearLayout.VERTICAL);
        push.setOrientation(LinearLayout.VERTICAL);
        newsAndPush.setOrientation(LinearLayout.VERTICAL);
        fullPage.setGravity(Gravity.CENTER);
        news.setGravity(Gravity.CENTER);
        push.setGravity(Gravity.CENTER);
        newsAndPush.setGravity(Gravity.CENTER);
        npscroll.addView(newsAndPush);
        newsAndPush.addView(push);
        newsAndPush.addView(news);
        push.setPadding(10, 15, 10, 15);
        news.setPadding(10, 15, 10, 15);
        TextView pushTitle, newsTitle;
        pushTitle = new TextView(getApplicationContext());
        newsTitle = new TextView(getApplicationContext());
        pushTitle.setText(R.string.pushmess);
        newsTitle.setText(R.string.news);
        push.addView(pushTitle);
        news.addView(newsTitle);
        push.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
        news.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
        newsTitle.setTextColor(textColor);
        pushTitle.setTextColor(textColor);
        newsTitle.setTextSize(fontSize);
        pushTitle.setTextSize(fontSize);
        newsTitle.setGravity(Gravity.CENTER);
        pushTitle.setGravity(Gravity.CENTER);
        newsTitle.setTypeface(getTypeface());
        pushTitle.setTypeface(getTypeface());
        Button close = new Button(getApplicationContext());
        close.setText(R.string.cls);
        close.setAllCaps(false);
        close.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
        close.setTextSize((float) 22);
        close.setTextColor(textColor);
        close.setTypeface(getTypeface());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        close.setLayoutParams(new LinearLayout.LayoutParams((int) (Light.Device.screenX(getApplicationContext()) * 0.8) + (Light.Device.screenX(getApplicationContext()) / 20), (Light.Device.screenY(getApplicationContext()) / 10)));
        fullPage.addView(npscroll);
        fullPage.addView(close);
        fullPage.setBackgroundColor(secolor);
        fullPage.setPadding(5, 5, 5, 5);
        news.setVisibility(View.INVISIBLE);
        push.setVisibility(View.GONE);
        fullPage.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 10 * 8));
        npscroll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 10 * 6));
        dialog.setContentView(fullPage);
        if (Light.Device.isOnline(getApplicationContext())) {
            new GetNews(Values.serviceProvider, new GetNews.GotNews() {
                @Override
                public void onNewsGet(final ArrayList<Link> link) {
                    for (int i = 0; i < link.size(); i++) {
                        Button cls = new Button(getApplicationContext());
                        cls.setPadding(10, 10, 10, 10);
                        cls.setTextSize((float) fontSize - 10);
                        cls.setGravity(Gravity.CENTER);
                        cls.setText(link.get(i).name);
                        cls.setTextColor(textColor);
                        cls.setEllipsize(TextUtils.TruncateAt.END);
                        cls.setLines(2);
                        //                            cls.setBackgroundColor(Color.TRANSPARENT);
                        cls.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
                        cls.setTypeface(getTypeface());
                        cls.setLayoutParams(new LinearLayout.LayoutParams((int) (Light.Device.screenX(getApplicationContext()) * 0.8), (Light.Device.screenY(getApplicationContext()) / 8)));
                        news.addView(cls);
                        if (!link.get(i).url.equals("")) {
                            final int finalI = i;
                            cls.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String url = link.get(finalI).url;
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url));
                                    startActivity(i);
                                }
                            });
                        }
                    }
                    news.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFail(ArrayList<Link> e) {
                }
            }).execute("");
            dialog.show();
        }
    }

    private void share(String st) {
        Intent s = new Intent(Intent.ACTION_SEND);
        s.putExtra(Intent.EXTRA_TEXT, st);
        s.setType("text/plain");
        s.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(s, "Share With"));
    }

    private void showHS(final Class c, final FrameLayout content, final ArrayList<Class> classes, final boolean showTime, final int fontSize, final boolean breakTimes, final boolean teacherMode) {
        currentClass = c;
        LinearLayout hsplace = new LinearLayout(this);
        hsplace.setGravity(Gravity.START | Gravity.CENTER_HORIZONTAL);
        hsplace.setOrientation(LinearLayout.VERTICAL);
        content.removeAllViews();
        content.addView(hsplace);
        if (!teacherMode) {
            final Button className = new Button(this);
            className.setTextSize((float) fontSize);
            //        className.setBackgroundColor(Color.TRANSPARENT);
            className.setGravity(Gravity.CENTER);
            className.setBackground(getDrawable(R.drawable.coaster_normal));
            String ctxt = c.name + " (" + day + ")";
            className.setText(ctxt);
            className.setTextColor(textColor);
            className.setTypeface(getTypeface());
            className.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout classesll = new LinearLayout(getApplicationContext());
                    classesll.setGravity(Gravity.CENTER);
                    classesll.setOrientation(LinearLayout.VERTICAL);
                    final Dialog dialog = new Dialog(Main.this);
                    dialog.setCancelable(true);
                    ScrollView classesllss = new ScrollView(getApplicationContext());
                    classesllss.addView(classesll);
                    classesll.setPadding(10, 10, 10, 10);
                    classesll.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
                    Button close = new Button(getApplicationContext());
                    close.setText(R.string.cls);
                    close.setAllCaps(false);
                    close.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
                    close.setTextSize((float) 22);
                    close.setTextColor(textColor);
                    close.setTypeface(getTypeface());
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    close.setLayoutParams(new LinearLayout.LayoutParams((int) (Light.Device.screenX(getApplicationContext()) * 0.8), (Light.Device.screenY(getApplicationContext()) / 10)));
                    LinearLayout full = new LinearLayout(getApplicationContext());
                    full.setOrientation(LinearLayout.VERTICAL);
                    full.setGravity(Gravity.CENTER);
                    full.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Light.Device.screenY(getApplicationContext()) * 0.7)));
                    classesllss.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) ((Light.Device.screenY(getApplicationContext()) * 0.7) - Light.Device.screenY(getApplicationContext()) / 9)));
                    full.addView(classesllss);
                    full.addView(close);
                    full.setBackgroundColor(secolor);
                    dialog.setContentView(full);
                    for (int cs = 0; cs < classes.size(); cs++) {
                        if (classes.get(cs) != c) {
                            Button cls = new Button(getApplicationContext());
                            cls.setTextSize((float) fontSize);
                            cls.setGravity(Gravity.CENTER);
                            cls.setText(classes.get(cs).name);
                            cls.setTextColor(textColor);
                            cls.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
                            cls.setPadding(10, 0, 10, 0);
                            cls.setTypeface(getTypeface());
                            cls.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (Light.Device.screenY(getApplicationContext()) / 10)));
                            classesll.addView(cls);
                            final int finalCs = cs;
                            cls.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
                                    sp.edit().putString(Values.favoriteClass, classes.get(finalCs).name).commit();
                                    showHS(classes.get(finalCs), content, classes, showTime, fontSize, breakTimes, false);
                                    dialog.dismiss();
                                }
                            });
                        }
                    }
                    dialog.show();
                }
            });
            hsplace.addView(className);
            hsplace.addView(hourSystemForClass(c, showTime, fontSize, breakTimes));
        } else {
            final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
            final ArrayList<ForTeachers.Teacher> teachers = ForTeachers.getTeacherSchudleForClasses(classes);
            int selectedClass = 0;
            if (sp.getString(Values.favoriteTeacher, null) != null) {
                if (teachers != null) {
                    for (int fc = 0; fc < teachers.size(); fc++) {
                        if (sp.getString(Values.favoriteTeacher, "").equals(teachers.get(fc).mainName)) {
                            selectedClass = fc;
                            break;
                        }
                    }
                } else {
                    openApp();
                }
            }
            if (teachers != null) {
                currentTeacher = teachers.get(selectedClass);
            }
            final Button className = new Button(this);
            className.setTextSize((float) fontSize);
            className.setGravity(Gravity.CENTER);
            className.setBackground(getDrawable(R.drawable.coaster_normal));
            String ctxt = currentTeacher.mainName + " (" + day + ")";
            className.setText(ctxt);
            className.setTextColor(textColor);
            className.setTypeface(getTypeface());
            className.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout classesll = new LinearLayout(getApplicationContext());
                    classesll.setGravity(Gravity.CENTER);
                    classesll.setOrientation(LinearLayout.VERTICAL);
                    final Dialog dialog = new Dialog(Main.this);
                    dialog.setCancelable(true);
                    ScrollView classesllss = new ScrollView(getApplicationContext());
                    classesllss.addView(classesll);
                    classesll.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
                    Button close = new Button(getApplicationContext());
                    close.setText(R.string.cls);
                    close.setAllCaps(false);
                    close.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
                    close.setTextSize((float) 22);
                    close.setTextColor(textColor);
                    close.setTypeface(getTypeface());
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    close.setLayoutParams(new LinearLayout.LayoutParams((int) (Light.Device.screenX(getApplicationContext()) * 0.8), (Light.Device.screenY(getApplicationContext()) / 10)));
                    LinearLayout full = new LinearLayout(getApplicationContext());
                    full.setOrientation(LinearLayout.VERTICAL);
                    full.setGravity(Gravity.CENTER);
                    full.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Light.Device.screenY(getApplicationContext()) * 0.7)));
                    classesllss.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) ((Light.Device.screenY(getApplicationContext()) * 0.7) - Light.Device.screenY(getApplicationContext()) / 9)));
                    full.addView(classesllss);
                    full.addView(close);
                    full.setBackgroundColor(secolor);
                    dialog.setContentView(full);
                    if (teachers != null) {
                        for (int cs = 0; cs < teachers.size(); cs++) {
                            if (teachers.get(cs) != currentTeacher) {
                                Button cls = new Button(getApplicationContext());
                                cls.setTextSize((float) fontSize);
                                cls.setGravity(Gravity.CENTER);
                                cls.setText(teachers.get(cs).mainName);
                                cls.setTextColor(textColor);
                                cls.setBackground(getDrawable(R.drawable.coaster_normal_transparent));
                                cls.setPadding(10, 0, 10, 0);
                                cls.setTypeface(getTypeface());
                                cls.setLayoutParams(new LinearLayout.LayoutParams((int) (Light.Device.screenX(getApplicationContext()) * 0.8), (Light.Device.screenY(getApplicationContext()) / 8)));
                                classesll.addView(cls);
                                final int finalCs = cs;
                                cls.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        final SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE);
                                        sp.edit().putString(Values.favoriteTeacher, teachers.get(finalCs).mainName).commit();
                                        showHS(currentClass, content, classes, showTime, fontSize, breakTimes, true);
                                        dialog.dismiss();
                                    }
                                });
                            }
                        }
                    }
                    dialog.show();
                }
            });
            hsplace.addView(className);
            hsplace.addView(ForTeachers.hourSystemForTeacher(getApplicationContext(), currentTeacher, showTime, fontSize, breakTimes, sp.getBoolean(Values.lessonName, false)));
        }
    }

    static void beginDND(Context c) {
        c.sendBroadcast(new Intent(Values.KILL_DND_SERVICE));
        c.startService(new Intent(c, DNDService.class));
    }

    static void startPush(Context c) {
        ComponentName serviceComponent = new ComponentName(c, Push.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(Values.pushLoop);
        JobScheduler jobScheduler = c.getSystemService(JobScheduler.class);
        if (jobScheduler != null) {
            jobScheduler.schedule(builder.build());
        }
    }

    private String hourSystemForClassString(Class fclass, boolean showTime) {
        String allsubj = "";
        for (int s = 0; s < fclass.subjects.size(); s++) {
            String before;
            if (showTime) {
                before = "(" + getRealTimeForHourNumber(fclass.subjects.get(s).hour) + ") " + fclass.subjects.get(s).hour + ". ";
            } else {
                before = fclass.subjects.get(s).hour + ". ";
            }
            String total = before + fclass.subjects.get(s).name;
            if (fclass.subjects.get(s).name != null && !fclass.subjects.get(s).name.equals("")) {
                allsubj += total + "\n";
            }
        }
        return allsubj;
    }

    private String readExcelDay(File f) {
        try {
            POIFSFileSystem myFileSystem = new POIFSFileSystem(new FileInputStream(f));
            Workbook myWorkBook = new HSSFWorkbook(myFileSystem);
            Sheet mySheet = myWorkBook.getSheetAt(0);
            return mySheet.getRow(0).getCell(0).getStringCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    private String readExcelDayXLSX(File f) {
        try {
            XSSFWorkbook myWorkBook = new XSSFWorkbook(new FileInputStream(f));
            Sheet mySheet = myWorkBook.getSheetAt(0);
            return mySheet.getRow(0).getCell(0).getStringCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    private String getEasterEgg() {
        int which = new Random().nextInt(2);
        if (which == 0) {
            int newrand = new Random().nextInt(infact.length);
            return "Did You Know: " + infact[newrand];
        } else {
            int newrand = new Random().nextInt(ees.length);
            return "\"" + ees[newrand] + "\"";
        }
    }

    static String getRealTimeForHourNumber(int hour) {
        switch (hour) {
            case 0:
                return "07:45";
            case 1:
                return "08:30";
            case 2:
                return "09:15";
            case 3:
                return "10:15";
            case 4:
                return "11:00";
            case 5:
                return "12:10";
            case 6:
                return "12:55";
            case 7:
                return "13:50";
            case 8:
                return "14:35";
            case 9:
                return "15:30";
            case 10:
                return "16:15";
            case 11:
                return "17:00";
            case 12:
                return "17:45";
        }
        return null;
    }

    static String getRealEndTimeForHourNumber(int hour) {
        switch (hour) {
            case 0:
                return "08:30";
            case 1:
                return "09:15";
            case 2:
                return "10:00";
            case 3:
                return "11:00";
            case 4:
                return "11:45";
            case 5:
                return "12:55";
            case 6:
                return "13:40";
            case 7:
                return "14:35";
            case 8:
                return "15:20";
            case 9:
                return "16:15";
            case 10:
                return "17:00";
            case 11:
                return "17:45";
            case 12:
                return "18:30";
        }
        return null;
    }

    private Typeface getTypeface() {
        return Typeface.createFromAsset(getAssets(), Values.fontName);
    }

    static Typeface getTypeface(Context c) {
        return Typeface.createFromAsset(c.getAssets(), Values.fontName);
    }

    static ArrayList<Class> readExcelFile(File f) {
        try {
            ArrayList<Class> classes = new ArrayList<>();
            POIFSFileSystem myFileSystem = new POIFSFileSystem(new FileInputStream(f));
            Workbook myWorkBook = new HSSFWorkbook(myFileSystem);
            Sheet mySheet = myWorkBook.getSheetAt(0);
            int rows = mySheet.getLastRowNum();
            int cols = mySheet.getRow(1).getLastCellNum();
            for (int c = 1; c < cols; c++) {
                ArrayList<Subject> subs = new ArrayList<>();
                for (int r = 2; r < rows; r++) {
                    Row row = mySheet.getRow(r);
                    subs.add(new Subject(r - 2, row.getCell(c).getStringCellValue().split("\\r?\\n")[0], row.getCell(c).getStringCellValue()));
                }
                classes.add(new Class(mySheet.getRow(1).getCell(c).getStringCellValue(), subs));
            }
            return classes;
        } catch (Exception e) {
            return null;
        }
    }

    static ArrayList<Class> readExcelFileXLSX(File f) {
        try {
            ArrayList<Class> classes = new ArrayList<>();
            XSSFWorkbook myWorkBook = new XSSFWorkbook(new FileInputStream(f));
            Sheet mySheet = myWorkBook.getSheetAt(0);
            int rows = mySheet.getLastRowNum();
            int cols = mySheet.getRow(1).getLastCellNum();
            for (int c = 1; c < cols; c++) {
                ArrayList<Subject> subs = new ArrayList<>();
                for (int r = 2; r < rows; r++) {
                    Row row = mySheet.getRow(r);
                    subs.add(new Subject(r - 2, row.getCell(c).getStringCellValue().split("\\r?\\n")[0], row.getCell(c).getStringCellValue()));
                }
                classes.add(new Class(mySheet.getRow(1).getCell(c).getStringCellValue(), subs));
            }
            return classes;
        } catch (Exception e) {
            return null;
        }
    }

    static int minuteOfDay(int h, int m) {
        return h * 60 + m;
    }

    static int getBreak(int washour) {
        switch (washour) {
            case 2:
                return 15;
            case 4:
                return 25;
            case 6:
                return 10;
            case 8:
                return 10;
            case 10:
                return 5;
        }
        return -1;
    }

    static ClassTime getTimeForLesson(int hour) {
        switch (hour) {
            case 0:
                return new ClassTime(7, 8, 45, 30);
            case 1:
                return new ClassTime(8, 9, 30, 15);
            case 2:
                return new ClassTime(9, 10, 15, 0);
            case 3:
                return new ClassTime(10, 11, 15, 0);
            case 4:
                return new ClassTime(11, 11, 0, 45);
            case 5:
                return new ClassTime(12, 12, 10, 55);
            case 6:
                return new ClassTime(12, 13, 55, 40);
            case 7:
                return new ClassTime(13, 14, 50, 35);
            case 8:
                return new ClassTime(14, 15, 35, 20);
            case 9:
                return new ClassTime(15, 16, 30, 15);
            case 10:
                return new ClassTime(16, 17, 15, 0);
            case 11:
                return new ClassTime(17, 17, 0, 45);
            case 12:
                return new ClassTime(17, 18, 45, 30);
        }
        return new ClassTime(-1, -1, -1, -1);
    }

    private TextView getTv(String text, int size, @Nullable LinearLayout.LayoutParams p) {
        TextView tv = new TextView(getApplicationContext());
        tv.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        tv.setTextSize(size);
        tv.setTextColor(Color.LTGRAY);
        tv.setText(text);
        tv.setTypeface(getTypeface());
        if (p != null) tv.setLayoutParams(p);
        return tv;
    }

    private LinearLayout hourSystemForClass(final Class fclass, boolean showTime, int fontSize, boolean breakTimes) {
        LinearLayout all = new LinearLayout(this);
        all.setGravity(Gravity.START | Gravity.CENTER_HORIZONTAL);
        all.setOrientation(LinearLayout.VERTICAL);
        all.setPadding(10, 10, 10, 10);
        for (int s = 0; s < fclass.subjects.size(); s++) {
            if (getBreak(fclass.subjects.get(s).hour - 1) != -1 && breakTimes) {
                final Button breakt = new Button(this);
                String btext = "הפסקה, " + getBreak(fclass.subjects.get(s).hour - 1) + " דקות";
                breakt.setText(btext);
                breakt.setGravity(Gravity.CENTER);
                breakt.setTextSize((float) fontSize - 2);
                breakt.setTextColor(textColor);
                breakt.setBackground(getDrawable(R.drawable.button));
                breakt.setPadding(20, 20, 20, 20);
                breakt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 10));
                breakt.setTypeface(getTypeface());
                breakt.setAllCaps(false);
                if (fclass.subjects.get(s).name != null && !fclass.subjects.get(s).name.equals("")) {
                    all.addView(breakt);
                }
                final int finalS1 = s;
                breakt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(Main.this);
                        dialog.setCancelable(true);
                        LinearLayout di = new LinearLayout(getApplicationContext());
                        TextView subjName = new TextView(getApplicationContext());
                        TextView hours = new TextView(getApplicationContext());
                        TextView fullInfo = new TextView(getApplicationContext());
                        subjName.setText(R.string.brkk);
                        //fclass.classes.get(s)
                        String txt = getRealEndTimeForHourNumber(fclass.subjects.get(finalS1).hour - 1) + "-" + getRealTimeForHourNumber(fclass.subjects.get(finalS1).hour);
                        hours.setText(txt);
                        String fulltext = getBreak(fclass.subjects.get(finalS1).hour - 1) + " Minutes";
                        fullInfo.setText(fulltext);
                        di.setGravity(Gravity.CENTER);
                        di.setOrientation(LinearLayout.VERTICAL);
                        di.addView(subjName);
                        di.addView(hours);
                        di.addView(fullInfo);
                        di.setBackgroundColor(secolor);
                        //                        di.setBackground(getDrawable(R.drawable.coaster_normal));
                        subjName.setTextColor(textColor);
                        hours.setTextColor(textColor);
                        fullInfo.setTextColor(textColor);
                        subjName.setTextSize(getFontSize());
                        hours.setTextSize(getFontSize() - 5);
                        fullInfo.setTextSize(getFontSize() - 5);
                        subjName.setGravity(Gravity.CENTER);
                        hours.setGravity(Gravity.CENTER);
                        fullInfo.setGravity(Gravity.CENTER);
                        subjName.setTypeface(getTypeface(), Typeface.BOLD);
                        hours.setTypeface(getTypeface());
                        fullInfo.setTypeface(getTypeface());
                        Button close = new Button(getApplicationContext());
                        close.setText(R.string.close);
                        close.setTextColor(textColor);
                        close.setTypeface(getTypeface());
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        close.setAllCaps(false);
                        close.setBackground(getDrawable(R.drawable.button));
                        close.setLayoutParams(new LinearLayout.LayoutParams((int) (Light.Device.screenX(getApplicationContext()) * 0.6) + (Light.Device.screenX(getApplicationContext()) / 20), (Light.Device.screenY(getApplicationContext()) / 10)));
                        close.setTextSize(getFontSize() - 5);
                        di.setPadding(40, 40, 40, 40);
                        di.addView(close);
                        dialog.setContentView(di);
                        dialog.show();
                    }
                });
            }
            final LinearLayout fsubj = new LinearLayout(getApplicationContext());
            fsubj.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            fsubj.setGravity(Gravity.CENTER);
            //            fsubj.setGravity(Gravity.START|Gravity.CENTER_VERTICAL);
            fsubj.setOrientation(LinearLayout.HORIZONTAL);
            ClassTime classTime = getTimeForLesson(fclass.subjects.get(s).hour);
            boolean isCurrent = false;
            Calendar c = Calendar.getInstance();
            int minute = c.get(Calendar.MINUTE);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            if (minuteOfDay(hour, minute) > minuteOfDay(classTime.startH, classTime.startM) && minuteOfDay(hour, minute) <= minuteOfDay(classTime.finishH, classTime.finishM)) {
                isCurrent = true;
            }
            Button subj = new Button(this);
            if (!isCurrent) {
                subj.setBackground(getDrawable(R.drawable.button));
            } else {
                subj.setBackground(getDrawable(R.drawable.button_dark));
            }
            String before;
            if (showTime) {
                before = "(" + getRealTimeForHourNumber(fclass.subjects.get(s).hour) + ") " + fclass.subjects.get(s).hour + ". ";
            } else {
                before = fclass.subjects.get(s).hour + ". ";
            }
            String total = before + fclass.subjects.get(s).name;
            String main = "\u200F" + total;
            subj.setText(main);
            subj.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            subj.setTextSize((float) fontSize - 2);
            subj.setTextColor(textColor);
            subj.setPadding(20, 20, 20, 20);
            subj.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 10));
            subj.setTypeface(getTypeface());
            subj.setSingleLine(true);
            subj.setEllipsize(TextUtils.TruncateAt.END);
            final int finalS = s;
            subj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(Main.this);
                    dialog.setCancelable(true);
                    LinearLayout di = new LinearLayout(getApplicationContext());
                    TextView subjName = new TextView(getApplicationContext());
                    TextView hours = new TextView(getApplicationContext());
                    TextView fullInfo = new TextView(getApplicationContext());
                    subjName.setText(fclass.subjects.get(finalS).name);
                    //fclass.classes.get(s)
                    String txt = getRealTimeForHourNumber(fclass.subjects.get(finalS).hour) + "-" + getRealEndTimeForHourNumber(fclass.subjects.get(finalS).hour);
                    hours.setText(txt);
                    fullInfo.setText(fclass.subjects.get(finalS).fullName);
                    di.setGravity(Gravity.CENTER);
                    di.setOrientation(LinearLayout.VERTICAL);
                    di.addView(subjName);
                    di.addView(hours);
                    di.addView(fullInfo);
                    di.setBackgroundColor(secolor);
                    subjName.setTextColor(textColor);
                    hours.setTextColor(textColor);
                    fullInfo.setTextColor(textColor);
                    subjName.setTextSize((float) 30);
                    hours.setTextSize((float) 30);
                    fullInfo.setTextSize((float) 30);
                    subjName.setGravity(Gravity.CENTER);
                    hours.setGravity(Gravity.CENTER);
                    fullInfo.setGravity(Gravity.CENTER);
                    subjName.setTypeface(getTypeface(), Typeface.BOLD);
                    hours.setTypeface(getTypeface());
                    fullInfo.setTypeface(getTypeface());
                    Button close = new Button(getApplicationContext());
                    close.setText(R.string.close);
                    close.setTextColor(textColor);
                    close.setTextSize((float) 25);
                    close.setTypeface(getTypeface());
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    close.setAllCaps(false);
                    close.setBackground(getDrawable(R.drawable.button));
                    close.setLayoutParams(new LinearLayout.LayoutParams((int) (Light.Device.screenX(getApplicationContext()) * 0.6) + (Light.Device.screenX(getApplicationContext()) / 20), (Light.Device.screenY(getApplicationContext()) / 10)));
                    di.addView(close);
                    di.setPadding(40, 40, 40, 40);
                    dialog.setContentView(di);
                    dialog.show();
                }
            });
            subj.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(getApplicationContext(), getRealTimeForHourNumber(fclass.subjects.get(finalS).hour) + "-" + getRealEndTimeForHourNumber(fclass.subjects.get(finalS).hour), Toast.LENGTH_LONG).show();
                    return true;
                }
            });
            if (fclass.subjects.get(s).name != null && !fclass.subjects.get(s).name.equals("")) {
                fsubj.addView(subj);
                all.addView(fsubj);
            }
        }
        return all;
    }

    interface After {
        void after();
    }

    static class Graphics {
        static class Tile extends LinearLayout {
            private String saveTo, popupText;
            private boolean defaultValue, flipValues;
            private int iconA, iconB, gravity;
            private OnValueChanged onValueChanged;
            private OnAction afterChange;
            private ImageView icon;
            private SharedPreferences sp;

            private void updateView() {
                if (!flipValues) {
                    if (!sp.getBoolean(saveTo, defaultValue)) {
                        setBackground(getContext().getDrawable(R.drawable.coaster_normal));
                        icon.setImageDrawable(getContext().getDrawable(iconA));
                    } else {
                        setBackground(getContext().getDrawable(R.drawable.coaster_pressed));
                        icon.setImageDrawable(getContext().getDrawable(iconB));
                    }
                } else {
                    if (sp.getBoolean(saveTo, defaultValue)) {
                        setBackground(getContext().getDrawable(R.drawable.coaster_normal));
                        icon.setImageDrawable(getContext().getDrawable(iconA));
                    } else {
                        setBackground(getContext().getDrawable(R.drawable.coaster_pressed));
                        icon.setImageDrawable(getContext().getDrawable(iconB));
                    }
                }
            }

            private void initTile() {
                OnClickListener onClick = new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean currentValue = !sp.getBoolean(saveTo, defaultValue);
                        sp.edit().putBoolean(saveTo, currentValue).commit();
                        updateView();
                        if (onValueChanged != null) {
                            if (currentValue) {
                                onValueChanged.on();
                            } else {
                                onValueChanged.off();
                            }
                        }
                        if (afterChange != null) afterChange.execute();
                    }
                };
                OnLongClickListener onLongClick = new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (popupText != null) {
                            Toast t = Toast.makeText(getContext(), popupText, Toast.LENGTH_SHORT);
                            t.setGravity(gravity, 0, 0);
                            View tv = t.getView().findViewById(android.R.id.message);
                            tv.setPadding(40, 30, 40, 30);
                            tv.setBackground(getContext().getDrawable(R.drawable.toast));
                            t.show();
                        }
                        return (popupText != null);
                    }
                };
                setBackground(getContext().getDrawable(R.drawable.coaster_normal));
                setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenY(getContext()) / 12, Light.Device.screenY(getContext()) / 12));
                setPadding(20, 20, 20, 20);
                setGravity(Gravity.CENTER);
                setOrientation(LinearLayout.HORIZONTAL);
                setOnClickListener(onClick);
                setOnLongClickListener(onLongClick);
                icon = new ImageView(getContext());
                icon.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenY(getContext()) / 20, Light.Device.screenY(getContext()) / 20));
                icon.setImageDrawable(getContext().getDrawable(iconA));
                icon.setOnClickListener(onClick);
                icon.setOnLongClickListener(onLongClick);
                addView(icon);
                updateView();
            }

            public Tile(Context c, String saveTo, boolean defaultValue, int iconA, boolean flipValues) {
                super(c);
                this.sp = c.getSharedPreferences("app", Context.MODE_PRIVATE);
                this.saveTo = saveTo;
                this.defaultValue = defaultValue;
                this.flipValues = flipValues;
                this.iconA = iconA;
                this.iconB = this.iconA;
                initTile();
            }

            public void setOnValueChanged(OnValueChanged onValueChanged) {
                this.onValueChanged = onValueChanged;
            }

            public void setSecondIcon(int iconB) {
                this.iconB = iconB;
                updateView();
            }

            public void setAfter(OnAction onAction) {
                this.afterChange = onAction;
            }

            public void setLongClick(String popupText, int gravity) {
                this.popupText = popupText;
                this.gravity = gravity;
            }

            public void manualOff() {
                sp.edit().putBoolean(saveTo, false).commit();
                updateView();
                if (afterChange != null) afterChange.execute();
            }

            public void manualOn() {
                sp.edit().putBoolean(saveTo, true).commit();
                updateView();
                if (afterChange != null) afterChange.execute();
            }

            interface OnValueChanged {
                void on();

                void off();
            }

            interface OnAction {
                void execute();
            }

            static class EndlessTile extends LinearLayout {
                private ImageView icon;
                private OnAction onAction;
                private Tile.OnAction after;
                private int iconA;

                private void initTile() {
                    OnClickListener onClick = new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onAction != null) onAction.click();
                            if (after != null) after.execute();
                        }
                    };
                    OnLongClickListener onLongClick = new OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if (onAction != null) onAction.hold();
                            return true;
                        }
                    };
                    setBackground(getContext().getDrawable(R.drawable.coaster_normal));
                    setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenY(getContext()) / 12, Light.Device.screenY(getContext()) / 12));
                    setPadding(20, 20, 20, 20);
                    setGravity(Gravity.CENTER);
                    setOrientation(LinearLayout.HORIZONTAL);
                    setOnClickListener(onClick);
                    setOnLongClickListener(onLongClick);
                    icon = new ImageView(getContext());
                    icon.setLayoutParams(new LinearLayout.LayoutParams(Light.Device.screenY(getContext()) / 20, Light.Device.screenY(getContext()) / 20));
                    icon.setImageDrawable(getContext().getDrawable(iconA));
                    icon.setOnClickListener(onClick);
                    icon.setOnLongClickListener(onLongClick);
                    addView(icon);
                }

                public EndlessTile(Context c, int iconA, OnAction onAction) {
                    super(c);
                    this.iconA = iconA;
                    this.onAction = onAction;
                    initTile();
                }

                public void setAfter(Tile.OnAction after) {
                    this.after = after;
                }

                interface OnAction {
                    void click();

                    void hold();
                }
            }
        }

        static class ColorFadeAnimation {
            private ColorState onChange;
            private int colorA, colorB;

            public ColorFadeAnimation(int start, int end, ColorState colorState) {
                onChange = colorState;
                colorA = start;
                colorB = end;
            }

            public void start(int milliseconds) {
                final int rOffset = Color.red(colorB) - Color.red(colorA);
                final int gOffset = Color.green(colorB) - Color.green(colorA);
                final int bOffset = Color.blue(colorB) - Color.blue(colorA);
                int total = (Math.abs(rOffset) + Math.abs(gOffset) + Math.abs(bOffset));
                if (total == 0) {
                    total = 1;
                }
                final double maxTimePerColor = milliseconds / total;
                final int rA = Color.red(colorA);
                final int gA = Color.green(colorA);
                final int bA = Color.blue(colorA);
                final int rB = Color.red(colorB);
                final int gB = Color.green(colorB);
                final int bB = Color.blue(colorB);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int r;
                        int g = gA;
                        int b = bA;
                        if (rOffset < 0) {
                            for (r = rA; r > rB; r--) {
                                onChange.onColor(Color.rgb(r, g, b));
                                try {
                                    Thread.sleep((long) maxTimePerColor);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            for (r = rA; r < rB; r++) {
                                onChange.onColor(Color.rgb(r, g, b));
                                try {
                                    Thread.sleep((long) maxTimePerColor);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (gOffset < 0) {
                            for (g = gA; g > gB; g--) {
                                onChange.onColor(Color.rgb(r, g, b));
                                try {
                                    Thread.sleep((long) maxTimePerColor);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            for (g = gA; g < gB; g++) {
                                onChange.onColor(Color.rgb(r, g, b));
                                try {
                                    Thread.sleep((long) maxTimePerColor);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (bOffset < 0) {
                            for (b = bA; b > bB; b--) {
                                onChange.onColor(Color.rgb(r, g, b));
                                try {
                                    Thread.sleep((long) maxTimePerColor);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            for (b = bA; b < bB; b++) {
                                onChange.onColor(Color.rgb(r, g, b));
                                try {
                                    Thread.sleep((long) maxTimePerColor);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }).start();
            }

            interface ColorState {
                void onColor(int color);
            }
        }

        static class CurvedTextView extends View {
            private Path circle;
            private Paint tPaint;
            private Paint cPaint;
            private String text;
            private float textSize;
            private int textColor, sizeX, sizeY, radius;

            public CurvedTextView(Context c) {
                super(c);
                this.text = "Example";
                circle = new Path();
                int sizeX = 100, sizeY = 100, radius = 50, textColor = Color.WHITE, textSize = 20;
                circle.addCircle(((sizeX - radius * 2) / 2) + radius, ((sizeY - radius * 2) / 2) + radius, radius, Path.Direction.CW);
                cPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                cPaint.setStyle(Paint.Style.STROKE);
                cPaint.setColor(Color.LTGRAY);
                cPaint.setStrokeWidth(3);
                tPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                tPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                tPaint.setColor(textColor);
                tPaint.setTextSize(textSize);
                tPaint.setTypeface(Typeface.createFromAsset(c.getAssets(), Values.fontName));
            }

            public CurvedTextView(Context context, String text, float textSize, int textColor, int sizeX, int sizeY, int radius) {
                super(context);
                this.text = text;
                this.textSize = textSize;
                this.textColor = textColor;
                this.sizeX = sizeX;
                this.sizeY = sizeY;
                this.radius = radius;
                init();
            }

            public void setText(String s) {
                this.text = s;
                init();
            }

            private void init() {
                circle = new Path();
                circle.addCircle(((sizeX - radius * 2) / 2) + radius, ((sizeY - radius * 2) / 2) + radius, radius, Path.Direction.CW);
                cPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                cPaint.setStyle(Paint.Style.STROKE);
                cPaint.setColor(Color.LTGRAY);
                cPaint.setStrokeWidth(3);
                tPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                tPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                tPaint.setColor(textColor);
                tPaint.setTextSize(textSize);
                tPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), Values.fontName));
            }

            @Override
            protected void onDraw(Canvas canvas) {
                canvas.drawTextOnPath(text, circle, 0, 0, tPaint);
                invalidate();
            }
        }
    }

    static class Values {
        static final String pushProvider = "http://h.nockio.com/pushes.json";
        static final String themeProvider = "http://h.nockio.com/themes.json";
        static final String keyProvider = "http://h.nockio.com/keys/index.php";
        static final String puzProvider = "http://h.nockio.com";
        static final String serviceProvider = "http://handasaim.co.il";
        static final String scheduleProvider = "http://handasaim.co.il/2017/06/13/%D7%9E%D7%A2%D7%A8%D7%9B%D7%AA-%D7%95%D7%A9%D7%99%D7%A0%D7%95%D7%99%D7%99%D7%9D/index.php";
        static final String KILL_DND = "nadav.tasher.handasaim.KILL_DND";
        static final String KILL_DND_SERVICE = "nadav.tasher.handasaim.KILL_DND_SERVICE";
        static final String fontName = "arimo.ttf";
        static final String latestFileDate = "latest_file_date";
        static final String teacherModeEnabler = "installed_pass_teacher_mode";
        static final String messageBoardSkipEnabler = "installed_pass_news_code_ver2";
        static final String teacherMode = "teacher_mode";
        static final String lessonName = "lesson_name";
        static final String lessonTime = "lesson_time";
        static final String breakTime = "break_time";
        static final String seasonalTheming = "seasonal_theming";
        static final String pushService = "push_service";
        static final String fontColor = "font_color";
        static final String autoMute = "auto_mute";
        static final String fontSize = "font_size";
        static final String fontSizeNumber = "font_size_number";
        static final String color = "color_v2";
        static final String favoriteClass = "favorite_class";
        static final String favoriteTeacher = "favorite_teacher";
        static final String seasonPriority = "season_priority";
        static final String seasonEndDay = "season_ed";
        static final String seasonEndMonth = "season_em";
        static final String seasonEndYear = "season_ey";
        static final String seasonMain = "season_main";
        static final String seasonSub = "season_sub";
        static final String seasonName = "season_name";
        static final String seasonID = "season_id_";
        static final String pushID = "push_id_";
        static final String latestFileName = "latest_file_name";
        static final String lastRecordedVersionCode = "last_recorded_version_code";
        static final String firstLaunch = "first";
        static final String latestFileNameDefault = "hs.xls";
        static final boolean pushDefault = true;
        static final boolean seasonDefault = true;
        static final boolean fontColorDefault = true;
        static final boolean lessonTimeDefault = false;
        static final boolean breakTimeDefault = true;
        static final boolean autoMuteDefault = false;
        static final boolean fontSizeDefault = false;
        static final int maxKeyEntering = 4;
        static final int waitTime = 10;
        static final int bakedIconColor = 0xffdd8833;
        static final int pushLoop = 1000 * 60 * 15;
        static final int fontSizeBig = 30;
        static final int fontSizeSmall = 20;
        static final int defaultColor = Color.parseColor("#333333");
    }

    static class ClassTime {
        int startH, finishH, startM, finishM;

        ClassTime(int sh, int fh, int sm, int fm) {
            startH = sh;
            startM = sm;
            finishH = fh;
            finishM = fm;
        }
    }

    static class Link {
        String url, name, imgurl;
    }

    static class Theme {
        int color;

        Theme(String color) {
            this.color = Color.parseColor(color);
        }

        Theme(int color) {
            this.color = color;
        }

        static class SchudledTheme {
            int main, sub;
            int sd, sm, sy, ed, em, ey;
            int id;
            public String name;
        }
    }

    static class Filters {
        static InputFilter colorFilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                if (charSequence != null) {
                    for (int c = 0; c < charSequence.length(); c++) {
                        boolean charAllowed = false;
                        String allowed = "0123456789ABCDEFabcdef";
                        for (int a = 0; a < allowed.length(); a++) {
                            if (charSequence.charAt(c) == allowed.charAt(a)) {
                                charAllowed = true;
                                break;
                            }
                        }
                        if (!charAllowed) return "";
                    }
                    return null;
                }
                return null;
            }
        };
        static InputFilter codeFilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                if (charSequence != null) {
                    for (int c = 0; c < charSequence.length(); c++) {
                        boolean charAllowed = false;
                        String allowed = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
                        for (int a = 0; a < allowed.length(); a++) {
                            if (charSequence.charAt(c) == allowed.charAt(a)) {
                                charAllowed = true;
                                break;
                            }
                        }
                        if (!charAllowed) return "";
                    }
                    return null;
                }
                return null;
            }
        };
    }

    static class Class {
        String name;
        ArrayList<Subject> subjects;

        Class(String name, ArrayList<Subject> subjects) {
            this.name = Light.Stringer.cutOnEvery(name, " ").get(0);
            this.subjects = subjects;
        }
    }

    static class Subject {
        int hour;
        String name, fullName;

        Subject(int hour, String name, String fullName) {
            this.hour = hour;
            this.name = name;
            this.fullName = fullName;
        }
    }

    static class ForTeachers {

        static LinearLayout hourSystemForTeacher(final Context c, final Teacher fclass, boolean showTime, int fontSize, boolean breakTimes, boolean lessonNames) {
            LinearLayout all = new LinearLayout(c);
            all.setGravity(Gravity.START | Gravity.CENTER_HORIZONTAL);
            all.setOrientation(LinearLayout.VERTICAL);
            all.setPadding(10, 10, 10, 10);
            for (int h = 0; h <= 12; h++) {
                final LinearLayout fsubj = new LinearLayout(c);
                fsubj.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                fsubj.setGravity(Gravity.CENTER);
                //            fsubj.setGravity(Gravity.START|Gravity.CENTER_VERTICAL);
                fsubj.setOrientation(LinearLayout.HORIZONTAL);
                final Button subj = new Button(c);
                subj.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                subj.setText(null);
                subj.setTextSize((float) fontSize - 2);
                subj.setTextColor(Main.textColor);
                subj.setPadding(20, 20, 20, 20);
                subj.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(c) / 10));
                subj.setTypeface(getTypeface(c));
                subj.setSingleLine(true);
                subj.setEllipsize(TextUtils.TruncateAt.END);
                String subjectName = null;
                ArrayList<TeacherLesson> currentLesson = new ArrayList<>();
                for (int s = 0; s < fclass.teaching.size(); s++) {
                    final int finalS = s;
                    if (fclass.teaching.get(s).hour == h) {
                        ClassTime classTime = getTimeForLesson(h);
                        boolean isCurrent = false;
                        Calendar ca = Calendar.getInstance();
                        int minute = ca.get(Calendar.MINUTE);
                        int hour = ca.get(Calendar.HOUR_OF_DAY);
                        if (minuteOfDay(hour, minute) >= minuteOfDay(classTime.startH, classTime.startM) && minuteOfDay(hour, minute) <= minuteOfDay(classTime.finishH, classTime.finishM)) {
                            isCurrent = true;
                        }
                        if (!isCurrent) {
                            subj.setBackground(c.getDrawable(R.drawable.button));
                        } else {
                            subj.setBackground(c.getDrawable(R.drawable.button_dark));
                        }
                        subj.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View view) {
                                Toast.makeText(c, Main.getRealTimeForHourNumber(fclass.teaching.get(finalS).hour) + "-" + getRealEndTimeForHourNumber(fclass.teaching.get(finalS).hour), Toast.LENGTH_LONG).show();
                                return true;
                            }
                        });
                        subj.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(c, fclass.teaching.get(finalS).lessonName, Toast.LENGTH_LONG).show();
                            }
                        });
                        if (subj.getText().toString().equals("")) {
                            String textt = Light.Stringer.cutOnEvery(fclass.teaching.get(s).className, " ").get(0);
                            subj.setText(textt);
                        } else {
                            String textt = subj.getText().toString() + ", " + Light.Stringer.cutOnEvery(fclass.teaching.get(s).className, " ").get(0);
                            subj.setText(textt);
                        }
                        currentLesson.add(fclass.teaching.get(s));
                        subjectName = fclass.teaching.get(s).lessonName;
                    }
                }
                if (currentLesson.size() > 1) {
                    if (getGrade(currentLesson) != -1) {
                        subj.setText(getGrade(getGrade(currentLesson)));
                    }
                }
                String before;
                if (showTime) {
                    before = "(" + Main.getRealTimeForHourNumber(h) + ") " + h + ". ";
                } else {
                    before = h + ". ";
                }
                String main = "\u200F" + before;
                if (!subj.getText().toString().equals("")) {
                    if (Main.getBreak(h - 1) != -1 && breakTimes) {
                        final Button breakt = new Button(c);
                        String txt = "הפסקה, " + getBreak(h - 1) + " דקות";
                        breakt.setText(txt);
                        breakt.setGravity(Gravity.CENTER);
                        breakt.setTextSize((float) fontSize - 2);
                        breakt.setTextColor(textColor);
                        breakt.setBackground(c.getDrawable(R.drawable.button));
                        breakt.setPadding(20, 20, 20, 20);
                        breakt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(c) / 10));
                        breakt.setTypeface(getTypeface(c));
                        breakt.setAllCaps(false);
                        all.addView(breakt);
                    }
                    String totext = main + subj.getText().toString();
                    if (lessonNames) {
                        totext += " (" + subjectName + ")";
                    }
                    subj.setText(totext);
                    fsubj.addView(subj);
                    all.addView(fsubj);
                }
            }
            return all;
        }

        static String hourSystemForTeacherString(Teacher fclass, boolean showTime) {
            String allsubj = "";
            for (int h = 0; h <= 12; h++) {
                String subjText = "";
                String before;
                if (showTime) {
                    before = "(" + Main.getRealTimeForHourNumber(h) + ") " + h + ". ";
                } else {
                    before = h + ". ";
                }
                int gradeSoFar = -2;
                int classCount = 0;
                for (int s = 0; s < fclass.teaching.size(); s++) {
                    if (fclass.teaching.get(s).hour == h) {
                        if (subjText.equals("")) {
                            subjText += Light.Stringer.cutOnEvery(fclass.teaching.get(s).className, " ").get(0);
                        } else {
                            subjText += ", " + Light.Stringer.cutOnEvery(fclass.teaching.get(s).className, " ").get(0);
                        }
                        if (gradeSoFar == -2) {
                            gradeSoFar = getGrade(new TeacherLesson(Light.Stringer.cutOnEvery(fclass.teaching.get(s).className, " ").get(0), null, 0));
                        } else {
                            if (gradeSoFar != getGrade(new TeacherLesson(Light.Stringer.cutOnEvery(fclass.teaching.get(s).className, " ").get(0), null, 0))) {
                                gradeSoFar = -1;
                            }
                        }
                        classCount++;
                    }
                }
                if (gradeSoFar != -1 && classCount > 1) {
                    subjText = getGrade(gradeSoFar);
                }
                if (!subjText.equals("")) {
                    allsubj += before + subjText + "\n";
                }
            }
            return allsubj;
        }

        static ArrayList<Teacher> getTeacherSchudleForClasses(ArrayList<Class> classes) {
            ArrayList<Teacher> teacherList = new ArrayList<>();
            for (int currentClass = 0; currentClass < classes.size(); currentClass++) {
                Class cClass = classes.get(currentClass);
                cClass.name = Light.Stringer.cutOnEvery(cClass.name, " ").get(0);
                for (int currentSubject = 0; currentSubject < cClass.subjects.size(); currentSubject++) {
                    Subject cSubject = cClass.subjects.get(currentSubject);
                    ArrayList<String> cSubjectTeachers = Light.Stringer.cutOnEvery(cSubject.fullName.substring(cSubject.fullName.indexOf("\n") + 1).trim().split("\\r?\\n")[0], ",");
                    TeacherLesson cLesson = new TeacherLesson(cClass.name, cSubject.name, cSubject.hour);
                    for (int currentTeacherOfSubject = 0; currentTeacherOfSubject < cSubjectTeachers.size(); currentTeacherOfSubject++) {
                        String nameOfTeacher = cSubjectTeachers.get(currentTeacherOfSubject);
                        boolean foundTeacher = false;
                        if (!cSubject.name.equals("")) {
                            for (int currentTeacher = 0; currentTeacher < teacherList.size(); currentTeacher++) {
                                Teacher cTeacher = teacherList.get(currentTeacher);
                                Log.i("MEME", String.valueOf(isTheSameTeacher(cTeacher.mainName, nameOfTeacher)));
                                if (isTheSameTeacher(cTeacher.mainName, nameOfTeacher) == 1) {
                                    if (!cTeacher.mainName.equals(nameOfTeacher)) {
                                        if (cTeacher.teaches(cSubject.name)) {
                                            cTeacher.teaching.add(cLesson);
                                            foundTeacher = true;
                                            break;
                                        }
                                    } else {
                                        if (!cTeacher.teaches(cSubject.name)) {
                                            cTeacher.subjects.add(cSubject.name);
                                        }
                                        cTeacher.teaching.add(cLesson);
                                        foundTeacher = true;
                                        break;
                                    }
                                } else if (isTheSameTeacher(cTeacher.mainName, nameOfTeacher) == 2) {
                                    if (!cTeacher.teaches(cSubject.name)) {
                                        cTeacher.subjects.add(cSubject.name);
                                    }
                                    cTeacher.teaching.add(cLesson);
                                    foundTeacher = true;
                                    break;
                                }
                            }
                            if (!foundTeacher) {
                                Teacher teacher = new Teacher();
                                teacher.mainName = nameOfTeacher;
                                teacher.subjects = new ArrayList<>();
                                teacher.subjects.add(cSubject.name);
                                teacher.teaching = new ArrayList<>();
                                teacher.teaching.add(cLesson);
                                if (!nameOfTeacher.equals(""))
                                    teacherList.add(teacher);
                            }
                        }
                    }
                }
            }
            return teacherList;
        }

        static int isTheSameTeacher(String a, String b) {
            ArrayList<String> aSplit = Light.Stringer.cutOnEvery(a, " ");
            ArrayList<String> bSplit = Light.Stringer.cutOnEvery(b, " ");
            if (aSplit.size() > 1 && bSplit.size() > 1) {
                if (aSplit.get(0).equals(bSplit.get(0))) {
                    if (aSplit.get(1).contains(bSplit.get(1))) {
                        return 2;
                    } else if (bSplit.get(1).contains(aSplit.get(1))) {
                        return 2;
                    } else {
                        return 0;
                    }
                }
                return 0;
            } else if (a.contains(b) || b.contains(a)) {
                return 1;
            }
            return 0;
        }

        static class Teacher {
            ArrayList<TeacherLesson> teaching;
            ArrayList<String> subjects;
            String mainName;

            boolean teaches(String l) {
                for (int tc = 0; tc < subjects.size(); tc++) {
                    if (subjects.get(tc).equals(l) || subjects.get(tc).contains(l) || l.contains(subjects.get(tc))) {
                        return true;
                    }
                }
                return false;
            }
        }

        static int getGrade(ArrayList<TeacherLesson> classNames) {
            if (classNames.size() > 0) {
                int grade = getGrade(classNames.get(0));
                for (int cTl = 1; cTl < classNames.size(); cTl++) {
                    int cGrade = getGrade(classNames.get(cTl));
                    if (cGrade != grade) {
                        return -1;
                    }
                }
                return grade;
            }
            return -1;
        }

        static int getGrade(TeacherLesson s) {
            String parsing = s.className;
            if (parsing.contains("י")) {
                if (parsing.contains("א")) {
                    return 2;
                } else if (parsing.contains("ב")) {
                    return 3;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }
        }

        static String getGrade(int grade) {
            switch (grade) {
                case 0:
                    return "ט'";
                case 1:
                    return "י'";
                case 2:
                    return "יא'";
                case 3:
                    return "יב'";
            }
            return "";
        }

        static class TeacherLesson {
            String className, lessonName;
            int hour;

            TeacherLesson(String className, String lessonName, int hour) {
                this.hour = hour;
                this.className = className;
                this.lessonName = lessonName;
            }
        }
    }

    static class MainSite {
        String princSaying;
        String readMorePrics;
        ArrayList<Main.Link> news;
    }

    static class GetLink extends AsyncTask<String, String, String> {
        private String ser;
        private GotLink gotlink;
        private boolean success;

        GetLink(String service, GotLink gl) {
            ser = service;
            gotlink = gl;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Document docu = Jsoup.connect(ser).get();
                Elements doc = docu.select("a");
                String file = null;
                for (int i = 0; i < doc.size(); i++) {
                    if (doc.get(i).attr("href").endsWith(".xls") || doc.get(i).attr("href").endsWith(".xlsx")) {
                        file = doc.get(i).attr("href");
                        break;
                    }
                }
                success = true;
                return file;
            } catch (IOException e) {
                success = false;
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (success) {
                gotlink.onLinkGet(s);
            } else {
                gotlink.onFail(s);
            }
        }

        interface GotLink {
            void onLinkGet(String link);

            void onFail(String e);
        }
    }

    static class GetNews extends AsyncTask<String, String, ArrayList<Main.Link>> {
        private String ser;
        private GotNews gotlink;
        private boolean success;

        GetNews(String service, GotNews gl) {
            ser = service;
            gotlink = gl;
        }

        @Override
        protected ArrayList<Main.Link> doInBackground(String... strings) {
            try {
                ArrayList<Main.Link> file = new ArrayList<>();
                Document docu = Jsoup.connect(ser).get();
                Elements ahs = docu.getAllElements().select("div.carousel-inner").select("div.item").select("a");
                for (int in = 0; in < ahs.size(); in++) {
                    Main.Link link = new Main.Link();
                    link.name = ahs.get(in).text();
                    link.url = ahs.get(in).attr("href");
                    boolean doesContain = false;
                    for (int containC = 0; containC < file.size(); containC++) {
                        if (file.get(containC).name.equals(link.name)) {
                            doesContain = true;
                            break;
                        }
                    }
                    if (!link.name.equals("") && !doesContain) file.add(link);
                }
                success = true;
                return file;
            } catch (IOException e) {
                success = false;
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Main.Link> s) {
            if (success) {
                if (gotlink != null) gotlink.onNewsGet(s);
            } else {
                if (gotlink != null) gotlink.onFail(s);
            }
        }

        interface GotNews {
            void onNewsGet(ArrayList<Main.Link> link);

            void onFail(ArrayList<Main.Link> e);
        }
    }

    static class GetMainSite extends AsyncTask<String, String, MainSite> {
        OnGet onGet;

        GetMainSite(OnGet og) {
            onGet = og;
        }

        @Override
        protected MainSite doInBackground(String... strings) {
            try {
                MainSite ms = new MainSite();
                ArrayList<Main.Link> news = new ArrayList<>();
                String ser = Values.serviceProvider;
                Document docu = Jsoup.connect(ser).get();
                Elements itemss = docu.getAllElements().select("div.carousel-inner").select("div.item");
                for (int in = 0; in < itemss.size(); in++) {
                    Main.Link link = new Main.Link();
                    link.name = itemss.get(in).select("a").first().text();
                    link.url = itemss.get(in).select("a").first().attr("href");
                    link.imgurl = itemss.get(in).select("img").attr("src");
                    boolean doesContain = false;
                    for (int containC = 0; containC < news.size(); containC++) {
                        if (news.get(containC).name.equals(link.name)) {
                            doesContain = true;
                            break;
                        }
                    }
                    if (!link.name.equals("") && !doesContain) news.add(link);
                }
                ms.news = news;
                try {
                    ms.princSaying = docu.select("div.pt-cv-ifield").select("div.pt-cv-content").first().text();
                    ms.readMorePrics = docu.select("div.pt-cv-ifield").select("div.pt-cv-content").select("a").first().attr("href");
                } catch (NullPointerException e) {
                    ms.princSaying = null;
                    ms.readMorePrics = null;
                }
                return ms;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(MainSite mainSite) {
            if (onGet != null) {
                onGet.onGet(mainSite);
            }
        }

        interface OnGet {
            void onGet(MainSite ms);
        }
    }

    static class FileDownloader extends AsyncTask<String, String, String> {
        private String furl;
        private File fdpath;
        private boolean available;
        private FileDownloader.OnDownload oe;

        FileDownloader(String url, File path, FileDownloader.OnDownload onfile) {
            oe = onfile;
            furl = url;
            fdpath = path;
        }

        private boolean check() {
            try {
                HttpURLConnection.setFollowRedirects(false);
                HttpURLConnection con = (HttpURLConnection) new URL(furl).openConnection();
                return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected String doInBackground(String... comment) {
            int perc = 0;
            if (check()) {
                available = true;
                int count;
                try {
                    URL url = new URL(furl);
                    URLConnection conection = url.openConnection();
                    conection.connect();
                    int lenghtOfFile = conection.getContentLength();
                    InputStream input = new BufferedInputStream(url.openStream(), 8192);
                    OutputStream output = new FileOutputStream(fdpath);
                    byte data[] = new byte[1024];
                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        output.write(data, 0, count);
                        total += count;
                        if (perc < (int) (total * 100 / lenghtOfFile)) {
                            perc++;
                            oe.onProgressChanged(fdpath, (int) (total * 100 / lenghtOfFile));
                        }
                    }
                    output.flush();
                    output.close();
                    input.close();
                } catch (Exception e) {
                    //                    Log.e("Error: ", e.getMessage());
                }
            } else {
                available = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {
            if (oe != null) {
                oe.onFinish(fdpath, available);
            }
        }

        interface OnDownload {
            void onFinish(File output, boolean isAvailable);

            void onProgressChanged(File output, int percent);
        }
    }

    static class FileReader extends AsyncTask<String, String, String> {
        private String furl;
        private FileReader.OnRead oe;

        FileReader(String url, FileReader.OnRead onfile) {
            oe = onfile;
            furl = url;
        }

        @Override
        protected String doInBackground(String... comment) {
            String s = "";
            try {
                URL url = new URL(furl);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String str;
                while ((str = in.readLine()) != null) {
                    s += str + "\n";
                }
                in.close();
            } catch (IOException e) {
                s = null;
            }
            return s;
        }

        @Override
        protected void onPostExecute(String file_url) {
            if (oe != null) {
                oe.done(file_url);
            }
        }

        interface OnRead {
            void done(String s);
        }
    }

    static class PictureLoader extends AsyncTask<String, String, Bitmap> {
        private String furl;
        private GotImage ong;

        PictureLoader(String url, GotImage og) {
            furl = url;
            ong = og;
        }

        @Override
        protected Bitmap doInBackground(String... comment) {
            try {
                URL url = new URL(furl);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                //                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (ong != null) {
                ong.onGet(bitmap);
            }
        }

        interface GotImage {
            void onGet(Bitmap image);
        }
    }

    static class DNDReceiver extends BroadcastReceiver {
        private boolean isAlive = true;

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                if (intent.getAction().equals(Values.KILL_DND)) {
                    if (isAlive) {
                        isAlive = false;
                        try {
                            context.unregisterReceiver(this);
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            final SharedPreferences sp = context.getSharedPreferences("app", Context.MODE_PRIVATE);
            if (sp.getBoolean(Values.autoMute, false)) {
                if (Build.VERSION.SDK_INT >= 23) {
                    NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    boolean granted = false;
                    if (nm != null) {
                        granted = nm.isNotificationPolicyAccessGranted();
                    }
                    Calendar c = Calendar.getInstance();
                    if (c.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY && c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                        if (granted) {
                            checkTime(context, sp);
                        } else {
                            sendNoPermissionNotification(context);
                        }
                    }
                }
            }
        }

        void checkTime(Context context, SharedPreferences sp) {
            Calendar c = Calendar.getInstance();
            File excel = new File(context.getFilesDir(), sp.getString(Values.latestFileName, Values.latestFileNameDefault));
            String name = sp.getString(Values.latestFileName, "");
            ArrayList<ClassTime> classTimes = new ArrayList<>();
            ArrayList<Class> classes;
            if (!name.equals("")) {
                if (name.endsWith(".xlsx")) {
                    classes = Main.readExcelFileXLSX(excel);
                } else {
                    classes = Main.readExcelFile(excel);
                }
            } else {
                classes = new ArrayList<>();
            }
            if (!sp.getBoolean(Values.teacherMode, false)) {
                if (sp.getString(Values.favoriteClass, null) != null) {
                    if (classes != null) {
                        for (int fc = 0; fc < classes.size(); fc++) {
                            if (sp.getString(Values.favoriteClass, "").equals(classes.get(fc).name)) {
                                ArrayList<Subject> subjects = classes.get(fc).subjects;
                                for (int sub = 0; sub < subjects.size(); sub++) {
                                    classTimes.add(Main.getTimeForLesson(subjects.get(sub).hour));
                                }
                                break;
                            }
                        }
                    }
                }
            } else {
                ArrayList<ForTeachers.Teacher> teachers = ForTeachers.getTeacherSchudleForClasses(classes);
                if (sp.getString(Values.favoriteTeacher, null) != null) {
                    if (teachers != null) {
                        for (int fc = 0; fc < teachers.size(); fc++) {
                            if (sp.getString(Values.favoriteTeacher, "").equals(teachers.get(fc).mainName)) {
                                ArrayList<ForTeachers.TeacherLesson> subjects = teachers.get(fc).teaching;
                                for (int sub = 0; sub < subjects.size(); sub++) {
                                    classTimes.add(Main.getTimeForLesson(subjects.get(sub).hour));
                                }
                                break;
                            }
                        }
                    }
                }
            }
            for (int ct = 0; ct < classTimes.size(); ct++) {
                ClassTime classTime = classTimes.get(ct);
                if (c.get(Calendar.MINUTE) == classTime.startM && c.get(Calendar.HOUR_OF_DAY) == classTime.startH) {
                    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    if (mNotificationManager != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY);
                        }
                    }
                } else if (c.get(Calendar.MINUTE) == classTime.finishM && c.get(Calendar.HOUR_OF_DAY) == classTime.finishH) {
                    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    if (mNotificationManager != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
                        }
                    }
                }
            }
        }

        void sendNoPermissionNotification(Context c) {
            NotificationManager manager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                notification = new NotificationCompat.Builder(c).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(c.getResources().getString(R.string.app_name) + " Warning").setContentText("The app does not have 'Do Not Disturb' permissions.").setContentIntent(PendingIntent.getActivity(c, 0, new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS), PendingIntent.FLAG_UPDATE_CURRENT)).build();
            }
            if (manager != null) {
                manager.notify(new Random().nextInt(100), notification);
            }
        }
    }
}