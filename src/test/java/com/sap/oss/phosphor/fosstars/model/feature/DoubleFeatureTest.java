package com.sap.oss.phosphor.fosstars.model.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sap.oss.phosphor.fosstars.model.Value;
import com.sap.oss.phosphor.fosstars.util.Json;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class DoubleFeatureTest {

  @Test
  public void serializeAndDeserialize() throws IOException {
    DoubleFeature feature = new DoubleFeature("test");
    byte[] bytes = Json.toBytes(feature);
    assertNotNull(bytes);
    assertTrue(bytes.length > 0);

    DoubleFeature clone = Json.mapper().readValue(bytes, DoubleFeature.class);
    assertNotNull(clone);
    assertEquals(feature, clone);
    assertEquals(feature.hashCode(), clone.hashCode());
  }

  @Test
  public void equalsAndHashCode() {
    DoubleFeature one = new DoubleFeature("one");
    DoubleFeature two = new DoubleFeature("two");
    DoubleFeature anotherOne = new DoubleFeature("one");

    assertNotEquals(one, two);
    assertEquals(one, anotherOne);

    assertNotEquals(one.hashCode(), two.hashCode());
    assertEquals(one.hashCode(), anotherOne.hashCode());
  }

  @Test
  public void value() {
    Value<Double> value = new DoubleFeature("test").value(10.2);
    assertNotNull(value);
    assertEquals(10.2, value.get(), 0.001);
  }

  @Test
  public void parseValidDouble() {
    Value<Double> value = new DoubleFeature("test").parse("10.2");
    assertNotNull(value);
    assertEquals(10.2, value.get(), 0.001);
  }

  @Test
  public void parseInvalidDouble() {
    assertThrows(NumberFormatException.class, () -> new DoubleFeature("test").parse("a"));
  }
}
