package tuntap;

public interface Selector<T extends Selector.Selectable> {

    Iterable<T> select(long timeout, TimeoutPrecision precision);

    Selector<T> register(T source, InterestOperation operation);

    interface Selectable {}

    enum InterestOperation {

        READ(0), WRITE(1), READ_WRITE(2);

        private static final InterestOperation[] VALUES = values();

        private final int value;

        InterestOperation(int value) {
            this.value = value;
        }

        public static InterestOperation fromValue(int value) {
            if (value < 0 || value > 2) {
                return null;
            }
            return VALUES[value];
        }

        public int value() {
            return value;
        }
    }

    /**
     * Copied from java.util.concurrent.TimeUnit.
     */
    enum TimeoutPrecision {
        NANOSECONDS {
            public long toNanos(long value) {
                return value;
            }

            public long toMicros(long value) {
                return value / 1000L;
            }

            public long toMillis(long value) {
                return value / 1000000L;
            }

            public long toSeconds(long value) {
                return value / 1000000000L;
            }

            public long toMinutes(long value) {
                return value / 60000000000L;
            }

            public long toHours(long value) {
                return value / 3600000000000L;
            }
        },
        MICROSECONDS {
            public long toNanos(long value) {
                return x(value, 1000L, 9223372036854775L);
            }

            public long toMicros(long value) {
                return value;
            }

            public long toMillis(long value) {
                return value / 1000L;
            }

            public long toSeconds(long value) {
                return value / 1000000L;
            }

            public long toMinutes(long value) {
                return value / 60000000L;
            }

            public long toHours(long value) {
                return value / 3600000000L;
            }
        },
        MILLISECONDS {
            public long toNanos(long value) {
                return x(value, 1000000L, 9223372036854L);
            }

            public long toMicros(long value) {
                return x(value, 1000L, 9223372036854775L);
            }

            public long toMillis(long value) {
                return value;
            }

            public long toSeconds(long value) {
                return value / 1000L;
            }

            public long toMinutes(long value) {
                return value / 60000L;
            }

            public long toHours(long value) {
                return value / 3600000L;
            }
        },
        SECONDS {
            public long toNanos(long value) {
                return x(value, 1000000000L, 9223372036L);
            }

            public long toMicros(long value) {
                return x(value, 1000000L, 9223372036854L);
            }

            public long toMillis(long value) {
                return x(value, 1000L, 9223372036854775L);
            }

            public long toSeconds(long value) {
                return value;
            }

            public long toMinutes(long value) {
                return value / 60L;
            }

            public long toHours(long value) {
                return value / 3600L;
            }
        },
        MINUTES {
            public long toNanos(long value) {
                return x(value, 60000000000L, 153722867L);
            }

            public long toMicros(long value) {
                return x(value, 60000000L, 153722867280L);
            }

            public long toMillis(long value) {
                return x(value, 60000L, 153722867280912L);
            }

            public long toSeconds(long value) {
                return x(value, 60L, 153722867280912930L);
            }

            public long toMinutes(long value) {
                return value;
            }

            public long toHours(long value) {
                return value / 60L;
            }
        },
        HOURS {
            public long toNanos(long value) {
                return x(value, 3600000000000L, 2562047L);
            }

            public long toMicros(long value) {
                return x(value, 3600000000L, 2562047788L);
            }

            public long toMillis(long value) {
                return x(value, 3600000L, 2562047788015L);
            }

            public long toSeconds(long value) {
                return x(value, 3600L, 2562047788015215L);
            }

            public long toMinutes(long value) {
                return x(value, 60L, 153722867280912930L);
            }

            public long toHours(long value) {
                return value;
            }
        };

        static final long C0 = 1L;
        static final long C1 = 1000L;
        static final long C2 = 1000000L;
        static final long C3 = 1000000000L;
        static final long C4 = 60000000000L;
        static final long C5 = 3600000000000L;
        static final long C6 = 86400000000000L;
        static final long MAX = 9223372036854775807L;

        TimeoutPrecision() {
        }

        static long x(long var0, long var2, long var4) {
            if (var0 > var4) {
                return 9223372036854775807L;
            } else {
                return var0 < -var4 ? -9223372036854775808L : var0 * var2;
            }
        }
    }
}
