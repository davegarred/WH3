package org.garred.wh3.wh3;

import junit.framework.Assert;

import org.garred.wh3.model.HashEvent;
import org.garred.wh3.model.v2.HashEventDto;
import org.garred.wh3.model.v2.Kennel;
import org.garred.wh3.service.DataHolder;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.garred.wh3.service.DataHolder.*;

/**
 * Created by dgarred on 5/20/16.
 */
public class HashEventTest {

    @Test
    public void testEvent1() {
        HashEvent event = HashEvent.fromDto(testEvent(RAW_EVENT_1));
        assertEquals("q4koace8a2fkk8rcf45mpbb28g_20160520T233000Z", event.getId());
        assertEquals("Fri\nMay 20", event.getDateStringShort());
        assertEquals("May 20, 4:30 PM", event.getDateTime().toString());
        assertFalse(event.getMapLink().isEmpty());
        assertEquals(Kennel.HAPPY_HOUR, event.getType());
    }

    @Test
    public void testEvent2() {
        HashEvent event = HashEvent.fromDto(testEvent(RAW_EVENT_2));
        assertEquals("5gl6qtd2ng769btrkirf17fna8_20160521", event.getId());
        assertEquals("Sat\nMay 21", event.getDateStringShort());
        assertEquals("May 21", event.getDateTime().toString());
        assertFalse(event.getMapLink().isEmpty());
        assertEquals(Kennel.SOUTH_SOUND, event.getType());
    }

    @Test
    public void testEvent7() {
        HashEvent event = HashEvent.fromDto(testEvent(RAW_EVENT_7));
        assertEquals("h9urcnpqcjjeenurbsufc3ho6k_20160526T230000Z", event.getId());
        assertEquals("Thu\nMay 26", event.getDateStringShort());
        assertEquals("May 26, 4:00 PM", event.getDateTime().toString());
        assertTrue(event.getMapLink().isEmpty());
        assertEquals(Kennel.OTHER, event.getType());
    }





    private static URL safeLink(String link) {
        if(link == null) {
            return null;
        }
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
