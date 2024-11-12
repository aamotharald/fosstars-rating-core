package com.sap.oss.phosphor.fosstars.model.value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sap.oss.phosphor.fosstars.model.feature.StringFeature;
import com.sap.oss.phosphor.fosstars.util.Json;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class StringValueTest {

  @Test
  public void serializationAndDeserialization() throws IOException {
    StringFeature feature = new StringFeature("test");
    StringValue value = new StringValue(feature, "2.3.3");
    StringValue clone = Json.read(Json.toBytes(value), StringValue.class);
    assertTrue(value.equals(clone) && clone.equals(value));
    assertEquals(value.hashCode(), clone.hashCode());
  }
}
