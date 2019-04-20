package com.nullgeodesic.washingtonhhh.dto;

import com.nullgeodesic.washingtonhhh.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kennel {

    public static final Kennel UNKNOWN = new Kennel();

    public static final String PUGET_SOUND = "PUGET_SOUND";
    public static final String NO_BALLS = "NO_BALLS";
    public static final String SEATTLE = "SEATTLE";
    public static final String RAIN_CITY = "RAIN_CITY";
    public static final String HSWTF = "HSWTF";
    public static final String SEAMON = "SEAMON";
    public static final String TACOMA = "TACOMA";
    public static final String SS_SHITSHOW = "SS_SHITSHOW";

    static {
        UNKNOWN.id = "UNKNOWN";
        UNKNOWN.name = "";
    }

    private static final Map<String, Integer> KENNEL_LOGOS = new HashMap<>();
    private static final Map<String, Integer> BADGES = new HashMap<>();

    static {
        KENNEL_LOGOS.put(PUGET_SOUND, R.drawable.logo_puget);
        KENNEL_LOGOS.put(NO_BALLS, R.drawable.logo_nbh3);
        KENNEL_LOGOS.put(SEATTLE, R.drawable.logo_seattle);
        KENNEL_LOGOS.put(RAIN_CITY, R.drawable.logo_raincity);
        KENNEL_LOGOS.put(HSWTF, R.drawable.logo_hswtf);
        KENNEL_LOGOS.put(SEAMON, R.drawable.logo_seamon);
        KENNEL_LOGOS.put(TACOMA, R.drawable.logo_tacoma);
        KENNEL_LOGOS.put(SS_SHITSHOW, R.drawable.logo_ssss);

        BADGES.put("WALKER_FRIENDLY", R.drawable.walker_badge);
        BADGES.put("PAVEMENT_POUNDER", R.drawable.pavement_badge);
        BADGES.put("SHIGGY", R.drawable.shiggy_badge);
        BADGES.put("5TH_GEN", R.drawable.fifth_gen_badge);
    }

    public String id;
    public String name;
    public String description;
    public String alternativeImage;
    public String hareraiserName;
    public String hareraiserEmail;
    public String firstHash;
    public String founders;
    public String lineage;
    public List<String> badges;

    public boolean isUnknown() {
        return "UNKNOWN".equals(this.id);
    }

    public int drawableId() {
        final Integer drawableId = KENNEL_LOGOS.get(this.id);
        if (drawableId == null) {
            return 0;
        }
        return drawableId;
    }

    public List<Integer> badgeDrawableIds() {
        final List<Integer> drawables = new ArrayList<>();
        for(final String badge : badges) {
            final Integer badgeDrawableId = BADGES.get(badge);
            if(badgeDrawableId != null) {
                drawables.add(badgeDrawableId);
            }
        }
        return drawables;
    }

}
