package com.sap.oss.phosphor.fosstars.model.value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sap.oss.phosphor.fosstars.model.feature.oss.LanguagesFeature;
import com.sap.oss.phosphor.fosstars.util.Json;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class LanguagesValueTest {

  @Test
  public void serializationAndDeserialization() throws IOException {
    LanguagesFeature feature = new LanguagesFeature("test");
    LanguagesValue value = new LanguagesValue(feature, new Languages(Language.JAVA, Language.C));
    LanguagesValue clone = Json.read(Json.toBytes(value), LanguagesValue.class);
    assertTrue(value.equals(clone) && clone.equals(value));
    assertEquals(value.hashCode(), clone.hashCode());
  }
}
