package br.com.eventoweb.view;

import java.io.Serializable;
import java.util.TimeZone;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TimezoneUI implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
}
