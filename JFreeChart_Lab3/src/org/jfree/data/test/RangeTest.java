package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
	/*
	 * Test combine()
	 */
	
    /**
     * Test Case: Both input ranges are null.
     * Test Strategy: ECP - Null case
     * Expected Behavior: The method should return null.
     */
    @Test
    public void testCombineBothNullReturnsNull() {
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(null, null);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertNull("Combining two null ranges should return null.", combinedRange);
    }

    /**
     * Test Case: First input range is null.
     * Test Strategy: ECP - Null handling
     * Expected Behavior: The method should return the second range.
     */
    @Test
    public void testCombineFirstNullReturnsSecondRange() {
        Range range2 = new Range(2, 5);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(null, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Combining null with a valid range should return the valid range.", range2, combinedRange);
    }

    /**
     * Test Case: Second input range is null.
     * Test Strategy: ECP - Null handling
     * Expected Behavior: The method should return the first range.
     */
    @Test
    public void testCombineSecondNullReturnsFirstRange() {
        Range range1 = new Range(3, 8);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, null);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Combining a valid range with null should return the valid range.", range1, combinedRange);
    }

    /**
     * Test Case: Two non-overlapping ranges.
     * Test Strategy: ECP - Non-overlapping ranges
     * Expected Behavior: The method should return a new range covering both inputs.
     */
    @Test
    public void testCombineNonOverlappingRangesMergesCorrectly() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(10, 15);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Non-overlapping ranges should be merged into (1, 15).", new Range(1, 15), combinedRange);
    }

    /**
     * Test Case: Two touching ranges.
     * Test Strategy: BVA - Touching boundaries
     * Expected Behavior: The method should merge them into a single continuous range.
     */
    @Test
    public void testCombineTouchingRangesMerges() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(5, 10);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Touching ranges should merge into (1,10).", new Range(1, 10), combinedRange);
    }

    /**
     * Test Case: Two overlapping ranges.
     * Test Strategy: ECP - Overlapping ranges
     * Expected Behavior: The method should merge the overlapping portions into a single range.
     */
    @Test
    public void testCombineOverlappingRangesMergesCorrectly() {
        Range range1 = new Range(1, 6);
        Range range2 = new Range(4, 10);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Overlapping ranges should merge into (1,10).", new Range(1, 10), combinedRange);
    }

    /**
     * Test Case: Identical ranges.
     * Test Strategy: ECP - Identical ranges
     * Expected Behavior: The method should return the same range.
     */
    @Test
    public void testCombineIdenticalRangesReturnsSameRange() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(1, 5);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Identical ranges should return the same range.", range1, combinedRange);
    }

    /**
     * Test Case: One range is fully inside another.
     * Test Strategy: ECP - Subset range
     * Expected Behavior: The method should return the larger range.
     */
    @Test
    public void testCombineOneRangeInsideAnotherReturnsLargerRange() {
        Range range1 = new Range(1, 10);
        Range range2 = new Range(3, 7);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Range inside another should return the larger range.", range1, combinedRange);
    }

    /**
     * Test Case: Merging when one range has Integer.MIN_VALUE.
     * Test Strategy: BVA - Lower boundary
     * Expected Behavior: The method should correctly handle the extreme lower bound.
     */
    @Test
    public void testCombineLowerBoundaryValuesMergeCorrectly() {
        Range range1 = new Range(Integer.MIN_VALUE, -1);
        Range range2 = new Range(0, 5);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Lower boundary values should merge into (Integer.MIN_VALUE, 5).", new Range(Integer.MIN_VALUE, 5), combinedRange);
    }

    /**
     * Test Case: Merging when one range has Integer.MAX_VALUE.
     * Test Strategy: BVA - Upper boundary
     * Expected Behavior: The method should correctly handle the extreme upper bound.
     */
    @Test
    public void testCombineUpperBoundaryValuesMergeCorrectly() {
        Range range1 = new Range(10, Integer.MAX_VALUE);
        Range range2 = new Range(5, 9);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Upper boundary values should merge into (5, Integer.MAX_VALUE).", new Range(5, Integer.MAX_VALUE), combinedRange);
    }

    /**
     * Test Case: Merging two ranges at the zero boundary.
     * Test Strategy: BVA - Zero boundary
     * Expected Behavior: The method should correctly handle zero-based merging.
     */
    @Test
    public void testCombineZeroBoundaryValuesMergeCorrectly() {
        Range range1 = new Range(-5, 0);
        Range range2 = new Range(0, 5);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Zero boundary values should merge into (-5,5).", new Range(-5, 5), combinedRange);
    }
    
    /**
     * Test Case: Identical single-point ranges.
     * Test Strategy: ECP - Single-point range
     * Expected Behavior: The method should return the same single-point range.
     */
    @Test
    public void testCombineSinglePointRangesIdenticalReturnsSamePoint() {
        Range range1 = new Range(3, 3);
        Range range2 = new Range(3, 3);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Identical single-point ranges (3,3) should return (3,3).",
            new Range(3, 3), combinedRange);
    }

    /**
     * Test Case: Merging two adjacent single-point ranges.
     * Test Strategy: BVA - Adjacent single points
     * Expected Behavior: The method should merge them into a larger range.
     */
    @Test
    public void testCombineSinglePointRangesAdjacentMerges() {
        Range range1 = new Range(3, 3);
        Range range2 = new Range(4, 4);
        Range combinedRange = null;
        try {
            combinedRange = Range.combine(range1, range2);
        } catch (IllegalArgumentException e) {
            fail("Failed to combine the range.");
        }
        assertEquals("Adjacent single-point ranges should merge into (3,4).", new Range(3, 4), combinedRange);
    }
    
    /*
     * Test constrain()
     */
    
    /**
     * Test Case: A normal value within the valid range.
     * Test Strategy: Inside the range
     * Expected Behavior: The method should return the input value as is.
     */
    @Test
    public void testConstrainWithinRangeReturnsSameValue() {
        Range range = new Range(2, 8);
        assertEquals("A value inside the range should return itself.",
            5, range.constrain(5), 0.000000001d);
    }

    /**
     * Test Case: The lower boundary value of the range.
     * Test Strategy: Lower boundary inside the range
     * Expected Behavior: The method should return the lower boundary itself.
     */
    @Test
    public void testConstrainLowerBoundaryReturnsSameValue() {
        Range range = new Range(2, 8);
        assertEquals("The lower boundary should return itself.",
            2, range.constrain(2), 0.000000001d);
    }

    /**
     * Test Case: The upper boundary value of the range.
     * Test Strategy: Upper boundary inside the range
     * Expected Behavior: The method should return the upper boundary itself.
     */
    @Test
    public void testConstrainUpperBoundaryReturnsSameValue() {
        Range range = new Range(2, 8);
        assertEquals("The upper boundary should return itself.",
            8, range.constrain(8), 0.000000001d);
    }

    /**
     * Test Case: A value below the lower boundary.
     * Test Strategy: Below the range
     * Expected Behavior: The method should return the lower boundary.
     */
    @Test
    public void testConstrainBelowRangeReturnsLowerBound() {
        Range range = new Range(2, 8);
        assertEquals("A value below the range should return the lower bound.",
            2, range.constrain(0), 0.000000001d);
    }

    /**
     * Test Case: A significantly lower value, far outside the range.
     * Test Strategy: Far below the range
     * Expected Behavior: The method should return the lower boundary.
     */
    @Test
    public void testConstrainFarBelowRangeReturnsLowerBound() {
        Range range = new Range(2, 8);
        assertEquals("A value far below the range should return the lower bound.",
            2, range.constrain(-5), 0.000000001d);
    }

    /**
     * Test Case: A value above the upper boundary.
     * Test Strategy: Above the range
     * Expected Behavior: The method should return the upper boundary.
     */
    @Test
    public void testConstrainAboveRangeReturnsUpperBound() {
        Range range = new Range(2, 8);
        assertEquals("A value above the range should return the upper bound.",
            8, range.constrain(10), 0.000000001d);
    }

    /**
     * Test Case: A significantly higher value, far outside the range.
     * Test Strategy: Far above the range
     * Expected Behavior: The method should return the upper boundary.
     */
    @Test
    public void testConstrainFarAboveRangeReturnsUpperBound() {
        Range range = new Range(2, 8);
        assertEquals("A value far above the range should return the upper bound.",
            8, range.constrain(100), 0.000000001d);
    }

    /**
     * Test Case: A value just below the lower boundary.
     * Test Strategy: Just below the lower boundary
     * Expected Behavior: The method should return the lower boundary.
     */
    @Test
    public void testConstrainJustBelowLowerBoundaryReturnsLowerBound() {
        Range range = new Range(2, 8);
        assertEquals("A value just below the lower boundary should return the lower bound.",
            2, range.constrain(1.9999), 0.000000001d);
    }

    /**
     * Test Case: A value slightly lower than the range.
     * Test Strategy: Lower boundary outside range
     * Expected Behavior: The method should return the lower boundary.
     */
    @Test
    public void testConstrainLowerBoundaryOutsideReturnsLowerBound() {
        Range range = new Range(2, 8);
        assertEquals("A value just outside the lower boundary should return the lower bound.",
            2, range.constrain(1), 0.000000001d);
    }

    /**
     * Test Case: A value just above the upper boundary.
     * Test Strategy: Just above the upper boundary
     * Expected Behavior: The method should return the upper boundary.
     */
    @Test
    public void testConstrainJustAboveUpperBoundaryReturnsUpperBound() {
        Range range = new Range(2, 8);
        assertEquals("A value just above the upper boundary should return the upper bound.",
            8, range.constrain(8.0001), 0.000000001d);
    }

    /**
     * Test Case: A value slightly higher than the range.
     * Test Strategy: Upper boundary outside range
     * Expected Behavior: The method should return the upper boundary.
     */
    @Test
    public void testConstrainUpperBoundaryOutsideReturnsUpperBound() {
        Range range = new Range(2, 8);
        assertEquals("A value just outside the upper boundary should return the upper bound.",
            8, range.constrain(9), 0.000000001d);
    }
	
    // Testing method Range.contains()
    // TC_RNG_033: Value exactly at the lower bound
    @Test
    public void testContains_AtLowerBound() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Range(5,10) should contain its lower bound 5.0", range.contains(5.0));
    }

    // TC_RNG_034: Value exactly at the upper bound
    @Test
    public void testContains_AtUpperBound() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Range(5,10) should contain its upper bound 10.0", range.contains(10.0));
    }

    // TC_RNG_035: Value within the range
    @Test
    public void testContains_ValueInsideRange() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Range(5,10) should contain 7.5", range.contains(7.5));
    }

    // TC_RNG_036: Value outside the range (lower than lower bound)
    @Test
    public void testContains_ValueBelowRange() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Range(5,10) should not contain 3.0", range.contains(3.0));
    }

    // TC_RNG_037: Value outside the range (greater than upper bound)
    @Test
    public void testContains_ValueAboveRange() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Range(5,10) should not contain 12.0", range.contains(12.0));
    }

    // TC_RNG_038: Value in a negative range
    @Test
    public void testContains_ValueInNegativeRange() {
        Range range = new Range(-10.0, -5.0);
        assertTrue("Range(-10,-5) should contain -7.0", range.contains(-7.0));
    }

    // TC_RNG_039: Value outside a negative range
    @Test
    public void testContains_ValueOutsideNegativeRange() {
        Range range = new Range(-10.0, -5.0);
        assertFalse("Range(-10,-5) should not contain -11.0", range.contains(-11.0));
    }

    // TC_RNG_040: Value at zero in a range that includes zero
    @Test
    public void testContains_ValueAtZero() {
        Range range = new Range(-5.0, 5.0);
        assertTrue("Range(-5,5) should contain 0.0", range.contains(0.0));
    }

    // TC_RNG_041: Value in a very large range
    @Test
    public void testContains_ValueInLargeRange() {
        Range range = new Range(-1e9, 1e9);
        assertTrue("Range(-1e9,1e9) should contain 0", range.contains(0.0));
    }

    // TC_RNG_042: Value outside a very large range
    @Test
    public void testContains_ValueOutsideLargeRange() {
        Range range = new Range(-1e9, 1e9);
        assertFalse("Range(-1e9,1e9) should not contain 1e12", range.contains(1e12));
    }

    // TC_RNG_043: Single-point range containing its own value
    @Test
    public void testContains_SinglePointRangeContainsItsValue() {
        Range range = new Range(3.0, 3.0);
        assertTrue("Single-point range (3,3) should contain 3.0", range.contains(3.0));
    }

    // TC_RNG_044: Single-point range should not contain other values
    @Test
    public void testContains_SinglePointRangeDoesNotContainOtherValues() {
        Range range = new Range(3.0, 3.0);
        assertFalse("Single-point range (3,3) should not contain 4.0", range.contains(4.0));
    }

    // TC_RNG_045: Extreme values - Checking Double.MIN_VALUE
    @Test
    public void testContains_WithDoubleMinValue() {
        Range range = new Range(Double.MIN_VALUE, 10.0);
        assertTrue("Range(Double.MIN_VALUE, 10) should contain Double.MIN_VALUE", range.contains(Double.MIN_VALUE));
    }

    // TC_RNG_046: Extreme values - Checking Double.MAX_VALUE
    @Test
    public void testContains_WithDoubleMaxValue() {
        Range range = new Range(-10.0, Double.MAX_VALUE);
        assertTrue("Range(-10, Double.MAX_VALUE) should contain Double.MAX_VALUE", range.contains(Double.MAX_VALUE));
    }

    // TC_RNG_047: Handling NaN values
    @Test
    public void testContains_WithNaN() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Range(5,10) should not contain NaN", range.contains(Double.NaN));
    }
    
    // Testing method Range.getCentralValue()
    // TC_RNG_048: Central value of a standard positive range
    @Test
    public void testGetCentralValue_PositiveRange() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Central value of Range(5,10) should be 7.5", 
                     7.5, range.getCentralValue(), 0.000000001d);
    }

    // TC_RNG_049: Central value of a negative range
    @Test
    public void testGetCentralValue_NegativeRange() {
        Range range = new Range(-10.0, -5.0);
        assertEquals("Central value of Range(-10,-5) should be -7.5", 
                     -7.5, range.getCentralValue(), 0.000000001d);
    }

    // TC_RNG_050: Central value when range includes zero
    @Test
    public void testGetCentralValue_IncludingZero() {
        Range range = new Range(-5.0, 5.0);
        assertEquals("Central value of Range(-5,5) should be 0.0", 
                     0.0, range.getCentralValue(), 0.000000001d);
    }

    // TC_RNG_051: Central value when lower bound is zero
    @Test
    public void testGetCentralValue_ZeroLowerBound() {
        Range range = new Range(0.0, 10.0);
        assertEquals("Central value of Range(0,10) should be 5.0", 
                     5.0, range.getCentralValue(), 0.000000001d);
    }

    // TC_RNG_052: Central value of a single-point range
    @Test
    public void testGetCentralValue_SinglePointRange() {
        Range range = new Range(3.0, 3.0);
        assertEquals("Central value of Range(3,3) should be 3.0", 
                     3.0, range.getCentralValue(), 0.000000001d);
    }

    // TC_RNG_053: Central value of a large range
    @Test
    public void testGetCentralValue_LargeRange() {
        Range range = new Range(-1e9, 1e9);
        assertEquals("Central value of Range(-1e9,1e9) should be 0.0", 
                     0.0, range.getCentralValue(), 0.000000001d);
    }

    // TC_RNG_054: Central value of an extreme range near Double.MIN_VALUE
    @Test
    public void testGetCentralValue_WithDoubleMinValue() {
        Range range = new Range(Double.MIN_VALUE, 10.0);
        assertEquals("Central value of Range(Double.MIN_VALUE, 10) should be (Double.MIN_VALUE + 10) / 2", 
                     (Double.MIN_VALUE + 10.0) / 2, range.getCentralValue(), 0.000000001d);
    }

    // TC_RNG_055: Central value of an extreme range near Double.MAX_VALUE
    @Test
    public void testGetCentralValue_WithDoubleMaxValue() {
        Range range = new Range(-10.0, Double.MAX_VALUE);
        assertEquals("Central value of Range(-10, Double.MAX_VALUE) should be (-10 + Double.MAX_VALUE) / 2", 
                     (-10.0 + Double.MAX_VALUE) / 2, range.getCentralValue(), 0.000000001d);
    }

    // TC_RNG_056: Central value of a large negative range
    @Test
    public void testGetCentralValue_LargeNegativeRange() {
        Range range = new Range(-1e12, -1e6);
        assertEquals("Central value of Range(-1e12,-1e6) should be (-1e12 + -1e6) / 2", 
                     (-1e12 + -1e6) / 2, range.getCentralValue(), 0.000000001d);
    }

    // TC_RNG_057: Central value of a small decimal range
    @Test
    public void testGetCentralValue_SmallDecimalRange() {
        Range range = new Range(0.0001, 0.0005);
        assertEquals("Central value of Range(0.0001,0.0005) should be 0.0003", 
                     0.0003, range.getCentralValue(), 0.000000001d);
    }
    
    // Testing method Range.getLength()
    // TC_RNG_058: Length of a standard positive range
    @Test
    public void testGetLength_PositiveRange() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Length of Range(5,10) should be 5.0", 
                     5.0, range.getLength(), 0.000000001d);
    }

    // TC_RNG_059: Length of a negative range
    @Test
    public void testGetLength_NegativeRange() {
        Range range = new Range(-10.0, -5.0);
        assertEquals("Length of Range(-10,-5) should be 5.0", 
                     5.0, range.getLength(), 0.000000001d);
    }

    // TC_RNG_060: Length of a range including zero
    @Test
    public void testGetLength_IncludingZero() {
        Range range = new Range(-5.0, 5.0);
        assertEquals("Length of Range(-5,5) should be 10.0", 
                     10.0, range.getLength(), 0.000000001d);
    }

    // TC_RNG_061: Length of a zero-width range (both bounds are the same)
    @Test
    public void testGetLength_ZeroWidthRange() {
        Range range = new Range(3.0, 3.0);
        assertEquals("Length of Range(3,3) should be 0.0", 
                     0.0, range.getLength(), 0.000000001d);
    }

    // TC_RNG_062: Length of a very large range
    @Test
    public void testGetLength_LargeRange() {
        Range range = new Range(-1e9, 1e9);
        assertEquals("Length of Range(-1e9,1e9) should be 2e9", 
                     2e9, range.getLength(), 0.000000001d);
    }

    // TC_RNG_063: Length of an extreme range near Double.MIN_VALUE
    @Test
    public void testGetLength_WithDoubleMinValue() {
        Range range = new Range(Double.MIN_VALUE, 10.0);
        assertEquals("Length of Range(Double.MIN_VALUE, 10) should be 10 - Double.MIN_VALUE", 
                     10.0 - Double.MIN_VALUE, range.getLength(), 0.000000001d);
    }

    // TC_RNG_064: Length of an extreme range near Double.MAX_VALUE
    @Test
    public void testGetLength_WithDoubleMaxValue() {
        Range range = new Range(-10.0, Double.MAX_VALUE);
        assertEquals("Length of Range(-10, Double.MAX_VALUE) should be Double.MAX_VALUE + 10", 
                     Double.MAX_VALUE + 10.0, range.getLength(), 0.000000001d);
    }

    // TC_RNG_065: Length of a large negative range
    @Test
    public void testGetLength_LargeNegativeRange() {
        Range range = new Range(-1e12, -1e6);
        assertEquals("Length of Range(-1e12,-1e6) should be (-1e6 + 1e12)", 
                     (-1e6 + 1e12), range.getLength(), 0.000000001d);
    }

    // TC_RNG_066: Length of a small decimal range
    @Test
    public void testGetLength_SmallDecimalRange() {
        Range range = new Range(0.0001, 0.0005);
        assertEquals("Length of Range(0.0001,0.0005) should be 0.0004", 
                     0.0004, range.getLength(), 0.000000001d);
    }
    
//////////////////////////////////////
///
///    

    @Test
    public void testGetLowerBound() {
        Range r = new Range(5, 10);
        assertEquals(5, r.getLowerBound(), 0.0001);
    }

    @Test
    public void testGetUpperBound() {
        Range r = new Range(5, 10);
        assertEquals(10, r.getUpperBound(), 0.0001);
    }

    @Test
    public void testGetLength() {
        Range r = new Range(3, 8);
        assertEquals(5, r.getLength(), 0.0001);
    }

    @Test
    public void testContains_ValueWithinRange_ShouldReturnTrue() {
        Range r = new Range(1, 5);
        assertTrue(r.contains(3));
    }

    @Test
    public void testContains_ValueBelowRange_ShouldReturnFalse() {
        Range r = new Range(1, 5);
        assertFalse(r.contains(0));
    }

    @Test
    public void testContains_ValueAboveRange_ShouldReturnFalse() {
        Range r = new Range(1, 5);
        assertFalse(r.contains(6));
    }

    @Test
    public void testShift_PositiveDelta_ShouldMoveRange() {
        Range r = new Range(2, 6);
        Range shifted = Range.shift(r, 3);
        assertEquals(new Range(5, 9), shifted);
    }

    @Test
    public void testShift_NegativeDelta_ShouldMoveRangeLeft() {
        Range r = new Range(2, 6);
        Range shifted = Range.shift(r, -3);
        assertEquals(new Range(-1, 3), shifted);
    }
    
    @Test
    public void testShift_ZeroCrossingAllowed() {
        Range r = new Range(-3, 5);
        Range shifted = Range.shift(r, 4, true);
        assertEquals(new Range(1, 9), shifted);
    }

    @Test
    public void testShift_ZeroCrossingDisallowed_ShouldNotGoNegative() {
        Range r = new Range(-3, 5);
        Range shifted = Range.shift(r, 4, false);
        assertEquals(new Range(0, 9), shifted);
    }

    @Test
    public void testScale_PositiveFactor_ShouldStretchRange() {
        Range r = new Range(2, 6);
        Range scaled = Range.scale(r, 2);
        assertEquals(new Range(4, 12), scaled);
    }

    /*@Test
    public void testScale_ZeroFactor_ShouldThrowException() {
        Range r = new Range(2, 6);
        try {
            Range.scale(r, 0);
            fail("Expected exception when scaling by zero");
        } catch (IllegalArgumentException e) {
            // Expected behavior
        }
    }*/

    @Test
    public void testExpandToInclude_ValueWithinRange_ShouldRemainSame() {
        Range r = new Range(5, 15);
        Range expanded = Range.expandToInclude(r, 10);
        assertEquals(new Range(5, 15), expanded);
    }

    @Test
    public void testExpandToInclude_ValueAboveRange_ShouldExpandUpperBound() {
        Range r = new Range(5, 15);
        Range expanded = Range.expandToInclude(r, 20);
        assertEquals(new Range(5, 20), expanded);
    }

    @Test
    public void testExpandToInclude_ValueBelowRange_ShouldExpandLowerBound() {
        Range r = new Range(5, 15);
        Range expanded = Range.expandToInclude(r, 2);
        assertEquals(new Range(2, 15), expanded);
    }

    @Test
    public void testHashCode_TwoIdenticalRanges_ShouldHaveSameHashCode() {
        Range r1 = new Range(3, 7);
        Range r2 = new Range(3, 7);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testEquals_SameBounds_ShouldReturnTrue() {
        Range r1 = new Range(3, 7);
        Range r2 = new Range(3, 7);
        assertTrue(r1.equals(r2));
    }

    @Test
    public void testEquals_DifferentBounds_ShouldReturnFalse() {
        Range r1 = new Range(3, 7);
        Range r2 = new Range(4, 7);
        assertFalse(r1.equals(r2));
    }
    
    @Test
    public void testCombineBothNullReturnsNull1() {
        assertNull("Combining two null ranges should return null.", Range.combine(null, null));
    }

    @Test
    public void testCombineFirstNullReturnsSecondRange1() {
        Range range2 = new Range(2, 5);
        assertEquals("Combining null with a valid range should return the valid range.", range2, Range.combine(null, range2));
    }

    @Test
    public void testCombineSecondNullReturnsFirstRange1() {
        Range range1 = new Range(3, 8);
        assertEquals("Combining a valid range with null should return the valid range.", range1, Range.combine(range1, null));
    }
    

    @Test
    public void testExpand_ShouldExpandBothSides() {
        Range r = new Range(5, 15);
        Range expanded = Range.expand(r, 0.5, 0.5);
        assertEquals(new Range(0, 20), expanded);
    }
    
    @Test
    public void testExpand_ShouldExpandLowerOnly() {
        Range r = new Range(5, 15);
        Range expanded = Range.expand(r, 0.5, 0);
        assertEquals(new Range(0, 15), expanded);
    }
    
    @Test
    public void testExpand_ShouldExpandUpperOnly() {
        Range r = new Range(5, 15);
        Range expanded = Range.expand(r, 0, 0.5);
        assertEquals(new Range(5, 20), expanded);
    }
    
    @Test
    public void testIntersects_OverlappingRanges_ShouldReturnTrue() {
        Range r = new Range(2, 6);
        assertTrue(r.intersects(4, 8));
    }
    
    @Test
    public void testIntersects_NonOverlappingRanges_ShouldReturnFalse() {
        Range r = new Range(2, 6);
        assertFalse(r.intersects(7, 9));
    }
    
    @Test
    public void testIntersects_TouchingEdges_ShouldReturnTrue() {
        Range r = new Range(2, 6);
        assertTrue(r.intersects(6, 9));
    }
    
    @Test
    public void testCombine_TwoValidRanges_ShouldMerge() {
        Range r1 = new Range(2, 5);
        Range r2 = new Range(4, 8);
        Range result = Range.combine(r1, r2);
        assertEquals(new Range(2, 8), result);
    }
    
    @Test
    public void testCombine_FirstRangeNull_ShouldReturnSecond() {
        Range r2 = new Range(4, 8);
        assertEquals(r2, Range.combine(null, r2));
    }
    
    @Test
    public void testCombine_SecondRangeNull_ShouldReturnFirst() {
        Range r1 = new Range(4, 8);
        assertEquals(r1, Range.combine(r1, null));
    }
    
    @Test
    public void testCombine_BothRangesNull_ShouldReturnNull() {
        assertNull(Range.combine(null, null));
    }
    
    @Test
    public void testIntersects_OneInsideAnother_ShouldReturnTrue() {
        Range r = new Range(2, 10);
        assertTrue("A range inside another should return true", r.intersects(4, 8));
    }

    @Test
    public void testIntersects_BoundaryMatches_ShouldReturnTrue() {
        Range r = new Range(3, 7);
        assertTrue("A range matching the boundary should return true", r.intersects(3, 7));
    }
    
    @Test
    public void testShift_BelowZeroWithZeroCrossingAllowed() {
        Range r = new Range(-2, 5);
        Range shifted = Range.shift(r, -3, true);
        assertEquals(new Range(-5, 2), shifted);
    }

    @Test
    public void testShift_BelowZeroWithZeroCrossingDisallowed() {
        Range r = new Range(-2, 5);
        Range shifted = Range.shift(r, -3, false);
        assertEquals(new Range(0, 5), shifted);
    }

    @Test
    public void testExpandToInclude_BothSidesExpansion() {
        Range r = new Range(3, 8);
        Range expanded = Range.expandToInclude(r, 10);
        expanded = Range.expandToInclude(expanded, 2);
        assertEquals(new Range(2, 10), expanded);
    }

    @Test
    public void testExpandToInclude_NoChange() {
        Range r = new Range(3, 8);
        Range expanded = Range.expandToInclude(r, 5);
        assertEquals("Value inside range should not expand it", new Range(3, 8), expanded);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScale_NegativeFactor_ShouldThrowException() {
        Range r = new Range(2, 6);
        Range.scale(r, -1);
    }

    @Test
    public void testIntersects_InputRangeBefore_ShouldReturnFalse() {
        Range r = new Range(5, 10);
        assertFalse("Range completely before should return false", r.intersects(1, 4));
    }

    @Test
    public void testIntersects_InputRangeAfter_ShouldReturnFalse() {
        Range r = new Range(5, 10);
        assertFalse("Range completely after should return false", r.intersects(11, 15));
    }

    @Test
    public void testCombine_IdenticalRanges_ShouldReturnSameRange() {
        Range r1 = new Range(3, 8);
        Range r2 = new Range(3, 8);
        assertEquals("Combining identical ranges should return the same range", r1, Range.combine(r1, r2));
    }

    @Test
    public void testCombine_OneInsideAnother_ShouldReturnOuterRange() {
        Range outer = new Range(2, 10);
        Range inner = new Range(4, 8);
        assertEquals("Combining an inner range should return the outer range", outer, Range.combine(outer, inner));
    }

    @Test
    public void testShift_AlignsWithZero() {
        Range r = new Range(-5, 5);
        Range shifted = Range.shift(r, 5, false);
        assertEquals("Range should start at 0 after shifting", new Range(0, 10), shifted);
    }

    @Test
    public void testShift_LargePositiveDelta() {
        Range r = new Range(-5, 5);
        Range shifted = Range.shift(r, 20);
        assertEquals("Range should move completely into positive territory", new Range(15, 25), shifted);
    }

    @Test
    public void testScale_ByOne_ShouldRemainSame() {
        Range r = new Range(3, 7);
        Range scaled = Range.scale(r, 1.0);
        assertEquals("Scaling by 1 should keep the range unchanged", new Range(3, 7), scaled);
    }

    @Test
    public void testScale_ByLargeFactor_ShouldExpand() {
        Range r = new Range(2, 6);
        Range scaled = Range.scale(r, 10);
        assertEquals("Scaling by 10 should expand the range", new Range(20, 60), scaled);
    }

    @Test
    public void testIntersects_ExactBoundaryMatch_ShouldReturnTrue() {
        Range r = new Range(5, 10);
        assertTrue("Intersection at exact boundary should return true", r.intersects(10, 15));
    }

    @Test
    public void testIntersects_TouchingButNoOverlap_ShouldReturnFalse() {
        Range r = new Range(5, 10);
        assertFalse("Ranges touching at boundary but not overlapping should return false", r.intersects(10, 10));
    }

    @Test
    public void testShift_ZeroDelta_ShouldRemainSame() {
        Range r = new Range(3, 8);
        Range shifted = Range.shift(r, 0);
        assertEquals("Shifting by zero should keep the same range", new Range(3, 8), shifted);
    }

    @Test
    public void testShift_PositiveShiftWithZeroCrossingDisabled() {
        Range r = new Range(-4, 4);
        Range shifted = Range.shift(r, 6, false);
        assertEquals("Shifting by 6 should stop at zero for lower bound", new Range(0, 10), shifted);
    }

    @Test
    public void testExpand_OnlyLower_ShouldExpandDownward() {
        Range r = new Range(4, 12);
        Range expanded = Range.expand(r, 0.5, 0.0);
        assertEquals("Expanding only lower should extend downward", new Range(-2, 12), expanded);
    }

    @Test
    public void testExpand_OnlyUpper_ShouldExpandUpward() {
        Range r = new Range(4, 12);
        Range expanded = Range.expand(r, 0.0, 0.5);
        assertEquals("Expanding only upper should extend upward", new Range(4, 18), expanded);
    }

    @Test
    public void testCombine_OneRangeNull_ShouldReturnOtherRange() {
        Range r = new Range(2, 10);
        assertEquals("Combining with null should return the non-null range", r, Range.combine(r, null));
    }

    

}
