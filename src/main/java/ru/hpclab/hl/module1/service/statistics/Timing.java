package ru.hpclab.hl.module1.service.statistics;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Timing {
    private String name;
    private Instant start;
    private Instant stop;

    public Timing(String name, Instant start, Instant stop) {
        this.name = name;
        this.start = start;
        this.stop = stop;
    }

    @Override
    public String toString() {
        return "Timing{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", stop=" + stop +
                '}';
    }
}
