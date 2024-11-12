package com.sap.oss.phosphor.fosstars.model.score.oss;

import com.sap.oss.phosphor.fosstars.model.score.WeightedCompositeScore;

/**
 * The security testing score uses the following sub-scores.
 *
 * <ul>
 *   <li>{@link DependencyScanScore}
 *   <li>{@link NoHttpToolScore}
 *   <li>{@link MemorySafetyTestingScore}
 *   <li>{@link StaticAnalysisScore}
 *   <li>{@link FuzzingScore}
 * </ul>
 *
 * <p>There is plenty room for improvements. The score can take into account a lot of other
 * information.
 */
public class ProjectSecurityTestingScore extends WeightedCompositeScore {

  /** Initializes a new score. */
  ProjectSecurityTestingScore() {
    super(
        "How well security testing is done for an open-source project",
        new DependencyScanScore(),
        new NoHttpToolScore(),
        new MemorySafetyTestingScore(),
        new StaticAnalysisScore(),
        new FuzzingScore());
  }
}
