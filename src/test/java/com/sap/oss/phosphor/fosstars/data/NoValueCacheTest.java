package com.sap.oss.phosphor.fosstars.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.sap.oss.phosphor.fosstars.model.value.ValueHashSet;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class NoValueCacheTest {

  @Test
  public void testPutAndGet() {
    NoValueCache cache = NoValueCache.create();
    Object key = new Object();
    cache.put(key, new ValueHashSet());
    assertEquals(0, NoValueCache.create().size());
    assertFalse(cache.get(key).isPresent());
  }

  @Test
  public void testPutAndGetWithExpiration() {
    NoValueCache cache = NoValueCache.create();
    Object key = new Object();
    cache.put(key, new ValueHashSet(), new Date());
    assertEquals(0, NoValueCache.create().size());
    assertFalse(cache.get(key).isPresent());
  }

  @Test
  public void testSize() {
    assertEquals(0, NoValueCache.create().size());
  }
}
