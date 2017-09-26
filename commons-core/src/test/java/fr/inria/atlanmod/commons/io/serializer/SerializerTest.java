package fr.inria.atlanmod.commons.io.serializer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link Serializer} instances.
 */
@ParametersAreNonnullByDefault
public class SerializerTest extends AbstractSerializerTest {

    @Test
    public void testSerializeDeserializeObject() throws IOException {
        Serializer<List<Integer>> serializer = SerializerFactory.getInstance().forAny();

        List<Integer> object = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = process(object, serializer);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void testSerializeDeserializeObjectWithStream() throws IOException {
        Serializer<List<Integer>> serializer = SerializerFactory.getInstance().forAny();

        List<Integer> object = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = process(object, serializer);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
}