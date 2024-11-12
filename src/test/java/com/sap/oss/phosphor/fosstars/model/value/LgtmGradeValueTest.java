package com.sap.oss.phosphor.fosstars.model.value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sap.oss.phosphor.fosstars.model.feature.LgtmGradeFeature;
import com.sap.oss.phosphor.fosstars.util.Json;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class LgtmGradeValueTest {

  @Test
  public void testSerializeAndDeserialize() throws IOException {
    LgtmGradeFeature feature = new LgtmGradeFeature("feature");
    LgtmGradeValue a = feature.value(LgtmGrade.A_PLUS);
    byte[] bytes = Json.toBytes(a);
    assertNotNull(bytes);
    assertTrue(bytes.length > 0);

    Object clone = Json.read(bytes, LgtmGradeValue.class);
    assertNotNull(clone);
    assertEquals(a, clone);
    assertEquals(a.hashCode(), clone.hashCode());
  }
}
