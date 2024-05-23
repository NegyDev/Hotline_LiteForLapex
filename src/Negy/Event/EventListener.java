/*
 * Decompiled with CFR 0.152.
 */
package Negy.Event;

import Negy.Event.Event;
import Negy.Module.Module;

public class EventListener {
    public static void ListenEvent(Event e) {
        Module.onEventAdd(e);
    }
}

