package org.garred.wh3.service;

import com.google.gson.Gson;

import org.garred.wh3.model.HashEvent;
import org.garred.wh3.model.v2.CalendarDto;
import org.garred.wh3.model.v2.HashEventDto;
import org.garred.wh3.wh3.dummy.DummyEvents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHolder {

    private static List<HashEvent> events;
    private static Map<String,HashEvent> eventMap;

    public static boolean dataAvailable() {
        return eventMap != null && !eventMap.isEmpty();
    }

    public static void load() {
        events = new ArrayList<>();
        eventMap = new HashMap<>();
        for (DummyEvents.DummyItem item : DummyEvents.ITEMS) {
            HashEvent hashEvent = buildEvent(item);
            events.add(hashEvent);
            eventMap.put(item.id, hashEvent);
        }
    }

    public List<HashEvent> events() {
        return events;
    }

    public static List<HashEvent> getEvents() {
        return events;
    }
    public static HashEvent getEvent(String eventId) {
        return eventMap.get(eventId);
    }

    private static HashEvent buildEvent(DummyEvents.DummyItem item) {
        return new HashEvent.Builder()
                .withId(item.id)
                .withEventName(item.content)
                .withDescription(item.details)
                .build();
    }

    public static HashEventDto testEvent(String rawEvent) {
        List<HashEvent> testEvents = new ArrayList<>();
        Gson gson = new Gson();
        return gson.fromJson(rawEvent, HashEventDto.class);
    }
    public static final String RAW_EVENT_1 = "{\"id\":\"q4koace8a2fkk8rcf45mpbb28g_20160520T233000Z\",\"date\":[2016,5,20,16,30],\"eventNumber\":0,\"hare\":null,\"eventName\":\"Seattle H3 Hashy Hour\",\"description\":\"Join your SH3 Kennel this Friday for:\\n\\tHashy Hour\\nat\\nThe Lookout\\n\\nMay 20 2016\\n5:37pm - whenever\\n\\n757 Bellevue Ave E\\nGoogle describes The Lookout as a \\\"Hilltop Bar With Skyline Views.\\\" I'd describe The Lookout as a near-perfect neighborhood bar. It's got a comfortable but not over-familiar vibe along with great cocktails, solid beer selections, and great food. In addition it has a pinball machine or two, a shady patio out back, and the location is perfect if you want to get a gradual start and then walk up to Broadway for more serious shenanigans.\\n\\nWeather should be perfect on Friday, warm and lightly overcast with no chance of precipitation after noon.\\n\\nHappy Hour starts at 4 and runs until 7 with $3.50 microbrew pints, $4 wines (Alison,) and $5 selected appetizers. Even after HH the food is well worth the price - sandwiches and salads around $12 and appetizers around $8 for good portions and good quality.\\n\\nOn On to the weekend!\\n\\n~ACD\",\"address\":null,\"mapLink\":\"https://maps.google.com/maps?q=The+Lookout,+757+Bellevue+Ave+E,+Seattle,+WA+98102,+United+States\",\"kennel\":\"HAPPY_HOUR\"}";
    public static final String RAW_EVENT_2 = "{\"id\":\"5gl6qtd2ng769btrkirf17fna8_20160521\",\"date\":[2016,5,21,0,0],\"eventNumber\":149,\"hare\":null,\"eventName\":\"SSH3 #149\",\"description\":\"South Sound H3 #149\\n\\nWhen: Saturday, May 21, 12pm\\n\\nWhere: Owen's Beach parking lot at Point Defiance State Park, https://goo.gl/maps/yLgtBVoNY462\\n\\nHash cash: 5$\\n\\nHare: Boy Batter Badge\\n\\nTrail: At to A; around 3 miles with medium shiggy, not stroller friendly.\\n\\nOn after: UP Station or wherever the pack decides\\n\\nInfo: B3, 253-678-8845\",\"address\":null,\"mapLink\":\"https://maps.google.com/maps?q=Owen+Beach,+Tacoma,+WA,+United+States\",\"kennel\":\"SOUTH_SOUND\"}";
    public static final String RAW_EVENT_3 = "{\"id\":\"68rj4e9l6phjab9jc8r3ib9k68oj0bb2c5j68b9lckr36or1c4s32dhj6g_20160522\",\"date\":[2016,5,22,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"TBD\",\"description\":\"Shiggy-lovers running on the last Sunday of the month.\\n\\nHareline: http://wh3.org/Harelines/RainCity.htm\",\"address\":null,\"mapLink\":null,\"kennel\":\"RAIN_CITY\"}";
    public static final String RAW_EVENT_4 = "{\"id\":\"kh3u3lep1fjq36ma2c9ueae8p4_20160522\",\"date\":[2016,5,22,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"HSWTFH3 #?\",\"description\":\"\",\"address\":null,\"mapLink\":null,\"kennel\":\"HSWTF\"}";
    public static final String RAW_EVENT_5 = "{\"id\":\"uaa5f0jk19dtg8gik4mjlr3rqs_20160523\",\"date\":[2016,5,23,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"TBD\",\"description\":\"Details still TBD.\\n\\nRunners kennel with trails every Monday night.\\nHareline: http://wh3.org/Harelines/SeaMon.htm\",\"address\":null,\"mapLink\":null,\"kennel\":\"SEAMON\"}";
    public static final String RAW_EVENT_6 = "{\"id\":\"aboqr1h816f0afuph771fnd4cg_20160525\",\"date\":[2016,5,25,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"Tea Party\",\"description\":\"Bimbos are cordially invited to the Annual No Balls Tea Party! \\n\\nFor our new bimbo's the No Balls Tea Party is our annual tradition. We have a short trail, and then eat finger sandwiches and drink \\\"tea\\\" in our finist tea party attire. (But tea party attire is totally optional too, we just want to see your beautiful faces!) \\n\\nMenu:\\nMini-blt's on biscuits\\nCaprese sandwiches (bread tbd)\\nChicken salad with apple on wheat\\n\\nSides: deviled eggs w/avocado\\nCucumber \\\"sandwiches\\\" \\nCheese \\nChips and assorted other orange foods\\n\\nDesserts:\\nBrownies\\nCookies\\n\\nBeverages:\\nWhite wine sangria with strawberries & (peaches or nectarines)\\nBeer\\nSweetend iced tea w/ vodka\\nArnold Palmer Jello shots! \\nWater\\n\\nLower Woodland Park Picnic shelter (either 4 or 7 depending on availablity, but they are right near each other) \\n\\nIf you're bussing take the E line and get off at the 65th St stop and walk about .25 miles. \\nIf you're driving: take aurora and get off at Whitman/59th street (its a very sharp turn and it cums up quick) \\n\\nHash Cash: 10$ (all food and beer/alcohol is provided) No need to bring anything additionall. \\n\\nA crown will be bestowed on the best Tea Party bimbo and she will also receive a special prize! \\n\\nHares: Panty Ho & Cums Up Wet\",\"address\":null,\"mapLink\":\"https://maps.google.com/maps?q=47.670214,+-122.346431\",\"kennel\":\"NO_BALLS\"}";
    public static final String RAW_EVENT_7 = "{\"id\":\"h9urcnpqcjjeenurbsufc3ho6k_20160526T230000Z\",\"date\":[2016,5,26,16,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"South End H3 Happy Hour?\",\"description\":\"Thursday south-end happy hour.\\nDetails and location still TBD.\\n\\nWeekly from November through March, 2nd and 4th Thursdays the rest of the year.\",\"address\":null,\"mapLink\":null,\"kennel\":\"OTHER\"}";
    public static final String RAW_EVENT_8 = "{\"id\":\"q4koace8a2fkk8rcf45mpbb28g_20160527T233000Z\",\"date\":[2016,5,27,16,30],\"eventNumber\":0,\"hare\":null,\"eventName\":\"Seattle H3 Hashy Hour\",\"description\":\"A weekly Friday happy hour hosted by Seattle H3.\\nLocation and details, TBD.\",\"address\":null,\"mapLink\":null,\"kennel\":\"HAPPY_HOUR\"}";
    public static final String RAW_EVENT_9 = "{\"id\":\"0q8349rbqa6oo9n93kirre9tns_20160528\",\"date\":[2016,5,28,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"TBD\",\"description\":\"Co-ed kennel that runs on the 2nd & 4th Saturday of the month in the PM.\\n\\nHareline: http://wh3.org/Harelines/Seattle.htm\",\"address\":null,\"mapLink\":null,\"kennel\":\"SEATTLE\"}";
    public static final String RAW_EVENT_10 = "{\"id\":\"38it45bs3aar3rn57kumdaa5f0_20160601\",\"date\":[2016,6,1,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"TBD\",\"description\":\"The latest and greatest hash in Washington.\\nRunning the first Wednesday of every month at 6:30pm.\\nExpect a 5k-ish trail and fukov-style circle, with on-afters at a great local pub or brewery!\\nMismanagement:\\nRA: Jackoff More Men\\nHash Cash: Low Temp Ho\\nBeermeister: Bubba Gump\\nHaberdasher: Heiney Girl\\nHare Raiser: Raspberry Beret\\nHash Flash: Bone A Friend\",\"address\":null,\"mapLink\":null,\"kennel\":\"OTHER\"}";
    public static final String RAW_EVENT_11 = "{\"id\":\"mtbb57mqim376r2bmku3i29avo_20160604\",\"date\":[2016,6,4,0,0],\"eventNumber\":709,\"hare\":null,\"eventName\":\"TH3 #709 \",\"description\":\"Tacoma H3 run #709\\n\\nHare: Boy Batter Badge\\n\\nWhen: Saturday, June 4, 1pm\\n\\nWhere: Kobayashi Park, Chambers Creek Rd W., University Place\\nhttps://goo.gl/maps/xKwgwjkbUwp\\n\\nHash cash: $5\\n\\nTrail: A to A, 4+ miles of water and shiggy, not stroller or kid friendly\\n\\nOn after: B3's for a BBQ and hot tubing (5405 Bristonwood Dr. W., University Place WA)\\n\\nExtra: Sunday the tide is perfect for a hike out to the beached WW2 TNT barge in DuPont. Some of us are going to hike up and do it.\\n\\nInfo: B3, 253-678-8845\",\"address\":null,\"mapLink\":\"https://maps.google.com/maps?q=Kobayashi+Park,+Kobayashi+Park+Drive,+University+Place,+WA+98467,+USA\",\"kennel\":\"TACOMA\"}";
    public static final String RAW_EVENT_12 = "{\"id\":\"vb1u7962c284sbio8fqnqhjbsc\",\"date\":[2016,6,4,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"WH3.org domain name expires\",\"description\":\"\",\"address\":null,\"mapLink\":null,\"kennel\":\"OTHER\"}";
    public static final String RAW_EVENT_13 = "{\"id\":\"87iigop7s5186353gqom209nig_20160605\",\"date\":[2016,6,5,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"family-friendly\",\"description\":\"Family-friendly hash that runs the 1st and 3rd Sundays aimed at providing hash runs that are challenging enough to appeal to both adults and minors, with participants typically ranging from ages 6 years to 80 years. The trails typically are not stroller-friendly.\\n\\nHareline: http://wh3.org/Harelines/Tacoma.htm\\n\",\"address\":null,\"mapLink\":null,\"kennel\":\"OTHER\"}";
    public static final String RAW_EVENT_14 = "{\"id\":\"kh3u3lep1fjq36ma2c9ueae8p4_20160605\",\"date\":[2016,6,5,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"HSWTFH3 #?\",\"description\":\"\",\"address\":null,\"mapLink\":null,\"kennel\":\"HSWTF\"}";
    public static final String RAW_EVENT_15 = "{\"id\":\"qe0jugesivs11fl34l8aqrtss4_20160609\",\"date\":[2016,6,9,0,0],\"eventNumber\":0,\"hare\":null,\"eventName\":\"TBD\",\"description\":\"Seattle area bash (i.e. bicycle hash) that rides on the third Sunday of the month and also the second Thursdays during Summer.\\n\\nHare(s): TBD\",\"address\":null,\"mapLink\":null,\"kennel\":\"BASH\"}";


}
