package com.nullgeodesic.washingtonhhh;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class FindHtmlTest {

    private static final String NO_HTML = "PLEASE SEND HELP!\\n\\nI never thought it would come to this, but there comes a time when a man has to do what a man has to do.\\n\\nCLICK BAIT, it is the plague of our times.\\n\\nHelp me find my love for hashing once again SATURDAY APRIL 6TH in DUPONT, WA. There will be shiggy. There will be beach. What's not to love? \\n\\nA TACOMA H3 event.\\n\\nThere will be all the things you expect at a hash.\\n\\nTHERE WILL BE SHIGGY.\\n\\nSO MUCH GOD DAMN SHIGGY. SO MUCH SHIGGY YOU WILL LOOK LIKE JESUS WITH A LOIN CLOTH OF THORNS BY THE END. YOU WILL HATE LIFE.\\n\\nBUT BY THE END, the beer will taste sweeter, the sun will shine brighter, your wounds will miraculously heal and 72 virgin men will have shitty sex with you.\\n\\nWhat was I talking about?\\n\\nWho knows!?!\\n\\n7 Shekels will get you nothing, but 7 dollars will be the hash cash unless your name is Shackless, then hash cash is covered by the hare due to prior debt of hash cash.\\n\\nLength of trail is 6.69 miles.\\n\\nBring your bathing suits and bring a tie and bring your golf clubs and bring virgins and bananas and fresh socks and underwear and a second or third pair of shoes.\\n\\nBRING A WHISTLE!!!\\n\\nTypical hash based in and around Tacoma, WA.\\nKennel runs once a month on the first Saturday of the month.";
    private static final String HAS_HTML = "FREE Trail to count the ballots, add your vote, and officially nominate your new SH3 Mismanagement!<br><br>Hare: Outgoing JGM, I'll Take Your Cherry<br><br>HashCash: FREE<br><br>Details TBD<br><br><a href=\\\"https://www.facebook.com/events/626869511105750/\\\" target=\\\"_blank\\\" id=\\\"ow1428\\\" __is_owner=\\\"true\\\">https://www.facebook.com/events/626869511105750/</a><br><br>***** VOTING DETAILS BELOW *****<br><br>It's time to vote for who you think will do the least shitty job of r*nning the Seattle Hash House Harriers for the next year!<br><br>Electronic Voting Closes on Friday April 12th at Midnight. There will also be ballots AT TRAIL on April 13th!<br><br>https://www.surveymonkey.com/r/6FTPDYV<br><br>The April 13th ERECTION TRAIL will be FREE and at Golden Gardens, more detrails to cum";
    @Test
    public void parseHtml() {

        Pattern pat = Pattern.compile("<[^>]*>");

        assertTrue(pat.matcher(HAS_HTML).find());
        assertFalse(pat.matcher(NO_HTML).matches());
    }
}