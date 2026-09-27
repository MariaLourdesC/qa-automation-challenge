
package petstore;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetRunner {

    @Test
    void runPetstore() {
        Results results = Runner
                .path("classpath:petstore/petstore.feature")
                .parallel(1);

        assertEquals(
                0,
                results.getFailCount(),
                results.getErrorMessages()
        );
    }
}
