package org.sphic.util;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class PubSub {
    private static Hashtable<String, Set<Subscriber>> subscribers = new Hashtable<>();

    public static void Publish(String channel, String message) {
        Set<Subscriber> subscribersInChannel = new HashSet(subscribers.get(channel));
        if (subscribersInChannel == null)
            return;
        for (Iterator<Subscriber> i = subscribersInChannel.iterator();i.hasNext();) {
            Subscriber s = i.next();
            s.Callback(message);
        }
    }

    public static void Subscribe(String channel, Subscriber subscriber) {
        Set<Subscriber> subscribersInChannel = subscribers.get(channel);
        if (subscribersInChannel == null) {
            Set<Subscriber> newSet = new HashSet();
            newSet.add(subscriber);
            subscribers.put(channel, newSet);
        } else {
            if (subscribersInChannel.contains(subscriber))
                return;
            subscribersInChannel.add(subscriber);
        }
    }

    public static void Unsubscribe(String channel, Subscriber subscriber) {
        Set<Subscriber> subscribersInChannel = subscribers.get(channel);
        if (subscribersInChannel == null) {
            return;
        } else {
            if (subscribersInChannel.contains(subscriber))
                subscribersInChannel.remove(subscriber);
        }
    }
}
