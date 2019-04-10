package com.nullgeodesic.washingtonhhh.dto;

import com.nullgeodesic.washingtonhhh.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kennel {

    public static final Kennel UNKNOWN = new Kennel();

    static {
        UNKNOWN.id = "UNKNOWN";
        UNKNOWN.name = "";
    }

    private static final Map<String, Integer> KENNEL_LOGOS = new HashMap<>();

    static {
        KENNEL_LOGOS.put("SEATTLE", R.drawable.logo_seattle);
        KENNEL_LOGOS.put("SEAMON", R.drawable.logo_seamon);
        KENNEL_LOGOS.put("RAIN_CITY", R.drawable.logo_raincity);
        KENNEL_LOGOS.put("HSWTF", R.drawable.logo_hswtf);
        KENNEL_LOGOS.put("PUGET_SOUND", R.drawable.logo_puget);
        KENNEL_LOGOS.put("NO_BALLS", R.drawable.logo_nbh3);
        KENNEL_LOGOS.put("TACOMA", R.drawable.logo_tacoma);
    }

    public String id;
    public String name;
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

}
