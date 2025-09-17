package com.mtovar.musicat.config;

import java.time.LocalDate;

public final class Constans {
    public static final LocalDate MIN_DATE = LocalDate.of(1900, 1, 1);
    public static final LocalDate MAX_DATE = LocalDate.now();
    public static final int DEFAULT_TAKE_AND_DISC_NUMBER = 1;

    private Constans() {}
}
