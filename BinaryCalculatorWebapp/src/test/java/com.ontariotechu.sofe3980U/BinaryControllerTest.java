package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }
public void postWithMissingOperand2() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111").param("operator", "+"))
                .andExpect(status().isOk())
                .andExpect(view().name("Error"));
    }

    @Test
    public void postWithValidOperandsAndOperator() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "+").param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1100"))
                .andExpect(model().attribute("operand1", "101"));
    }

    @Test
    public void postWithMultiplication() throws Exception {
        // Test case for multiplication operation with valid operands and operator
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "*").param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1010"))
                .andExpect(model().attribute("operand1", "101"));
    }

    @Test
    public void postMultiplicationUnequalLength() throws Exception {
        // Test case for multiplication operation with different length of operands
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "*").param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1010"))
                .andExpect(model().attribute("operand1", "101"));
    }

    @Test
    public void postWithAND() throws Exception {
        // Test case for AND operation with valid operands and operator
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "&").param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "100"))
                .andExpect(model().attribute("operand1", "101"));
    }

    @Test
    public void postWithANDUnequalLength() throws Exception {
        // Test case for AND operation with operands of unequal lengths
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "&").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "100")) // Expecting the result of the AND operation
                .andExpect(model().attribute("operand1", "101"));
    }

    @Test
    public void postWithOR() throws Exception {
        // Test case for OR operation with valid operands and operator
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "|").param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "111"))
                .andExpect(model().attribute("operand1", "101"));
    }

    @Test
    public void postWithORUnequalLength() throws Exception {
        // Test case for OR operation with operands of unequal lengths
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "|").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1101"))
                .andExpect(model().attribute("operand1", "101"));
    }
}
