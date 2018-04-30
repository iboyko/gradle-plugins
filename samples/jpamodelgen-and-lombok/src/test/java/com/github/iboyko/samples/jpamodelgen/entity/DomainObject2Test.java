package com.github.iboyko.samples.jpamodelgen.entity;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.Test;
import com.github.iboyko.samples.jpamodelgen.entity.DomainObject2_;

public class DomainObject2Test {
    @Test
    public void testMetadata() throws Exception{
	Field idField = DomainObject2_.class.getDeclaredField("id");
	assertNotNull(idField);
    }
}
