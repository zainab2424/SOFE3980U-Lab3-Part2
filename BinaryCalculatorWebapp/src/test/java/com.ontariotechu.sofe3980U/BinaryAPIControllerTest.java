package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
	@Test
    public void addWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "").param("operand2", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void addWithMissingOperand2() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "111"))
                .andExpect(status().isOk())
                .andExpect(content().string("Error"));
    }

    @Test
    public void addWithValidOperands() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "101").param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(content().string("1011"));
    }

    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "10").param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("100")); // Expected result of multiplication
    }

    @Test
    public void multiplyWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "").param("operand2", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("0")); // Expected result of multiplication with empty operands
    }

    @Test
    public void AND() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "101").param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(content().string("101")); // Expected result of AND operation
    }

    @Test
    public void ANDWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "").param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(content().string("0")); // Expected result of AND operation with empty operand
    }

    @Test
    public void OR() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "101").param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(content().string("111")); // Expected result of OR operation
    }

    @Test
    public void ORWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "").param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(content().string("110")); // Expected result of OR operation with empty operand
    }
}
