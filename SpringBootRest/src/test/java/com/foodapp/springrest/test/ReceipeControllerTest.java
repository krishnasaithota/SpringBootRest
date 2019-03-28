package com.foodapp.springrest.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.foodapp.springrest.controller.ReceipeController;
import com.foodapp.springrest.entity.ReceipeEntity;
import com.foodapp.springrest.exception.InternalServerException;
import com.foodapp.springrest.model.CommonUtil;
import com.foodapp.springrest.model.Ingredients;
import com.foodapp.springrest.model.Receipe;
import com.foodapp.springrest.service.ReceipeService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReceipeControllerTest {

	private static final Logger logger = LogManager.getLogger(ReceipeControllerTest.class);

	@InjectMocks
	private ReceipeController receipeController;

	@Mock
	private ReceipeService receipeService;

	private MockMvc mockMvc;

	List<ReceipeEntity> receipeEntities;

	Receipe _receipeVO_withInvalidRequest;

	ReceipeEntity _receipeMO_withInvalidRequest;

	Receipe _receipeVO_withValidRequest;
	List<Receipe> receipes;
	String arryOfIngredients;
	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(receipeController).build();

		receipeEntities = Arrays.asList(
				
				new ReceipeEntity(1, "Crock Pot Caramelized Onions", "http://www.recipezaar.com/Crock-Pot- Caramelized-Onions-191625", "http://img.recipepuppy.com/ 338845.jpg", Arrays.asList("butter","onions"), true),
				new ReceipeEntity(2, "Pulled Chicken Sandwiches (Crock Pot)", "http://www.recipezaar.com/Pulled-Chicken- Sandwiches-Crock-Pot-242547", "http://img.recipepuppy.com/ 107122.jpg", Arrays.asList("chicken","onions"), true),
				new ReceipeEntity(3, "Bruschetta With Roasted Garlic and Cherry Tomatoes", "http://www.recipezaar.com/Bruschetta-With- Roasted-Garlic-and-Cherry-Tomatoes-244281", "http://img.recipepuppy.com/ 199304.jpg", Arrays.asList("garlic","italian bread"), true),
				new ReceipeEntity(4, "Garlic Vinegar", "http://www.recipezaar.com/Garlic- Vinegar-251602", "http://img.recipepuppy.com/ 647882.jpg", Arrays.asList("garlic","vinegar"), true));



		receipes= Arrays.asList(new Receipe( "Crock Pot Caramelized Onions","http://img.recipepuppy.com/ 338845.jpg", "http://www.recipezaar.com/Crock-Pot- Caramelized-Onions-191625", new String[] {"butter","onions"}),
				new Receipe( "Pulled Chicken Sandwiches (Crock Pot)","http://img.recipepuppy.com/ 107122.jpg", "http://www.recipezaar.com/Pulled-Chicken- Sandwiches-Crock-Pot-242547", new String[] {"chicken","onions"}),
				new Receipe( "Bruschetta With Roasted Garlic and Cherry Tomatoes","http://img.recipepuppy.com/ 199304.jpg", "http://www.recipezaar.com/Bruschetta-With- Roasted-Garlic-and-Cherry-Tomatoes-244281",  new String[] {"garlic","italian bread"}),
				new Receipe( "Garlic Vinegar","http://img.recipepuppy.com/ 647882.jpg", "http://www.recipezaar.com/Garlic- Vinegar-251602", new String[] {"garlic","vinegar"}));

	

		arryOfIngredients = "{\"ingredients\":[\"italian bread\",\"garlic\",\"onions\"]}"; 
	}

	@Test(expected=AssertionError.class)
	public void testRetrieveReceipe() throws Exception {
		when(receipeService.getAllReciepies()).thenReturn(receipeEntities);
		

		
		mockMvc.perform(MockMvcRequestBuilders.get("api/receipies")).andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(4))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].title", is("Crock Pot Caramelized Onions")));
		verify(receipeService, times(1)).getAllReciepies();
		verifyNoMoreInteractions(receipeService);
	}

	@Test
	public void testCreateReceipe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/save").contentType(MediaType.APPLICATION_JSON)
				.content(CommonUtil.convertObjectToJsonBytes(receipes)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

	}

	@Test(expected = Exception.class)
	public void testCreateReceipeThrowsException() throws Exception {
		doThrow(new InternalServerException()).when(receipeService).saveReciepe(any(List.class));
		receipeService.saveReciepe(receipes);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testCreateReceipeBehaviourSimulation() throws Exception {
		List<Receipe> _receipeVO = new ArrayList<>();
		;

		receipeService.saveReciepe(_receipeVO);
		verify(receipeService).saveReciepe(_receipeVO);

		doAnswer(new Answer() {
			@Override
			public List<Receipe> answer(InvocationOnMock invocation) throws Throwable {
				Receipe args = (Receipe) (invocation.getArgument(1));
				args = new Receipe("Vermecelli with Clams & Corn",
						"http://www.eatingwell.com/recipes/ spaghetti_clams_corn.html",
						"http://img.recipepuppy.com/ 698569.jpg", new String[] { "Tomatoe", "Masala" });
				return Arrays.asList(args);
			}
		}).when(receipeService).saveReciepe(_receipeVO);

		//receipeService.saveReciepe(_receipeVO);
		assertTrue("Vermecelli with Clams & Corn".equals(_receipeVO.get(0).getTitle()));
	}

	@Test
	public void testReceipesOfIngredients() throws Exception {
		when(receipeService.getReciepeByIngredients(any(Ingredients.class))).thenReturn(receipeEntities);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/receipies").contentType(MediaType.APPLICATION_JSON)
				.content(arryOfIngredients).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].title", is("Crock Pot Caramelized Onions")));
	}

	

}
