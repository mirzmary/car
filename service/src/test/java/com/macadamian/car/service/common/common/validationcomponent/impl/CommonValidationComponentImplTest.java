package com.macadamian.car.service.common.common.validationcomponent.impl;

import com.macadamian.car.service.common.AbstractServiceImplTest;
import com.macadamian.car.service.common.validationcomponent.CommonValidationComponent;
import com.macadamian.car.service.common.validationcomponent.impl.CommonValidationComponentImpl;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CommonValidationComponentImplTest extends AbstractServiceImplTest {

    @TestSubject
    private CommonValidationComponent commonValidationComponent = new CommonValidationComponentImpl();

    // region validateId

    @Test
    public void testValidateIdWhenNull() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateId(null);
        // Verify
        Assert.assertFalse(valid);
        verifyAll();
    }

    @Test
    public void testValidateIdWhenNegative() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateId(-1L);
        // Verify
        Assert.assertFalse(valid);
        verifyAll();
    }

    @Test
    public void testValidateIdWhenZero() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateId(0L);
        // Verify
        Assert.assertFalse(valid);
        verifyAll();
    }

    @Test
    public void testValidateIdWhenValid() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateId(1L);
        // Verify
        Assert.assertTrue(valid);
        verifyAll();
    }

    // endregion

    // region validateString

    @Test
    public void testValidateStringWhenNull() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateString(null);
        // Verify
        Assert.assertFalse(valid);
        verifyAll();
    }

    @Test
    public void testValidateStringWhenEmpty() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateString("");
        // Verify
        Assert.assertFalse(valid);
        verifyAll();
    }

    @Test
    public void testValidateStringWhenValid() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateString("asd");
        // Verify
        Assert.assertTrue(valid);
        verifyAll();
    }

    // endregion

    // region validateAmount

    @Test
    public void testValidateAmountWhenNull() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateAmount(null);
        // Verify
        Assert.assertFalse(valid);
        verifyAll();
    }

    @Test
    public void testValidateAmountWhenNegative() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateAmount(BigDecimal.valueOf((-300)));
        // Verify
        Assert.assertFalse(valid);
        verifyAll();
    }

    @Test
    public void testValidateAmountWhenZero() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateAmount(BigDecimal.ZERO);
        // Verify
        Assert.assertFalse(valid);
        verifyAll();
    }

    @Test
    public void testValidateAmountWhenValid() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        final boolean valid = commonValidationComponent.validateAmount(BigDecimal.valueOf(8000L));
        // Verify
        Assert.assertTrue(valid);
        verifyAll();
    }

    // endregion
}
