package Negy.Event.impl;

import Negy.Event.Event;

public class TextEvent extends Event{
    public String textt;

    public TextEvent(String text) {
        this.textt = text;
    }

    public String getText() {
        return this.textt;
    }

}
