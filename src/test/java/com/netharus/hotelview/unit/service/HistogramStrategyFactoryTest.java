package com.netharus.hotelview.unit.service;

import com.netharus.hotelview.histogramFactory.HistogramStrategy;
import com.netharus.hotelview.histogramFactory.HistogramStrategyFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class HistogramStrategyFactoryTest {

    static class DummyStrategy implements HistogramStrategy {
        private final String key;

        DummyStrategy(String key) {
            this.key = key;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Map<String, Long> getHistogram() {
            return Map.of();
        }
    }

    @Nested
    @DisplayName("Test getStrategy method")
    class GetStrategyTests {

        @Test
        @DisplayName("Should return correct strategy by key")
        void givenKey_whenGetStrategy_thenReturnsCorrectStrategy() {
            // Arrange
            DummyStrategy foo = new DummyStrategy("foo");
            DummyStrategy bar = new DummyStrategy("bar");
            HistogramStrategyFactory factory = new HistogramStrategyFactory(List.of(foo, bar));

            // Act
            HistogramStrategy actualFoo = factory.getStrategy("foo");
            HistogramStrategy actualBar = factory.getStrategy("bar");

            // Assert
            assertSame(foo, actualFoo);
            assertSame(bar, actualBar);
        }

        @Test
        @DisplayName("Should be case-insensitive")
        void givenKeyInDifferentCase_whenGetStrategy_thenReturnsCorrectStrategy() {
            // Arrange
            DummyStrategy foo = new DummyStrategy("foo");
            HistogramStrategyFactory factory = new HistogramStrategyFactory(List.of(foo));

            // Act
            HistogramStrategy actual = factory.getStrategy("FoO");

            // Assert
            assertSame(foo, actual);
        }

        @Test
        @DisplayName("Should throw exception for unknown key")
        void givenUnknownKey_whenGetStrategy_thenThrowsException() {
            // Arrange
            DummyStrategy foo = new DummyStrategy("foo");
            HistogramStrategyFactory factory = new HistogramStrategyFactory(List.of(foo));

            // Act & Assert
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> factory.getStrategy("unknown")
            );
            assertTrue(ex.getMessage().contains("unknown"));
        }
    }
}
