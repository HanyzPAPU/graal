package jdk.graal.compiler.test.bytecodefuzz;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;

import com.code_intelligence.jazzer.mutation.annotation.InRange;
import com.code_intelligence.jazzer.mutation.annotation.FloatInRange;
import com.code_intelligence.jazzer.mutation.annotation.DoubleInRange;
import com.code_intelligence.jazzer.mutation.annotation.WithUtf8Length;
import com.code_intelligence.jazzer.mutation.support.TypeSupport;
import com.code_intelligence.jazzer.mutation.support.TypeHolder;

public final class JazzerTypeSupport {
    public static AnnotatedType InRange(AnnotatedType type, long min, long max) {
        InRange inRange = inRangeImplementation(min, max);
        return TypeSupport.withExtraAnnotations(type, inRange);
    }

    public static AnnotatedType FloatInRange(AnnotatedType type, float min, float max, boolean allowNaN) {
        FloatInRange floatInRange = floatInRangeImplementation(min, max, allowNaN);
        return TypeSupport.withExtraAnnotations(type, floatInRange);
    }

    public static AnnotatedType DoubleInRange(AnnotatedType type, double min, double max, boolean allowNaN) {
        DoubleInRange doubleInRange = doubleInRangeImplementation(min, max, allowNaN);
        return TypeSupport.withExtraAnnotations(type, doubleInRange);
    }

    public static AnnotatedType WithUtf8Length(AnnotatedType type, int min, int max){
        WithUtf8Length withUtf8Length = withUtf8LengthImplementation(min, max);
        return TypeSupport.withExtraAnnotations(type, withUtf8Length);
    }

    private static InRange inRangeImplementation(long min, long max) {
        return new InRange() {
            @Override
            public long min() {
                return min;
            }

            @Override
            public long max() {
                return max;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return InRange.class;
            }

            @Override
            public boolean equals(Object o) {
                if (o instanceof InRange other) {
                    return this.min() == other.min() && this.max() == other.max();
                }
                return false;
            }

            @Override
            public int hashCode() {
                int hash = 0;
                hash += ("min".hashCode() * 127) ^ Long.valueOf(this.min()).hashCode();
                hash += ("max".hashCode() * 127) ^ Long.valueOf(this.max()).hashCode();
                return hash;
            }
        };
    }

    private static FloatInRange floatInRangeImplementation(float min, float max, boolean allowNaN) {
        return new FloatInRange() {
            @Override
            public float min() {
                return min;
            }

            @Override
            public float max() {
                return max;
            }

            @Override
            public boolean allowNaN() {
                return allowNaN;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return FloatInRange.class;
            }

            @Override
            public boolean equals(Object o) {
                if (o instanceof FloatInRange other) {
                    return this.min() == other.min() && this.max() == other.max() && this.allowNaN() == other.allowNaN();
                }
                return false;
            }

            @Override
            public int hashCode() {
                int hash = 0;
                hash += ("min".hashCode() * 127) ^ Float.valueOf(this.min()).hashCode();
                hash += ("max".hashCode() * 127) ^ Float.valueOf(this.max()).hashCode();
                hash += ("allowNaN".hashCode() * 127) ^ Boolean.valueOf(this.allowNaN()).hashCode();
                return hash;
            }
        };
    }

    private static DoubleInRange doubleInRangeImplementation(double min, double max, boolean allowNaN) {
        return new DoubleInRange() {
            @Override
            public double min() {
                return min;
            }

            @Override
            public double max() {
                return max;
            }

            @Override
            public boolean allowNaN() {
                return allowNaN;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return DoubleInRange.class;
            }

            @Override
            public boolean equals(Object o) {
                if (o instanceof DoubleInRange other) {
                    return this.min() == other.min() && this.max() == other.max() && this.allowNaN() == other.allowNaN();
                }
                return false;
            }

            @Override
            public int hashCode() {
                int hash = 0;
                hash += ("min".hashCode() * 127) ^ Double.valueOf(this.min()).hashCode();
                hash += ("max".hashCode() * 127) ^ Double.valueOf(this.max()).hashCode();
                hash += ("allowNaN".hashCode() * 127) ^ Boolean.valueOf(this.allowNaN()).hashCode();
                return hash;
            }
        };
    }

    private static WithUtf8Length withUtf8LengthImplementation(int min, int max) {
        return new WithUtf8Length() {
            @Override
            public int min() {
                return min;
            }

            @Override
            public int max() {
                return max;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return WithUtf8Length.class;
            }

            @Override
            public boolean equals(Object o) {
                if (o instanceof WithUtf8Length other) {
                    return this.min() == other.min() && this.max() == other.max();
                }
                return false;
            }

            @Override
            public int hashCode() {
                int hash = 0;
                hash += ("min".hashCode() * 127) ^ Integer.valueOf(this.min()).hashCode();
                hash += ("max".hashCode() * 127) ^ Integer.valueOf(this.max()).hashCode();
                return hash;
            }
        };
    }

}
