package br.com.edubarbieri.whishlist.application.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public abstract class Event {
    protected String id;
    protected Date created;

    public abstract String getType();
}
