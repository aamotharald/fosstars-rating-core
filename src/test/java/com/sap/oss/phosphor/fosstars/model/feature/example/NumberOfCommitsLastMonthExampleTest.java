package com.sap.oss.phosphor.fosstars.model.feature.example;

import static com.sap.oss.phosphor.fosstars.model.feature.example.ExampleFeatures.NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sap.oss.phosphor.fosstars.model.Value;
import org.junit.jupiter.api.Test;

public class NumberOfCommitsLastMonthExampleTest {

  @Test
  public void name() {
    assertNotNull(NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE.name());
  }

  @Test
  public void equalsAndHashCode() {
    assertEquals(NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE, NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE);
    assertEquals(
        NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE.hashCode(),
        NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE.hashCode());
  }

  @Test
  public void positive() {
    assertEquals(Integer.valueOf(0), NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE.value(0).get());
    assertEquals(Integer.valueOf(1), NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE.value(1).get());
    assertFalse(NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE.value(1).isUnknown());
  }

  @Test
  public void negative() {
    assertThrows(
        IllegalArgumentException.class, () -> NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE.value(-1));
  }

  @Test
  public void unknown() {
    Value<Integer> value = NUMBER_OF_COMMITS_LAST_MONTH_EXAMPLE.unknown();
    assertTrue(value.isUnknown());
  }
}
