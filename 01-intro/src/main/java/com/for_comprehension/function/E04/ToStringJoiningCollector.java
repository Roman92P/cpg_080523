package com.for_comprehension.function.E04;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToStringJoiningCollector {

    public static void main(String[] args) {
        String c1 = Stream.of(1, 2, 3)
            .collect(joiningAsStringsSmart("", "", ""));

        String c2 = Stream.of(1, 2, 3)
            .collect(joiningAsStringsSmart(",", "", ""));

        String c3 = Stream.of(1, 2, 3)
            .collect(joiningAsStringsSmart(",", "{", "}"));

        String c4 = Stream.of()
            .collect(joiningAsStringsSmart(",", "{", "}"));

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
    }

    public static <T> Collector<T, ?, String> joiningAsStrings() {
        return new JoiningCollector<>();
    }

    public static <T> Collector<T, ?, String> joiningAsStrings(String separator) {
        return new JoiningCollector<>(separator);
    }

    public static <T> Collector<T, ?, String> joiningAsStrings(String separator, String prefix, String postfix) {
        return new JoiningCollector<>(separator, prefix, postfix);
    }

    public static <T> Collector<T, ?, String> joiningAsStringsSmart(String separator, String prefix, String postfix) {
        return new SmartJoiningCollector<>(separator, prefix, postfix);
    }

    public static class SmartJoiningCollector<T> implements Collector<T, List<String>, String> {

        private final String separator;
        private final String prefix;
        private final String postfix;

        public SmartJoiningCollector(String separator, String prefix, String postfix) {
            this.separator = separator;
            this.prefix = prefix;
            this.postfix = postfix;
        }

        @Override
        public Supplier<List<String>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<String>, T> accumulator() {
            return (strings, t) -> strings.add(t.toString());
        }

        @Override
        public BinaryOperator<List<String>> combiner() {
            return (strings, strings2) -> {
                strings.addAll(strings2);
                return strings;
            };
        }

        @Override
        public Function<List<String>, String> finisher() {
            return strings -> strings.stream().collect(Collectors.joining(separator, prefix, postfix));
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }


    public static class JoiningCollector<T> implements Collector<T, StringBuilder, String> {

        private final String separator;

        private final String prefix;

        private final String postfix;

        public JoiningCollector() {
            separator = "";
            prefix = "";
            postfix = "";
        }

        public JoiningCollector(String separator) {
            Objects.requireNonNull(separator);
            this.separator = separator;
            prefix = "";
            postfix = "";
        }

        public JoiningCollector(String separator, String prefix, String suffix) {
            Objects.requireNonNull(separator);
            Objects.requireNonNull(prefix);
            Objects.requireNonNull(suffix);
            this.separator = separator;
            this.prefix = prefix;
            this.postfix = suffix;
        }

        @Override
        public Supplier<StringBuilder> supplier() {
            return StringBuilder::new;
        }

        @Override
        public BiConsumer<StringBuilder, T> accumulator() {
            return (stringBuilder, t) -> {
                if (!stringBuilder.toString().isEmpty()) {
                    stringBuilder.append(separator);
                }

                stringBuilder.append(t);
            };
        }

        @Override
        public BinaryOperator<StringBuilder> combiner() {
            return (sb1, sb2) -> {
                if (sb1.toString().isEmpty()) {
                    return sb2;
                }
                else if (sb2.toString().isEmpty()) {
                    return sb1;
                }

                return sb1.append(separator).append(sb2);
            };
        }

        @Override
        public Function<StringBuilder, String> finisher() {
            return sb -> prefix + sb + postfix;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }

}
