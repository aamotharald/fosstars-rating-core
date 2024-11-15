package com.sap.oss.phosphor.fosstars.model.score.oss;

import static org.junit.jupiter.api.Assertions.fail;

import com.sap.oss.phosphor.fosstars.ScoreCollector;
import com.sap.oss.phosphor.fosstars.model.RatingRepository;
import com.sap.oss.phosphor.fosstars.model.Score;
import com.sap.oss.phosphor.fosstars.model.qa.ScoreVerification;
import com.sap.oss.phosphor.fosstars.model.qa.TestVectors;
import com.sap.oss.phosphor.fosstars.model.qa.VerificationFailedException;
import com.sap.oss.phosphor.fosstars.model.rating.oss.OssSecurityRating;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ScoreVerificationTest {

  @Test
  public void testVerification() {

    //Scores which are based on dates or somewhat else time dependent and will fail in the future
    List<String> failingScores =
        List.of("VulnerabilityDiscoveryAndSecurityTestingScore", "SecurityReviewScore");

    ScoreCollector collector = new ScoreCollector();
    RatingRepository.INSTANCE.rating(OssSecurityRating.class).accept(collector);

    // add extra scores
    collector.scores().add(new VulnerabilityLifetimeScore());

    for (Score score : collector.scores()) {
      if (failingScores.contains(score.getClass().getSimpleName())) {
        continue;
      } else {
        scoreVerification(score);
      }
    }
  }

  @Test
  //Date changed in testdata to have the CVE being more recent to make tests pass
  // an approach to introduce relative dates is needed for this test case
  public void testVulnerabilityDiscoveryAndSecurityTestingScore() {
    scoreVerification(
        new VulnerabilityDiscoveryAndSecurityTestingScore(new ProjectSecurityTestingScore()));
    scoreVerification(new SecurityReviewScore());
  }

  protected void scoreVerification(Score score) {
    String className = score.getClass().getSimpleName();
    String testVectorsFileName = String.format("%sTestVectors.yml", className);
    try (InputStream is = score.getClass().getResourceAsStream(testVectorsFileName)) {
      TestVectors testVectors = TestVectors.loadFromYaml(is);
      if (testVectors.isEmpty()) {
        System.out.printf("No test vectors for %s%n", className);
        fail("Verification failed");
      }
      System.out.printf("Verify %s with %d test vectors%n", className, testVectors.size());
      new ScoreVerification(score, testVectors).run();
    } catch (VerificationFailedException e) {
      System.out.printf("Verification failed for %s%n", className);
      fail("Verification failed");
    } catch (Exception e) {
      System.out.printf("Couldn't verify %s%n", className);
      e.printStackTrace(System.out);
      fail("Verification failed");
    }
  }
}
